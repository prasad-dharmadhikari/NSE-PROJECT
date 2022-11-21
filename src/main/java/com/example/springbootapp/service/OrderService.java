package com.example.springbootapp.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springbootapp.config.ModelMapperConfig;
import com.example.springbootapp.dto.OrderDTO;
import com.example.springbootapp.model.Error;
import com.example.springbootapp.model.ErrorResponse;
import com.example.springbootapp.model.JobStatus;
import com.example.springbootapp.model.LoginRequest;
import com.example.springbootapp.model.Order;
import com.example.springbootapp.model.OrderResponse;
import com.example.springbootapp.utility.HttpHeaderGenerator;
import com.example.springbootapp.utility.RandomRunIdGenerator;
import com.example.springbootapp.utility.ResponseJsonBodyGenerator;
import com.example.springbootapp.utility.RunIdPersister;
import com.example.springbootapp.utility.URLBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class OrderService {

	Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private RestTemplate restTemplate;

	private JobStatus jobStatus = new JobStatus();

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private RunIdPersister runIdPersister = new RunIdPersister();

	private ModelMapperConfig mapperConfig = new ModelMapperConfig();

	private ModelMapper modelMapper = mapperConfig.getModelMapper();

	private URLBuilder urlBuilder = new URLBuilder();

	private ResponseJsonBodyGenerator jsonBodyGenerator = new ResponseJsonBodyGenerator();

	ResponseEntity<Object> authenticationResponse = null;

	public OrderService() {
		super();
	}

	@SuppressWarnings({ "deprecation", "null", "static-access" })
	public ResponseEntity<Object> authenticateUser(LoginRequest loginRequest) {
		// Convert loginRequest to JSON
		// this.urlBuilder = urlBuilder2;
		logger.trace("authenticateUser method accessed...");
		ResponseEntity<Object> authenticationResponse = null;
		try {
			logger.trace("Inside try block...");
			String loginDetails = gson.toJson(loginRequest);
			// System.out.println(loginDetails);
			// create headers specifying that it is JSON request
			HttpHeaders authenticationHeaders = HttpHeaderGenerator.getHeaders();
			// create entity using headers and request body
			HttpEntity<String> authenticationEntity = new HttpEntity<String>(loginDetails, authenticationHeaders);
			logger.info("authenticationEntity : " + authenticationEntity.toString());
			// call restTemplate method to get response from server
			String LOGIN_URL = urlBuilder.buildLoginURL();// "https://ctrlmapdv1:8446/automation-api/session/login";//
															// URLBuilder.buildLoginURL();
			logger.info("Login URL: " + LOGIN_URL);
			authenticationResponse = restTemplate.exchange(LOGIN_URL, HttpMethod.POST, authenticationEntity,
					Object.class);
			logger.info("Authentication Response Body : " + authenticationResponse.getBody());
			logger.info("Authentication Response Body code : " + authenticationResponse.getStatusCodeValue());
			if (authenticationResponse.getStatusCodeValue() == 200) {
				logger.trace("Inside if block..");
				// logger.info();
				// String successResponse = gson.toJson(authenticationSuccessResponse);
				logger.info("Authentication Success Response : " + gson.toJson(authenticationResponse.getBody()));
				return ResponseEntity.status(HttpStatus.OK).build().of(Optional.of(authenticationResponse.getBody()));
			} else {
				logger.trace("Inside else block..");
				ErrorResponse authenticationErrorResponse = gson
						.fromJson(jsonBodyGenerator.getJsonBody(authenticationResponse.getBody()), ErrorResponse.class);
				logger.info("Authentication Error Response : " + gson.toJson(authenticationErrorResponse));

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
						.of(Optional.of(authenticationErrorResponse));
			}
		} catch (Exception e) {
			logger.trace("Inside catch block...");
			logger.error("Exception caught : " + e.getMessage());
			JsonElement je = JsonParser.parseString(jsonBodyGenerator.getJsonBody(e.getMessage()));
			String json = gson.toJson(je);
			return ResponseEntity.status(jsonBodyGenerator.getStatusCode(e.getMessage())).body(json);
		} finally {

		}

	}

	@SuppressWarnings({ "null", "deprecation" })
	public ResponseEntity<Object> sendNumericRunId(OrderDTO orderDTO, String authHeader) {
		// Convert orderRequest to JSON
		logger.trace("sendNumericRunId method accessed..");
		ResponseEntity<Object> runOrderResponse = null;
		try {
			// System.out.println(orderDTO.toString());
			logger.trace("Inside try block...");
			Order order = modelMapper.map(orderDTO, Order.class);
			logger.info("New Order deatils are : " + gson.toJson(order));
			ArrayList<HashMap<String, String>> variables = order.getVariables();
			HashMap<String, String> runIdMap = new HashMap<>();
			String runId = RandomRunIdGenerator.generateRandomRunId();
			runIdMap.put("AZ_RUN_ID", runId);
			variables.add(runIdMap);
			logger.info("Variable details after adding Run ID: " + gson.toJson(variables));
			order.setVariables(variables);
			String orderDetails = gson.toJson(order);
			logger.info("Final Order Details in json are : " + orderDetails);
			// create headers specifying that it is JSON request and also include
			// authorization token
			HttpHeaders orderDetailsHeaders = HttpHeaderGenerator.getHeaders();
			orderDetailsHeaders.set("Authorization", authHeader);
			logger.info("Headers : " + orderDetailsHeaders.toString());
			// create entity using headers and request body
			HttpEntity<String> orderDetailsEntity = new HttpEntity<String>(orderDetails, orderDetailsHeaders);
			// call restTemplate method to get response from server
			logger.info("Order Details Entity : " + orderDetailsEntity.toString());
			String RUN_ORDER_URL = urlBuilder.buildRunOrderURL();// "https://ctrlmapdv1:8446/automation-api/run/order";//
			// URLBuilder.buildRunOrderURL();
			logger.info("RUN_ORDER_URL : " + RUN_ORDER_URL);
			runOrderResponse = restTemplate.exchange(RUN_ORDER_URL, HttpMethod.POST, orderDetailsEntity, Object.class);
			logger.info("Run Order Response Body: " + runOrderResponse.getBody());
			logger.info("Run Order Response Code value: " + runOrderResponse.getStatusCodeValue());
			if (runOrderResponse.getStatusCodeValue() == 200) {
				logger.trace("Inside if block...");
				OrderResponse orderResponse = gson.fromJson(gson.toJson(runOrderResponse.getBody()),
						OrderResponse.class);
				logger.info("Order API Response : " + runOrderResponse.getBody());
				jobStatus.setAlphanumericRunId(orderResponse.getRunId());
				jobStatus.setStatusURI(orderResponse.getStatusURI());
				jobStatus.setNumericRunId(Integer.parseInt(runId));
				runIdPersister.saveRunIdAndAlphanumericRunId(jobStatus.getNumericRunId().toString(),
						jobStatus.getAlphanumericRunId());
				logger.info("Final Job status Response after appending run ID : " + gson.toJson(jobStatus));
				logger.info("Final response Entity : " + ResponseEntity.ok(jobStatus).toString());
				return ResponseEntity.ok(jobStatus);
			} else {
				logger.trace("Inside Else block..");
				return runOrderResponse;
			}

		} catch (Exception e) {
			logger.trace("Inside catch block...");
			logger.error("Exception caught.. : " + e);
//			return ResponseEntity.ok(e.getMessage());
			if (jsonBodyGenerator.getStatusCode(e.getMessage()) == 401) {
				// User not found. Session token is invalid or expired.
				ErrorResponse errorResponse = new ErrorResponse();
				Error error = new Error();
				error.setMessage("User not found. Session token is invalid or expired.");
				ArrayList<Error> errorList = new ArrayList<>();
				errorList.add(error);
				errorResponse.setErrors(errorList);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(gson.toJson(errorResponse));
			}
			return ResponseEntity.status(jsonBodyGenerator.getStatusCode(e.getMessage()))
					.body(jsonBodyGenerator.getJsonBody(e.getMessage()));
		} finally {

		}

	}

	@SuppressWarnings("static-access")
	public Object sendJobStatusByNumericRunId(String numericRunId, String authHeader)
			throws FileNotFoundException, IOException {
		logger.trace("sendJobStatusByRunId method accessed...");
		try {
			// JobStatusResponse runIdResponse = null;
			String alphanumericRunId = runIdPersister.getAlphanumericRunId(numericRunId);
			HttpHeaders jobStatusHeaders = HttpHeaderGenerator.getHeaders();
			jobStatusHeaders.set("Authorization", authHeader);
			logger.info("Required Header : " + jobStatusHeaders.toString());
			// create entity using headers and request body
			HttpEntity<String> jobStatusEntity = new HttpEntity<String>(jobStatusHeaders);
			logger.info("Required Http Entity : " + jobStatusEntity.toString());

			String JOB_STATUS_URL = urlBuilder.buildJobStatusURL() + alphanumericRunId;// ;"https://ctrlmapdv1:8446/automation-api/run/status/";//
			logger.info("JOB_STATUS_URL :" + JOB_STATUS_URL);
			ResponseEntity<Object> runIdResponse = restTemplate.exchange(JOB_STATUS_URL, HttpMethod.GET,
					jobStatusEntity, Object.class);
			logger.info("run ID response : " + runIdResponse.getBody());
			// JobStatusResponse jobStatusResponse =
			// gson.fromJson(gson.toJson(runIdResponse.getBody().toString()),
			// JobStatusResponse.class);
			logger.info("Job Status Response : \n" + gson.toJson(runIdResponse.getBody()));
			return gson.toJson(runIdResponse.getBody());
		} catch (Exception e) {
			logger.trace("Inside catch block...");
			logger.error("Exception caught.. : " + e.getMessage());
			if (jsonBodyGenerator.getStatusCode(e.getMessage()) == 401) {
				// User not found. Session token is invalid or expired.
				ErrorResponse errorResponse = new ErrorResponse();
				Error error = new Error();
				error.setMessage("User not found. Session token is invalid or expired.");
				ArrayList<Error> errorList = new ArrayList<>();
				errorList.add(error);
				errorResponse.setErrors(errorList);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(gson.toJson(errorResponse));
			}
			return ResponseEntity.status(jsonBodyGenerator.getStatusCode(e.getMessage()))
					.body(jsonBodyGenerator.getJsonBody(e.getMessage()));
		}
	}

//	public ResponseEntity<Object> sendJobStatusByNumericRunId(String numericRunId, String authHeader)
//			throws IOException {
//		logger.trace("sendJobStatusByNumericRunId method accessed...");
//		String alphanumericRunId = runIdPersister.getAlphanumericRunId(numericRunId);
//		return sendJobStatusByRunId(alphanumericRunId, authHeader);
//
//	}
}

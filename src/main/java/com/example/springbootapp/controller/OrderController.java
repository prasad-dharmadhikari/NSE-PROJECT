package com.example.springbootapp.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootapp.dto.OrderDTO;
import com.example.springbootapp.model.LoginRequest;
import com.example.springbootapp.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class OrderController {

	@Autowired
	public OrderService orderService;

	@Autowired
	ApplicationArguments applicationArguments;

	Logger logger = LoggerFactory.getLogger(OrderController.class);

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	public OrderController() {
		super();
	}

	@PostMapping("/login")
	public ResponseEntity<Object> authenticate(@RequestBody LoginRequest loginRequest) {
		logger.trace("Authenticate Method accessed..");
		logger.info("Login details : " + gson.toJson(loginRequest));
		return orderService.authenticateUser(loginRequest);
	}

	@PostMapping("/run/order")
	public ResponseEntity<Object> sendNumericRunId(@RequestBody OrderDTO order,
			@RequestHeader("Authorization") String authHeader) {
		logger.trace("sendNumericRunId method accessed..");
		logger.info("Order Details :" + gson.toJson(order));
		return ResponseEntity.ok(orderService.sendNumericRunId(order, authHeader).getBody());
	}


	@GetMapping("/run/order/status/{numericRunId}")
	public Object sendJobStatusByNumericRunId(@PathVariable String numericRunId,
			@RequestHeader("Authorization") String authHeader) throws IOException {
		logger.trace("sendJobStatusByNumericRunId method accessed..");
		logger.info("Numeric Run Id from request :" + numericRunId);  
		return orderService.sendJobStatusByNumericRunId(numericRunId, authHeader);
	}

}

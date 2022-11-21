package com.example.springbootapp.utility;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ResponseJsonBodyGenerator {
	Logger logger = LoggerFactory.getLogger(ResponseJsonBodyGenerator.class);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public String getJsonBody(Object responseBody) {
		logger.trace("Inside getJsonBody method...");

		String response = gson.toJson(responseBody);
		System.out.println("Response is :" + response);
		int begin = response.indexOf("{");
		int end = response.lastIndexOf("}") + 1;
		String jsonResponse = response.substring(begin, end);
		return getPreetyJson(jsonResponse);
	}

	public String getJsonBody(String message) {
		logger.trace("Inside getJsonBody method...");
		int begin = message.indexOf("{");
		int end = message.lastIndexOf("}") + 1;
		String jsonResponse = message.substring(begin, end);
		return getPreetyJson(jsonResponse);
	}

	public int getStatusCode(String message) {
		logger.trace("Inside getStatusCode method...");
		char[] messageArray = message.toCharArray();
		StringBuffer sb = new StringBuffer();
		// System.out.println("message substring : " + sb.toString());
		int statusCode = Integer
				.parseInt(sb.append(messageArray[0]).append(messageArray[1]).append(messageArray[2]).toString());
		logger.info("Status code is: " + statusCode);
		return statusCode;

	}

	public String getPreetyJson(String message) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		StringBuffer finalMessage = new StringBuffer();
		StringTokenizer stringTokenizer = new StringTokenizer(message, "<EOL>");
		while (stringTokenizer.hasMoreTokens()) {
			finalMessage.append(stringTokenizer.nextToken());
		}
		JsonElement je = JsonParser.parseString(finalMessage.toString());
		return gson.toJson(je);
	}

}

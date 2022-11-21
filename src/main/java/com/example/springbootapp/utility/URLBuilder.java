package com.example.springbootapp.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.springbootapp.config.ControlMConfig;

public class URLBuilder {

	Logger logger = LoggerFactory.getLogger(URLBuilder.class);

	FileInputStream fileInputStream = null;
	FileOutputStream fileOutputStream = null;
	FileUtility fileUtility = new FileUtility();
	ControlMConfig config = null;
	public String serverName;
	public String port;

	public URLBuilder() {
		try {
			config = getControlMProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("Exception caught in URLBuilder constructor:" + e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "URLBuilder [serverName=" + serverName + ", port=" + port + "]";
	}

	public ControlMConfig getControlMProperties() throws FileNotFoundException, IOException {
		logger.trace("Inside getControlMProperties method..");
		ControlMConfig controlMConfig = new ControlMConfig();
		fileInputStream = new FileInputStream(fileUtility.createFile("controlm.txt"));
		Properties properties = new Properties();
		properties.load(fileInputStream);
		controlMConfig.setSessionLoginApi(properties.getProperty("session.login.api"));
		controlMConfig.setOrderApi(properties.getProperty("order.api"));
		controlMConfig.setJobStatusApi(properties.getProperty("job.status.api"));
		controlMConfig.setServerName(properties.getProperty("server.name"));
		controlMConfig.setPort(Integer.parseInt(properties.getProperty("port")));
		fileInputStream.close();
		return controlMConfig;
	}

	public String buildLoginURL() throws FileNotFoundException, IOException {
		StringBuffer sb = new StringBuffer();
		config = getControlMProperties();
		logger.trace("Inside buildLoginURL method");
		logger.info("ControlM config details: " + config.toString());
		sb.append("https://");
		sb.append(config.getServerName()).append(":").append(config.getPort()).append(config.getSessionLoginApi());
		return sb.toString();
	}

	public String buildRunOrderURL() throws FileNotFoundException, IOException {
		StringBuffer sb = new StringBuffer();
		logger.trace("Inside buildRunOrderURL method");
		logger.info("ControlM config details: " + config.toString());
		sb.append("https://");
		sb.append(config.getServerName()).append(":").append(config.getPort()).append(config.getOrderApi());
		return sb.toString();
	}

	public String buildJobStatusURL() throws FileNotFoundException, IOException {
		StringBuffer sb = new StringBuffer();
		logger.trace("Inside buildJobStatusURL method");
		logger.info("ControlM config details: " + config.toString());
		sb.append("https://");
		sb.append(config.getServerName()).append(":").append(config.getPort()).append(config.getJobStatusApi());
		return sb.toString();
	}

//	public static void main(String[] args) throws FileNotFoundException, IOException {
//		URLBuilder urlBuilder = new URLBuilder();
//		System.out.println(urlBuilder.buildLoginURL());
//		System.out.println(urlBuilder.buildRunOrderURL());
//		System.out.println(urlBuilder.buildJobStatusURL());
//	}
}

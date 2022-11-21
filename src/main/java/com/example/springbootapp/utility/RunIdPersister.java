package com.example.springbootapp.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunIdPersister {

	FileInputStream fileInputStream = null;
	FileOutputStream fileOutputStream = null;
	FileUtility fileUtility = new FileUtility();

	Logger logger = LoggerFactory.getLogger(RunIdPersister.class);

	public void saveRunIdAndAlphanumericRunId(String numericRunId, String alphaNumericRunId) throws IOException {
		logger.trace("Inside saveRunIdAndAlphanumericRunId method.....");
		// getFile();
		File file = fileUtility.createFile("Store.txt");
		fileInputStream = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		fileInputStream.close();
		fileOutputStream = new FileOutputStream(file);
		properties.setProperty(numericRunId, alphaNumericRunId);
		properties.store(fileOutputStream, null);
		fileOutputStream.close();
	}

	public String getAlphanumericRunId(String numericRunId) throws IOException {
		logger.trace("Inside getAlphanumericRunId method.....");
		fileInputStream = new FileInputStream(fileUtility.createFile("Store.txt"));
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String alphanumericRunId = properties.getProperty(numericRunId);
		fileInputStream.close();
		return alphanumericRunId;
	}

	public String getNumericRunIdUsingAlphaNumericRunId(String alphaNumericRunId) throws FileNotFoundException, IOException {
		logger.trace("Inside getAlphanumericRunId method.....");
		fileInputStream = new FileInputStream(fileUtility.createFile("Store.txt"));
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String numericRunId = null;
		Set<Entry<Object,Object>> propertySet = properties.entrySet();
		for (Entry<Object, Object> entry : propertySet) {
			if (entry.getValue().equals(alphaNumericRunId)) {
				numericRunId = (String)entry.getKey();
			}
		}
		fileInputStream.close();
		return numericRunId;
	}

//    public static void main(String[] args) throws IOException {
//        RunIdPersister runIdPersister = new RunIdPersister();
////        runIdPersister.saveRunIdAndAlphanumericRunId(RandomRunIdGenerator.generateRandomRunId(), "599845");
////        runIdPersister.saveRunIdAndAlphanumericRunId(RandomRunIdGenerator.generateRandomRunId(), "88637584");
//        System.out.println(runIdPersister.getAlphanumericRunId("1630521600"));
//        //System.out.println(runIdPersister.getAlphanumericRunId("123654567"));
//    }

}

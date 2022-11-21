package com.example.springbootapp.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtility {

	Logger logger = LoggerFactory.getLogger(FileUtility.class);

	File createFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		File file;
		logger.trace("Inside createFile method...");
		if (fileName.equals("Store.txt")) {
			file = new File("src\\main\\resources\\Store.txt");
		} else {
			file = new File(Path.of("").toAbsolutePath().toString() + "\\" + fileName);

		}
		boolean result = file.createNewFile();
		if (result) {
			logger.info("File Created.... : " + fileName);
			logger.info("File created at path :" + file.getAbsolutePath());
			return file;
		} else {
			logger.info("File already exists : " + fileName);
			return file;
		}

	}
//    public static void main(String[] args) throws IOException {
//		var fileUtility = new FileUtility();
//    	//fileUtility.createFile("abc.txt");
//		Path path = FileSystems.getDefault().getPath(".");
//		String cwd = Path.of("").toAbsolutePath().toString();
//		System.out.println(path);
//		System.out.println(cwd);
//	}

}

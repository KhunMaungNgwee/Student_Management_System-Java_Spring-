/**
 * 
 */
package com.internship.sms.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 */
public class Util {

	public static String uploadFile(MultipartFile file, String realPath, String relativePath) {
		String fileName = file.getOriginalFilename();
		fileName = fileName.replaceAll(" ", "_");
		File destinationPath = new File(realPath + fileName);
		try {
			FileUtils.writeByteArrayToFile(destinationPath, file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relativePath + fileName;
	}

}

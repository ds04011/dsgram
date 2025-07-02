package com.ds04011.dsgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	
	public static final String FILE_UPLOAD_PATH = "D:\\yhw\\SpringProjectdsgram\\spring-tools-for-eclipse-4.31.0.RELEASE-e4.36.0-win32.win32.x86_64\\upload\\dsgram";
	// 서버pc 의 저장경로 말함. 로컬호스트면 달라질 수 있겠지.
	
	public static String saveFile(long userId, MultipartFile file) {
		
		if(file==null) {
			return null;
		}
		
		String directoryName =  "/" + userId + "_" + System.currentTimeMillis(); 
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			//생성 실패
			return null;
		}
		
		String filePath = directoryPath + "/" + file.getOriginalFilename(); 
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
			
		} catch (IOException e) {
			//파일 저장 실패
			e.printStackTrace();
			return null;
		}
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	
	
	
}

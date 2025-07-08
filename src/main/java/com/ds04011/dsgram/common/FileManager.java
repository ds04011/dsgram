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
	
	public static boolean removeFile(String filePath) { // /images/2_98734957/test.png
		
		if(filePath==null) {
			return false;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH  +  filePath.replace("/images", "");   
		// 삭제할 대상 경로 완성
		// 문자열 경로를 path 라는 객체로 생성.
		Path path =  Paths.get(fullFilePath);
		// 디렉토리도 생성했으니까, 폴더도 삭제해줘야함
		Path directoryPath = path.getParent();
		try {
			Files.delete(path);	
			Files.delete(directoryPath);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
		
		
	}
	
	
	
}

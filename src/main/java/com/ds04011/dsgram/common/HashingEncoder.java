package com.ds04011.dsgram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingEncoder {

	// 문자열 해싱하는 기능
	public static String encode(String message) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("sha256");
									// md5 대신 sha256 으로 하면 32 자리에서 64자리 까지 인코딩 가능 
			byte[] bytes = message.getBytes();  
			messageDigest.update(bytes);
			byte[] digest = messageDigest.digest();
			
			String result = "";
			for(int i =0; i < digest.length; i++) {
				// byte 연산 으로 16진수 변환.
				result +=  Integer.toHexString( digest[i] & 0xff);
				
				
			}
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;  // md5 라는 알고리즘이 없을때, 변환안된거니까, null 리턴으로 문제있음을 알림
		}
	}
	
	
	
	
	
}

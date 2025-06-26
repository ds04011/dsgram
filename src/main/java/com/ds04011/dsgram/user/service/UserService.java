package com.ds04011.dsgram.user.service;

import org.springframework.stereotype.Service;

import com.ds04011.dsgram.common.MD5HashingEncoder;
import com.ds04011.dsgram.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;  
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public boolean addUser(String loginId, String password, String email, String nickname) {
		
		String encodedPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encodedPassword, email, nickname);
		if(count ==1) {
			return true;
		} else {
			return false;
		}	
	}
	
	public boolean emailCheck(String email) {
		
		int count = userRepository.countByEmail(email);
		if(count ==0) {
			return false;
		}
		else return true;
	}
	
	public boolean nicknameCheck(String nickname) {
		
		int count = userRepository.countByNickname(nickname);
		if(count ==0) {
			return false;
		}
		else return true;
	}

}

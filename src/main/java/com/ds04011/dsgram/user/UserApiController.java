package com.ds04011.dsgram.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.dsgram.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserApiController {
	
	
	private UserService userService;
	UserApiController(UserService userService){
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public Map<String, String> register(@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("email") String email
			, @RequestParam("nickname") String nickname){
		
		
		boolean result = userService.addUser(loginId, password, email, nickname);
		Map<String, String> resultMap = new HashMap<>();
		if(result) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		
		return resultMap;
	}
	
	@GetMapping("/check/email")
	public Map<String, String> emailDupCheck(@RequestParam("email") String email){
		
		boolean result = userService.emailCheck(email);
		Map<String, String> resultMap = new HashMap<>();
		if(result) {
			resultMap.put("duplicate", "yes");
		} else {
			resultMap.put("duplicate", "no");
		}
		return resultMap;
	}
	
	@GetMapping("/check/nickname")
	public Map<String, String> nicknameDupCheck(@RequestParam("nickname") String nickname){
		
		boolean result = userService.nicknameCheck(nickname);
		Map<String, String> resultMap = new HashMap<>();
		if(result) {
			resultMap.put("duplicate", "yes");
		} else {
			resultMap.put("duplicate", "no");
		}
		return resultMap;
		
	}
	
//	@PostMapping("/login")
//	public Map<String, String> login(@RequestParam("loginId") String loginId
//			, @RequestParam("password") String password){
//		
//		boolean result = userService.login(loginId, password);
//		
//		Map<String, String> resultMap = new HashMap<>();
//		if(result) {
//			resultMap.put("result", "success");
//		} else {
//			resultMap.put("result", "fail");
//		}
//	}
	
	

}

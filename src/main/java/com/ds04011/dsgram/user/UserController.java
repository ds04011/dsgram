package com.ds04011.dsgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/view")
public class UserController {
	
	@GetMapping("/login")
	public String loginInput() {
		return "user/login.html";
	}
	
	@GetMapping("/register")
	public String registerInput() {
		return "user/register.html";
	}

	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("loginId");
		session.removeAttribute("nickname");
		
		// 로그아웃 기능, 로그인 기능의 작동 반대로 하면된다. 
		
		return "redirect:/user/view/login";
	}
	
}

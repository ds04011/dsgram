package com.ds04011.dsgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	
}

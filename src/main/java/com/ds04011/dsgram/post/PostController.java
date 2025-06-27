package com.ds04011.dsgram.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	
	@GetMapping("/timeline")
	public String timeline() {
		
		return "layouts/timelinedefault";
	}

}

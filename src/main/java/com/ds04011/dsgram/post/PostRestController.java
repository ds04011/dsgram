package com.ds04011.dsgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.dsgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	
	@PostMapping("/create")
	public Map<String, String> create(@RequestParam("contents") String contents
			, HttpSession session
			, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile){
		
		long userId = (Long)(session.getAttribute("userId")); 
		// 비로그인시 세션 없어서 userId 못가져오는 경우 어떻게 작동하는지 궁금.
		Map<String, String> resultMap = new HashMap<>();
		if(postService.addPost(userId, contents, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;

	}

}

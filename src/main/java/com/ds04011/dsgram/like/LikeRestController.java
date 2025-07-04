package com.ds04011.dsgram.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.dsgram.like.Service.LikeService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	
	private LikeService likeService;
	public LikeRestController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@PostMapping("/like/create")
	public Map<String, String> like(@RequestParam("postId") long postId
			, HttpSession session){
		
		long userId = (Long)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		if(likeService.addLike(postId, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
		
	}

}

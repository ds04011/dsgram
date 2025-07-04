package com.ds04011.dsgram.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.dsgram.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private CommentService commentService;
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	// RestController 보다는 각 메서드를 판단하고 진행하자. 메서드 자체가 적으니까. 
	
	// 댓글을 다는거 자체를, detail 페이지에서만 가능하게 해야할듯

	@PostMapping("/create")
	public Map<String, String> createComment(@RequestParam("postId") long postId
			, @RequestParam("contents") String contents
			, HttpSession session){
		
		long userId = (Long)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		if(commentService.addComment(userId, postId, contents)) {
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;
	}
	
	

}

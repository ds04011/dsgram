package com.ds04011.dsgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.dsgram.comment.service.CommentService;
import com.ds04011.dsgram.like.Service.LikeService;
import com.ds04011.dsgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostRestController {
	
	private final PostService postService;
	private final CommentService commentService;
	private final LikeService likeService;
	
	
	
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
	
	@DeleteMapping("/delete")
	public Map<String, String> deletePost(@RequestParam("postId") long postId){
		
		
		// like comment post 모두 삭제
		// 먼저 좋아요 싹 찾아와, 아냐, 바로 삭제요청 보내 
		likeService.deleteByPostId(postId);
		commentService.deleteByPostId(postId);
		postService.deleteById(postId);
		// 이게 , 서비스끼리 호출하는게 더 예쁜가?
		// likeService, commentService 모두를 postService 에서 처리하는게 그림상 나은가?
		
		
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", "success");
		return resultMap;
		
		
	}

}

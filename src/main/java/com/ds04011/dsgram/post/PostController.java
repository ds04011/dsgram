package com.ds04011.dsgram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ds04011.dsgram.post.domain.Post;
import com.ds04011.dsgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/timeline")
	public String timeline(Model model
			, HttpSession session) {
		
		
		// 세션 정보 + DB 에서 포스트 정보 긁어다가 보내줘야함, 
		// 세션정보 -> 누구님 채우고, 
		// DB 정보 -> 타임라인 구성
		
		// 생각해보니, 세션에서 정보를 가져올 필요가 없는데,
		// 왜냐면 타임리프 문법으로 직접 세션에서 정보를 판단 할 수 있으니까.
		
		List<Post> totalPost = postService.getAllPost();
		
		
		
		return "post/timeline";
	}

}

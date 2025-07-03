package com.ds04011.dsgram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds04011.dsgram.comment.domain.Comment;
import com.ds04011.dsgram.comment.service.CommentService;
import com.ds04011.dsgram.post.Dto.PostDto;
import com.ds04011.dsgram.post.domain.Post;
import com.ds04011.dsgram.post.service.PostService;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	
	private PostService postService;
	private CommentService commentService;
	
	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}
	
	@GetMapping("/timeline")
	public String timeline(Model model) {
		
		
		// 세션 정보 + DB 에서 포스트 정보 긁어다가 보내줘야함, 
		// 세션정보 -> 누구님 채우고, 
		// DB 정보 -> 타임라인 구성
		
		// 생각해보니, 세션에서 정보를 가져올 필요가 없는데,
		// 왜냐면 타임리프 문법으로 직접 세션에서 정보를 판단 할 수 있으니까.
		
		List<PostDto> totalPost = postService.getPostList();
		
		model.addAttribute("postList", totalPost);

		return "post/timeline";
	}
	
	
	@GetMapping("/create")
	public String create() {
		
		return "post/input";
	}
	
	
	@GetMapping("/detail")
	public String detail(Model model
			,@RequestParam("id") long id ) {
		
		
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
									
		List<Comment> commentList = commentService.getCommentsByPostId(id);
		model.addAttribute("comments", commentList);
		
		
		
		// <a th:href="|/post/view/detail?id=${memo.id}|" th:text="${memo.title}"></a> 
		// 이런식으로 post id 자체를 바로 넘겨받아
		
		
		
		
		return "post/detail";
	}

}

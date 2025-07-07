package com.ds04011.dsgram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds04011.dsgram.comment.Dto.CommentDto;
import com.ds04011.dsgram.comment.service.CommentService;
import com.ds04011.dsgram.like.Service.LikeService;
import com.ds04011.dsgram.post.Dto.PostDto;
import com.ds04011.dsgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	
	private PostService postService;
	private CommentService commentService;
	private LikeService likeService;
	
	public PostController(PostService postService, CommentService commentService
			, LikeService likeService) {
		this.postService = postService;
		this.commentService = commentService;
		this.likeService = likeService;
	}
	
	@GetMapping("/timeline")
	public String timeline(Model model
			, HttpSession session) {
		
		
		// 세션 정보 + DB 에서 포스트 정보 긁어다가 보내줘야함, 
		// 세션정보 -> 누구님 채우고, 
		// DB 정보 -> 타임라인 구성
		
		// 생각해보니, 세션에서 정보를 가져올 필요가 없는데,
		// 왜냐면 타임리프 문법으로 직접 세션에서 정보를 판단 할 수 있으니까.
		long currentUserId = (Long)session.getAttribute("userId");
		List<PostDto> totalPost = postService.getPostList(currentUserId);
		model.addAttribute("postList", totalPost);
		
		// 이거 보니까, postDto 에 좋아요 수 , 댓글 1,2개 를 넣어야 하는건가..?
		

		return "post/timeline";
	}
	
	
	@GetMapping("/create")
	public String create() {
		
		return "post/input";
	}
	
	
	@GetMapping("/detail")
	public String detail(Model model
			,@RequestParam("id") long id 
			, HttpSession session) {
		// 이거 포스트 아이디 말하는거임. 
		
		PostDto post = postService.getPostById(id);
		model.addAttribute("post", post);
									
		List<CommentDto> commentList = commentService.getCommentsByPostId(id);
		model.addAttribute("comments", commentList);
		
		// 댓글 단 유저 닉 보이려면, commentDto 로 보내야겠는데?
		// 이제 nickname 있음.
		
		long likeCount = likeService.getCountByPostId(id);
		boolean isLike = likeService.isLike((Long)session.getAttribute("userId"), id);
		model.addAttribute("isLike", isLike);
		model.addAttribute("likeCount", likeCount);
		// <a th:href="|/post/view/detail?id=${memo.id}|" th:text="${memo.title}"></a> 
		// 이런식으로 post id 자체를 바로 넘겨받아
		// 이렇게 안해도, 버튼에 값 부여 해서 또는 아이디부여해서, ajax 해도 된다. 
		
		
		
		return "post/detail";
	}

}

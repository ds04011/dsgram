package com.ds04011.dsgram.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.dsgram.comment.service.CommentService;
import com.ds04011.dsgram.common.FileManager;
import com.ds04011.dsgram.like.Service.LikeService;
import com.ds04011.dsgram.post.Dto.PostDto;
import com.ds04011.dsgram.post.domain.Post;
import com.ds04011.dsgram.post.repository.PostRepository;
import com.ds04011.dsgram.user.domain.User;
import com.ds04011.dsgram.user.service.UserService;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor  이거 있으면 final 선언된 멤버변수만 추가해주면 롬복이 객체 주입을 알아서 해줌.
@RequiredArgsConstructor
public class PostService {
	
	
	private final PostRepository postRepository;
	private final UserService userService;
	private final LikeService likeService;
	private final CommentService commentService;
	
//	public PostService(PostRepository postRepository, UserService userService) {
//		this.postRepository = postRepository;
//		this.userService = userService;
//	}  
	// RequiredArgsConstructor 가 이 부분을 생략시켜줌
	
	
	public List<Post> getAllPost(){
		return postRepository.findAllByOrderByIdDesc();
		// findAll(Sort.by(Sort.Direction.DESC, "id")) 로도 같은 역할 가능.
	}
	
	
	public boolean addPost(long userId, String contents
			, MultipartFile file) {
		
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder().userId(userId).contents(contents)
				.imagePath(imagePath).build();
		
		try {
			postRepository.save(post);
			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;

	}
	
	public List<PostDto> getPostList(long currentUserId){
		List<Post> postList  = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
		List<PostDto> dtoList = new ArrayList<>();
		
		//isLike, commentList, like number 담아야하는데, 
		
		for(Post post : postList) {
			
			User user = userService.getUserById(post.getUserId());
			
//			likeService.isLike(post.getUserId(), post.getId());	isLike 
//			likeService.getCountByPostId(post.getId());  		like number
//			commentService.getComments(post.getId());  			commentList
			 
			// isLike 할꺼면, 작성자 id 가 아니라 로그인해있는 유저 id 로 판별해야함
			// 그러면 타임라인 자체에서, 세션정보로 해야하는거아님?
			
			PostDto postDto = PostDto.builder()
					.id(post.getId())
					.contents(post.getContents())
					.imagePath(post.getImagePath())
					.userId(post.getUserId())
					.nickname(user.getNickname())
					.createdAt(post.getCreatedAt())
					.isLike(likeService.isLike(currentUserId, post.getId()))
					//						여기 들어갈게, 지금 로그인해있는 유저 아이디인데?
					.commentList(commentService.getCommentsByPostId(post.getId()))
					.likeCount(likeService.getCountByPostId(post.getId()))
					.build();
					
					
			dtoList.add(postDto);
			
		}
		
		return dtoList; 
		
	}
	
	public PostDto getPostById(long id) {
		Optional<Post> opPost =  postRepository.findById(id);
		if(opPost.isPresent()) {
			Post post =  opPost.get();
			User user = userService.getUserById(post.getUserId());
			
			
			
			PostDto postDto = PostDto.builder()
					.id(post.getId())
					.contents(post.getContents())
					.imagePath(post.getImagePath())
					.userId(post.getUserId())
					.nickname(user.getNickname())
					.createdAt(post.getCreatedAt())
					.build();
			
			return postDto;
			
			
			
			
		} else {
			return null;
		}
		
	}
	
	public boolean deleteById(long id) {
		
		Optional<Post> opPost= postRepository.findById(id);
		if(opPost.isPresent()) {
			Post post = opPost.get();
			
			FileManager.removeFile(post.getImagePath());
			postRepository.delete(post);
			return true;
			
		} else {
			return false;
		}
	}

}

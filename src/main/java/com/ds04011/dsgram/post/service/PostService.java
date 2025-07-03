package com.ds04011.dsgram.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.dsgram.common.FileManager;
import com.ds04011.dsgram.post.Dto.PostDto;
import com.ds04011.dsgram.post.domain.Post;
import com.ds04011.dsgram.post.repository.PostRepository;
import com.ds04011.dsgram.user.domain.User;
import com.ds04011.dsgram.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	
	
	private final PostRepository postRepository;
	private final UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	
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
	
	public List<PostDto> getPostList(){
		List<Post> postList  = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
		List<PostDto> dtoList = new ArrayList<>();
		
		for(Post post : postList) {
			
			User user = userService.getUserById(post.getUserId());
			PostDto postDto = PostDto.builder()
					.id(post.getId())
					.contents(post.getContents())
					.imagePath(post.getImagePath())
					.userId(post.getUserId())
					.nickname(user.getNickname())
					.createdAt(post.getCreatedAt())
					.build();
					
					
			dtoList.add(postDto);
			
		}
		
		return dtoList; 
		
	}
	
	public Post getPost(long id) {
		Optional<Post> opPost =  postRepository.findById(id);
		if(opPost.isPresent()) {
			return opPost.get();
			
		} else {
			return null;
		}
		
	}

}

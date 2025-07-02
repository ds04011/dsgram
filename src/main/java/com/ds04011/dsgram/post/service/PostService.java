package com.ds04011.dsgram.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.dsgram.common.FileManager;
import com.ds04011.dsgram.post.domain.Post;
import com.ds04011.dsgram.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	
	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
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

}

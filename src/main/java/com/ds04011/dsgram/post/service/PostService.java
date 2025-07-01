package com.ds04011.dsgram.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds04011.dsgram.post.domain.Post;
import com.ds04011.dsgram.post.repository.PostRepository;

@Service
public class PostService {
	
	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public List<Post> getAllPost(){
		return postRepository.findAll();
	}

}

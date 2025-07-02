package com.ds04011.dsgram.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.dsgram.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	public List<Post> findAllByOrderByIdDesc();

	
	
}

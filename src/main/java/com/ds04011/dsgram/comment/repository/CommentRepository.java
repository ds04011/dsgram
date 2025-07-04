package com.ds04011.dsgram.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.dsgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	public List<Comment> findAllByPostId(long postId);
}

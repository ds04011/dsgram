package com.ds04011.dsgram.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.dsgram.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}

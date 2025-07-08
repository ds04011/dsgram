package com.ds04011.dsgram.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.dsgram.comment.domain.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	public List<Comment> findAllByPostId(long postId);
	
	// JPA 에서 여러행이 삭제될 수 있는 메서드에는, 추가 처리가 필요하다. 
	// 이게 보면, delete 전에 select 쿼리문을 실행하고 에러가 난 로그를 볼 수 있는데, 
	// 여러 쿼리가 쭉 진행되려고 할 때, 서버라서, 그냥 순서대로 쿼리를 진행하면, 다른 쿼리가 끼어들 수도 있음
	// 그래서 transanction 이라는 쿼리진행틀 , 쿼리묶음, 이 필요한 것
	// 이렇게 하면 다른 쿼리가 끼어들지 못하고, 한번에 이 묶음을 먼저 다룸. 
	// 또한 동시에 Roll back , 쿼리묶음의 수행과정에 문제가 생기면 이 쿼리묶음 전체가 되돌아가는 것
	// 그래서 은행 입금 출금 개념으로 설명을 하는거네..
	
	@Transactional
	public void deleteByPostId(long postId);
}

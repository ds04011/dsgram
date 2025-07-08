package com.ds04011.dsgram.like.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.dsgram.like.domain.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Long>{

	
	public long countByPostId(long postId);
	public long countByUserIdAndPostId(long userId, long postId);
	
	public Optional<Like> findByPostIdAndUserId(long postId, long userId);
	public void deleteByPostIdAndUserId(long postId, long userId);
	
	// JPA 에서 여러행이 삭제될 수 있는 메서드에는, 추가 처리가 필요하다. 
	@Transactional
	public void deleteAllByPostId(long postId);
}

package com.ds04011.dsgram.like.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.dsgram.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{

	
	public long countByPostId(long postId);
}

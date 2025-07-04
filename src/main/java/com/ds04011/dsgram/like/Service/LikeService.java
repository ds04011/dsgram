package com.ds04011.dsgram.like.Service;

import org.springframework.stereotype.Service;

import com.ds04011.dsgram.like.Repository.LikeRepository;
import com.ds04011.dsgram.like.domain.Like;

import jakarta.persistence.PersistenceException;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	public boolean addLike(long postId, long userId) {
		Like l1 = Like.builder().postId(postId).userId(userId).build();
		
		
		try {
		likeRepository.save(l1);
		} catch(PersistenceException e) {
			return false;
		}
		return true;
	}
	
	public long getCountByPostId(long postId) {
		long count = likeRepository.countByPostId(postId);
		return count;
	}
	
	
}

package com.ds04011.dsgram.like.Service;

import java.util.List;
import java.util.Optional;

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
	
	public boolean isLike(long userId, long postId) {
		long isLike  = likeRepository.countByUserIdAndPostId(userId, postId);
		// 1 이면 누른거고, 0 이면 안누른거고, 
		if(isLike == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean cancelLike(long postId, long userId) {
		
		Optional<Like> oplike = likeRepository.findByPostIdAndUserId(postId, userId); 
		if(oplike.isPresent()) {
			Like like = oplike.get() ;
			likeRepository.delete(like);
			return true;
		 } else {
			 return false;
		 } 
	}
	
	public boolean deleteByPostId(long postId) {
		likeRepository.deleteAllByPostId(postId);
		return true;
		
	}
	
}

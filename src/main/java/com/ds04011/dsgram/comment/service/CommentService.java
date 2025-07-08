package com.ds04011.dsgram.comment.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ds04011.dsgram.comment.Dto.CommentDto;
import com.ds04011.dsgram.comment.domain.Comment;
import com.ds04011.dsgram.comment.repository.CommentRepository;
import com.ds04011.dsgram.post.Dto.PostDto;
import com.ds04011.dsgram.user.domain.User;
import com.ds04011.dsgram.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	public CommentService(CommentRepository commentRepository
			, UserService userSerivce) {
		this.commentRepository = commentRepository;
		this.userService = userSerivce;
	}
	
	
	public boolean addComment(long userId, long postId, String contents) {
		
		Comment c1 = Comment.builder().userId(userId)
				.postId(postId)
				.contents(contents)
				.build();
		
		
		try {
			commentRepository.save(c1);
			
		} catch (PersistenceException e){
			return false;
		}
		return true;
	}
	
	public List<CommentDto> getCommentsByPostId(long postId){
		 List<Comment> commentList = commentRepository.findAllByPostId(postId);
		 List<CommentDto> dtoList = new ArrayList<>();
		 
		 for(Comment c : commentList) {
			 User user = userService.getUserById(c.getUserId());
			 
			 CommentDto cd  = CommentDto.builder().id(c.getId())
					 .userId(c.getUserId())
					 .postId(c.getPostId())
					 .contents(c.getContents())
					 .createdAt(c.getCreatedAt())
					 .nickname(user.getNickname())
					 .build();
			 
			 dtoList.add(cd);
		 }
		 
		 return dtoList;
	}
	
	public List<Comment> getComments(long postId){
		List<Comment> commentList = commentRepository.findAllByPostId(postId);
		return commentList;
	}
	
	public boolean deleteByPostId(long postId) {
		
		commentRepository.deleteByPostId(postId);
		return true;
	}

}

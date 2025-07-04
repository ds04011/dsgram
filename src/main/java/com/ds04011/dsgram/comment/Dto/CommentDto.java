package com.ds04011.dsgram.comment.Dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder=true)
@Getter
public class CommentDto {
	
	private long id;
	private String contents;
	
	private long userId;
	private long postId;
	private LocalDateTime createdAt;
	
	private String nickname;
	

}

package com.ds04011.dsgram.post.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ds04011.dsgram.comment.Dto.CommentDto;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder=true)
@Getter
public class PostDto {
	
	// d아이디 컨텐츠 이미지패스 유저아이디 로그인아이디
	
	private long id;
	
	private String contents;
	private String imagePath;
	
	private long userId;
	private String nickname;
	
	private LocalDateTime createdAt;
	
	private long likeCount;
	private boolean isLike;
	
	private List<CommentDto> commentList;
	
	
	
	
}

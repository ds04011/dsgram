package com.ds04011.dsgram.post.Dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder(toBuilder=true)
public class PostDto {
	
	// d아이디 컨텐츠 이미지패스 유저아이디 로그인아이디
	
	private long id;
	
	private String contents;
	private String imagePath;
	
	private long userId;
	private String nickname;
	
	private LocalDateTime createdAt;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}

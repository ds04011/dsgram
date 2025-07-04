package com.ds04011.dsgram.like.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="`like`")
@Getter
@Setter
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class Like {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long postId;
	private long userId;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
}

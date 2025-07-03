package com.ds04011.dsgram.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ds04011.dsgram.user.domain.User;

@Mapper
public interface UserRepository {
	
	public int insertUser(@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("email") String email
			, @Param("nickname") String nickname);

	
	public int countByEmail(@Param("email") String email);
	public int countByNickname(@Param("nickname") String nickname);
	
	public User selectUser(@Param("loginId") String loginId,@Param("password") String password );
	
	public User selectUserById(@Param("id") long id);
}

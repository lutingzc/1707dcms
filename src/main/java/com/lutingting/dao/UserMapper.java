package com.lutingting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lutingting.entity.User;

public interface UserMapper {

	User getById(Integer userId);
	@Update("UPDATE cms_user SET locked=${status} WHERE id=${userId}")
	int updateStatus(@Param("userId")Integer userId,@Param("status")int status);

	List<User> list(String name);
	int add(User user);
	@Select("SELECT * FROM cms_user "
			+ " WHERE username = #{value} limit 1 ")
	User findByUserName(String username);
	
	
	

	
}

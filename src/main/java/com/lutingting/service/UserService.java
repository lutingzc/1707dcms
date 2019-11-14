package com.lutingting.service;

import com.github.pagehelper.PageInfo;
import com.lutingting.entity.User;

public interface UserService {

	PageInfo<User> getPageList(String name, Integer page);

	int updateStatus(Integer userId, int status);

	User getUserById(Integer userId);

}

package com.lutingting.service;

import java.util.List;

import com.lutingting.entity.Category;

public interface CategoryService {

	List<Category> listByChannelId(int chnId);


}

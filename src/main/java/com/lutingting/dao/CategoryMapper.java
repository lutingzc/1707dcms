package com.lutingting.dao;

import java.util.List;

import com.lutingting.entity.Category;

public interface CategoryMapper {

	List<Category> listByChannelId(int chnId);

}

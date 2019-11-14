package com.lutingting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lutingting.dao.CategoryMapper;
import com.lutingting.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryMapper mategoryMapper;

	@Override
	public List<Category> listByChannelId(int chnId) {
		// TODO Auto-generated method stub
		return mategoryMapper.listByChannelId(chnId);
	}
}

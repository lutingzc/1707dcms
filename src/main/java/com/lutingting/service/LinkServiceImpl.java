package com.lutingting.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lutingting.dao.LinkMapper;
import com.lutingting.entity.Link;

@Service
public class LinkServiceImpl implements LinkService{

	@Autowired
	LinkMapper mapper;

	@Override
	public int add(Link link) {
		return mapper.add(link);
		
	}

	@Override
	public PageInfo list(int page) {
		PageHelper.startPage(page, 10);
		
		return new PageInfo<Link>(mapper.list());
	}
}

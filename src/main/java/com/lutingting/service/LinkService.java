package com.lutingting.service;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.lutingting.entity.Link;

public interface LinkService {

	public int add(Link link);
	public PageInfo list(int page);
}

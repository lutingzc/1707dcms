package com.lutingting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lutingting.dao.ChannelMapper;
import com.lutingting.entity.Channel;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	ChannelMapper channelMapper;

	@Override
	public List<Channel> list() {
		// TODO Auto-generated method stub
		return channelMapper.list();
	}
	
}

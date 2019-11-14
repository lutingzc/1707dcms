package com.lutingting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.lutingting.entity.Article;
import com.lutingting.entity.Category;
import com.lutingting.entity.Channel;
import com.lutingting.service.ArticleService;
import com.lutingting.service.CategoryService;
import com.lutingting.service.ChannelService;

@Controller
public class IndexController {

	@Autowired
	ChannelService channelService ;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ArticleService articleService;

	
	@RequestMapping("channel")
	public String channel(HttpServletRequest request, 
			@RequestParam(defaultValue = "1") int chnId,
			@RequestParam(defaultValue = "0") int categoryId,
			@RequestParam(defaultValue = "1") int page) {
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		
		// 获取这个频道下的所有的分类
		List<Category> categories =  categoryService.listByChannelId(chnId);
		request.setAttribute("categories", categories);
		
		PageInfo<Article> articles=  articleService.listByCat(chnId,categoryId,page);
		request.setAttribute("articles", articles);
		return "channelindex";
	
	}	
	
	
	
	@RequestMapping(value= {"index","/"})
	public String index(HttpServletRequest request,@RequestParam(defaultValue="1")int page) {
		List<Channel> channels=channelService.list();
		request.setAttribute("channels", channels);
		
		PageInfo<Article> hotList=articleService.hotList(page);
		
		List<Article> newArticles=articleService.getNewArticles(5);
		
		request.setAttribute("hotList", hotList);
		request.setAttribute("newArticles", newArticles);
		
		return "index";
	}
}

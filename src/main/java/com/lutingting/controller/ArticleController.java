package com.lutingting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lutingting.entity.Article;
import com.lutingting.service.ArticleService;

@RequestMapping("article")
@Controller
public class ArticleController {

	@Autowired
	ArticleService articleService ;
	@RequestMapping("showdetail")
	public String showDetail(HttpServletRequest request,Integer id) {
		Article article= articleService.getById(id);
		request.setAttribute("article", article);
		return "article/detail";
	}
}

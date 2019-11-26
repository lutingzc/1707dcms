package com.lutingting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lutingting.commom.CmsAssert;
import com.lutingting.commom.MsgResult;
import com.lutingting.entity.Article;
import com.lutingting.entity.Category;
import com.lutingting.entity.Comment;
import com.lutingting.entity.Image;
import com.lutingting.entity.TypeEnum;
import com.lutingting.service.ArticleService;
import com.lutingting.service.CategoryService;


@RequestMapping("article")
@Controller
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	CategoryService catService;
	
	@RequestMapping("showdetail")
	public String showDetail(HttpServletRequest request,Integer id) {
		
		Article article = articleService.getById(id); 
		CmsAssert.AssertTrueHtml(article!=null, "文章不存在");
		
		
		request.setAttribute("article",article);
		if(article.getArticleType()==TypeEnum.IMG) {
			
			Gson gson = new Gson();
			// 文章内容转换成集合对象
			List<Image> imgs = gson.fromJson(article.getContent(), new TypeToken<List<Image>>() {}.getType());
			article.setImgList(imgs);
			System.out.println(" article is "  + article);
			return "article/detailimg";
			
		}
		return "article/detail";
		
		
		
	}
	
	@RequestMapping("getCategoryByChannel")
	@ResponseBody
	public MsgResult getCategoryByChannel(int chnId) {
		List<Category> categories = catService.listByChannelId(chnId);
		return new MsgResult(1,"",categories);
	}
	@RequestMapping("commentlist")
	public String commentlist(HttpServletRequest request ,int id,@RequestParam(defaultValue="1")int page) {
		PageInfo<Comment> pageComment =  articleService.commentlist(id,page);
		request.setAttribute("pageComment", pageComment);
		return "article/comments";
	}
}

package com.lutingting.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lutingting.entity.Article;
import com.lutingting.entity.Comment;

public interface ArticleService {

	PageInfo<Article> hotList(int page);

	List<Article> getNewArticles(int i);

	Article getById(Integer id);

	PageInfo<Article> listByCat(int chnId, int categoryId, int page);

	PageInfo<Article> listByUser(int page, Integer userId);

	int delete(int id);

	Article checkExist(int id);

	PageInfo<Article> getPageList(int status, Integer page);

	Article getDetailById(int id);

	int apply(int id, int status);

	int setHot(int id, int status);

	int add(Article article);

	int update(Article article);

	int favarite(Integer userId, int articleId);

	List<Article> getImgArticles(int num);

	int comment(Integer userId, int articleId, String content);

	PageInfo<Comment> commentlist(int articleId, int page);

}

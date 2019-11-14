package com.lutingting.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lutingting.entity.Article;

public interface ArticleService {

	PageInfo<Article> hotList(int page);

	List<Article> getNewArticles(int i);

	Article getById(Integer id);

	PageInfo<Article> listByCat(int chnId, int categoryId, int page);

}

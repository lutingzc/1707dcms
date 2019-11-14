package com.lutingting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lutingting.entity.Article;

public interface ArticleMapper {

	
	List<Article> hostList();

	List<Article> newList(int i);

	Article getById(Integer id);

	List<Article> listByCat(@Param("chnId")int chnId,@Param("categoryId") int categoryId);

}

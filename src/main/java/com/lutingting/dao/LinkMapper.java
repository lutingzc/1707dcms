package com.lutingting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lutingting.entity.Link;

public interface LinkMapper {

	@Insert("INSERT INTO cms_link (url,name,created) "
			+ " VALUES(#{url},#{name},now())")
	int add(Link link);

	@Select("SELECT * FROM cms_link ORDER BY created DESC")
	List<Link> list();

}

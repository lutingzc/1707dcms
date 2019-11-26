package com.lutingting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lutingting.commom.MsgResult;
import com.lutingting.entity.User;
import com.lutingting.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	UserService userService;
	@RequestMapping("index")
	public String index() {
		return "amdin/index";
	}
	@RequestMapping("articles")
	public String articles() {
		return "amdin/article/list";
	}
	@RequestMapping("users")
	public String users(HttpServletRequest request,@RequestParam(defaultValue="")String name,@RequestParam(defaultValue="1")Integer page) {
		PageInfo<User> userPage=userService.getPageList(name,page);
		request.setAttribute("info", userPage);
		request.setAttribute("name", name);
		return "amdin/user/list";
		
	}
	@RequestMapping("lockuser")
	@ResponseBody
public MsgResult lock(Integer userId,int status) {
		
		/**
		 * 
		 */
		if(status != 0 && status!=1) {
			return new MsgResult(2,"参数无效",null);
		}
		
		/**
		 * 
		 */
		User user  = userService.getUserById(userId);
		
		/**
		 * 
		 */
		if(user == null) {
			return new MsgResult(2,"该用户不存在",null);
		}
		
		/**
		 * 
		 */
		if(user.getLocked()==status) {
			return new MsgResult(2,"无需做该操作",null);
		}
		
		int result = userService.updateStatus(userId,status);
		if(result>0) {
			return new MsgResult(1,"恭喜您，处理成功",null);
		}else{
			return new MsgResult(2,"非常抱歉，处理失败，请与管理员联系！",null);
		}
		
		
		
		
		
		
		
	}
}

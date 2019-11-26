package com.lutingting.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.lutingting.commom.ConstantClass;
import com.lutingting.entity.User;


/**
 * 
 * @author 
 *拦截器
 */
public class UserInterceptor  implements HandlerInterceptor {
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException
			 {
		
		User loginUser =  (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		
		// 用户没有登录
		if(loginUser==null) {
			response.sendRedirect("/user/login");
			return false;
		}
		
		/**
		 * 普通用户不能进入管理员页面
		 */
		if(request.getServletPath().contains("/admin/") 
				&& loginUser.gethrowle()==ConstantClass.USER_ROLE_GENERAL ) {
			request.setAttribute("errorMsg", "只有管理员才能访问这个页面");
			//response.sendRedirect("/user/login");
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		return true;
	}
	
}

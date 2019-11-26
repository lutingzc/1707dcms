package com.lutingting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.lutingting.entity.Link;
import com.lutingting.service.LinkService;

@Controller
@RequestMapping("link")
public class LinkController {


	@Autowired
	LinkService linkService;
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request, 
			@RequestParam(defaultValue="1") int page) {
		
		PageInfo info = linkService.list(page);
		request.setAttribute("info", info);
		return "amdin/link/list";
		
		
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("add")
	public String add(HttpServletRequest request) {
		request.setAttribute("link", new Link());
		return "amdin/link/add";	 
	}
	
	/**
	 * 
	 * @param request
	 * @param link
	 * @param result
	 * @return
	 */
	@PostMapping("add")
	public String add(HttpServletRequest request,
			@Valid  @ModelAttribute("link") Link link,
			BindingResult result
			) {
		// 有错误 还在原来的页面
		if(result.hasErrors()) {
			return "amdin/link/add";	
		}
		linkService.add(link);
		
		// 没有错误跳转到列表页面
		return "redirect:list";
	}
	
}

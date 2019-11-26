package com.lutingting.controller;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lutingting.commom.CmsAssert;
import com.lutingting.commom.ConstantClass;
import com.lutingting.commom.MsgResult;
import com.lutingting.entity.Article;
import com.lutingting.entity.Channel;
import com.lutingting.entity.Collect;
import com.lutingting.entity.Image;
import com.lutingting.entity.TypeEnum;
import com.lutingting.entity.User;
import com.lutingting.service.ArticleService;
import com.lutingting.service.ChannelService;
import com.lutingting.service.CollectService;
import com.lutingting.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	Logger log=Logger.getLogger(UserController.class);
	
	@Value("d:/pic")
	String uploadPath;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	CollectService collectService;
	private SimpleDateFormat dateFormat;
	
	@RequestMapping("listt")
	public String select(HttpServletRequest request) {
		List<Collect> list=collectService.selectObjects();
		request.setAttribute("users", list);
		return "/article/detail2";
	
	}
	
	@RequestMapping("collect")
	@ResponseBody
	public MsgResult collect(HttpServletRequest request,Collect collect) {
		User loginUser = (User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser!=null, "亲，您尚未登录");
		if(collect.getName().length()>20) {
			collect.setName(collect.getName().substring(0,20)+"...");
			
		}
		collect.setUserId(loginUser.getId());
		int result=collectService.add(collect);
		CmsAssert.AssertTrue(result>0, "很遗憾，将入收藏夹");
		return new MsgResult(1,"恭喜，收藏成功",null);
	}
	
	@RequestMapping("favarite")
	@ResponseBody
	public MsgResult favarite(HttpServletRequest request, int id) {
		
		CmsAssert.AssertTrue(id>0, "id 不合法");
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser!=null, "亲，您尚未登录！！");
		int result = articleService.favarite(loginUser.getId(),id);
		CmsAssert.AssertTrue(result>0, "很遗憾，收藏失败！！");
		return new MsgResult(1,"恭喜，收藏成功",null);
		
	}
	//  httppxxxx://user/hello
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public String tet(HttpServletRequest request) {
		request.setAttribute("info", "hello");
		return "user/test";
	}
	
	/**
	 * 跳转到注册页面 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(HttpServletRequest request) {
		return "user/register";
	}
	
	/**
	 * 处理用户提交的注册的数据
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(HttpServletRequest request,User user) {
		
		
		int result = userService.register(user);
		CmsAssert.AssertTrue(result>0,"用户注册失败，请稍后再试");
		
		
		return "redirect:/user/login";
	}
	
	@RequestMapping(value= {"","/","geren"})
	public String Geren(HttpServletRequest request) {
		
		return "user/geren";
		
	}
	
	/**
	 * 跳转到登录页面 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "user/login";
	}
	
	/**
	 * 处理用户提交的登录的数据
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,User user) {
		
		
		User loginUser  = userService.login(user);
		// 用户存在 登录成功
		if(loginUser!=null) {
			request.getSession().setAttribute(ConstantClass.USER_KEY, loginUser);
			
			//return "redirect:/";
			return loginUser.gethrowle()==ConstantClass.USER_ROLE_ADMIN
					?"redirect:/admin/index":"redirect:/user/home";
		}else {
			request.setAttribute("errorMsg", "用户名或密码错误！！");
			request.setAttribute("user", user);
			return "user/login";
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstantClass.USER_KEY);
		return "redirect:/";
	}
	
	@RequestMapping("checkname")
	@ResponseBody
	public boolean checkname(String username) {
		return null==userService.findByName(username);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {
		return "/user/home";
	}
	
	@GetMapping("updateArticle")
	public String updateArticle(HttpServletRequest request,int id) {
		Article article = articleService.getDetailById(id);
		request.setAttribute("article", article);
		request.setAttribute("content1", htmlspecialchars(article.getContent()));
		System.out.println("将要修改的文章"+article);
		
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);
		return "article/update";
	}
	@PostMapping("updateArticle")
	@ResponseBody
	public MsgResult updateArticle(HttpServletRequest request,MultipartFile file,Article article) throws IllegalStateException, IOException {
		if(!file.isEmpty()) {
			String picUrl=processFile(file);
			article.setPicture(picUrl);
		}
		
		
		int result=articleService.update(article);
		if(result>0) {
			return new MsgResult(1,"",null);
		}else {
			return new MsgResult(2,"失败",null);
		}
	}
	/**
	 * 
	 * @Title: postArticle 
	 * @Description: 进入发表文章的页面
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("postArticle")
	public String postArticle(HttpServletRequest request) {
		List<Channel> channels=channelService.list();
		request.setAttribute("channels", channels);
		return "article/publish";
	}
	/**
	 * 上传文件的规则
	 * 文件扩展名不能改变
	 * 保存到某个路径下 要求子目录
	 * 子目录 每天一个子目录
	 * @Title: postArticle 
	 * @Description: TODO
	 * @param request
	 * @param file
	 * @param article
	 * @return
	 * @thro IllegalStateException
	 * @thro IOException
	 * @return: MsgResult
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("postArticle")
	@ResponseBody
	public MsgResult postArticle(HttpServletRequest request, MultipartFile file,Article article) throws IllegalStateException, IOException {
		if(!file.isEmpty()) {
			String fileUrl=processFile(file);
			article.setPicture(fileUrl);
		}
		User loginUser=(User) request.getSession().getAttribute(ConstantClass.USER_KEY);
		article.setUserId(loginUser.getId());
		
		int result=articleService.add(article);
		if(result>0) {
			return new MsgResult(1,"处理成功",null);
		}else {
			return new MsgResult(2,"添加失败，请稍后再试",null);
		}
		
		
	}
	/**
	 * 保存文件相对路径
	 *
	 * @Title: processFile 
	 * @Description: TODO
	 * @param file
	 * @return
	 * @thro IllegalStateException
	 * @thro IOException
	 * @return: String
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
private String processFile(MultipartFile file) throws IllegalStateException, IOException {
    	
    	

    	
    	//1 求扩展名  "xxx.jpg"
    	String suffixName =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
    	String fileNamePre = UUID.randomUUID().toString();
    	// 计算出新的文件名称
    	String fileName = fileNamePre + suffixName;
		dateFormat=new SimpleDateFormat("yyyyMMdd");
		String path=dateFormat.format(new Date());
    	File pathFile=new File(uploadPath+"/"+path);
    	if(!pathFile.exists()) {
    		pathFile.mkdirs();
    	}
    	String newFileName=uploadPath+"/"+path+"/"+fileName;
    	file.transferTo(new File(newFileName));
    	return path+"/"+fileName;
    	
    	
    }
		
	

/**
 * 获取文章列表
 * @return
 */
@RequestMapping("myarticles")
public String myarticles(HttpServletRequest request,
		@RequestParam(defaultValue="1") int page) {
	
	User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
	
	PageInfo<Article> pageInfo=  articleService.listByUser(page,loginUser.getId());
	request.setAttribute("pageInfo", pageInfo);
	return "user/myarticles";
}

	
	@RequestMapping("delArticle")
	@ResponseBody
	public MsgResult delArticle(HttpServletRequest request,int id){
		
		CmsAssert.AssertTrue(id>0, "文章id必须大于0");
		Article article =  articleService.checkExist(id);
		CmsAssert.AssertTrue(article!=null, "该文章不存在");
		
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(
				loginUser.gethrowle()==ConstantClass.USER_ROLE_ADMIN 
				|| loginUser.getId()==article.getUserId(),
				"只有管理员和文章的作者能删除文章");
		
		int result = articleService.delete(id);
		CmsAssert.AssertTrue(result>0,"文章删除失败");
		return new MsgResult(1,"删除成功",null);
		
	}
	/**
	 * 图片
	 * @return
	 */
	@GetMapping("postImg")
	public String postImg(HttpServletRequest request) {
		
		// 获取所有的频道
		List<Channel> channels =  channelService.list();
		request.setAttribute("channels", channels);	
		return "article/postimg";
		
	}
	/**
	 * 
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @thro IOException 
	 * @thro IllegalStateException 
	 */
	@RequestMapping(value = "postImg",method=RequestMethod.POST)
	@ResponseBody
	public MsgResult postImg(HttpServletRequest request,Article article,
			MultipartFile file[],String desc[]) throws IllegalStateException, IOException {
		
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		
		
		List<Image> list = new ArrayList<>();
		// 遍历处理每个上传图片 并存入list
		for (int i = 0; i < file.length && i < desc.length; i++) {
			String url = processFile(file[i]);
			Image image = new Image();
			image.setDesc(desc[i]);
			image.setUrl(url);
			list.add(image);
		}
		
		//
		Gson gson = new Gson();
		
		//设置作者
		article.setUserId(loginUser.getId());
		article.setContent(gson.toJson(list));
		//设置文章类型 是图片
		article.setArticleType(TypeEnum.IMG);
		
		int add = articleService.add(article);
		if(add > 0) {
			return new MsgResult(1,"发布成功11",null);
		}else {
			return new MsgResult(2,"发布失败11",null);
		}
		
		
	}
	/**
	 * 评论
	 * @param request
	 * @param id
	 * @param content
	 * @return
	 */
	@RequestMapping("comment")
	@ResponseBody
	public MsgResult comment(HttpServletRequest request, int id,String content) {
		
		User loginUser = (User)request.getSession().getAttribute(ConstantClass.USER_KEY);
		CmsAssert.AssertTrue(loginUser!=null, "亲，您尚未登录");
		
		int result = articleService.comment(loginUser.getId(),id,content);
		CmsAssert.AssertTrue(result>0, "亲，评论失败了！！");
		return new MsgResult(1,"评论成功","");
	}
	private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
}

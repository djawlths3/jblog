package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/{id:(?!assets|images).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@Auth
	@RequestMapping( "/basic" )
	public String adminBasic(Model model, @PathVariable String id) {
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogInfo", blogVo);
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping( "/basicUpload" )
	public String adminBasicUpload(@ModelAttribute BlogVo blogVo, 
			@RequestParam(value="logoFile") MultipartFile multipartFile,
			HttpServletRequest request,
			@PathVariable String id,
			Model model, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		blogService.blogInfoUpdate(blogVo, multipartFile, authUser, request);
		model.addAttribute("blogInfo", blogVo);
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping( "/category" )
	public String adminCategoryGetList(Model model, HttpSession session, @PathVariable String id) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = blogService.getBlogInfo(id);
		List li = blogService.getList(userVo);
		
		model.addAttribute("list",li);
		model.addAttribute("blogInfo", blogVo);
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping( value="/category" , method=RequestMethod.POST  )
	public JSONResult adminCategoryDelete(@RequestParam(value="no", required=true, defaultValue="") Long no) {
		blogService.categoryDelete(no);
		JSONResult result = JSONResult.success(true);	
		return result;
	}
	
	@ResponseBody
	@RequestMapping( value="/category/add" , method=RequestMethod.POST  )
	public JSONResult adminCategory(@RequestParam(value="name", required=true, defaultValue="") String name,
			@RequestParam(value="explanation", required=true, defaultValue="") String explanation, HttpSession session) {
		
		CategoryVo categoryVo = new CategoryVo(name, explanation, (UserVo) session.getAttribute("authUser"));
		blogService.categoryAdd(categoryVo);
		JSONResult result = JSONResult.success(categoryVo);
		return result;
	}
	
	@Auth
	@RequestMapping( "/write" )
	public String adminWrite(Model model, HttpSession session, @PathVariable String id) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		List li = blogService.getList(userVo);
		BlogVo blogVo = blogService.getBlogInfo(id);
	
		model.addAttribute("list",li);
		model.addAttribute("blogInfo", blogVo);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping( value="/write" , method=RequestMethod.POST )
	public String adminWrite(@ModelAttribute PostVo postVo, Model model, HttpSession session, @PathVariable String id ) {
		blogService.postAdd(postVo);
		
		//return "blog/blog-admin-write";
		return "redirect:/"+id+"/main";
	}
	

	@RequestMapping({"","/main","/","/{pathNo1}/","/{pathNo1}/{pathNo2}","/{pathNo1}/{pathNo2}/","/{pathNo1}"})
	public String adminMain(@PathVariable String id,
	         @PathVariable Optional<Long> pathNo1,
	         @PathVariable Optional<Long> pathNo2,
	         Model model) {
		
		PostVo postVo= blogService.postSelect(id, pathNo1, pathNo2);
		List listPostVo = blogService.getPostList(id, pathNo1);
		List listCategoryVo= blogService.getList(new UserVo(id, null));
		BlogVo blogVo = blogService.getBlogInfo(id);
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("postList", listPostVo);
		model.addAttribute("categoryList", listCategoryVo);
		model.addAttribute("blogInfo", blogVo);
		return "blog/blog-main";
	}
	
}

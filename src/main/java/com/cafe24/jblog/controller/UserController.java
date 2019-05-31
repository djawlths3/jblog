package com.cafe24.jblog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping( value="/join" , method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping( value="/join" , method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,BindingResult result, Model model) {
		userService.join(userVo);
		return "user/join";
	}
	
	@RequestMapping( "/joinsuccess" )
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping( value="/login" , method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}
	

	
}

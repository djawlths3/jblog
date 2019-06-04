package com.cafe24.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping( value="/join" , method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@RequestMapping( value="/join" , method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,BindingResult result, Model model) {	
		if(userService.valiCheck(result)) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping( "/joinsuccess" )
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping( value="/login" , method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}
	
	@ResponseBody
	@RequestMapping( value="/check" , method=RequestMethod.POST  )
	public JSONResult adminCategory(@RequestParam(value="id", required=true, defaultValue="") String id,
			 HttpSession session) {
		JSONResult result;
		if(userService.checkId(id)) {
			result = JSONResult.success("true");			
		} else {
			result = JSONResult.fail("false");
		}
		return result;
	}

}

package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.cafe24.jblog.dao.UserDao;
import com.cafe24.jblog.vo.UserVo;


@Service
public class UserService {

	@Autowired
	private UserDao userDao; 
	
	public Boolean join(UserVo userVo) {
		if(userDao.insertId(userVo)) {
			return userDao.insertBlog(userVo.getId());
		}
		return false;
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.login(userVo);
	}
	
	public boolean valiCheck(BindingResult result) {
		if(result.hasErrors()) {
			if( result.hasErrors() ) {
				List<ObjectError> list = result.getAllErrors();
				for(ObjectError error : list) {
					System.out.println(error);
				}				
				return true;
			}
		}
		return false;
	}
	
	public Boolean checkId(String id) {
		return userDao.checkId(id);
	}
}

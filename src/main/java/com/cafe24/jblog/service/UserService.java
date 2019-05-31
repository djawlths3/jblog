package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return userDao.login(userVo);
	}
}

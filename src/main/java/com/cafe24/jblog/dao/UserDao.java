package com.cafe24.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertId(UserVo vo) {
		int cnt = sqlSession.insert("user.insert",vo);
		return 1==cnt;
	}
	
	public Boolean insertBlog(String id) {
		int cnt = sqlSession.insert("blog.insert",id);
		return 1==cnt;
	}
	
	public UserVo login(UserVo vo) {		
		return sqlSession.selectOne("user.login",vo);
	}
	
	public Boolean checkId(String id) {		
		int ck =  sqlSession.selectOne("user.check",id);
		return ck > 0;
	}
}
	
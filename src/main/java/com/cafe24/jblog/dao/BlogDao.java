package com.cafe24.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean updateBlogInfo(BlogVo blogVo) {
		int num = sqlSession.update("blog.updateBlogInfo", blogVo);
		return (num > 0);
	}
	
	public Long categoryAdd(CategoryVo categoryVo) {
		sqlSession.insert("category.insert", categoryVo);	
		return categoryVo.getNo();
	}
	
	public boolean categoryDelete(Long no) {		
		int num = sqlSession.delete("category.delete", no);
		return (num > 0);
	}

	public List getList(UserVo userVo) {	
		return sqlSession.selectList("category.select", userVo);
	}
	
	public Long postAdd(PostVo postVo) {
		if(sqlSession.insert("post.insertPost", postVo) > 0) {
			sqlSession.update("category.updatePostCount",postVo);
		}
		return postVo.getNo();
	}

	public PostVo getPost(PostVo postVo) {
		return sqlSession.selectOne("post.getPost", postVo);
	}
	
	public PostVo getLatelyPost(PostVo postVo) {
		return sqlSession.selectOne("post.getLatelyPost", postVo);
	}
	
	public Long getLatelyPostNo(PostVo postVo) {
		return sqlSession.selectOne("post.getLatelyPostNo", postVo);
	}
	
	public Long getLatelycategoryNo(PostVo postVo) {
		return sqlSession.selectOne("category.getLatelycategoryNo", postVo);
	}
	
	public List getPostList(PostVo postVo) {
		return sqlSession.selectList("post.getPostList", postVo);
	}
	
	public BlogVo getBlogInfo(String id) {
		return sqlSession.selectOne("blog.getBlogInfo", id);
	}
}

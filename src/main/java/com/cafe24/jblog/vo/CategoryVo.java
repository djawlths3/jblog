package com.cafe24.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String explanation;
	private String regDate;
	private String blogId;
	private int postCount;
	private UserVo vo;
	public CategoryVo(String name, String explanation, UserVo vo) {
		setName(name);
		setExplanation(explanation);
		setBlogId(vo.getId());
	}
	
	
	
	public int getPostCount() {
		return postCount;
	}



	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}



	public CategoryVo() {

	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
	
	
}

package com.cafe24.jblog.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	@NotEmpty
	@Length(min=2,max=10)
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String pw;
	private String regDate;
	private String hostCheck;
	
	
	public String getHostCheck() {
		return hostCheck;
	}


	public void setHostCheck(String hostCheck) {
		this.hostCheck = hostCheck;
	}


	public UserVo() {
		
	}
	
	
	public UserVo(String id, String pw) {
		setId(id);
		setPw(pw);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	
	
}

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		 <div id="header">
		 <c:import url="/WEB-INF/views/include/blogHeader.jsp"></c:import>
		</div>
		 
		 
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${fn:replace(postVo.content,newline,'<br>') }
					<p>
				</div>
				<ul class="blog-list">
				<h3>글목록</h3>
				<hr>
					<c:forEach items='${postList }' var='vo' varStatus="index">
						<li><a href="${pageContext.request.contextPath}/${categoryList[0].blogId}/${vo.categoryNo}/${vo.no}">${vo.title}</a></li>							
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogInfo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items='${categoryList }' var='vo' varStatus="index">
						<li><a href="${pageContext.request.contextPath}/${vo.blogId}/${vo.no}/">${vo.name}</a></li>							
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>
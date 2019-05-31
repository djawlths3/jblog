<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">

<h1><a style="color: white;" href="${pageContext.servletContext.contextPath}/${authUser.id}">${blogInfo.title }</a></h1>
<ul class="menu">
	<c:choose>
		<c:when test='${empty authUser }'>
			<li><a
				href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/basic">블로그
					관리</a></li>
		</c:otherwise>
	</c:choose>
	<input type="hidden" name="sessionId" id='sessionId' value="${authUser.id}">

</ul>

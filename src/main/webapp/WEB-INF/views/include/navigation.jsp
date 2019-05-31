<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<ul class="admin-menu">
	<c:choose>
		<c:when test="${param.menu =='write'}">
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/basic">기본설정</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/category">카테고리</a></li>
			<li class="selected">글작성</li>
		</c:when>
		
		<c:when test="${param.menu =='basic'}">
			<li class="selected">기본설정</li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/category">카테고리</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/write">글작성</a></li>
		</c:when>
		
		<c:when test="${param.menu =='category'}">
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/basic">기본설정</a></li>
			<li class="selected">카테고리</li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id }/write">글작성</a></li>
		</c:when>
	</c:choose>
</ul>

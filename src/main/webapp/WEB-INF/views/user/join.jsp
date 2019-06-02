<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="${pageContext.servletContext.contextPath }/assets/js/check/check.js"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		
		<form:form modelAttribute="userVo" class="join-form" id="join-form" name="joinForm" method="post"
					action="${pageContext.servletContext.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input id ='name' path="name"/><p id='nameMsg' class='err-msg'>이름을 입력해주세요</p>
			<label class="block-label" for="id">아이디</label>
			<form:input id ='id' path="id"/>			
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.servletContext.contextPath}/assets/images/check.png">
			<p id='idMsg' class='err-msg'>아이디 길이는 2~10 사이입니다</p>
			<label class="block-label" for="password">패스워드</label>
			<form:input path="pw" id="pw" name="pw" type="password"/>
			<p id='pwMsg' class='err-msg'>비밀번호를 입력해주세요</p>	
			<!-- 
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>
 			-->
			<input id='submitBtn' type="submit" value="가입하기" disabled="">
			
			</form:form>

	</div>
</body>
</html>
<script>
$( document ).ready(function() {
	
    $('#btn-checkemail').click(function(){	
		let id = $('#id').val()
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/user/check", 
			type: "post", 
			dataType: "json",
			data: {id:id},
			success: function(result){
				if(result.result == 'fail'){
					$('#submitBtn').attr('disabled', false)
					$('#btn-checkemail').css('display','none')
					$('#img-checkemail').css('display','')
				} else {
					$('#idMsg').text('존재하는 아이디 입니다')
					$('#submitBtn').attr('disabled', true)
				}
			}
		})
		
    })
     $('#submitBtn').click(function(){	
    	 $('#id').val('')
    	 $('#name').val('')
    	 $('#pw').val('')
     })
   
    $('#name').change(function(){
    	checkName()
    })
    
    $('#id').change(function(){
    	 checkId()
    })
    
    $('#pw').change(function(){
    	checkPw()
    })
    

});
</script>

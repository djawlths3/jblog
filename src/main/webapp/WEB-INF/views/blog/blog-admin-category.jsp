<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/blogHeader.jsp"></c:import>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/include/navigation.jsp">
					<c:param name="menu" value="category" />
				</c:import>

				<table id='resultTable' class="admin-cat" val>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:set var='count' value="${fn:length(list) }" scope="page" />
						<c:forEach items='${list }' var='vo' varStatus="index">
							<tr value='${vo.no}'>
								<td>${count - index.index }</td>
								<td>${vo.name}</td>
								<td>${vo.postCount}</td>
								<td>${vo.explanation}</td>
								<td><img class='imgDelete'
									src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id='categoryName' name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id='explanation' name="desc"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id='categoryAdd' type="button" value="카테고리 추가"></td>
					</tr>
				</table>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>

<script>

$(document).on('click','.imgDelete',function(e){
	let _this = $(this);
	let no = _this.closest("tr")[0].attributes[0].value;
	let hostId = $('#sessionId').val();
	$.ajax({
		url : '/jblog/'+hostId+'/category',
		type : 'post',
		dataType : 'json',
		data : {
			no : no
		},
		success : function(result) {
			_this.closest("tr").remove();
		}
	})
})

	$('#categoryAdd').click(function(e) {
						let hostId = $('#sessionId').val();
						let categoryName = $('#categoryName').val();
						let explanation = $('#explanation').val();
						$.ajax({
									url : '/jblog/'+hostId+'/category/add',
									type : 'post',
									dataType : 'json',
									data : {
										name : categoryName,
										explanation : explanation
									},
									success : function(result) {
										let data = result.data;
										var row = '<tr value='+data.no+'><td>'
												+ $("#resultTable tr").size()
												+ '</td><td>'
												+ data.name
												+ '</td><td>0</td><td>'
												+ data.explanation
												+ '</td><td><img class="imgDelete" src="/jblog/assets/images/delete.jpg"></td></tr>';
										$('#resultTable').prepend(row);
										$('#categoryName').val('')
										$('#explanation').val('');
									}
								})
					})
</script>
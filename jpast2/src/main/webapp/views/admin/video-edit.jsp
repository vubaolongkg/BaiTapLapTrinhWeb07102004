<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>

<form action="${pageContext.request.contextPath}/admin/video/update"  method="post" enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" value="${cate.categoryId}" hidden="hidden"><br> 
	<label for="videoname">Category
		Name:</label><br> <input type="text" id="videoname"
		name="videoname" value="${cate.categoryname}"><br> <label
		for="images">Video</label><br>
	<c:if test="${cate.images.substring(0,5) !='https'}">
		<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${cate.images.substring(0,5) =='https'}">
		<c:url value="${cate.images}" var="imgUrl"></c:url>
	</c:if>
	<img  id="imagess" height="150" width="200" src="${imgUrl }" /> <input type="file"
		onchange="chooseFile(this)" id="images" name="images" value="${cate.images}"}> <br> <br>
	<label for="status">Status</label> <br> <input type="text"
		id="status" name="status" value="${cate.status}"> <br> <br>
	<input type="submit" value="Submit">
</form>


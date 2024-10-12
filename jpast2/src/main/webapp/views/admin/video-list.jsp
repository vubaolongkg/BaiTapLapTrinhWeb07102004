<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>

<a href="${pageContext.request.contextPath}/admin/video/add">Add Video</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>Video ID</th>
		<th>Video Name</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td><c:if test="${cate.images.substring(0,5) !='https'}">
					<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
				</c:if> <c:if test="${cate.images.substring(0,5) =='https'}">
					<c:url value="${cate.images}" var="imgUrl"></c:url>
				</c:if> <img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.categoryId }</td>
			<td>${cate.categoryname }</td>
			<td><c:if test="${cate.status==1}">
					<span>Hoạt động</span></td>
			</c:if>
			<c:if test="${cate.status!=1}">
				<span>Khóa</span>
				</td>
			</c:if>
			<td><a
				href="<c:url value='/admin/video/edit?id=${cate.categoryId }'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/video/delete?id=${cate.categoryId }'/>">Xóa</a></td>
		</tr>
	</c:forEach>

</table>
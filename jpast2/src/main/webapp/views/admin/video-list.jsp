<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video List</title>
    <!-- Thêm Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <a href="${pageContext.request.contextPath}/admin/video/add" class="btn btn-primary mb-3">Add Video</a>

    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th>STT</th>
            <th>VideoID</th>
            <th>Title</th>
            <th>Category</th>
            <th>Description</th>
            <th>Poster</th>
            <th>Views</th>
            <th>Active</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listvideo}" var="video" varStatus="STT">
            <tr>
                <td>${STT.index + 1}</td>
<%--                <td>--%>
<%--                    <c:if test="${video.images.substring(0, 5) != 'https'}">--%>
<%--                        <c:url value="/image?fname=${video.images}" var="imgUrl"></c:url>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${video.images.substring(0, 5) == 'https'}">--%>
<%--                        <c:url value="${video.images}" var="imgUrl"></c:url>--%>
<%--                    </c:if>--%>
<%--                    <img height="150" width="200" src="${imgUrl}" class="img-thumbnail" />--%>
<%--                </td>--%>
                <td>${video.videoId}</td>
                <td>${video.title}</td>
                <td>${video.category.categoryname}</td>
                <td>${video.description}</td>
                <td>${video.poster}</td>
                <td>${video.views}</td>
                <td>
                    <c:if test="${video.active == 1}">
                        <span class="badge badge-success">Hoạt động</span>
                    </c:if>
                    <c:if test="${video.active == 0}">
                        <span class="badge badge-danger">Khoá</span>
                    </c:if>
                </td>
                <td>
                    <a href="<c:url value='/admin/video/edit?id=${video.videoId}'/>" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="<c:url value='/admin/video/delete?id=${video.videoId}'/>" class="btn btn-danger btn-sm">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Thêm Bootstrap JS (tùy chọn) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

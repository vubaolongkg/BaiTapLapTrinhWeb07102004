<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
    <form action="${pageContext.request.contextPath}/admin/video/insert" method="post">
        <div class="mb-3">
            <label for="title" class="form-label
            ">Title:</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label
            ">Description:</label>
            <input type="text" class="form-control" id="description" name="description" required>
        </div>
        <div class="mb-3">
            <label for="poster" class="form-label
            ">Poster:</label>
            <input type="text" class="form-control" id="poster" name="poster" required>
        </div>

        <!-- category cho chọn -->
        <div class="mb-3">
            <label for="category" class="form-label">Category:</label>
            <select class="form-select" id="category" name="category">
               <c:forEach items="${listcate}" var="cate" varStatus="STT">
                    <option value="${cate.categoryId}">${cate.categoryname}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="active" class="form-label">Active:</label>
            <select class="form-select" id="active" name="active">
                <option value="1">Hoạt động</option>
                <option value="0">Khoá</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</form>

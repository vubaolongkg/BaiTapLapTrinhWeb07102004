<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
  <label for="categoryname">Category Name:</label><br>
  <input type="text" id="categoryname" name="categoryname"><br>
  <label for="images">Images</label><br>
  <img height="150" width="200" src="" id="imagess" />
  <input type="file" onchange="chooseFile(this)" id="images" name="images"/>
  <p>Status:</p>
  <input type="radio" id="ston" name="status" value="1" checked>
  <label for="html">Đang hoạt động</label> <br>
  <input type="radio" id="stoff" name="status" value="0">
  <label for="css">Khóa</label> <br>
  <input type="submit" value="Submit">
</form> 
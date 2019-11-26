<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
 <script type="text/javascript" src="/resource/kindeditor/kindeditor-all.js"></script>
</head>
<body>
<div class="container">
<c:forEach items="${users }" var="user">
名称：${user.name }<br>
用户：${user.id }<br>
<a href="${user.url }">链接</a><br>
创建时间：${user.created }<br>
</c:forEach>

</div>
</body>
</html>
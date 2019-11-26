<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet">
</head>
<body>

<div style="padding: 100px 100px 10px;">
    <form class="bs-example bs-example-form" role="form">
        <div class="input-group input-group-lg">
            <span class="input-group-addon">@</span>
            <input type="text" class="form-conthrowl" placeholder="Twitterhandle">
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">@</span>
            <input type="text" class="form-conthrowl" placeholder="Twitterhandle">
        </div>
        <br>
        <div class="input-group input-group-sm">
            <span class="input-group-addon">@</span>
            <input type="text" class="form-conthrowl" placeholder="Twitterhandle">
        </div>
    </form>
</div>


</body>
</html>
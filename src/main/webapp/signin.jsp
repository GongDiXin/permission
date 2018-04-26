<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<jsp:include page="${contextPath}/common/backend_common.jsp"/>
<html lang="zh-CN">
<%
    String ret = request.getParameter("ret");
    if(StringUtils.isNotBlank(ret)) {
        ret = URLEncoder.encode(ret);
    } else {
        ret = "";
    }
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">
    <title>登陆</title>
    <link href="${contextPath}/css/signin.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <form class="form-signin" action="${contextPath}/login.page?ret=<%=ret%>" method="post">
            <h2 class="form-signin-heading">请登陆</h2>
            <label for="inputEmail" class="sr-only">邮箱/电话</label>
            <input type="text" id="inputEmail" class="form-control" placeholder="Email/Telephone" name="username" required autofocus value="${username}">
            <label for="inputPassword" class="sr-only">密码</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required >
            <div class="checkbox" style="color: red;">${error}</div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">登 陆</button>
        </form>
    </div>
</body>
</html>

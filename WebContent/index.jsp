<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
      String path = request.getContextPath();
      String BasePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
  // response.sendRedirect(BasePath+"sm/goLoginPage");
  
%>
<form action=<%= BasePath+"phone/sendCode"%> method="post">
手机号：<input type="text" name="phone" /><br>
<input type="submit" value="发送"><br>

<a href="countSession"> 查看Session数量</a><br>
<div id="footer">
${numUser} user(s) are logged in!
</div>

<a href="listActiveUsers">查看活跃用户的信息</a>
<h1>Active Users</h1>
<ul>
<c:forEach items="${activeUsers}" var="uinfo">
<li><strong>${uinfo.key.username}</strong>
/ Last Active: <strong>${uinfo.value}</strong></li>
</c:forEach>
</ul>
 </form>
</body>
</html>
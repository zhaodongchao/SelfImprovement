<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%
      String path = request.getContextPath();
      String BasePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统登陆与注册</title>
<link rel="stylesheet" type="text/css" href=<%=BasePath + "plugins/ext3/resources/css/ext-all.css"%> />
<link rel="stylesheet" type="text/css" href=<%=BasePath + "css/login.css"%> />

</head>
<body>
<script type="text/javascript" src=<%=BasePath + "plugins/ext3/adapter/ext/ext-base.js"%>></script>
<script type="text/javascript" src=<%=BasePath + "plugins/ext3/ext-all.js"%>></script>

<script type="text/javascript" src=<%=BasePath+"sm/login/js/LoginWindow.js"%>></script>
<script type="text/javascript" src=<%=BasePath+"sm/login/js/login.js"%>></script>

<input type="hidden" id="BasePath" value=<%=BasePath %>>
</body>
</html>
<%@ page language="java" import="java.util.*"   contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
      String path = request.getContextPath();
      String BasePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统主界面</title>
<link rel="stylesheet" type="text/css" href=<%=BasePath + "plugins/ext3/resources/css/ext-all.css"%> />
</head>
<body>
<script type="text/javascript" src=<%=BasePath + "plugins/ext3/adapter/ext/ext-base.js"%>></script>
<script type="text/javascript"src=<%=BasePath + "plugins/ext3/ext-all.js"%>> </script>
<script type="text/javascript" src=<%=BasePath+"sm/login/js/main.js" %>></script>
<script type="text/javascript" src=<%=BasePath+"sm/login/js/MainPanel.js" %>></script>
<script type="text/javascript" src=<%=BasePath+"sm/login/js/CenterTabPanel.js" %>></script>
<script type="text/javascript" src=<%=BasePath+"sm/login/js/LeftMenu.js" %>></script>


<input type="hidden" id="BasePath" value=<%=BasePath %>>
</body>
</html>
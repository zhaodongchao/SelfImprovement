<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>   
     <%
      String path = request.getContextPath();
      String BasePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href=<%=BasePath + "plugins/ext3/resources/css/ext-all.css"%> />
<link rel="stylesheet" type="text/css" href=<%=BasePath + "css/images.css"%> />
<link rel="stylesheet" type="text/css" href=<%=BasePath + "plugins/ext3/examples/ux/css/RowEditor.css"%> />

<title>菜单管理</title>
<script type="text/javascript" src=<%=BasePath + "plugins/ext3/adapter/ext/ext-base.js"%>></script>
<script type="text/javascript"src=<%=BasePath + "plugins/ext3/ext-all.js"%>> </script>
<script type="text/javascript"src=<%=BasePath + "plugins/ext3/examples/ux/RowEditor.js"%>> </script>

<script type="text/javascript" src=<%=BasePath+"sm/menuManager/scripts/MainPanel.js" %>></script> 
<script type="text/javascript" src=<%=BasePath+"sm/menuManager/scripts/MenuManager.js" %>></script>

	<style type="text/css">
		.x-grid3 .x-window-ml{
			padding-left: 0;	
		} 
		.x-grid3 .x-window-mr {
			padding-right: 0;
		} 
		.x-grid3 .x-window-tl {
			padding-left: 0;
		} 
		.x-grid3 .x-window-tr {
			padding-right: 0;
		} 
		.x-grid3 .x-window-tc .x-window-header {
			height: 3px;
			padding:0;
			overflow:hidden;
		} 
		.x-grid3 .x-window-mc {
			border-width: 0;
			background: #cdd9e8;
		} 
		.x-grid3 .x-window-bl {
			padding-left: 0;
		} 
		.x-grid3 .x-window-br {
			padding-right: 0;
		}
		.x-grid3 .x-panel-btns {
			padding:0;
		}
		.x-grid3 .x-panel-btns td.x-toolbar-cell {
			padding:3px 3px 0;
		}
		.x-box-inner {
			zoom:1;
		}
        .icon-user-add {
            background-image: url(../shared/icons/fam/user_add.gif) !important;
        }
        .icon-user-delete {
            background-image: url(../shared/icons/fam/user_delete.gif) !important;
        }        
    </style>
    
</head>
<body >
          <input type="hidden" id="BasePath" value=<%=BasePath %>></input>  
          <!-- SpringSecurity标签 ,security之authentication标签用于获取当前认证管理器里面的用户信息，principal里面包含登陆用户的 所有信息--> 
          <input type="hidden" id="userId" value=" <security:authentication property="principal.user_id"/>"/>

		 <div id="loading"  class="loading-indicator">
		    <img src=<%=BasePath + "images/wait.gif"%>
		    	width="80"
		    	height="80"
		    	style="margin-right:8px;"
		    	align="middle"/>页面加载中...
		    </div>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>仲恺论坛平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="/wirelessplatform/board.html" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="keyword" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>用户名</td>
				<td>姓名</td>
				<td>邮箱</td>
				<td>专业</td>
				<td>手机</td>
				<td>班级</td>
				<td>头像</td>
				<td>用户状态</td>
				<td>用户角色</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:forEach items="${userList }" var="user">
			<tr class="TableDetail1">
				<td align="center">${user.username }&nbsp;</td>
				<td align="center">${user.name }&nbsp;</td>
				<td align="center">${user.email }</td>
				<td align="center">${user.major }</td>
				<td align="center">${user.telephone }</td>
				<td align="center">${user.className }</td>
				<td align="center"><img src="/photo/${user.photoPath}" width=100 height=120/></td>
				<td align="center">${user.status }</td>
				<td align="center">${user.roleStatus }</td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=1&isBook=0" class="FunctionButton">新增</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=1" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        </c:forEach>
			<!-- <tr class="TableDetail1">
				<td align="center">2&nbsp;</td>
				<td align="center"> 巴黎&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=2&isBook=1" class="FunctionButton">预定</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=2" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">3&nbsp;</td>
				<td align="center"> 丹麦&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=3&isBook=1" class="FunctionButton">预定</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=3" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">5&nbsp;</td>
				<td align="center"> 伦敦&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=5&isBook=1" class="FunctionButton">预定</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=5" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr> -->
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/ZHKUCommunity/user/addUser.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>

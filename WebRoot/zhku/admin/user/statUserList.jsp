<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>角色管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-datetimepicker.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/ckform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>

  </head>
  
  <body>
<%-- <form class="form-inline definewidth m20" action="${pageContext.request.contextPath}/user/queryUser.action" method="get">  
   用户名：
    <input type="text" name="username" class="abc input-default" placeholder="输入用户名" value="">&nbsp;&nbsp; 
 用户状态： 
 	<select name="status">
 		<option value="-1">全部</option>
 		<option value="1">启用</option>
 		<option value="0">禁用</option>
 	</select>
    用户角色： 
    <select name="roleStatus">
 		<option value="-1">全部</option>
 		<option value="1">普通用户</option>
 		<option value="2">超级管理员</option>
 	</select>
    
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <!-- <button type="button" class="btn btn-success" id="addnew">新增商品</button> -->
</form> --%>
<table class="table table-bordered table-hover definewidth m10" >
	<tr><td>角色列表</td></tr>
</table>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>角色</th>
        <th>人数</th>
		<!-- <th>有效人数</th>
		<th>无效人数</th> -->
		
    </tr>
    </thead>
    <c:forEach items="${maps }" var="map">
	     <tr>
	     	<td>
	     		<c:choose>
            		<c:when test="${map.role==1 }">普通用户</c:when>
            		<c:otherwise>超级管理员</c:otherwise>
            	</c:choose>
	     	</td>
            <td>${map.count }</td>
        </tr>
     </c:forEach>
        </table>

</body>	
<script>
    $(function () {
        
    });
		/* $('#close').click(function(){

				window.location.href="${pageContext.request.contextPath}/user/unableUser.action?uid="+${user.id };
		 });
		
		$('#open').click(function(){

			window.location.href="${pageContext.request.contextPath}/user/enableUser.action?uid="+${user.id };
	 	});
		
		$('#del').click(function(){

			window.location.href="${pageContext.request.contextPath}/user/deleteUser.action?uid="+${user.id };
	 	}); */
    	function unable(id)
    	{
	 		if(confirm("确定要禁用吗？"))
			{
    			var url = "${pageContext.request.contextPath}/user/unableUser.action?uid="+id;
    			window.location.href=url;
			}
    	};
    	
    	function enable(id)
    	{
    		if(confirm("确定要启用吗？"))
			{
    			var url = "${pageContext.request.contextPath}/user/enableUser.action?uid="+id;
    			window.location.href=url;	
			}
    	};
    	
    	function del(id)
    	{
    		if(confirm("确定要删除吗？"))
			{
    			var url = "${pageContext.request.contextPath}/user/deleteUser.action?uid="+id;
    			window.location.href=url;	
			}
    	};


    

	/* function unable(id)
	{
			var url = "${pageContext.request.contextPath}/user/unableUser.action?uid="+id;
			window.location.href=url;		
	};
	
	function enable(id)
	{
			var url = "${pageContext.request.contextPath}/user/enableUser.action?uid="+id;
			window.location.href=url;		
	};
	
	function del(id)
	{
			var url = "${pageContext.request.contextPath}/user/deleteUser.action?uid="+id;
			window.location.href=url;		
	}; */
	
	$("#pdateStart").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        //startDate:new Date()
    }).on("click",function(){
        $("#pdateStart").datetimepicker("setEndDate",$("#pdateEnd").val());
    });
    $("#pdateEnd").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        //startDate:new Date()
    }).on("click",function(){
        $("#pdateEnd").datetimepicker("setStartDate",$("#pdateStart".val()));
    });
	
</script>
</html>

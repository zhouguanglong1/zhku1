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
    <title>帖子管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/ckform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/common.js"></script>

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
<form class="form-inline definewidth m20" action="${pageContext.request.contextPath}/section/querySection.action" method="get">  
    帖子id：
    <input type="text" name="sid" class="abc input-default" placeholder="输入要查询的id" value="">&nbsp;&nbsp; 
  帖子名称：  
    <input type="text" name="sectionName" id="name"class="abc input-default" placeholder="输入名称" value="">&nbsp;&nbsp;
 帖子描述:     
    <input type="text" name="remark" id="description"class="abc input-default" placeholder="输入描述" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <!-- <button type="button" class="btn btn-success" id="addnew">新增版块</button> -->
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>id</th>
        <th>帖子名称</th>
        <th>发布时间</th>
        <th>修改时间</th>
       <!--  
         -->
         <th>所属版块</th>
         <th>所属用户</th>
        <th>帖子状态</th>
        <th>管理操作</th> 
    </tr>
    </thead>
    <c:forEach items="${pageBean.list }" var="post">
	     <tr>
            <td>${post.id }</td>
            <td>${post.postName }</td> 
            
            <td>${post.publishTime }</td> 
            <td>
            	${post.modifyTime }
            </td> 
            <%--   --%>
            <td>${post.section.sectionName }</td>
            <td>${post.user.username }</td> 
            <!-- <td>1</td> -->
            <td>
            	<c:choose>
            		<c:when test="${post.status==1 }">
            			有效
            		</c:when>
            		<c:otherwise>
            			无效
            		</c:otherwise>
            	</c:choose>
            	
            </td> 
            <td>
                  <%-- <a href="${pageContext.request.contextPath}/section/findSection.action?sid=${section.id}">编辑</a>&nbsp;&nbsp;&nbsp; --%>
                  <button type="button" class="btn btn-danger" id="del" onclick="del(${post.id });">删除</button>
                  <%-- <a href="${pageContext.request.contextPath}/section/deleteSection.action?sid=${section.id}"><font color="red">删除</font></a> --%>
            </td>
        </tr>
     </c:forEach>
        </table>
<div class="inline pull-right page">
        <!--  10122 条记录 1/507 页  <a href='#'>下一页</a>     <span class='current'>1</span><a href='#'>2</a><a href='/chinapost/index.php?m=Label&a=index&p=3'>3</a><a href='#'>4</a><a href='#'>5</a>  <a href='#' >下5页</a> <a href='#' >最后一页</a>     -->
	<a href = "${pageContext.request.contextPath}/post/adminPostList.action?page=1" >首页</a>
	    	<c:choose>
	    		<c:when test="${pageBean.page gt 1 }">
	    			<a href = "${pageContext.request.contextPath}/post/adminPostList.action?page=${pageBean.page -1} " >上一页</a> 
	    		</c:when>
	    		<c:otherwise>
	    			上一页
	    		</c:otherwise>
	    	</c:choose>
	    	<c:choose>
	    		<c:when test="${pageBean.totalPage gt 1 && pageBean.page ne pageBean.totalPage}">
	    			<a href = "${pageContext.request.contextPath}/post/adminPostList.action?page=${pageBean.page +1} " >下一页</a>
	    		</c:when>
	    		<c:otherwise>
	    			下一页
	    		</c:otherwise>
	    	</c:choose>
	    	
			<a href = "${pageContext.request.contextPath}/post/adminPostList.action?page=${pageBean.totalPage}" >尾页</a>  
			第<span class='current'>${pageBean.page}</span>页/共${pageBean.totalPage}页 
</div>
</body>	
<script>
    $(function () {
        
		$('#addnew').click(function(){

				window.location.href="${pageContext.request.contextPath}/zhku/admin/forum/addZone.jsp";
		 });


    });

	function del(id)
	{
		
		
		if(confirm("确定要删除吗？"))
		{
		
			var url = "${pageContext.request.contextPath}/post/adminPostSection.action?pid="+id;
			
			window.location.href=url;		
		
		}
	
	
	
	
	}
</script>
</html>

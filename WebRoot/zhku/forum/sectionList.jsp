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
    
    <title>版面列表</title>
    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index1.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tribune.css"/>
    <link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/common1.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/ckform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/common.js"></script>
  </head>
  
  <body>
  
  <header class="zhku-nav ">
    <nav class="navber w">
        <a href="#" class="f_l" title="zhkw" target="_blank">
            <img src="${pageContext.request.contextPath}/images/logo1.png" alt=""/>
        </a>
        <div class="n-sign f_l">
            <ul class="">
                <li class="active">
                    <a href="${pageContext.request.contextPath}/index.jsp">
                        首页
                    </a>
                </li>
                <%-- <li>
                    <a href="${pageContext.request.contextPath}/zhku/sys/bbs.jsp">
                        论坛
                    </a>
                </li> --%>
                
                <li>
                    <a href="${pageContext.request.contextPath}/zhku/sys/zhkuStyle.jsp">
                       仲园风采
                    </a>
                </li>
                <li>
                    <a href="<%=path%>/category/categoryList.action?page=1">
                        仲园咸鱼
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/zhku/sys/board.jsp">
                       论坛
                    </a>
                </li>
                <!-- <li>
                    <a href="#">仲园咸鱼</a>
                </li> -->
            </ul>
            <em style="left: 20.5px; display: inline;"></em>
        </div>
        <div class="n-search f_r">
            <div class="input f_l">
                <a href="javascript:;" id="scbar_type" class="xg1" hidefocus="true">搜索</a>
                <input type="text" name="srchtxt" value="请输入搜索内容"  class=" xg1" placeholder="请输入搜索内容">
                <button type="submit" name="searchsubmit" class="pn pnc" value="true"><strong class="xi2"></strong></button>
            </div>
            
            <c:if test="${sessionScope.username == null }"> 
            <div class="text f_l" id="login">
                <a href="${pageContext.request.contextPath}/zhku/login.jsp">登录</a>
                <span></span>
                <a href="${pageContext.request.contextPath}/zhku/register.jsp">注册</a>
            </div>
            </c:if>
            <c:if test="${ sessionScope.username != null}">
            <div id="username">
            	<tr>
            		<td>
		            	<font color="white">欢迎您，</font>
		            	<span></span>
		            	<font color="red">${sessionScope.username}</font>
	            	</td>
	            	&nbsp;
	            	<td>
	            		<a href="${pageContext.request.contextPath}/zhku/modify.jsp"><font color="white">修改</font></a>
	            	</td>
	            	&nbsp;
	            	<td>
	            		<a href="${pageContext.request.contextPath}/user/quit.action"><font color="white">退出</font></a>
	            	</td>
            	</tr>
            </div>
            </c:if>
            
        </div>
    </nav>
</header>

<div class="wu">
<div class="tribune-table w">
    <ul class="f_l">
       	<li class="aa"><span>&nbsp;&nbsp;&nbsp;今日：<font color="red">${todayCount }</font></span></li>
        <li class="bb"><span>&nbsp;&nbsp;&nbsp;昨日：<font color="red">${yestodayCount }</font></span></li>
        <li class="cc"><span>&nbsp;&nbsp;&nbsp;帖子：<font color="red">${allCount }</font></span></li>
        <li class="dd"><span>&nbsp;&nbsp;&nbsp;会员：<font color="red">${userCount }</font></span></li>
    </ul>
    <%-- <div class="day f_r">
        <a href="${pageContext.request.contextPath}/post/QueryBeforeAdd.action"><img src="${pageContext.request.contextPath}/images/3232.bmp" alt=""/></a>
    </div> --%>
</div>
</div>


  <table class="table table-bordered table-hover definewidth m10" border="0" frame="void">
<tr>
	<td>版块</td><td style="color: red;font-size: 30">${z.name }</td>
	<%-- <td>订单号为：${order.orderNum }</td> --%>
</tr>
</table>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
       <th>版面名称</th>
        <th>版主</th>
        <th>所属版块</th>
        <th>描述</th>
        <th>状态</th>
        <th>版面图片路径</th>
        <th>操作</th> 
    </tr>
    </thead>
    <c:forEach items="${sectionList }" var="section"> 
	     <tr>
            <td><a href="${pageContext.request.contextPath}/section/sectionPostList.action?sid=${section.id}&page=1">${section.sectionName }</a></td>
            <td>${section.master.username }</td>
            <td>${section.zone.name }</td>
            <td>${section.remark }</td>
            <td>
            	<c:choose>
            		<c:when test="${section.status == 1}">
            			有效
            		</c:when>
            		<c:otherwise>
            			失效
            		</c:otherwise>
            	</c:choose>
            </td>
            <td><img src="/photo/${section.sectionImgPath }"/></td>
            <td>
                 
                  <c:choose>
            		<c:when test="${username == section.master.username}">
            			<button type="button" class="btn btn-info" id="update" onclick="update(${section.id});">修改</button> 
            			<button type="button" class="btn btn-danger" id="del" onclick="del(${section.id});">删除</button> 
            		</c:when>
            		<c:otherwise>
            			您无权对此版面进行操作
            		</c:otherwise>
            	</c:choose>
                  
                 <%--  <a href="${pageContext.request.contextPath}/category/deleteCategory.action?cid=${category.id}"><font color="red">删除</font></a> --%>
            </td>
        </tr>
      </c:forEach>
        </table>
      
        
        
        <!-- end -->
  
		
		
	
	  <!--底部-->
<div class="zhkufloor w">
    <div class="zhku-fleft f_l">
        <!-- <p>Archiver | 手机版 | 小黑屋 | vivo智能手机V粉社区</p> -->
        <p>COPYRIGHT © 2017  COMMUNICATION TECHNOLOGY CO.LTD.</p>
        <p>ALL RIGHTS RESERVED. 粤ICP备25070663号</p>
        <!-- <p>GMT+8, 2016-12-23 23:06, Processed in 0.033098 second(s), 1 queries
            , Gzip On, Redis On.</p> -->
    </div>
    <ul class="f_r">
        <li class="ll">联系我们:</li>
        <li class="hh"></li>
        <li class="ii"></li>
        <li class="jj"></li>
    </ul>

</div>
	
  </body>
  

<!--jq-->
<script src="${pageContext.request.contextPath}/js/lib/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/template-native.js"></script>
<script src="${pageContext.request.contextPath}/js/tribune.js"></script>
<script type="text/javascript">
	function del(id){
		window.location.href="${pageContext.request.contextPath}/section/deleteSection.action?sid="+id;
	};

	function update(id){
		window.location.href='${pageContext.request.contextPath}/section/queryBeforeUpdate.action?sid='+id;
	};

</script>
<script src="${pageContext.request.contextPath}/js/lib/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/template-native.js"></script>
<script src="${pageContext.request.contextPath}/js/tribune.js"></script>  
  
  
</html>

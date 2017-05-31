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
    <title>订单管理</title>
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



<table class="table table-bordered table-hover definewidth m10" >
	<tr><td>欢迎您,<font color="red">${sessionScope.username }</font>。您的订单列表如下：</td></tr>
</table>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <!-- <th>id</th>
        <th>分类名称</th> -->
        <!-- <th>状态</th>-->
        
        <th>订单号</th>
        <th>用户名</th>
		<th>联系电话</th>
		<th>地址</th>
		<th>订单时间</th>
		<th>订单金额</th>
		<th>订单状态</th>
    </tr>
    </thead>
    <c:forEach items="${orderList  }" var="order">
	     <tr>
	     	<td><font color="red">${order.orderNum }</font></td>
            <td>${order.name }</td>
            <td>${order.phone }</td>
            <td>${order.addr }</td>
            <td>${order.ordertime }</td>
            <td>${order.total }</td>
            <!-- <td>1</td> -->
            <td>
            	<c:choose>
            		<c:when test="${order.state == 1 }">
            			待付款,<a href="${pageContext.request.contextPath}/order/payOrder.action?oid=${order.id}">付款</a>
            		</c:when>
            		<c:when test="${order.state == 2 }">
            			已付款，待卖家发货
            		</c:when>
            		<c:when test="${order.state == 3 }">
            			已发货，<a href="${pageContext.request.contextPath}/order/sureOrder.action?oid=${order.id}">确认收货</a>
            		</c:when>
            		<c:otherwise>
            			已完结
            		</c:otherwise>
            	</c:choose>
                  
                  <%-- <button type="button" class="btn btn-warning" id="del" onclick="del(${category.id });">删除</button> --%>
                  <a href="${pageContext.request.contextPath}/order/deleteOrder.action?pid=${order.id}&page=${pageBean.page}"><font color="red">删除</font></a>
            </td>
        </tr>
     </c:forEach>
        </table>

</body>	
  <script src="${pageContext.request.contextPath}/js/lib/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/template-native.js"></script>
<script src="${pageContext.request.contextPath}/js/tribune.js"></script>
</html>

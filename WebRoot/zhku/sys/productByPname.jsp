<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title>模糊查询商品</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index1.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tribune.css"/>
    <link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/common1.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!--头部-->
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
<!-- 主要内容 -->
<span style="font-size: 10px">&nbsp;</span>
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<!-- 一级分类显示 -->
				<c:forEach items="${ sessionScope.categoryList}" var="category">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product/findByCid.action?cid=${category.id }&page=1">${category.cname }</a>
							</dt>
								<!-- 显示二级分类 -->
								<c:forEach items="${category.categorySeconds }" var="categorySecond" >
									<dd>
										<a href="${pageContext.request.contextPath}/product/findByCsid.action?csid=${categorySecond.id }&page=1">${categorySecond.csname }</a>
									</dd>
								</c:forEach>
						</dl>
				</c:forEach>
			</div>
		</div>
		<div class="span18 last">
			
					
				<div id="result" class="result table clearfix">
						<ul>
						<c:forEach items="${productList1 }" var="product">
						<li>
							<a href="${pageContext.request.contextPath}/product/findByPid.action?pid=${product.id}">
								<img src="/photo/${product.image}" width="170" height="170"  style="display: inline-block;">
								   
								<span style='color:green'>
								 ${product.pname }
								</span>
								 
								<span class="price">
									商城价： ￥${product.shop_price }
								</span>
								 
							</a>
						</li>
						</c:forEach>	
						</ul>
				</div>
	<div class="pagination">
		第  ${pageBean1.page }/${pageBean1.totalPage }页
			<%-- <c:if test="${pageBean.page ne 1} ">
				<a href="${ pageContext.request.contextPath }/product/findByCid.action?cid=${category.id }&page=1" class="firstPage">&nbsp;</a>		
				<a href="${ pageContext.request.contextPath }/product/findByCid.action?cid=${category.id }&page=${pageBean.page-1 } class="previousPage">&nbsp;</a>	
			</c:if>	 --%>
			
			
			<a href = "${pageContext.request.contextPath}/product/findByCid.action?cid=${categoryId }&page=1" >首页</a>
    	<c:choose>
    		<c:when test="${pageBean1.page gt 1 }">
    			<a href = "${pageContext.request.contextPath}/product/findByCid.action?cid=${categoryId }&page=${pageBean1.page -1} " >上一页</a> 
    		</c:when>
    		<c:otherwise>
    			&nbsp;
    		</c:otherwise>
    	</c:choose>
    	<c:choose>
    		<c:when test="${pageBean1.totalPage gt 1 && pageBean1.page ne pageBean1.totalPage}">
    			<a href = "${pageContext.request.contextPath}/product/findByCid.action?cid=${categoryId }&page=${pageBean1.page +1} " >下一页</a>
    		</c:when>
    		<c:otherwise>
    			&nbsp;
    		</c:otherwise>
    	</c:choose>
    	
		<a href = "${pageContext.request.contextPath}/product/findByCid.action?cid=${categoryId }&page=${pageBean1.totalPage }">尾页</a> 
			
			
			
			
	</div>
		</div>
	</div>
<!-- 主要内容 -->

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
<!--框架-->
<!--<script src="${pageContext.request.contextPath}/js/lib/bootstrap/js/bootstrap.js"></script>-->
<!--渲染模板   在ajax那几天讲的-->
<script src="${pageContext.request.contextPath}/js/template-native.js"></script>
<!--另一个渲染模板的包-->
<!--<script src="${pageContext.request.contextPath}/js/underscore-min.js"></script>-->
<!--可拽动-->
<!--<script src="${pageContext.request.contextPath}/js/swipe.js"></script>-->
<!--index-->
<!--<script src="${pageContext.request.contextPath}/js/index.js"></script>-->
<script src="${pageContext.request.contextPath}/js/tribune.js"></script>
</html>

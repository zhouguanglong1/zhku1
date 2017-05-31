<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String today = dateFormat.format(new Date());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tribune.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3.css"/>
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
                    <a href="${pageContext.request.contextPath}/zone/zoneIndex.action">
                       论坛
                    </a>
                </li>
                <!-- <li>
                    <a href="#">
                        仲园咸鱼
                    </a>
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
<!--图标导航-->
<div class="wu">
<div class="tribune-table w">
    <ul class="f_l">
        <li class="aa"><span>&nbsp;&nbsp;&nbsp;今日：<font color="red">${todayCount }</font></span></li>
        <li class="bb"><span>&nbsp;&nbsp;&nbsp;昨日：<font color="red">${yestodayCount }</font></span></li>
        <li class="cc"><span>&nbsp;&nbsp;&nbsp;帖子：<font color="red">${allCount }</font></span></li>
        <li class="dd"><span>&nbsp;&nbsp;&nbsp;会员：<font color="red">${userCount }</font></span></li>
    </ul>
    <div class="day f_r">
        <a href="${pageContext.request.contextPath}/post/QueryBeforeAdd.action"><img src="${pageContext.request.contextPath}/images/left.png" alt=""/></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <img src="${pageContext.request.contextPath}/images/right1.png" alt="" onclick="myPost()"/><%-- <a href="${pageContext.request.contextPath}/post/postListByUser.action"></a> --%>
    </div>
</div>
</div>
<!--主体部分-->
<div class="w clearfix">
    <div class="center f_l">
        <div class="centert">所有版块</div>
        <div class="centerb">
            <ul>
            	<c:forEach items="${zoneList }" var="zone">
                <li>
                    <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=${zone.id}"><img src="${pageContext.request.contextPath}/images/77.gif" alt=""/></a>
                    <div>
                        <h3><a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=${zone.id}">${zone.name }</a></h3>
                        <p>主题: 3, 帖数: 15</p>
                        <p>最后发表: <%= today%></p>
                    </div>
                </li>
                </c:forEach>
                <%-- <li>
                    <a href=""><img src="${pageContext.request.contextPath}/images/113.gif" alt=""/></a>
                    <div>
                        <h3><a href="">资源共享</a></h3>
                        <p>主题: 16万, 帖数: 1113万</p>
                        <p>最后发表: 2016-12-22 18:52</p>
                    </div>
                </li>
                <li>
                    <a href=""><img src="${pageContext.request.contextPath}/images/121.gif" alt=""/></a>
                    <div>
                        <h3><a href="">玩机技巧</a></h3>
                        <p>主题: 2万, 帖数: 153万</p>
                        <p>最后发表: 2016-12-4 12:23</p>
                    </div>
                </li>
                <li>
                    <a href=""><img src="${pageContext.request.contextPath}/images/140.gif" alt=""/></a>
                    <div>
                        <h3><a href="">热门吐槽</a></h3>
                        <p>主题: 22万, 帖数: 815万</p>
                        <p>最后发表: 2016-11-30 11:20</p>
                    </div>
                </li> --%>
            </ul>
        </div>
    </div>

   <div class="right f_r">
       <div class="rightt">社区活动</div>
       <div class="rightb">
           <ul>
               <li><img src="${pageContext.request.contextPath}/images/3r1.jpg" alt=""/></li>
               <li><img src="${pageContext.request.contextPath}/images/3r2.jpg" alt=""/></li>
               <li><img src="${pageContext.request.contextPath}/images/3r3.jpg" alt=""/></li>
           </ul>
       </div>
   </div>
</div>



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
<script src="${pageContext.request.contextPath}/js/3.js"></script>
<script type="text/javascript">
	function myPost(){
		if(${sessionScope.username == null}){
			alert("您还没有登录，请先去登录！");
			return false;
		}
		window.location.href='${pageContext.request.contextPath}/post/postListByUser.action';
	};
</script>
</html>

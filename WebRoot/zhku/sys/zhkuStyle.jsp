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
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tribune.css"/>
    <style type="text/css">
		/* blue */ 
		.blue { 
		color: #d9eef7; 
		border: solid 1px #0076a3; 
		background: #0095cd; 
		background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5)); 
		background: -moz-linear-gradient(top, #00adee, #0078a5); 
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#0078a5'); 
		} 
		.blue:hover { 
		background: #007ead; 
		background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e)); 
		background: -moz-linear-gradient(top, #0095cc, #00678e); 
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0095cc', endColorstr='#00678e'); 
		} 
		.blue:active { 
		color: #80bed6; 
		background: -webkit-gradient(linear, left top, left bottom, from(#0078a5), to(#00adee)); 
		background: -moz-linear-gradient(top, #0078a5, #00adee); 
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0078a5', endColorstr='#00adee'); 
		} 
		</style> 
  <%--   
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-responsive.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script> --%>
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
                    <a href="${pageContext.request.contextPath}/category/categoryList.action?page=1">
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
            <div class="text f_l">
                <a href="${pageContext.request.contextPath}/zhku/login.jsp">登录</a>
                <span></span>
                <a href="${pageContext.request.contextPath}/zhku/register.jsp">注册</a>
            </div>
        </div>
    </nav>
</header>
<!--编辑-->
<div class="zhku-edit w">
    <div class="zhkuleft f_l">
        <div class="zhkutop">
        	仲恺一角
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<button type="button" onclick="" class="blue">
        		更多
        	</button>
        </div>
        <div class="zhkubottom">
       		<%-- <c:forEach items="${twoPostList }" var="post">
       			<div class="zhkuone">
                <a href=""><img class="f_l" src="/photo/${post.section.sectionImgPath }" alt=""/></a>
                <div class="f_l">
                    <a href=""><h3>${post.section.sectionName} | ${post.postName }</h3></a>
                    <div><i class="aa">2</i><i class="bb">3</i><i class="cc">${post.user.username }</i></div>
                    <a href=""><p>${post.content }</p></a>
                </div>
            </div>
       		</c:forEach> --%>
       		<c:forEach items="${sceneryList }" var="scenery">
       			<div class="zhkuone">
                <a href=""><img style="width:360px;height:210px" class="f_l" src="/photo/${scenery.photoPath }" alt=""/></a>
                <div class="f_l">
                    <a href=""><h3>${scenery.sname }</h3></a>
                    
                    <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><p>${scenery.remark }</p></a>
                </div>
            </div>
       		</c:forEach>
            <div class="zhkuone">
                <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><img style="width:360px;height:210px" class="f_l" src="${pageContext.request.contextPath}/images/duantui.jpg" alt=""/></a>
                <div class="f_l">
                    <a href=""><h3>井盖上的短腿猫</h3></a>
                    
                    <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><p>这只短腿猫每天的生活就是，吃完睡，睡完吃。观察到它是炸毛的猫，品种奇特，深得A栋127同学的喜欢。</p></a>
                </div>
            </div>
            <div class="zhkutwo">
                <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><img class="f_l" style="width:360px;height:210px" src="${pageContext.request.contextPath}/images/zhujiang.jpg" alt=""/></a>
                <div class="f_l">
                    <a href=""><h3>静谧的珠江，耀眼的海印桥</h3></a>
                    
                    <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=5"><p>或许，大学期间最幸福的事情，就是在闲暇时间在珠江边散步，看着美丽的海印桥，感受那一份美好。</p></a>
                </div>
            </div>
            
        </div>
        
        
    </div>
    <div class="zhkuright f_r">
        <!-- <div class="zhkutop">About Me</div> -->
        <div class="zhkuone">
        	About Me
        	<%-- <img src="${pageContext.request.contextPath}/images/right1.png" alt="" onclick="myPost()"/> --%>
            <!-- <button type="button" class="btn btn-primary" id="share">我要分享</button>&nbsp;&nbsp;
            <button type="button" class="btn btn-success" id="myshare">我的分享</button> -->
        </div>
        <div class="zhkutwo">
        	<br />
        	<a href="${pageContext.request.contextPath}/zhku/forum/addScenery.jsp"><img src="${pageContext.request.contextPath}/images/share1.jpg" style="margin-left: 15px;" alt=""/></a>
        	<br />
        	<a href=""><img src="${pageContext.request.contextPath}/images/share2.jpg" style="margin-left: 15px;" alt="" onclick="myPost()"/></a>
        </div>
        <%-- <div class="zhkuthree">
            	仲恺论坛版块
        </div>
        <div class="zhkufour">
            <ul>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=1"><li class="ff">资源共享</li></a>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=2"><li class="gg">爱我仲园</li></a>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=3"><li class="dd">热门吐槽</li></a>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=4"><li class="ee">玩机技巧</li></a>
            </ul>
        </div> --%>
    </div>
</div>
<!--底部-->
<div class="zhkufloor w">
    <div class="zhku-fleft f_l">
        <p>COPYRIGHT © 2017  COMMUNICATION TECHNOLOGY CO.LTD.</p>
        <p>ALL RIGHTS RESERVED. 粤ICP备25070663号</p>
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

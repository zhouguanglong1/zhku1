<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <%-- <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head> --%>
	 <head lang="en">
	    <meta charset="UTF-8">
	    <!--适应ie-->
	    <meta http-equiv="x-ua-compatible" content="IE=edge">
	    <!--手机端，响应式-->
	    <meta name="viewport"
	          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
	    <title>ZHKU社区网站</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/lib/bootstrap/css/bootstrap.css"/>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
	    <script src="${pageContext.request.contextPath}/js/lib/html5shiv/html5shiv.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/lib/respond/respond.js"></script>
	    <script type="text/javascript">
	    <%-- $(function(){
	    	var username = '<%=request.getAttribute("username") %>';
	    	if(username!=null&&!"".equals(username)){
	    		$('#username').show();
	    		$('#login').hide();
	    	}else{
	    		$('#login').show();
	    		$('#username').hide();
	    	}
	    }); --%>
    </script>
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
            <%-- <c:choose> --%>
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

<!--编辑-->
<div class="zhku-edit w">
    <div class="zhkuleft f_l">
        <div class="zhkutop">编辑推荐</div>
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
            <div class="zhkuone">
                <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><img class="f_l" src="${pageContext.request.contextPath}/images/4.jpg" alt=""/></a>
                <div class="f_l">
                    <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><h3>学习交流 | 见光丑</h3></a>
                    <div><i class="aa">12</i><i class="bb">4</i><i class="cc">hello</i></div>
                    <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><p>大多数智能手机选择的是后置双摄像头，vivo X9却逆其道而行。前置2000w柔光双摄的设定，对于爱自拍的一类人来说，就是死死抓住了他们的痛点。</p></a>
                </div>
            </div>
            <div class="zhkutwo">
                <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=8"><img class="f_l" src="${pageContext.request.contextPath}/images/5.jpg" alt=""/></a>
                <div class="f_l">
                    <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=5"><h3>二手专贴 | 哈哈哈哈</h3></a>
                    <div><i class="aa">25</i><i class="bb">8</i><i class="cc">kitty</i></div>
                    <a href="${pageContext.request.contextPath}/post/postDetail.action?pid=5"><p>哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或或或。</p></a>
                </div>
            </div>
        </div>
    </div>
    <div class="zhkuright f_r">
        <div class="zhkuone">热门标签</div>
        <div class="zhkutwo clearfix">
            <ul>
                <a href=""><li>小米6</li></a>
                <a href=""><li>vivoX9</li></a>
                <a href=""><li>hello</li></a>
                <a href=""><li>见光丑</li></a>
                <a href=""><li>小米5</li></a>
                <a href=""><li>xplay6</li></a>
                <a href=""><li>P10闪存</li></a>
                <a href=""><li>iphone7</li></a>
            </ul>
        </div>
        <div class="zhkuthree">
            热门版块
        </div>
        <div class="zhkufour">
            <ul>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=1"><li class="ff">资源共享</li></a>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=2"><li class="gg">爱我仲园</li></a>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=3"><li class="dd">热门吐槽</li></a>
                <a href="${pageContext.request.contextPath}/zone/findByZid.action?zid=4"><li class="ee">玩机技巧</li></a>
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
<script src="${pageContext.request.contextPath}/js/lib/bootstrap/js/bootstrap.js"></script>
<!--渲染模板-->
<script src="${pageContext.request.contextPath}/js/template-native.js"></script>
<!--另一个渲染模板的包-->
<!--<script src="../js/underscore-min.js"></script>-->
<!--可拽动-->
<script src="${pageContext.request.contextPath}/js/swipe.js"></script>
<!--index-->
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript">
	/* $(function(){
		$.post("${pageContext.request.contextPath}/post/topTwoPost.action",function(result){
			if(result.success){
				var result=eval('('+result+')'); 
				alert("数据已成功删除！");
				location.reload(true);
			}else{
				alert("数据删除失败！");
			}
		},"json");
		
		
		
	}); */

</script>
</html>

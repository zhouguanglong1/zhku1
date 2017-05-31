<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<!--图标导航-->
<div class="wu">
    <div class="tribune-table w">
        <ul class="f_l">
            <li class="aa"><span>今日：2890</span></li>
            <li class="bb"><span>昨日：1445</span></li>
            <li class="cc"><span>帖子：21143</span></li>
            <li class="dd"><span>会员：8394</span></li>
        </ul>
        <div class="day f_r">
            <img src="${pageContext.request.contextPath}/images/3232.bmp" alt=""/>
        </div>
    </div>
</div>

<!--评论部分-->
<div class="tribune-text w clearfix">
    <%-- <div class="tribune-textl f_l">
        <script type="text/template" id="discuss">
            <% for(var i=0; i< items.length; i++){ %>
            <div class="item <%=i==0? 'tribune-l':''%> ">
                <img class="mm" src="<%=items[i].pc%>" alt=""/>
                <div class="tribune-lr">
                    <h3><%=items[i].text%><img src="${pageContext.request.contextPath}/images/agree.gif" alt="heatlevel" title="3 热度">
                    </h3>
                    <div>
                        <p><%=items[i].text1%></p>
                        <i></i>
                        <p><%=items[i].text2%></p>
                        <i></i>
                        <p><%=items[i].text3%></p>
                    </div>
                </div>
            </div>
            <% } %>
        </script>

    </div> --%>
    <div class="tribune-textr f_r">
        <div class="tribune-textrt">精选推荐</div>
        <div class="tribune-textrb">
            <ul>
                <li><img src="${pageContext.request.contextPath}/images/2right1.jpg" alt=""/></li>
                <li><img src="${pageContext.request.contextPath}/images/2right2.jpg" alt=""/></li>
                <li><img src="${pageContext.request.contextPath}/images/2right3.jpg" alt=""/></li>
                <li><img src="${pageContext.request.contextPath}/images/2right4.jpg" alt=""/></li>
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
<script src="${pageContext.request.contextPath}/js/tribune.js"></script>
</html>

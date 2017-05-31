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
<form action="${ pageContext.request.contextPath }/product/addOrSaveProduct.action" method="post" enctype="multipart/form-data">
	<table class="table table-bordered table-hover definewidth m10">
	
    <tr>
        <td width="10%" class="tableleft">风景名称</td>
        <td><input type="text" name="sname" value="${scenery.sname}"/></td>
        <td><input type="hidden" id="id" name="id" value="${scenery.id }"/></td>
    </tr>
    


    <tr>
        <td class="tableleft">风景描述</td>
        <td><textarea name="remark" style="width:200px;height:80px;" >${scenery.remark}</textarea></td>
    </tr>

    
    <tr>
        <td class="tableleft">风景图片</td>
        <td>
	        <c:if test="${scenery.photoPath !=null}">
				<img src="/photo/${scenery.photoPath}" width=100 height=100/>
				<br/>
			</c:if>
			<!-- <div id="preview"></div>  -->
			<input type="file" id="image" name="photoPath" >
        </td>
    </tr>  
    

    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
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

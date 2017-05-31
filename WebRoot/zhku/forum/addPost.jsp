<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/js/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/js/lib/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/style.css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tribune.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/emoticon.css" />
<%-- <script src="${pageContext.request.contextPath}/js/lib/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery.emoticons.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/bootstrap/js/bootstrap.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.sorted.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/ckform.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/common.js"></script>


<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>


<script type="text/javascript">
function checkForm(){
	if('${sessionScope.username}'==''){
		alert("请先登录，再发帖！");
		/* var url="Report_preSave.action?role=0&reportType=1";
		window.open("login.jsp?url="+url); */
		return false;
	}
	if ($("#postName").val()==""||$("#postName").val()==null) {
		alert("请填写帖子主题！");
		return false;
	}
	if ($("#section").val()==0||$("#section").val()==null) {
		alert("请选择板块！");
		return false;
	}
	if (CKEDITOR.instances.Content.getData().length<10) {
		alert("帖子内容最少10个字符！");
		return false;
	}
}
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
                    <a href="${pageContext.request.contextPath}/zhku/sys/zhkuShop.jsp">
                        仲园咸鱼
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/zhku/sys/board.jsp">
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
<!-- 主要区域 -->

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


<div style="width: 1200px; margin: 0 auto;">

	<table border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;width: 1400px;" align="center">
		<tr height="30" background="${pageContext.request.contextPath}/images/classT.jpg">
			<td style="text-indent:5;">
				<b><font color="white">■ 发表帖子</font></b>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="width: 220px;" valign="top">
				<table style="margin-top: 10;margin-left: 20px;" class="">
					<tr>
						<td valign="top" width="99%">发帖许可：<br><br>
						<ul class="unstyled">
							<li>*请不要发表危害祖国的非法信息！</li><br><br>
							<li>*请不要发表侵犯个人名誉的信息！</li><br><br>
							<li>*请不要发表不文明内容！</li>
						</ul>
					</tr>
					<tr height="40">
						<td align="center">违反以上规则所发生的后果自负！</td>
					</tr>
				</table>
			</td>
			<td>
				<form class="form-horizontal" style="margin-top: 10px;" action="${pageContext.request.contextPath}/post/saveOrUpdate.action" method="post" onsubmit="return checkForm()">
					<div class="control-group">
						<label class="control-label" for="title">【主题】</label>
						<div class="controls">
							<input type="text" id="postName" name="postName" value="${post.postName }" style="width: 600px;">
							<input type="hidden" id="pid" name="pid" value="${post.id }" >
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="section">【版面】</label>
						<div class="controls">
							<select id="section" name="sectionid" style="width: 400px;">
							<%-- <c:choose>
								<c:when test="${post!=null && post!='' }">
									<option value="${post.section.id }">${post.section.sectionName }</option>
									<c:forEach var="section" items="${sectionList }">
										<option value="${section.id }" >${section.sectionName }</option>
									</c:forEach>
								</c:when>
								<c:otherwise> --%>
									<option value="0">请选择版面...</option>
									<c:forEach var="section" items="${sectionList }">
										<option value="${section.id }" <c:if test="${section.id == post.section.id }">selected</c:if> >${section.sectionName }</option>
									</c:forEach>
								<%-- </c:otherwise>
							</c:choose> --%>
							</select>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="Content">【内容】</label>
						<div class="controls">
							<textarea name="content" id="Content" class="ckeditor" cols="50" style="height:200px;width: 800px;" >${post.content }</textarea>
						</div>
					</div>
					<input id="user" name="username" value="${username }" type="hidden"/>
					<%-- <input id="section" type="hidden" name="topic.section.id" value="${curSection.id }"/> --%>
					<div class="control-group">
						<div class="controls">
							<Button class="btn btn-primary " data-dismiss="modal" aria-hidden="true" type="submit">提交</Button>
							<font id="error"></font>
						</div>
					</div>
				</form>
			</td>
		</tr>
	</table>
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
	<script type="text/javascript">
		/* //放新浪微博表情
		$("#message_face").jqfaceedit({
			txtAreaObj : $('#Content'),
			containerObj : $('#container'),
			top : 25,
			left : -27
		});

		//显示表情
		$("#show_face").click(function() {
			$('.show_e').html($('#Content').val());
			$('.show_e').emotionsToHtml();
		}); */
	</script>
</body>
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
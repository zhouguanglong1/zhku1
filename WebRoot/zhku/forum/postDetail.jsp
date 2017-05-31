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
    
    <title>帖子详情</title>
    
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/emoticon.css" />
    
    
    <script src="js/jquery.emoticons.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/ckform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/common.js"></script>
    <script type="text/javascript">
$(function(){
	//放新浪微博表情
    $("#message_face").jqfaceedit({txtAreaObj:$("#Content"),containerObj:$('#container'),top:25,left:-27});
	 //显示表情
	$(".show_e").emotionsToHtml();
});
function checkForm(){
	if('${username}'==''){
		alert("请先登录，再回帖！");
		/* var url="Report_preSave.action?role=0&reportType=1";
		window.open("login.jsp?url="+url); */
		return false;
	}
	if ($("#Content").val().length<5) {
		alert("最少输入5个字符！");
		return false;
	}
	if ($("#Content").val().length>1000) {
		alert("最多输入1000个字符！");
		return false;
	}
	/* $.post("${pageContext.request.contextPath}/reply/saveReply.action",$("#replyForm").serialize(),function(result){
		if(result.success){
			alert("回复成功！");
			location.reload(true);
		}else{
			alert("回复失败！");
		}
	},"json"); */
}
function deleteReply(replyId){
	if (confirm("您确定要删除这条回复吗？")) {
		window.location.href='${pageContext.request.contextPath}/reply/deleteReply.action?replyId='+replyId;
	}else{
		return;
	}
}
</script>
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

<div style="width: 1200px; margin: 0 auto;">
	<div class="container-fluid" style="padding-left: 0px;padding-right: 0px;">
		<div class="row-fluid">
			<div class="span2">
				<table style="width: 100%;" cellpadding="5px;">
					<tr>
						<td>
							★楼主&nbsp;<a href="#" style="font-size: 9pt;color: black;"><strong>${post.user.username }</strong></a>
						</td>
					</tr>
					<tr>
						<td>
							<c:choose>
									<c:when test="${(post.user.photoPath==null||post.user.photoPath=='')&&post.user.sex=='男'}">
										<img alt="" src="${pageContext.request.contextPath}/images/user/user0.gif" style="width: 100px;height: 100px;">
									</c:when>
									<c:when test="${(post.user.photoPath==null||post.user.photoPath=='')&&post.user.sex=='女'}">
										<img alt="" src="${pageContext.request.contextPath}/images/user/female.gif" style="width: 100px;height: 100px;">
									</c:when>
									<c:otherwise>
										<img alt="" src="/photo/${post.user.photoPath}" style="width: 100px;height: 100px;">
									</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td>
							性别：${post.user.sex }
						</td>
					</tr>
					<tr>
						<td>
							邮箱：${post.user.email }
						</td>
					</tr>
					<tr>
						<td>
							<c:choose>
									<c:when test="${post.user.sectionList.size()==0&&post.user.roleStatus!=2 }">
										<font style="color: black;">普通用户</font>
									</c:when>
									<c:when test="${post.user.sectionList.size()!=0&&post.user.roleStatus!=2 }">
										<font style="color: blue;">版主</font>
										【<c:forEach items="${post.user.sectionList }" var="section">
					                  	  				${section.sectionName };
					                  	 </c:forEach>】
					                </c:when>
									<c:otherwise>
										<font style="color: red;">管理员</font>
									</c:otherwise>
								</c:choose>
							</td>
					</tr>
				</table>
			</div>
			<div class="span10">
				<table style="width: 100%;">
					<tr style="height: 50px;">
						<td>
							【主题】:<strong>${post.postName }</strong>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							发表时间:『${post.publishTime }』
						</td>
					</tr>
					<tr>
						<td>
							【内容】:
							<div style="width: 982px;padding:6px; background-color: #F0F0F0" class="show_e">
								${post.content }
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<c:forEach items="${replyList }" var="reply" varStatus="status">
		<div class="row-fluid" style="margin-top: 20px;">
			<div class="span2">
				<table style="width: 100%;" cellpadding="5px;">
					<tr>
						<td>
							▲${(page-1)*10+status.index+1 }楼
						</td>
					</tr>
					<tr>
						<td>
							<c:choose>
									<c:when test="${(reply.user.photoPath==null||reply.user.photoPath=='')&&reply.user.sex=='男'}">
										<img alt="" src="${pageContext.request.contextPath}/images/user/user0.gif" style="width: 100px;height: 100px;">
									</c:when>
									<c:when test="${(reply.user.photoPath==null||reply.user.photoPath=='')&&reply.user.sex=='女'}">
										<img alt="" src="${pageContext.request.contextPath}/images/user/female.gif" style="width: 100px;height: 100px;">
									</c:when>
									<c:otherwise>
										<img alt="" src="/photo/${reply.user.photoPath}" style="width: 100px;height: 100px;">
									</c:otherwise>
							</c:choose>
							<a href="#" style="font-size: 9pt;color: black;"><strong>${reply.user.username }</strong></a>
						</td>
					</tr>
					<tr>
						<td>
							性别：${reply.user.sex }
						</td>
					</tr>
					<tr>
						<td>
							邮箱：${reply.user.email }
						</td>
					</tr>
					<tr>
						<td>
							<c:choose>
									<c:when test="${reply.user.sectionList.size()==0&&reply.user.roleStatus!=2 }">
										<font style="color: black;">普通用户</font>
									</c:when>
									<c:when test="${reply.user.sectionList.size()!=0&&reply.user.roleStatus!=2 }">
										<font style="color: blue;">版主</font>
										【<c:forEach items="${reply.user.sectionList }" var="section">
					                  	  				${section.sectionName }；
					                  	 </c:forEach>】
					                </c:when>
									<c:otherwise>
										<font style="color: red;">管理员</font>
									</c:otherwise>
								</c:choose>
							</td>
					</tr>
				</table>
			</div>
			<div class="span10">
				<table style="width: 100%;">
					<tr>
						<td style="text-align: right;">
							<c:choose>
								<c:when test="${username==reply.user.username }">
									<button class="btn btn-danger" onclick="javascript:deleteReply(${reply.id })">删除</button>
								</c:when>
								<%-- <c:when test="${currentUser.type==2 }">
									<button class="btn btn-danger" onclick="javascript:deleteReply(${reply.id })">删除</button>
								</c:when> --%>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							回复时间:『${reply.replyTime }』
						</td>
					</tr>
					<tr>
						<td>
							<div style="width: 982px;padding:6px; background-color: #F0F0F0" class="show_e">
								${reply.content }
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		</c:forEach>
	</div>
	<%-- <div class="pagination alternate" align="center">
		<c:choose>
			<c:when test="${replyList ==''}">
				本帖还没有人回复
			</c:when>
			<c:otherwise>
				<ul class="clearfix">${pageCode }
				</ul>
			</c:otherwise>
		</c:choose>
	</div> --%>
	<div>
		<table>
			<tr>
				<td style="width: 20%;">
					回帖许可：
				</td>
				<td style="width: 80%;">
					<!-- onsubmit="return checkForm()" -->
					<form id="replyForm" action="${pageContext.request.contextPath}/reply/saveReply.action" class="form-horizontal" style="margin-top: 10px;" onsubmit="checkForm();" >
					<table style="width: 100%;" cellpadding="10px;">
						<tr>
							<td>
								【表情】:
							</td>
							<td>
								<div id="container">
								<a href="JavaScript:void(0)" id="message_face">请选择...</a>
								</div>
							</td>
						</tr>
						<tr>
							<td style="vertical-align: top;">
								【内容】:
							</td>
							<td>
								<textarea name="reply.content" id="Content" cols="50" style="height:200px;width: 800px;" ></textarea>
							</td>
						</tr>
						<tr>
							<td>
								<input id="username" name="reply.username" value="${sessionScope.username }" type="hidden"/>
								<input id="postId" name="reply.post.id" value="${post.id }" type="hidden"/>
							</td>
							<td>
								<Button class="btn btn-primary " data-dismiss="modal" aria-hidden="true" type="submit" >提交</Button>
								<font id="error"></font>
							</td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
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
</html>

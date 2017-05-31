<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/3.css"/> --%>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	function checkForm(){
		// 校验用户名:
		var username = document.getElementById("username").value;
		if(username == ''){
			alert("用户名不能为空!");
			return false;
		}
		
		// 校验密码:
		var password = document.getElementById("password").value;
		if(password == ''){
			alert("密码不能为空!");
			return false;
		}
		
	}
	
	function change(){
		var img = document.getElementById("checkImg");
		img.src="<%=path%>/user/CheckImg.action?"+new Date().getTime();
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
                    <a href="${pageContext.request.contextPath}>/category/categoryList.action?page=1">
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
<div class="container login">
		<div class="span12">
<div class="ad">
	<img src="${pageContext.request.contextPath}/images/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
</div>		
</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<div class="title">
					</div>
					<form id="loginForm" action="${ pageContext.request.contextPath }/user/login.action"  method="post" novalidate="novalidate">
						<table>
							<tbody><tr>
								<th>
										用户名:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20"/><span style="color: red">${message }</span>
								</td>
							</tr>
							<tr>
								<th>
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off"/><span></span>
								</td>
							</tr>
								<tr>
									<th>
										验证码:
									</th>
									<td>
										<span class="fieldSet">
											<input type="text"
											id="checkcode" name="checkcode" class="text captcha"
											maxlength="4" autocomplete="off"/>
											<img
												id="checkImg" class="captchaImage"
												src="<%=path%>/user/CheckImg.action"
												title="点击更换验证码" onclick="change()"/>
										</span>
										<span id="code1"></span>
									</td>
								</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<label>
										<input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true">记住用户名
									</label>
									<label>
										&nbsp;&nbsp;<a >找回密码</a>
									</label>
								</td>
							</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<input type="submit" class="submit" value="登 录">
								</td>
							</tr>
							<tr class="register">
								<th>&nbsp;
									
								</th>
								<td>
									<dl>
										<dt>还没有注册账号？</dt>
										<dd>
											立即注册即可体验仲恺社区！
											<a href="${pageContext.request.contextPath}/zhku/register.jsp">立即注册</a>
										</dd>
									</dl>
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
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
</html>

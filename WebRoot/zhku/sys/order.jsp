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
    
    <title>订单详情</title>
    
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
  <table class="table table-bordered table-hover definewidth m10" border="0" frame="void">
<tr>
	<td>提交订单成功</td>
	<td>订单号为：${order.orderNum }</td>
</tr>
</table>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
       <th>图片</th>
        <th>商品</th>
        <th>价格</th>
        <th>数量</th>
        <th>小计</th>
        <!-- <th>操作</th>  -->
    </tr>
    </thead>
    <c:forEach items="${order.orderItems }" var="orderItem"> 
	     <tr>
            <td><img src="/photo/${orderItem.product.image }" width="100px"></td>
            <td><a target="_blank">${orderItem.product.pname }</a></td>
            <td>￥${orderItem.product.shop_price }</td>
            <td>1</td>
            <td>￥${orderItem.product.shop_price }</td>
            
            <!-- <td>1</td> -->
            <%-- <td>
                  <button type="button" class="btn btn-warning" id="del" onclick="cancel();">取消</button> &nbsp;&nbsp;&nbsp;
                  <button type="button" class="btn btn-info" id="del" onclick="submitt(${product.id});">提交订单</button> 
                  <a href="${pageContext.request.contextPath}/category/deleteCategory.action?cid=${category.id}"><font color="red">删除</font></a>
            </td> --%>
        </tr>
      </c:forEach>
        </table>
       <table class="table table-bordered table-hover definewidth m10" border="0" frame="void">
		<tr>
			<td>订单金额:</td>
			<td>${order.total }</td>
		</tr>
		</table> 
        
        
        <!-- end -->
  
		
				
				
			<form id="orderForm" action="${pageContext.request.contextPath }/order/payOrder.action" method="post">
				<input type="hidden" name="oid" value="${order.id }"/>
				
				<table class="table table-bordered table-hover definewidth m10">
					<tr>
				        <td width="10%" class="tableleft">收货地址</td>
				        <td>
				        	<!-- <input type="text" name="addr" value=""/> -->
				        	<textarea name="addr" style="height:80px" value="${order.addr }"></textarea>
				        </td>
				    </tr>
					<tr>
				        <td width="10%" class="tableleft">收货人姓名</td>
				        <td><input type="text" name="name" value="${order.name }"/></td>
				    </tr>
				    <tr>
				        <td width="10%" class="tableleft">联系电话</td>
				        <td><input type="text" name="phone" value="${order.phone }"/></td>
				    </tr>
				     <tr>
				        <td width="10%" class="tableleft">选择银行：</td>
				        <td>
				        	<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
							<img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
							<img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
							<img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle"/>
							<br/>
							<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
							<img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
							<img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
							<img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle"/>
							<br/>
							<input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
							<img src="${pageContext.request.contextPath}/bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
							<img src="${pageContext.request.contextPath}/bank_img/cmb.bmp" align="middle"/>
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
<script src="${pageContext.request.contextPath}/js/template-native.js"></script>
<script src="${pageContext.request.contextPath}/js/tribune.js"></script>
<script type="text/javascript">
	function cancel(){
		window.location.href="${pageContext.request.contextPath}/category/categoryList.action?page=1";
	};

	function submitt(id){
		window.location.href='${pageContext.request.contextPath}/order/submitOrder.action?pid='+id;
	};

</script>
  
  
  
</html>

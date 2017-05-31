<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/boot/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/boot/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/boot/assets/css/main-min.css" rel="stylesheet" type="text/css" />

	<!-- <style type="text/css">
		.dl-title{
			height:50px;
		}
	</style> -->
  </head>
  
  
  <body>
    <div class="header">

    <div class="dl-title">
        <!-- <img src="/chinapost/Public/assets/img/top.png"> -->
        <img src="${pageContext.request.contextPath}/images/logo2.png" alt=""/>
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user" style="color:red">${adminUser.username }</span><a href="${pageContext.request.contextPath}/user/quitAdmin.action" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>		
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">商城管理</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">论坛管理</div></li>
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">仲恺一角管理</div></li>

        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/assets/js/bui-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/assets/js/common/main-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/boot/assets/js/config-min.js"></script>
<script>
    BUI.use('common/main',function(){
        var config = [{id:'1',homePage : '12',menu:[{text:'系统管理',items:[
                                                        {id:'12',text:'用户管理',href:'${pageContext.request.contextPath}/user/userList.action'},
                                                        {id:'3',text:'角色管理',href:'${pageContext.request.contextPath}/user/userStatList.action'},
                                                        {id:'4',text:'权限管理',href:'User/index.html'}
                                                        ]
        							}]},
                      {id:'7',homePage : '9',menu:[{text:'商城管理',items:[
                                                                       {id:'9',text:'一级分类管理',href:'${pageContext.request.contextPath}/category/adminCategoryList.action'},
                                                                       {id:'14',text:'二级分类管理',href:'${pageContext.request.contextPath}/categorySecond/adminCategorySecondList.action?page=1'},
                                                                       {id:'15',text:'商品管理',href:'${pageContext.request.contextPath}/product/adminProductList.action?page=1'},
                                                                       {id:'16',text:'订单管理',href:'${pageContext.request.contextPath}/order/adminOrderList.action?page=1'}
                                                                       ]
                      								}]},
                 	  {id:'8',homePage : '10',menu:[{text:'论坛管理',items:[
                 		                                               {id:'10',text:'版块管理',href:'${pageContext.request.contextPath}/zone/adminZoneList.action'},
                 		                                               {id:'11',text:'版面管理',href:'${pageContext.request.contextPath}/section/adminSectionList.action?page=1'},
                 		                                               {id:'13',text:'帖子管理',href:'${pageContext.request.contextPath}/post/adminPostList.action?page=1'}
                 		                                               
                 		                                               ]
                 		                      		}]},								
                 	  {id:'19',homePage : '20',menu:[{text:'仲恺一角管理',items:[
																		{id:'20',text:'分享管理',href:'${pageContext.request.contextPath}/zhku/admin/forum/shareList.jsp'} 
                 	                                ]}]},						
        			  ];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
  </body>
</html>

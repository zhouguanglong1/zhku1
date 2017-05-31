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
    <title>商品管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-datetimepicker.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/ckform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>

  </head>
  
  <body>
<form class="form-inline definewidth m20" action="${pageContext.request.contextPath}/product/queryProduct.action" method="get">  
    商品名称：
    <input type="text" name="pname" class="abc input-default" placeholder="" value="">&nbsp;&nbsp; 
    商品价格：  
    <input type="text" name="shop_price_from" id="shop_price"class="abc input-default" placeholder="最低价格" value="">&nbsp;&nbsp;  
    ~  
    <input type="text" name="shop_price_to" id="shop_price"class="abc input-default" placeholder="最高价格" value="">&nbsp;&nbsp;  
    添加时间：  
    <input type="text" name="pdateStart" id="pdateStart"class="abc input-default" placeholder="起始时间" value="">&nbsp;&nbsp; 
    ~
    <input type="text" name="pdateEnd" id="pdateEnd"class="abc input-default" placeholder="结束时间" value="">&nbsp;&nbsp; 
    所在地： 
    <input type="text" name="address" id="address"class="abc input-default" placeholder="如：广东 广州" value="">&nbsp;&nbsp;  
    
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增商品</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <!-- <th>id</th>
        <th>分类名称</th> -->
        <!-- <th>状态</th>-->
        
        
        <th>商品名称</th>
		<th>市场价</th>
		<th>商城价</th>
		<th>商品图片</th>
		<th>商品描述</th>
		<th>是否热门（1是热门）</th>
		<th>添加日期</th>
		<th>商品所在地</th>
		<th>所属分类</th>
		<th>管理操作</th> 
    </tr>
    </thead>
    <c:forEach items="${pageBean.list  }" var="product">
	     <tr>
            <td>${product.pname }</td>
            <td>${product.market_price }</td>
            <td>${product.shop_price }</td>
            <td><img src="/photo/${product.image}" width=100 height=100/></td>
            <td>${product.pdesc }</td>
            <td>${product.is_hot }</td>
            <td>${product.pdate }</td>
            <td>${product.address }</td>
            <td>${product.categorySecond.csname }</td>
            <!-- <td>1</td> -->
            <td>
                  <a href="${pageContext.request.contextPath}/product/modifyProduct.action?pid=${product.id}">编辑</a>&nbsp;&nbsp;&nbsp;
                  <%-- <button type="button" class="btn btn-warning" id="del" onclick="del(${category.id });">删除</button> --%>
                  <a href="${pageContext.request.contextPath}/product/deleteProduct.action?pid=${product.id}&page=${pageBean.page}"><font color="red">删除</font></a>
            </td>
        </tr>
     </c:forEach>
        </table>
<div class="inline pull-right page">
        <!--  10122 条记录 1/507 页  <a href='#'>下一页</a>     <span class='current'>1</span><a href='#'>2</a><a href='/chinapost/index.php?m=Label&a=index&p=3'>3</a><a href='#'>4</a><a href='#'>5</a>  <a href='#' >下5页</a> <a href='#' >最后一页</a>     -->
	<a href = "${pageContext.request.contextPath}/product/adminProductList.action?page=1" >首页</a>
    	<c:choose>
    		<c:when test="${pageBean.page gt 1 }">
    			<a href = "${pageContext.request.contextPath}/product/adminProductList.action?page=${pageBean.page -1} " >上一页</a> 
    		</c:when>
    		<c:otherwise>
    			上一页
    		</c:otherwise>
    	</c:choose>
    	<c:choose>
    		<c:when test="${pageBean.totalPage gt 1 && pageBean.page ne pageBean.totalPage}">
    			<a href = "${pageContext.request.contextPath}/product/adminProductList.action?page=${pageBean.page +1} " >下一页</a>
    		</c:when>
    		<c:otherwise>
    			下一页
    		</c:otherwise>
    	</c:choose>
    	
		<a href = "${pageContext.request.contextPath}/product/adminProductList.action?page=${pageBean.totalPage }" >尾页</a>  
		第<span class='current'>${pageBean.page }</span>页/共${pageBean.totalPage }页  
			<!-- 第<span class='current'>1</span>页/共1页  -->
</div>
</body>	
<script>
    $(function () {
        
		$('#addnew').click(function(){

				window.location.href="${pageContext.request.contextPath}/zhku/admin/product/addProduct1.jsp";
		 });


    });

	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = "${pageContext.request.contextPath}/category/deleteCategory.action?cid=id";
			window.location.href=url;		
		}
	};
	
	$("#pdateStart").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        //startDate:new Date()
    }).on("click",function(){
        $("#pdateStart").datetimepicker("setEndDate",$("#pdateEnd").val());
    });
    $("#pdateEnd").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        minView:'month',
        language: 'zh-CN',
        autoclose:true,
        //startDate:new Date()
    }).on("click",function(){
        $("#pdateEnd").datetimepicker("setStartDate",$("#pdateStart".val()));
    });
	
</script>
</html>

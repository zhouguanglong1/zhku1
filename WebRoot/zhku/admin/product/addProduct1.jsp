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
    
    <title>添加商品</title>
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/boot/Css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/ckform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/Js/common.js"></script> 

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
    <form action="${ pageContext.request.contextPath }/product/addOrSaveProduct.action" method="post" enctype="multipart/form-data">
	<table class="table table-bordered table-hover definewidth m10">
	
    <tr>
        <td width="10%" class="tableleft">商品名</td>
        <td><input type="text" name="pname" value="${product.pname}"/></td>
        <td><input type="hidden" id="id" name="id" value="${product.id }"/></td>
    </tr>
    
    <tr>
    	<td width="10%" class="tableleft">选择二级分类</td>
		<td>	
			<select name="csid">
			<c:forEach items="${sessionScope.categorySecondList }" var="categorySecond">
				<option value="${categorySecond.id }">${categorySecond.csname}</option>
			
			</c:forEach>
			</select>
		</td>
    </tr>
    <tr>
        <td class="tableleft">市场价</td>
        <td><input type="text" name="market_price" value="${product.market_price}"/></td>
    </tr>
    <tr>
        <td class="tableleft">咸鱼价</td>
        <td><input type="text" name="shop_price" value="${product.shop_price}"/></td>
    </tr>
    <tr>
        <td class="tableleft">商品描述</td>
        <td><textarea name="pdesc" style="width:200px;height:80px;" >${product.pdesc}</textarea></td>
    </tr>
    <tr>
        <td class="tableleft">是否热门</td>
        <td><select name="is_hot">
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
		</td>
    </tr>
    
    <tr>
        <td class="tableleft">是否上架</td>
        <td><select name="status">
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
		</td>
    </tr>
    
    <tr>
        <td class="tableleft">所在地</td>
        <td><input type="text" name="address" value="${product.address}"/></td>
    </tr> 
    
    <tr>
        <td class="tableleft">商品图片</td>
        <td>
	        <c:if test="${product.image !=null}">
				<img src="/photo/${product.image}" width=100 height=100/>
				<br/>
			</c:if>
			<!-- <div id="preview"></div>  -->
			<input type="file" id="image" name="file" >
        </td>
    </tr>  
    
    <!-- <tr>
        <td class="tableleft">机构名称</td>
        <td><input type="text" name="moduletitle"/></td>
    </tr>   
    <tr>
        <td class="tableleft">状态</td>
        <td>
            <input type="radio" name="status" value="1" checked/> 启用
            <input type="radio" name="status" value="0"/> 禁用
        </td>
    </tr> -->
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
  </body>
  <script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="${pageContext.request.contextPath}/product/adminProductList.action?page=1";
		 });

    });
</script>
</html>

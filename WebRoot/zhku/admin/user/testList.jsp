<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testList.jsp' starting page</title>
    
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" >
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/common/js/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/common/js/easyUI/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>/common/js/jquery/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/js/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/js/easyUI/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/js/easyUI/validate.js"></script>
	<script type="text/javascript" src="<%=basePath%>/common/js/jquery/jquery.form.js"></script>

 <script type="text/javascript">
$(function(){
	$('#toolbar').show();
});
</script>
<style type="text/css">
body { font-size: 12px; margin: 1px}

#fm{margin:0;padding:5px 10px;}
.ftitle{
    font-size:14px;
    font-weight:bold;
    padding:5px 0;
    margin-bottom:10px;
    border-bottom:1px solid #ccc;
}
.addupdateTable label{
    display:inline-block;
    padding-left:8px;
    width:90px;
}
.addupdateTable input{
    width:158px;
}
.addupdateTable select{
    width:162px;
}

</style>

</head>
<body>
<div class="easyui-panel" title="渠道资料查询" style="width:100%;">

<table id="dg" class="easyui-datagrid" style="display: none;"
	data-options="singleSelect:false,fitColumns:false,rownumbers:true,pagination:true,toolbar:'#toolbar'
	,url:'<%=path%>/user/testList.action'">
	<thead data-options="frozen:true"><tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',hidden:true"></th>
			<th data-options="field:'code'">渠道编码</th>
			<th data-options="field:'name'">渠道名称</th>
	</tr></thead>
	<thead>
		<tr>
			<th data-options="field:'chlevel'">渠道星级</th>
			<th data-options="field:'companyId'">所属分公司</th>
			<th data-options="field:'manager'">看管渠道经理</th>
			<th data-options="field:'managerTel'">渠道经理电话</th>
			<th data-options="field:'tel'">渠道联系电话</th>
			<th data-options="field:'person'">法人代表名字</th>
			<th data-options="field:'waytype'">渠道类型</th>
			<th data-options="field:'addr'">店铺地址</th>
			<th data-options="field:'bossNumber'">boss工号</th>
			<th data-options="field:'timsNumber'">tims账号</th>
			<th data-options="field:'status'">渠道状态</th>
			<th data-options="field:'longitude'">经度</th>
			<th data-options="field:'latitude'">纬度</th>
			<th data-options="field:'hasbossStr'">是否有BOSS权限</th>
			<th data-options="field:'bankName'">酬金支付账号开户银行</th>
			<th data-options="field:'chanBankName'">酬金支付账号银行户名</th>
			<th data-options="field:'bankAccount'">酬金支付账号银行账号</th>
			<th data-options="field:'bankType'">酬金支付账号类型</th>
			<th data-options="field:'hasboss',hidden:true"></th>
		</tr>
	</thead>
</table>
<div id="toolbar" style="display: none;">
	<div id="serachDiv_id" style="padding-left: 6px;padding-top: 5px">
        <span class="fitem">
            <label>渠 道 编 码:</label>
            <input id="chan_channelCode" style="width:155px" class="easyui-textbox" >
        </span>
        <span class="fitem">
            <label>渠 道 名 称:</label>
            <input id="chan_channelName" style="width:155px" class="easyui-textbox" >
        </span>
        <span class="fitem">
            <label>渠道经理电话:</label>
            <input id="chan_managerTel" style="width:155px" class="easyui-textbox" >
        </span>
        <br>
        <span class="fitem">
            <label>法人代表姓名:</label>
            <input id="chan_person" style="width:155px" class="easyui-textbox" >
        </span>
        <span class="fitem">
            <label>所属分公司:</label>
            <select id="chan_company" class="easyui-combobox" style="width:155px" data-options="editable:false">
	           	<option value="-1">--全部--</option>
	           	<c:forEach var="item" items="${companys }">
	           	    <option value="${item.key }">${item.value }</option>
	           	</c:forEach>
           	</select>
        </span>
        <span class="fitem">
            <label>渠 道 星 级 :</label>
            <select id="chan_chlevel" class="easyui-combobox" style="width:155px" data-options="editable:false">
	           	<option value="-1">--全部--</option>
	           	<c:forEach var="item" items="${chlevels }">
	           	    <option value="${item.key }">${item.value }</option>
	           	</c:forEach>
           	</select>
        </span>
        <br>
        <span class="fitem">
            <label>渠 道 类型 :</label>
            <select id="chan_waytype" class="easyui-combobox" style="width:155px" data-options="editable:false">
	           	<option value="-1">--全部--</option>
	           	<c:forEach var="item" items="${chwaytypes }">
	           	    <option value="${item.key }">${item.value }</option>
	           	</c:forEach>
           	</select>
        </span>
        <span class="fitem">
            <label>渠道状态 :</label>
            <select id="chan_waystatus" class="easyui-combobox" style="width:155px" data-options="editable:false">
	           	<option value="-1">--全部--</option>
	           	<option value="1">有效</option>
		        <option value="0">无效</option>
           	</select>
        </span>
        
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryData()">查询</a>
	</div>
	
	<hr class="hr-list-underline">
	
	<div>
	    <table><tr>
			<td>
			<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" onclick="channEditData()">修改</a>
			|<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-export',plain:true" onclick="exportData()">导出</a>
			|<a href="#" class="easyui-menubutton" data-options="menu:'#bstxt',iconCls:'icon-edit'">批量导入</a>
			<div id="bstxt">
				<div onclick="clickopenimpot();">导入TXT</div>
				<div onclick="javascript:location.href='<%=path %>/channel/downDataTemplate.action?templateType=channel'">批量导入模板下载</div>
			</div>
			</td>
			</tr>
		</table>
	</div>
</div>

</div>


<!-- 修改渠到资料弹窗 -->
<div id="chanEdit_div" class="easyui-dialog"
 		style="width:750px;height:450px;padding:10px 20px;display: none;"
        data-options="closed:true,buttons:'#chanEdit_button',modal:true,top:20">
    <div class="ftitle">修改渠道资料</div>
 	<form id="chanEdit_fm" enctype="multipart/form-data" method="post" action="" class="easyui-form" data-options="novalidate:true">
 	
 	<input type="hidden" name="id" id="chanEdit_id">
 	
    <table class="addupdateTable" id="attr_table" style="width: 400px; margin: auto;">
        <tr>
	         <td style="width: 10%;"><label>渠道编码：</label></td>
	         <td><input name="code" style="width: 200px;" class="easyui-textbox" disabled="disabled"></td>
	    
	         <td><label>渠道名称：</label></td>
	         <td><input name="name" style="width: 200px;" class="easyui-textbox" disabled="disabled"></td>
	     </tr>
	     <tr>
	         <td style="width: 10%;"><label>渠道星级：</label></td>
	         <td>
	             <input style="width: 200px;" class="easyui-textbox" name="chlevel" disabled="disabled">
	         </td>
	         <td><label>所属分公司：</label></td>
	         <td>
	             <input style="width: 200px;" class="easyui-textbox" name="companyId" disabled="disabled">
	         </td>
	    </tr>
	    <tr>
	         <td style="width: 10%;"><label>看管渠道经理：</label></td>
	         <td><input name="manager" style="width: 200px;" class="easyui-textbox" disabled="disabled"></td>
	         <td><label>渠道经理电话：</label></td>
	         <td>
	              <input style="width: 200px;" class="easyui-textbox" name="managerTel" disabled="disabled">
	         </td>
	    </tr>
	    <tr>
	         <td><label>经度：</label></td>
	         <td>
	              <input style="width: 200px;" class="easyui-textbox" name="longitude" disabled="disabled">
	         </td>
	          <td style="width: 10%;"><label>纬度：</label></td>
	         <td><input name="latitude" style="width: 200px;" class="easyui-textbox" disabled="disabled"></td>
	    </tr>
	    <tr>
	         <td><label>渠道状态：</label></td>
	         <td>
	              <input style="width: 200px;" class="easyui-textbox" name="status" disabled="disabled">
	         </td>
	         <td style="width: 10%;"><label>法人代表姓名：</label></td>
	         <td><input name="person" style="width: 200px;" class="easyui-textbox"></td>
	    </tr>
	    <tr>
	         <td><label>渠道联系电话：</label></td>
	         <td>
	              <input style="width: 200px;" class="easyui-textbox" name="tel">
	         </td>
	          <td style="width: 10%;"><label>酬金支付账号开户银行：</label></td>
	         <td><input name="bankName" style="width: 200px;" class="easyui-textbox"></td>
	    </tr>
	    <tr>
	         <td><label>酬金支付账号银行户名：</label></td>
	         <td>
	              <input style="width: 200px;" class="easyui-textbox" name="chanBankName">
	         </td>
	         <td style="width: 10%;"><label>酬金支付账号银行账号：</label></td>
	         <td><input name="bankAccount" style="width: 200px;" class="easyui-textbox"></td>
	    </tr>
	    <tr>
	         <td style="width: 10%;"><label>酬金支付账号账号类型：</label></td>
	         <td><input name="bankType" style="width: 200px;" class="easyui-textbox"></td>
	         <td><label>是否有BOSS权限：</label></td>
	          <td>
	            <select name="hasboss" style="width: 200px;" class="easyui-combobox" data-options="editable:false">
		           	<option value="true">是</option>
		           	<option value="false">否</option>
	           	</select>
	         </td>
	    </tr>
	     <tr>
	         <td style="width: 10%;"><label>boss工号：</label></td>
	         <td><input name="bossNumber" style="width: 200px;" class="easyui-textbox"></td>
	         <td><label>tims账号：</label></td>
	         <td><input name="timsNumber" style="width: 200px;" class="easyui-textbox"></td>
	    </tr>
	    <tr>
	    	<td style="width: 10%;"><label>店铺地址：</label></td>
	        <td><input name="addr" style="width: 200px;height: 70px;" class="easyui-textbox" data-options="multiline:true" disabled="disabled"></td>
	    	<td style="width: 10%;"><label>渠道类型：</label></td>
	         <td><input name="waytype" style="width: 200px;" class="easyui-textbox" disabled="disabled"></td>
	    </tr>
    </table>
	</form>
	
<div id="chanEdit_button">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="chanSaveData()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#chanEdit_div').dialog('close')" style="width:90px">取消</a>
</div>

</div>
<!-- end -->

<!-- ------------------------------------------导入渠道信息-------------------------------------------------------- -->
<div id="importWindowId" class="easyui-dialog" title="导入渠道信息"
 		style="width:450px;height:440px;padding:10px 20px;display: none;"
        data-options="closed:true,buttons:'#dlg-buttons-import',modal:true,top:20">
        
 	<form id="importMyform" enctype="multipart/form-data" method="post" action="" class="easyui-form" data-options="novalidate:true">
    <table>
	<tr align="center">
		<td align="left" colspan="2">
		
			<input id="fileUrl1" style="width: 300px;" data-options="prompt:'选择一个txt...',buttonText:'选择文件',required:true" 
				class="easyui-filebox" name="channelInfoTxt" accept=".txt" />
			
			<a href="#" id="importComExlButton" class="easyui-linkbutton" onclick="importComExl()" data-options="iconCls:'icon-import',toggle:true">导入</a>
			<br>
			<span style="display:inline-block; height: 18px;vertical-align: middle;">
				<img id="loadingpictureId" style="display: none" src="<%=basePath%>common/js/easyUI/themes/default/images/loading.gif">
			</span>
			<span id="validateTipID" style="color:#8F5700;padding:6px;display: none;">
			<span id="errmsgId"> </span>
			</span>
			<span id="errmsgId2" style="color:#8F5700;padding:6px;">
			</span>
			
		</td>
	</tr></table>
	</form>
    
    <div style="margin:10px 0;"></div>

    <table id="impotComTxtTableId" class="easyui-datagrid" style="width:auto;height: 250px" data-options="rownumbers:true,singleSelect:true,fitColumns:true">  
	<thead>
          <tr><th data-options="field:'erroinfo'">错误信息</th></tr>
	</thead>
	<tbody></tbody>
	</table>
    
<div id="dlg-buttons-import">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#importWindowId').dialog('close')" style="width:90px">关闭</a>
</div>

</div>
<!-- ---------------------------------------------end--------------------------------------------------- -->

<!-- 导出数据form -->
<form action="<%=path%>/channel/exportChannelExcel.action" id="exportForm" method="post">
<input type="hidden" name="channelCode" />
<input type="hidden" name="channelName" />
<input type="hidden" name="managerTel" />
<input type="hidden" name="person" />
<input type="hidden" name="company" />
<input type="hidden" name="chlevel" />
<input type="hidden" name="chwaytype" />
</form>
<!-- end -->




    <script type="text/javascript">
        var url;
        
        function getQueryPreams(){//获取查询参数
			var queryJson={
					'channelCode': $('#chan_channelCode').textbox('getValue'),
	        		'channelName': $('#chan_channelName').textbox('getValue'),
	        		'managerTel': $('#chan_managerTel').textbox('getValue'),
	        		'person': $('#chan_person').textbox('getValue'),
	        		'company': $('#chan_company').combobox('getValue'),
	        		'chlevel': $('#chan_chlevel').combobox('getValue'),
	        		'chwaytype': $('#chan_waytype').combobox('getValue'),
	        		'chwaystatus': $('#chan_waystatus').combobox('getValue')
		    	};
			return queryJson;
		}
        
        function queryData(){
        	$('#dg').datagrid('load', getQueryPreams());
        }
        
        function newData(){
        	$('#dlg').show();
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            url = 'save_user.php';
        }
        function editData(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','编辑');
                $('#fm').form('load',row);
                url = 'update_user.php?id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('destroy_user.php',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
        
       //打开渠道资料修改窗口
    	function channEditData(){
    		var allRows = $('#dg').datagrid('getSelections');
	    	if(allRows.length<=0){
	    		$.messager.alert('提示','请选择要修改的项！','warning');
	    		return;
	    	}else if(allRows.length>1){
	    		$.messager.alert('提示','请选择最多只能一项进行修改！','warning');
	    		return;
	    	}else{
	    		var row = $('#dg').datagrid('getSelected');
	    		if (row){
	    			$('#chanEdit_div').show();
		            $('#chanEdit_div').dialog('open').dialog('setTitle','编辑');
		            $('#chanEdit_fm').form('load',row);
		        }
	    	}
    	}
    	
    	 //渠道资料  保存数据
        function chanSaveData(){
            $('#chanEdit_fm').form('submit',{
                url: "<%=path%>/channel/channelUpdate.action",
                onSubmit: function(){
                	if($(this).form('enableValidation').form('validate')){
                		return true;
                	}
                	return false;
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        alert(result.errorMsg);
                    } else {
                    	$('#chanEdit_div').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload'); 
                    }
                }
            });
        }
    	 
        //导出数据
	    function exportData(){
	    	$.messager.confirm('提示', '是否要导出xls文档?', function (r){
		       	if(r){
		       		$('#exportForm').form('load',getQueryPreams());
			  		$('#exportForm').submit();
		        }else{return;}
			});
	    }
	    //打开导入窗口
        function clickopenimpot(){
        	//$('#importMyform').resetForm();
        	$('#importWindowId').show();
	        $('#importWindowId').dialog('open');
        }
      	//渠道信息txt导入
        function importComExl(){
        	if($("#importMyform").form('enableValidation').form('validate')){
        	
        	  $('#importComExlButton').linkbutton('disable');    //禁用按钮  
        	  $("#importMyform").attr("action","<%=path%>/channel/recordImportTxt.action");
        	  $("#loadingpictureId").show();
        	  $("#errmsgId").html("导入中……");
        	  $("#validateTipID").show();
        	  $('#importMyform').ajaxSubmit({
                     success: function (data) {
                    	 var bacmap=data;
                    	 bacmap = $.parseJSON(bacmap);
                    	 if(bacmap.uploadInfo != '上传成功'){//上传文件为空或者类型不对
                    		 $("#errmsgId").html(bacmap.uploadInfo);
                    		 $("#validateTipID").fadeOut(2200);
                    		 $("#loadingpictureId").hide();
                    		 setTimeout(function(){$('#importComExlButton').linkbutton('enable');},1000);
                    	 }else if(bacmap.uploadInfo == '上传成功'){
                    		 $("#errmsgId").html("导入完毕！");
                    		 $("#validateTipID").fadeOut(700);
                    		 $("#loadingpictureId").hide();
                    		 $('#errmsgId2').html("成功导入 "+bacmap.succnum+" 条数据，失败 "+bacmap.erronum+" 条。");
                    		 $('#impotComTxtTableId').datagrid('loadData', bacmap.jsonMap);
                    		 setTimeout(function(){
                    			 $('#importComExlButton').linkbutton('enable');
                    			 $('#dg').datagrid("reload");
                    		 },1000);
                    	 }
                      }
             });	
           }
        }
    </script>

</body>
</html>

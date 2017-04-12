<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<style type="text/css">
    	textarea{ resize:none; width:660px; height:200px;}
	</style>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('feedbackAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增意见反馈
  </div> 
  
    <div>
		<table class="commonTable" cellspacing="1">
	       <!--  <tr>
	       	提出人:默认是当前用户
	       	提出时间:默认是当前时间点
	            <td class="columnTitle">提出人：</td>
	            <td class="tableContent"><input type="text" name="inputBy" value=""/></td>
	            <td class="columnTitle">提出时间：</td>
	            <td class="tableContent">
	            	<input type="text" style="width:90px;" name="inputTime"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	 -->
	        <tr>
	            <td class="columnTitle">标题：</td>
	            <td class="tableContent"><input type="text" name="title" value=""/></td>
	             <td class="columnTitle">分类:</td>
	            <td class="tableContent">
	            	<select name="classType">
	            		<option value="">--请选择--</option>
	            		<option value="1">管理</option>
	            		<option value="2">安全</option>
	            		<option value="3">建议</option>
	            		<option value="4">其他</option>
	            	</select>
	            </td>
	           
	        </tr>	
	        <tr>
	            <td class="columnTitle">联系方式：</td>
	            <td class="tableContent"><input type="text" name="tel" value=""/></td>
	            <td class="columnTitle">是否公开:</td>
	             <td class="tableContent">
	            	<select name="isShare">
	            		<option value="">--请选择--</option>
	            		<option value="0">不公开</option>
	            		<option value="1">公开</option>
	            	</select>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">内容：</td>
	            <td class="tableContent">
	            <textarea rows="16" cols="value" name="content" ></textarea>
	            </td>
	            </tr>
	       <!--  <tr>
	       	解决人:默认是上级领导
	       	解决时间:默认是上级领导答复时间点
	            <td class="columnTitle">解决人：</td>
	            <td class="tableContent"><input type="text" name="answerBy" value=""/></td>
	            <td class="columnTitle">解决时间：</td>
	            <td class="tableContent">
	            	<input type="text" style="width:90px;" name="answerTime"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr> -->	
	       <!--  <tr>
	            <td class="columnTitle">解决办法：</td>
	            <td class="tableContent"><input type="text" name="solveMethod" value=""/></td>
	            <td class="columnTitle">解决方式:</td>
	            <td class="tableContent">
	            	<select name="resolution">
	            		<option value="">--请选择--</option>
	            		<option value="1">已修改</option>
	            		<option value="2">无需修改</option>
	            		<option value="3">重复问题</option>
	            		<option value="4">描述不完整</option>
	            		<option value="5">无法再现</option>
	            		<option value="6">其他</option>
	            	</select>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">解决难度:</td>
	            <td class="tableContent">
	            	<select name="difficulty">
	            		<option value="">--请选择--</option>
	            		<option value="1">极难</option>
	            		<option value="2">比较难</option>
	            		<option value="3">有难度</option>
	            		<option value="4">一般</option>
	            	</select>
	            </td>
	        </tr> -->	
		</table>
	</div>
 
 
</form>
</body>
</html>


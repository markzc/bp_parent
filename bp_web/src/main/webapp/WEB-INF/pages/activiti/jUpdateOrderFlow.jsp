<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="sid" value="${sid }">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('orderFlowAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
   修改采购单
  </div> 
  </div>
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">商品名称：</td>
	            <td class="tableContent"><input type="text" name="productName" value="${productName}"/></td>
	            <td class="columnTitle">数量：</td>
	            <td class="tableContentAuto">
	            	<input type="text" name="count" value="${count}"/>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">单价：</td>
	            <td class="tableContent"><input type="text" name="price" value="${price}"/></td>
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="desc" value="${desc}"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">生产厂家：</td>
	            <td class="tableContent"><input type="text" name="factory" value="${factory}"/></td>
	            <td class="columnTitle">重量：</td>
	            <td class="tableContent"><input type="text" name="weight" value="${weight}"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">采购人：</td>
	            <td class="tableContent"><input type="text" name="uname" value="${uname}"/></td>
	        </tr>
		</table>
	</div>
 
 
</form>
</body>
</html>


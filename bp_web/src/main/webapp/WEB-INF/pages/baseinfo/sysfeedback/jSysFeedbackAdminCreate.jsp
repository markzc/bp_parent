<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id }">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('sysFeedbackAction_adminsave','_self');this.blur();">保存</a></li>

<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
     
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   处理系统反馈
  </div> 
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">处理人：</td>
	            <td class="tableContent">
	            	${_CURRENT_USER.userInfo.name }
	            </td>
	            <td class="columnTitle">处理时间：</td>
	            <td class="tableContent">
	             	<input type="text" style="width:90px;" name="answerTime"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>
	       	<tr>
	        	<td class="columnTitle">处理难度：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="difficulty" value="3" checked class="input">★★★
	            	<input type="radio" name="difficulty" value="2" class="input">★★
	            	<input type="radio" name="difficulty" value="1" class="input">★
	            </td>
	        </tr>	
	        <tr>
	        	<td class="columnTitle">处理方式：</td>
	            <td class="tableContent"><textarea name="resolution" style="height:150px;"></textarea>
	        </tr>
		</table>
	</div>
   
</form>
</body>
</html>


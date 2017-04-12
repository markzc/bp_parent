<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id }"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('sysFeedbackAction_update','_self');this.blur();">保存</a></li>

<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
     
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改系统反馈
  </div> 
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">提出人：</td>
	            <td class="tableContent">${inputBy }</td>
	            <td class="columnTitle">提出时间：</td>
	            <td class="tableContent">
	             	<%-- <input type="text" style="width:90px;" name="inputTime"
	            	 value="${inputTime }"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/> --%>
	          	${inputTime }
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">标题：</td>
	            <td class="tableContent"><input type="text" name="title" value="${title }"/></td>
	            
	        </tr>
	        <tr>
	        	<td class="columnTitle">问题描述：</td>
	            <td class="tableContent"><textarea name="content" style="height:150px;">${content}</textarea></td>
	        </tr>
		</table>
	</div>
</form>
</body>
</html>


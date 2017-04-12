<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改密码</title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" id="inform" method="post" >
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
<li id="back"><a href="javascript:void(0)"  onclick="formSubmit('${ctx }/sysadmin/userAction_updatePassword','_self');this.blur();">修改</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
 修改密码
  </div> 
  
    <div>
		<table class="commonTable" cellspacing="1">

	        <font color="red">${err }</font>
	        <tr>
	            <td class="columnTitle">原密码：</td>
	            <td class="tableContent"><input type="password" name="password" /></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">新密码：</td>
	            <td class="tableContent"><input type="password" name="newpassword"/></td>
	            <td class="columnTitle">确定密码：</td>
	            <td class="tableContent"><input type="password" name="repassword"/></td>
	        </tr>

		</table>
	</div>
 
 
</form>

<script type="text/javascript">
   
   
   
</script>

</body>
</html>


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>个人信息</title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
<li id="update"><a href="#" onclick="formSubmit('${ctx }/sysadmin/userAction_edituser','_self');this.blur();">修改</a></li>
<li id="work_assign"><a href="#" onclick="formSubmit('${ctx }/sysadmin/userAction_editPassword','_self');this.blur();">修改密码</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
  个人基本信息
  </div> 
  
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">用户名：</td>
	            <td class="tableContent">${username}</td>
	            <td class="columnTitle">真实姓名：</td>
	            <td class="tableContent">${userInfo.name}</td>
	           	
	        </tr>	
	        <tr>
	            <td class="columnTitle">入职时间</td>
	            <td class="tableContent">${userInfo.joinDate}</td>
	            <td class="columnTitle">薪水：</td>
	            <td class="tableContent">${userInfo.salary}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">生日：</td>
	            <td class="tableContent">${userInfo.birthday}</td>
	            <td class="columnTitle">性别：</td>
	            <td class="tableContent">
		            <c:if test="${userInfo.gender==1}">男</c:if>
		            <c:if test="${userInfo.gender==0}">女</c:if>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">岗位：</td>
	            <td class="tableContent">${userInfo.station}</td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent">${userInfo.telephone}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">等级：</td>
	            <td class="tableContent"><pre>${userInfo.degree}</pre></td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><pre>${userInfo.remark}</pre></td>
	           
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>


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
<li id="back"><a href="#" onclick="formSubmit('${ctx }/sysadmin/userAction_saveuser','_self');this.blur();">保存</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
  修改基本信息
  </div> 
  
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">用户名：</td>
	            <td class="tableContent"><input type="text" name="username" value="${username}" /></td>
	            <td class="columnTitle">真实姓名：</td>
	            <td class="tableContent"><input type="text" name="userInfo.name" value="${userInfo.name}" /></td>
	            
	        </tr>	
	        <tr>
	            <td class="columnTitle">入职时间</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="userInfo.joinDate"
	            	 value="<fmt:formatDate value='${userInfo.joinDate}' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	            <td class="columnTitle">薪水：</td>
	            <td class="tableContent"><input type="text" name="userInfo.salary" value="${userInfo.salary}" /></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">生日：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="userInfo.birthday"
	            	 value="<fmt:formatDate value='${userInfo.birthday}' pattern='yyyy-MM-dd' />"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	            <td class="columnTitle">性别：</td>
	            <td >
	            	<input type="radio" name="userInfo.gender" value="1" <c:if test="${userInfo.gender==1}">checked="checked"</c:if>>男
	            	<input type="radio" name="userInfo.gender" value="0" <c:if test="${userInfo.gender==0}">checked="checked"</c:if>>女
		            
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">岗位：</td>
	            <td class="tableContent"><input type="text" name="userInfo.station" value="${userInfo.station}" /></td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="userInfo.telephone" value="${userInfo.telephone}" /></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">等级：</td>
	            <td class="tableContent"><pre><input type="text" name="userInfo.degree" value="${userInfo.degree}" /></pre></td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><pre><input type="text" name="userInfo.remark" value="${userInfo.remark}" /></pre></td>
	           
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>


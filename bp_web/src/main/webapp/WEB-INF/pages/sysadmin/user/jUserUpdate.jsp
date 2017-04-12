<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
      <input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('userAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改用户
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">所在部门：</td>
	             <td class="tableContent">
	            	<s:select name="dept.id" list="deptList"
	            		listKey="id" listValue="deptName"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">用户名：</td>
	            <td class="tableContent"><input type="text" name="username" value="${username }"/></td>
	        </tr>	
	         <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto">
	              <input type="radio" name="state" class="input" ${state==0?'checked':'' } value="0">停用 
	              <input type="radio" name="state" class="input"  ${state==1?'checked':'' } value="1">启用 
	            </td>
	        </tr>
	         <tr>
	            <td class="columnTitle">直属领导：</td>
	            <td class="tableContent">
	            	<s:select name="userInfo.manager.id" list="#userList" 
	            		listKey="id" listValue="userInfo.name"
	            		headerKey="" headerValue="--请选择--"
	            	></s:select>
	            </td>
	        </tr>
	        <tr>
	        	<td class="columnTitle">等级：</td>
	            <td class="tableContentAuto">
	            	<s:radio list="#{'0':'超级管理员','1':'跨部门跨人员','2':'管理所有下属部门和人员','3':'管理本部门','4':'普通员工'}" name="userInfo.degree" lable="等级" />
	            </td>
	        </tr>		
		</table>
	</div>
 </form>
</body>
</html>
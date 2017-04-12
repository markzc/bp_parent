<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="${ctx }/sysadmin/taskAction_list.action">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
   浏览任务
  </div> 
  </div>
  </div>
  
<!-- <td class="tableHeader">创建人</td>
		<td class="tableHeader">创建人的部门</td>
		<td class="tableHeader">开始时间</td>
		<td class="tableHeader">截止时间</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">修改人</td>
		<td class="tableHeader">修改的时间</td> -->
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">任务：</td>
	            <td class="tableContent">${module.name}</td>
	      
	            <td class="columnTitle">创建人：</td>
	            <c:forEach items="${createUserList }" var="createUser">
	            <td class="tableContent">
	            	
	            		${createUser.username }
	            	
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">创建人部门：</td>
	            <td class="tableContent">${createUser.dept.deptName}</td>
	      </c:forEach>
	            <td class="columnTitle">创建时间：</td>
	            <td class="tableContent"><fmt:formatDate value="${createTime }" pattern="yyyy-MM-dd"/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">截止时间：</td>
	            <td class="tableContent"><fmt:formatDate value="${finishDate }" pattern="yyyy-MM-dd"/></td>
	      
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent">
	            	<c:if test="${state==0 }">未完成</c:if>
	            	<c:if test="${state==1 }">已完成</c:if>
	            	<c:if test="${state==2 }">已转交</c:if>
	            		<c:if test="${task.state==3 }">已退回</c:if>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">修改人：</td>
	            <td class="tableContent">${username}</td>
	      
	            <td class="columnTitle">修改时间：</td>
	            <td class="tableContent"><fmt:formatDate value="${updateTime }" pattern="yyyy-MM-dd"/></td>
	        </tr>
	         <tr>
	            <td class="columnTitle">任务描述</td>
	            <td class="tableContent">${description}</td>
	        
	       </tr>		
		</table>
	</div>
 
 
</form>
</body>
</html>


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   查看反馈
  </div> 
  
  <c:if test="${state==0 }">
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">提出人：</td>
	            <td class="tableContent">
	            	${inputBy }
	            </td>
	            <td class="columnTitle">提出时间：</td>
	            <td class="tableContent">
					<fmt:formatDate value='${inputTime }' pattern='yyyy-MM-dd' />
				</td>
	        </tr>	
	         <tr>
	         	<td class="columnTitle">标题：</td>
	            <td class="tableContent">
	            	${title }
	            </td>
	            <td class="columnTitle">问题描述：</td>
	            <td class="tableContent">
	            	${content }
	            </td>
	        </tr>	
	        <tr>
	        	<td class="columnTitle">状态：</td>
	            <td class="tableContent">${state==0?'未处理':'已处理' }</td>
	        </tr>	
		</table>
	</div>
	</c:if>
	<c:if test="${state==1 }">
		 <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">提出人：</td>
	            <td class="tableContent">
	            	${inputBy }
	            </td>
	            <td class="columnTitle">提出时间：</td>
	            <td class="tableContent">
					<fmt:formatDate value='${inputTime }' pattern='yyyy-MM-dd' />
				</td>
	        </tr>	
	         <tr>
	         	<td class="columnTitle">标题：</td>
	            <td class="tableContent">
	            	${title }
	            </td>
	            <td class="columnTitle">问题描述：</td>
	            <td class="tableContent">
	            	${content }
	            </td>
	        </tr>
	        <tr>
	        	<td class="columnTitle">处理人：</td>
	            <td class="tableContent">
	            	${answerBy }
	            </td>
	            <td class="columnTitle">处理时间：</td>
	            <td class="tableContent">
	            	${answerTime }
	            </td>
	        </tr>	
	        <tr>
	        	 <td class="columnTitle">处理方式：</td>
	            <td class="tableContent">
	            	${resolution }
	            </td>
	        	<td class="columnTitle">处理难度：</td>
	            <td class="tableContentAuto">
	            	${difficulty==3?"★★★":(difficulty==2?"★★":"★") } 
	            </td>
	        </tr>
	        <tr>
	        	<td class="columnTitle">状态：</td>
	            <td class="tableContent">${state==0?'未处理':'已处理' }</td>
	        </tr>	
		</table>
	</div>
	</c:if>
 </form>
</body>
</html>
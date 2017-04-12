<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<style type="text/css">
		textarea{ resize:none; width:660px; height:200px;}
    	#title{
    		width:300px;
    	}
	</style>
	
	</style>
	
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('messageAction_reply','_self');this.blur();">发送</a></li>
<li id="back"><a href="#" onclick="formSubmit('messageAction_list','_self');this.blur();">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   留言回复
  </div> 
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">收件人：</td>
	            <td class="tableContent">${userInfo.name }</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">发送时间：</td>
	            <td class="tableContent">${createTime }</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">留言内容：</td>
	            <td class="tableContent">${ms}</td>
	        </tr>	
	        
	        <tr >
	            <td class="columnTitle">回复信息：</td>
	            <td class="tableContent">
	            	<textarea rows="100" cols="100" name = "message" value="${message}"></textarea>
	            </td>
	        </tr>	
		</table>
	</div>
</form>
</body>
</html>


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
	/* function reloadleft(){
		window.location.href="/jk_parent/";
	} */
	</script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="messageAction_list.action">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
  查看留言
  </div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">发件人：</td>
	            <td class="tableContent">${name}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">题目：</td>
	            <td class="tableContent">${title}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">信息内容：</td>
	            <td class="tableContent">${message}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">发件时间：</td>
	            <td class="tableContent">${createTime}</td>
	        </tr>	
		</table>
	</div>
</form>
</body>
</html>


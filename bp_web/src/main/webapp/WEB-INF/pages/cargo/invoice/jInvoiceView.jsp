<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   浏览
  </div>
  
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">合同编号：</td>
	            <td class="tableContent">${scNo}</td>
	            <td class="columnTitle">价钱：</td>
	            <td class="tableContent">${price}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">信用证号：</td>
	            <td class="tableContent">${blNo}</td>
	            <td class="columnTitle">买家：</td>
	            <td class="tableContent">${buyer}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">委托单编号：</td>
	            <td class="tableContent">${tradeTerms}</td>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent">${id}</td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>


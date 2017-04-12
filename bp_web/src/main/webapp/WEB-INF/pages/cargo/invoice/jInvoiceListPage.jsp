<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<li id="view"><a href="#" onclick="formSubmit('invoiceAction_toview','_self');this.blur();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('invoiceAction_printInvoice','_self');this.blur();">打印</a></li>
<li id="update"><a href="#" onclick="formSubmit('invoiceAction_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('invoiceAction_delete','_self');this.blur();">删除</a></li>
<li id="submit"><a href="#" onclick="formSubmit('invoiceAction_subState','_self');this.blur();">完成</a></li>
<li id="delete"><a href="#" onclick="formSubmit('invoiceAction_lowState','_self');this.blur();">取消</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/folder_edit.png"/>
   委托列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead >
	<tr align="left">
		<td class="tableHeader">
		<input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">报运单编号</td>
		<td class="tableHeader">信用证号</td>
		<td class="tableHeader">委托单编号</td>
		<td class="tableHeader">价钱</td>
		<td class="tableHeader">买家</td>
		<td class="tableHeader">卖家</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.scNo}</td>
		<td>${o.blNo}</td>
		<td>${o.tradeTerms}</td>
		<td>${o.price}</td>
		<td>${o.buyer}</td>
		<td>${o.saller}</td>
		<td>
			<c:if test="${o.state==0}">草稿</c:if>
		    <c:if test="${o.state==1}">已完成</c:if>
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>


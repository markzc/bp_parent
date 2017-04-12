<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<!-- <div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('sysFeedbackAction_insert','_self');this.blur();">保存</a></li>

<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div> -->
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    系统反馈列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">提出人</td>
		<td class="tableHeader">提出时间</td>
		<td class="tableHeader">标题</td>
		<td class="tableHeader">处理人</td>
		<td class="tableHeader">处理时间</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.inputBy}</td>
		<td>${o.inputTime}</td>
		<td>${o.title}</td>
		<td>${o.answerBy}</td>
		<td>${o.answerTime}</td>
		<td>
			<c:if test="${o.state==0 }">未处理</c:if>
			<c:if test="${o.state==1 }">已处理</c:if>
		</td>
		<td>
			<c:if test="${o.state==0 }">
				<a href="${ctx }/sysFeedbackAction_toadminview?id=${o.id}">[查看]</a>
				<a href="${ctx }/sysFeedbackAction_gotopage?id=${o.id}">[处理]</a>
			</c:if>
			<c:if test="${o.state==1 }">
				<a href="${ctx }/sysFeedbackAction_toadminview?id=${o.id}">[查看]</a>
				<a href="javascript:void(0);" style="color: gray;">[处理]</a> 
			</c:if>		
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


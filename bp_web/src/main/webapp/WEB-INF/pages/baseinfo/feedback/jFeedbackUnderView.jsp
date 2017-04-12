<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="${ctx}/js/jquery-1.11.3.min.js"></script>
<head>
	<title></title>
		<c:if test="${msg !=null }">
			<script type="text/javascript">
				alert('${msg }');
				location.href = "feedbackAction_tounderling";
			</script>
		</c:if>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="view"><a href="#" onclick="formSubmit('feedbackAction_toview','_self');this.blur();">查看</a></li>
	<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    下级意见反馈列表
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
		<td class="tableHeader">分类<!-- :1管理2安全3建议4其他 --></td>
		<td class="tableHeader">状态<!-- :0未处理1已处理 --></td>
		<td class="tableHeader">操作<!-- :0未处理1已处理 --></td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.inputBy}</td>
		<td>${o.inputTime}</td>
		<td>${o.title}</td>
		<td>
			<c:if test="${o.classType == 1}">
				管理
			</c:if>
			<c:if test="${o.classType == 2}">
				安全
			</c:if>
			<c:if test="${o.classType == 3}">
				建议
			</c:if>
			<c:if test="${o.classType == 4}">
				其他
			</c:if>
		</td>
		<td>
			<c:if test="${o.state ==0}">
				未处理
			</c:if>
			<c:if test="${o.state ==1}">
				已处理
			</c:if>
		</td>
		<td>
			<c:if test="${o.state ==1}">
				<a href="feedbackAction_deleteById?id=${o.id }" onclick="formSubmit('feedbackAction_deleteById','_self');this.blur();">[删除]</a>
			</c:if>
			<c:if test="${o.state ==0}">
				<a href="feedbackAction_toanswer?id=${o.id }" >[答复]</a>
				<a href="feedbackAction_deleteById?id=${o.id }">[删除]</a>
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


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
/* 	function choice(id){
		alert(id);
		$("#userId").html("<option value=''>--请选择--</option>");
		$.post("${ctx}/sysadmin/taskAction_choice.action",
				{"modId":id},
				function(data){
					
					$(data).each(function(i,n){
						$("#userId").append("<option value='"+n.id+"'>"+n.username+"</option>");
					});
				
				},
				"json")
	}; */
	
	/* $(function(){
		$("#userId").html("<option value=''>--请选择--</option>");
		$.post("${ctx}/sysadmin/taskAction_choice.action",
				{"modId":'${module.id}'},
				function(data){
					
					$(data).each(function(i,n){
						$("#userId").append("<option value='"+n.id+"'>"+n.username+"</option>");
					});
				
				},
				"json")
	}); */
	
	</script>
</head>

<body>
<form name="form" method="post">
   <input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('taskAction_transmit','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   转交任务
  </div> 
  
    <div>
		<table class="commonTable" >
	        <tr>
	            <td class="columnTitle">任务</td>
	            <td class="tableContent">
	            	<input id="inputId" type="text" style="width: 200px" value="${module.name }" name="module.id" onload="choice('${module.id }')">
	            	
	            		
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">员工</td>
	            
	          
	            <td class="tableContent">
	            	<select name="user.id" id="userId">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${userList }" var="u">
	            			<c:if test="${user.id==u.id }">
	            			<option value="${u.id }" selected="selected">${u.username }</option>
	            			</c:if>
	            			<c:if test="${user.id!=u.id }">
	            			<option value="${u.id }" >${u.username }</option>
	            			</c:if>
	            		</c:forEach>
	            		
	            	</select>
	            </td>
	           </tr>
	           
	      
	       
	       
		</table>
	</div>
 
 
</form>
</body>
</html>


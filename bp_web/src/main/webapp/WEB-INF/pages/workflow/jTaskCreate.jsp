<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	function choice(id){
		
		$("#userId").html("<option value=''>--请选择--</option>");
		$.post("${ctx}/sysadmin/taskAction_choice.action",
				{"modId":id},
				function(data){
					
					$(data).each(function(i,n){
						$("#userId").append("<option value='"+n.id+"'>"+n.username+"</option>");
					});
				
				},
				"json")
	};
	</script>
</head>

<body>
<form name="form" method="post">
   
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('taskAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   分配任务
  </div> 
  
    <div>
		<table class="commonTable" >
	        <tr>
	            <td class="columnTitle">任务</td>
	            <td class="tableContent">
	            	<select name="module.id" onchange="choice(this.value)">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${ moduleList }" var="module">
	            			<option value="${module.id }">${module.name }</option>
	            		</c:forEach>
	            	</select>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">员工</td>
	            <td class="tableContent">
	            	<select name="user.id" id="userId">
	            		<option value="">--请选择--</option>
	            	</select>
	            </td>
	        </tr>	
	       
	       <tr>
	            <td class="columnTitle">开始时间</td>
	            <td class="tableContent"><input type="text" style="width:90px;" name="createTime"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">结束时间</td>
	            <td class="tableContent"><input type="text" style="width:90px;" name="finishDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
	       
	        </tr>	
	         <tr>
	            <td class="columnTitle">任务描述</td>
	            <td class="tableContent"><input type="text" style="width: 450px "  name="description"/></td>
	        
	       </tr>
	       
		</table>
	</div>
 
 
</form>
</body>
</html>


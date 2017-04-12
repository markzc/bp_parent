<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	function choice(id){
		alert();
		$("#userId").html("<option value=''>--请选择--</option>");
		$.post("${ctx}/sysadmin/taskAction_choice.action",
				{"modId":id},
				function(data){
					
					$(data).each(function(i,n){
						$("#userId").append("<option value='"+n.id+"'>"+n.username+"</option>");
					});
					$("#userId option[value='${user.id}']").prop("selected",true)
				},
				"json")
	};
	</script>
</head>

<body>
<form name="form" method="post">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="userId" value="${user.id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('taskAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改任务
  </div> 
  
    <div>
		<table class="commonTable" >
	        <tr>
	            <td class="columnTitle">任务</td>
	            <td class="tableContent">
	            <input type="hidden" style="width: 200px "  value="${module.id }" name="module.id"/>
	            <input type="text" style="width: 200px " readonly="readonly" value="${module.name }" />
	            
	            	<%-- <s:select list="moduleList" onchange="choice(this.value)"  listKey="module.id" listValue="module.name" headerKey="" headerValue="--请选择--"></s:select> --%>
	            	<%-- <select name="module.id" onchange="choice(this.value)">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${ moduleList }" var="m">
	            			<c:if test="${module.id==m.id }">
	            			
	            			<option value="${m.id }" selected="selected">${m.name }</option>
	            			</c:if>
	            			<c:if test="${module.id!=m.id }">
	            			<option value="${m.id }" >${m.name }</option>
	            			</c:if>
	            		</c:forEach>
	            	</select> --%>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">员工</td>
	            <td class="tableContent">
	            <input type="hidden" style="width: 200px "  value="${user.id }" name="user.id"/>
	            <input type="text" style="width: 200px " readonly="readonly" value="${user.username }"/>
	            	<%-- <select name="user.id" id="userId">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${userList }" var="u">
	            			<c:if test="${user.id==u.id }">
	            			<option value="${u.id }" selected="selected">${u.username }</option>
	            			</c:if>
	            			<c:if test="${user.id!=u.id }">
	            			<option value="${u.id }" >${u.username }</option>
	            			</c:if>
	            		</c:forEach>
	            		
	            	</select> --%>
	            </td>
	        </tr>	
	       
	       <tr>
	            <td class="columnTitle">开始时间</td>
	            <td class="tableContent"><input type="text" style="width:90px;" name="createTime"
	            	 value="<s:date name="createTime"  format="yyyy-MM-dd"/>"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
	        </tr>	
	        <tr>
	        
	            <td class="columnTitle">结束时间</td>
	            <td class="tableContent"><input type="text" style="width:90px;" name="finishDate"
	            	 value="<s:date name="finishDate"  format="yyyy-MM-dd"/>"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
	       
	        </tr>	
	        <c:if test="${_CURRENT_USER.id!=createBy }">
	         <tr>
	            <td class="columnTitle">修改人</td>
	            <td class="tableContent">
	            	<select name="updateBy">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${userList }" var="u">
	            			<option value="${u.id }" >${u.username }</option>
	            		</c:forEach>
	            		
	            	</select>
	            </td>
	        
	      </tr>
	         <tr>
	            <td class="columnTitle">修改时间</td>
	            <td class="tableContent"><input type="text" style="width:90px;" name="updateTime"
	            	 value="${updateTime }"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
	        
	       </tr>
	       </c:if>
	       <tr>
	            <td class="columnTitle">任务描述</td>
	            <td class="tableContent"><input type="text" style="width: 450px " value="${description }" name="description"/></td>
	        
	       </tr>
	       
		</table>
	</div>
 
 
</form>
</body>
</html>


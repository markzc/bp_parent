<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('feedbackAction_answer','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   答复下级反馈
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">提出人：</td>
	            <td class="tableContent"><%-- <input type="text" name="inputBy" value=" --%>${inputBy }<!-- " readonly="readonly"/> --></td>
	            <td class="columnTitle">提出时间：</td>
	            <td class="tableContent">
	            	${inputTime }
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">标题：</td>
	            <td class="tableContent">${title }</td>
	            <td class="columnTitle">内容：</td>
	            <td class="tableContent">${ content}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">分类:</td>
	            <td class="tableContent">
	            	<select name="classType" >
	            		<option value="">--请选择--</option>
	            		<c:if test="${classType==1 }">
	            			<option value="1" selected="selected">管理</option>
	            			<option value="2">安全</option>
	            			<option value="3">建议</option>
	            			<option value="4">其他</option>
	            		</c:if>
	            		<c:if test="${classType==2 }">
	            			<option value="1">管理</option>
	            			<option value="2" selected="selected">安全</option>
	            			<option value="3">建议</option>
	            			<option value="4">其他</option>
	            		</c:if>
	            		<c:if test="${classType==3 }">
	            			<option value="1">管理</option>
	            			<option value="2">安全</option>
	            			<option value="3" selected="selected">建议</option>
	            			<option value="4">其他</option>
	            		</c:if>
	            		<c:if test="${classType==4 }">
	            			<option value="1">管理</option>
	            			<option value="2">安全</option>
	            			<option value="3">建议</option>
	            			<option value="4" selected="selected">其他</option>
	            		</c:if>
	            	</select>
	            </td>
	            <td class="columnTitle">联系方式：</td>
	            <td class="tableContent">${tel }</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">解决人：</td>
	            <td class="tableContent"><%-- <input type="text" name="answerBy" value="${answerBy }" readonly="readonly"/> --%>
	            
	            	<s:select list="userList" headerKey="" headerValue="--请选择--" listKey="answerBy" listValue="userInfo.name" name="answerBy"></s:select>
	            
	            </td>
	            <td class="columnTitle">解决时间：</td>
	            <td class="tableContent">
	            	<input type="text" style="width:90px;" name="answerTime"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">解决办法：</td>
	            <td class="tableContent"><input type="text" name="solveMethod" value=""/></td>
	            <td class="columnTitle">解决方式:</td>
	            <td class="tableContent">
	            	<select name="resolution">
	            			<option value="">--请选择--</option>
	            			<option value="1">已修改</option>
	            			<option value="2">无需修改</option>
	            			<option value="3">重复问题</option>
	            			<option value="4">描述不完整</option>
	            			<option value="5">无法再现</option>
	            			<option value="6">其他</option>
	            	</select>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">解决难度:</td>
	            <td class="tableContent">
	            	<select name="difficulty">
	            			<option value="">--请选择--</option>
	            			<option value="1">极难</option>
	            			<option value="2">比较难</option>
	            			<option value="3">有难度</option>
	            			<option value="4">一般</option>
	            	</select>
	            </td>
	            <td class="columnTitle">是否公开:</td>
	             <td class="tableContent">
	            	<select name="isShare">
	            		<option value="">--请选择--</option>
	            		<c:if test="${isShare==0 }">
	            			<option value="0" selected="selected">不公开</option>
	            			<option value="1">公开</option>
	            		</c:if>
	            		<c:if test="${isShare==1 }">
	            			<option value="0">不公开</option>
	            			<option value="1" selected="selected">公开</option>
	            		</c:if>
	            	</select>
	            </td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>


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
<li id="save"><a href="#" onclick="formSubmit('feedbackAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改反馈详细信息
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
			<c:if test="${isCurUser != yes }">
				 <tr>
		            <td class="columnTitle">提出人：</td>
		            <td class="tableContent">${inputBy}</td>
		            <td class="columnTitle">提出时间：</td>
		            <td class="tableContent">
		            <input type="text" style="width:90px;" name="inputTime"
	            	 value="${inputTime }"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
		            </td>
		        </tr>	
		        <tr>
		            <td class="columnTitle">标题：</td>
		            <td class="tableContent"><input type="text" name="title" value="${title }"/></td>
		            <td class="columnTitle">内容：</td>
		            <td class="tableContent"><input type="text" name="content" value="${ content}"/></td>
		        </tr>
		        <tr>
	       			 <td class="columnTitle">联系方式：</td>
	           	 <td class="tableContent"><input type="text" name="tel" value="${tel }"/></td>
			</c:if>
			<c:if test="${isCurUser == yes }">
				 <tr>
			           <td class="columnTitle">提出人：</td>
			           <td class="tableContent">${inputBy}</td>
			           <td class="columnTitle">提出时间：</td>
			           <td class="tableContent">
			           <input type="text" style="width:90px;" name="inputTime"
	            	 value="${inputTime }"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
		            </td>
			           </td>
			       </tr>	
			       <tr>
			           <td class="columnTitle">标题：</td>
			           <td class="tableContent"><input type="text" name="title" value="${title }"/></td>
			           <td class="columnTitle">内容：</td>
			           <td class="tableContent"><input type="text" name="content" value="${ content}"/></td>
			       </tr>	
			   <tr>
	       		 <td class="columnTitle">联系方式：</td>
	            <td class="tableContent"><input type="text" name="tel" value="${tel }"/></td>
			</c:if>
	       	
	       
	            <td class="columnTitle">分类:</td>
	            <td class="tableContent">
	            	<select name="classType">
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
	        </tr>	
	        
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


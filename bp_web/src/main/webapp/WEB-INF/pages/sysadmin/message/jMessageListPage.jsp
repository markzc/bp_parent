<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
		var sId;
	    function isOnlyChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				}

			if(count==1)
				return true;
			else
				return false;
	     }
	    function isChecked(){
	    	 var checkBoxArray = document.getElementsByName('id');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				}

			if(count>=1)
				return true;
			else
				return false;
	     }
	     function toView(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('messageAction_toview','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     function todelete(){
	    	 if(isChecked()){
	    		 formSubmit('messageAction_delete','_self');
	    	 }else{
	    		 alert("请最少选择一项，再进行操作！");
	    	 }
	     }
	  
	</script>
	
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="javascript:toView();">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('messageAction_tocreate','_self');this.blur();">新增</a></li>
<li id="delete"><a href="#" onclick="javascript:todelete();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    留言列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		
		<td class="tableHeader">序号</td>
		<td class="tableHeader">主题</td>
		<td class="tableHeader">发件人</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">时间</td>
		<td class="tableHeader">详情</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.title}</td>
		<td>
		<c:forEach items="${userList}" var="u" varStatus="sta">
        	<c:if test="${u.id == o.senderId}">
        		${u.userInfo.name}
        	</c:if>
        </c:forEach>   
		</td>
		<td>
			<c:if test="${o.state==0}">未读</c:if>
			<c:if test="${o.state==1}">已读</c:if>
		</td>
		<td>
			 <fmt:formatDate value="${o.createTime }" pattern="yyyy/MM/dd hh:mm:ss"/> 
		</td>

		<td>
			<a href="messageAction_toview.action?id=${o.id }">查看详情</a> ||
			<a href="messageAction_toreply.action?id=${o.id }">回复信息</a>
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


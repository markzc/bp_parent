<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
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
		function toView(){
		   	 if(isOnlyChecked()){
		   		 formSubmit('baseinfo_toview','_self');
		   	 }else{
		   		 alert("请先选择一项并且只能选择一项，再进行操作！");
		   	 }
	    }
		function toUpdate(){
		   	 if(isOnlyChecked()){
		   		 formSubmit('baseinfo_toupdate','_self');
		   	 }else{
		   		 alert("请先选择一项并且只能选择一项，再进行操作！");
		   	 }
	    }
		function toDelete(id){
		   	 if(confirm("确定要删除？")){
		   		 formSubmit('baseinfo_deletes?id='+id,'_self');	   		 
		   	 }
	    }
		function toAdd(){
		    formSubmit('baseinfo_toadd','_self');
	    }
	</script>
</head>

<body>
<form name="icform" method="post" >
 
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="javascript:toView()">查看</a></li>
<li id="print"><a href="#" onclick="javascript:toUpdate();">修改</a></li>
<li id="new"><a href="#" onclick="javascript:toAdd()">添加</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
	厂家列表
  </div> 


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">类型</td>
		<td class="tableHeader">厂家全称</td>
		<td class="tableHeader">名称缩写</td>
		<td class="tableHeader">联系人</td>
		<td class="tableHeader">电话</td>
		<td class="tableHeader">手机</td>
		<td class="tableHeader">传真</td>
		<td class="tableHeader">地址</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">说明</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links}
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.ctype}</td>
		<td>
			${o.fullName}
		</td>
		<td >
			${o.factoryName}
		</td>
		<td>${o.contacts}</td>
		<td>${o.phone}</td>
		<td>${o.mobile}</td>
		<td>${o.fax}</td>
		<td>${o.address}</td>
		<td>${o.inspector}</td>
		<td>${o.remark}</td>
		<td>
			<c:if test="${o.state==1}">启用</c:if>
			<c:if test="${o.state==0}">草稿</c:if>
		</td>
		<td><a href="#" onclick="javascript:toDelete('${o.id}')">删除</a></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</div>


</div>
 
 
</form>
</body>
</html>


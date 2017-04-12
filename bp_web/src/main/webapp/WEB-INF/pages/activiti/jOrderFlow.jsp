<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
	<script>
	     function isOnlyChecked(){
	    	 var checkBoxArray = document.getElementsByName('sid');
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
	     function toupdate(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('${ctx}/orderFlowAction_toupdate','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     function deletes(){
	    	 var checkBoxArray = document.getElementsByName('sid');
				var count=0;
				for(var index=0; index<checkBoxArray.length; index++) {
					if (checkBoxArray[index].checked) {
						count++;
					}	
				}
			if(count>=1){
				formSubmit('orderFlowAction_delete','_self');
			}else{
				alert("请至少选择一个！");				
				return false;
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
<li id="update"><a href="javascript:void(0);" onclick="toupdate()">修改</a></li>
<li id="delete"><a href="#" onclick="deletes()">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
  <img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
采购单列表
  </div> 
  </div>
  </div>
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('sid',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">商品名称</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">单价</td>
		<td class="tableHeader">介绍</td>
		<td class="tableHeader">工厂</td>
		<td class="tableHeader">重量</td>
		<td class="tableHeader">采购人</td>
	</tr>
	</thead>
	<tbody class="tableBody" align="left">
	${links}
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="sid" value="${o.sid}"/></td>
		<td>${status.index+1}</td>
		<td>${o.productName}</td>
		<td>${o.count }</td>
		<td>${o.price }</td>
		<td>${o.desc}</td>
		<td>${o.factory}</td>
		<td>${o.weight}</td>
		<td>${o.uname}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>

</div>
 
</form>
</body>
</html>


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('financeAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx}/skin/default/images/icon/currency_yen.png"/>
   修改财务报运单
  </div> 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	             <td class="columnTitle">制单时间：</td>
	            <td class="tableContent">
	            <input type="text" style="width:90px;" name="inputDate"
	            	value="${inputDate}"
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	            <td class="columnTitle">委托船家：</td>
	            <td class="tableContent"><input type="text" name="shiper" value="${shiper}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">发票编号：</td>
	            <td class="tableContent"><input type="text" name="invoiceId" value="${invoiceId}"/></td>
	            <td class="columnTitle">买家：</td>
	            <td class="tableContent"><input type="text" name="buyer" value="${buyer}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">总价钱：</td>
	            <td class="tableContent"><input type="text" name="totalprice" value="${totalprice}"/></td>
	            <td class="columnTitle">报运单编号：</td>
	            <td class="tableContent"><input type="text" name="exportId" value="${exportId}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">合同编号：</td>
	            <td class="tableContent"><input type="text" name="contractIds" value="${contractIds}"/></td>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><input type="text" name="desc" value="${desc}"/></td>
	        </tr>	
		</table>
	</div>
	</div>
 
 
</form>
</body>
</html>


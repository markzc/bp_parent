<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
</head>

<body>
<form name="icform" method="post">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('shippingOrderAction_insert','_self');this.blur();">委托</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   委托
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
			<input type="hidden" name="id" value="${id }" />
	        <tr>
	            <td class="columnTitle">海运/空运：</td>
	            <td class="tableContent"><input type="text" name="orderType" value="${orderType }"/></td>
	        
	            <td class="columnTitle">船家：</td>
	            <td class="tableContent"><input type="text" name="shipper" value="${shipper }"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">提单抬头：</td>
	            <td class="tableContent"><input type="text" name="consignee" value="${consignee }"/></td>
	        
	            <td class="columnTitle">发票日期：</td>
	            <td class="tableContent">
	            <input type="text" style="width:90px;" name="invoiceDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">正本通知人：</td>
	            <td class="tableContent"><input type="text" name="notifyParty" value="${notifyParty}"/></td>
	        
	            <td class="columnTitle">信用证：</td>
	            <td class="tableContent"><input type="text" name="lcNo" value="${lcNo}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent"><input type="text" name="portOfLoading" value="${portOfLoading}"/></td>
	        
	            <td class="columnTitle">卸货港：</td>
	            <td class="tableContent"><input type="text" name="portOfDischarge" value="${portOfDischarge}"/></td>
	            
	        </tr>	
	        <tr>
	            <td class="columnTitle">份数：</td>
	            <td class="tableContent"><input type="text" name="copyNum" value="${copyNum }"/></td>
	        
	            <td class="columnTitle">扼要说明：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">运输要求：</td>
	            <td class="tableContent"><input type="text" name="specialCondition" value="${specialCondition}"/></td>
	        
	            <td class="columnTitle">运输说明：</td>
	            <td class="tableContent"><input type="text" name="freight" value="${freight}"/></td>
	        </tr>	
	        	
	        <tr>
	            <td class="columnTitle">装期：</td>
	        	<td class="tableContent">
	            <input type="text" style="width:90px;" name="loadingDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	            
	            <td class="columnTitle">效期：</td>
	            <td class="tableContent">
	             <input type="text" style="width:90px;" name="limitDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">是否分期：</td>
	            <td class="tableContent">
	            	<input style="width:30px;height: 15px;" type="radio" name="isBatch" value="1">是</input>
	            	<input style="width:30px;height: 15px;" type="radio" name="isBatch" value="0">否</input>
	            </td>
	        
	            <td class="columnTitle">是否转船：</td>
	            <td class="tableContent">
	            	<input style="width:30px;height: 15px;" type="radio" name="isTrans" value="1">是</input>
	            	<input style="width:30px;height: 15px;" type="radio" name="isTrans" value="0">否</input>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">复核人：</td>
	            <td class="tableContent"><input type="text" name="checkBy" value="${checkBy}"/></td>
	            <td class="columnTitle">转船港：</td>
	            <td class="tableContent"><input type="text" name="portOfTrans" value="${portOfTrans}"/></td>
	           
	        </tr>
		</table>
	</div>
 
</form>
</body>
</html>


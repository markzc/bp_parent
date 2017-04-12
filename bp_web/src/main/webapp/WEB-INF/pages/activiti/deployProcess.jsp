<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../base.jsp" %>
<html> 
<head>
<title>部署流程</title>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/main.css" media="all"/>
</head>
<body>
<form>
<div class="textbox"></div>

	<div class="modelDiv">

        <table class="modelTable" cellspacing="1">
        	<tr>
        		<td colspan="2" class="modelTitle">使用流程介绍</td>
        	</tr>
        	<tr>
        		<td colspan="2" class="subModelTitle">介绍</td>
        	</tr>
        	<tr>
				<td class="model_intro_left">部门介绍：</td>
				<td class="model_intro_right">董事长、总经理、行政部、销售部、船运部、财务部、开发部
				<p>销售部负责签订合同，录入和维护合同；船运部负责联系货代，安排集装箱，安排船期；同时负责向海关报关；财务部负责账务审核、开发票和收款；开发部负责软件系统的维护。</p>
				</td>
        	</tr>        	
			<tr>
				<td class="model_intro_left">使用介绍：</td>
				<td class="model_intro_right" align="left">
						 1)合同管理：合同由销售人员录入，并进行日常维护。一个合同包括多个货物信息，可能几款，可能几十款。每个货物可有几个附件信息，也可没有，附件就是包装纸等一些包装材料。
					<br/>2)出货表：按船期统计当月的出货情况，也就是当月签订的合同信息。支持导出到excel中，支持直接打印。销售部每月底出报表，送总经理审阅。具体格式详见《2报运单.xls》。
					<br/>3)报运管理：报运单是提交给海关的审核性文件。销售专责负责报运单的录入。报运单中的货物信息从合同中取。一个工作簿放11条货物信息，超过放下一个工作簿。
					<br/>4)HOME装箱单：也叫预装箱，是在装箱前，先做装箱计划，发给客户审核，客户同意后，才可装箱；如有调整，则修改出口报运单，可能拆成多个报运。。HOME装箱单是根据出口报运单制定。
					<br/>5)装箱管理：根据出口报运单制定装箱单，填写发票号、发票时间，以及客人等相关信息。发票号自动按规则生成，年度后2位+JK+当年自增序号（3位，一年基本上在几百单）。自动合计。它是用来发给货运公司，确定集装箱个数、规格及其费用。
					<br/>6)委托管理：根据装箱制定委托书，目前主要通过海洋运输，以后会有空运。委托内容主要是合计总的毛重、净重、体积，还有要将Quantity显示为英文，例如：68CTNS要显示为say:SIXTY AND EIGHT Only.。
					<br/>7)发票管理：根据装箱单、委托制定发票。发给客户，客户支付费用。自动合计，按美元结算。
					<br/>8)财务管理：根据报运单、委托、发票制定财务出口报运单，并加入税金，按1.17费率计算。（国家会每年度下发费率标准）。自动按发票进行统计。留底，报税。
				</td>
        	</tr>        	
			
			
			<tfoot>
				<tr>
					<td colspan="2" class="tableFooter"></td>
				</tr>
			</tfoot>
        </table>
 
	</div>
</form>
</body>

<!-- <body>
<form>
<div class="textbox"></div>

	<div class="modelDiv">

       <table class="modelTable" cellspacing="1">
        	<tr>
        		<td colspan="2" class="modelTitle">流程管理</td>
        	</tr>
        	<tr>
        		<td colspan="2" class="subModelTitle"></td>
        	</tr>
        	<tr>
				<td >
				</td>
				</tr>
        	<tr>
				<td >
				</td>
        	</tr>
        	<tr>
        		<td colspan="2" class="subModelTitle">流程介绍</td>
        	</tr>
        	<tr>
				<td class="model_intro_right">
				
				
				
				</td>
        	</tr>        	
			     	
			
			
			<tfoot>
				<tr>
					<td colspan="2" class="tableFooter"></td>
				</tr>
			</tfoot>
        </table>
 
	</div>
</form>
</body> -->

</html>

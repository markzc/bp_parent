<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
	<form name="icform" method="post">
		<input type="hidden" name="id" value="${id}" />

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
							<li id="save"><a href="#" onclick="formSubmit('${ctx }/baseinfo_save','_self');this.blur();">保存</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" />
			修改厂家信息
		</div>

		<div>
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle">类型：</td>
					<td class="tableContent"><input type="text" name="ctype"
						value="${ctype}" /></td>
					<td class="columnTitle">厂家全称：</td>
					<td class="tableContent"><input type="text" name="fullName"
						value="${fullName}" /></td>
				</tr>
				<tr>
					<td class="columnTitle">名称缩写</td>
					<td class="tableContent"><input type="text" name="factoryName"
						value="${factoryName}" /></td>
					<td class="columnTitle">联系人：</td>
					<td class="tableContent"><input type="text" name="contacts"
						value="${contacts}" /></td>
				</tr>
				<tr>
					<td class="columnTitle">电话：</td>
					<td class="tableContent"><input type="text" name="phone"
						value="${phone}" /></td>
					<td class="columnTitle">手机：</td>
					<td class="tableContent"><input type="text" name="mobile"
						value="${mobile}" /></td>
				</tr>
				<tr>
					<td class="columnTitle">传真：</td>
					<td class="tableContent"><input type="text" name="fax"
						value="${fax}" /></td>
					<td class="columnTitle">地址：</td>
					<td class="tableContent"><input type="text" name="address"
						value="${address}" /></td>
				</tr>
				<tr>
					<td class="columnTitle">验货员：</td>
					<td class="tableContent">
							<input type="text" name="inspector" value="${inspector}" />
						</td>
					<td class="columnTitle">说明：</td>
					<td class="tableContent">
							<input type="text" name="remark" value="${remark}" />
						</td>
				</tr>
				<tr>
					<td class="columnTitle">状态：</td>
					<td><input type="radio" name="state" value="0"
						<c:if test="${state==0}">checked="checked"</c:if>>草稿</启用> <input
						type="radio" name="state" value="1"
						<c:if test="${state==1}">checked="checked"</c:if>>启用</启用></td>
				</tr>

			</table>
		</div>


	</form>
</body>
</html>


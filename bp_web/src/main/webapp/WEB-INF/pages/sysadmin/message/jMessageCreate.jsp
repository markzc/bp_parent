<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
	<style type="text/css">
    	textarea{ resize:none; width:660px; height:200px;}
    	#title{
    		width:300px;
    	}
	</style>
</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('messageAction_insert','_self');this.blur();">发送</a></li>
							<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="${ctx }/skin/default/images/icon/currency_yen.png" /> 新增留言
		</div>
		
		<div>
			<table class="commonTable" cellspacing="1">
			
				<tr>
					<td class="columnTitle">收信人：</td>
					<td class="tableContent">
						 <s:select name="userId" list="#userList" headerKey="" headerValue="--请选择--" listKey="id" listValue="userInfo.name" ></s:select>
					</td>
					</tr>
					<tr>
					<td class="columnTitle">标题：</td>
					<td class="tableContent"><input id="title" type="text" name="title" value="" /></td>
					
				</tr>
				<tr>
					<td class="columnTitle">留言内容：</td>
					<td align="left">
						<textarea rows="50" cols="value" name="message" ></textarea>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>


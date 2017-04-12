<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
<script>
	function isOnlyChecked() {
		var checkBoxArray = document.getElementsByName('id');
		var count = 0;
		for (var index = 0; index < checkBoxArray.length; index++) {
			if (checkBoxArray[index].checked) {
				count++;
			}
		}
		//jquery
		//var count = $("[input name='id']:checked").size();
		if (count == 1)
			return true;
		else
			return false;
	}
	function isChecked() {
		var checkBoxArray = document.getElementsByName('id');
		var count = 0;
		for (var index = 0; index < checkBoxArray.length; index++) {
			if (checkBoxArray[index].checked) {
				count++;
			}
		}
		//jquery
		//var count = $("[input name='id']:checked").size();
		if (count >= 1)
			return true;
		else
			return false;
	}
	function toView() {
		if (isOnlyChecked()) {
			formSubmit('taskAction_toview', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
	//删除
	function todelete() {
		if (isChecked()) {
			formSubmit('taskAction_delete', '_self');
		} else {
			alert("请最少选择一项，再进行操作！");
		}
	}
	//实现更新
	function toUpdate() {
		if (isOnlyChecked()) {
			formSubmit('taskAction_toupdate', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
	//转交任务
	function toTransmit() {
		if (isOnlyChecked()) {
			formSubmit('taskAction_toTransmit', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
	//任务完成
	function submit() {
		if (isOnlyChecked()) {
			formSubmit('taskAction_submit', '_self');
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	}
	/* function toCheck(obj) {
		alert(1111);
		if (isOnlyChecked()) {
			
			alert(obj);
			alert("#td"+obj);
			var updateBy = document.getElementById("td"+obj).html;
			alert(updateBy);
			if(updateBy!=null){
				
			
			formSubmit('taskAction_toRefund', '_self');
			}else{
				alert("请选择一个其他人传来的任务!");
			}
		} else {
			alert("请先选择一项并且只能选择一项，再进行操作！");
		}
	} */
	
</script>
</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<!--<shiro:hasPermission name="查看"></shiro:hasPermission>-->
							<li id="view"><a href="#" onclick="javascript:toView()">查看</a></li>

							<c:if test="${_CURRENT_USER.userInfo.degree!=4 }">
							<li id="work_assign"><a href="#"
								onclick="formSubmit('taskAction_tocreate','_self');this.blur();">分配任务</a></li>

							</c:if>
							<c:if test="${_CURRENT_USER.userInfo.degree!=4}">
							<li id="update"><a href="#" onclick="javascript:toUpdate()">修改</a></li>
							</c:if>
							<li id="delete"><a href="#"
								onclick="javascript:todelete();">删除</a></li>
							<c:if test="${_CURRENT_USER.id==results[0].user.id }">
							<li id="save_edit"><a href="#"
								onclick="javascript:toTransmit();">转交任务</a></li>
							<li id="save_edit"><a href="#"
								onclick="javascript:submit();">任务完成</a></li>
							<li id="save_edit"><a href="#" 
								onclick="formSubmit('taskAction_toRefund','_self');this.blur();">转交列表</a></li>
							</c:if>

						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">任务列表</div>
				</div>
			</div>

			<div>


				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<thead>
							<tr>
								<td class="tableHeader"><input type="checkbox" name="selid"
									onclick="checkAll('id',this)"></td>
								<td class="tableHeader">序号</td>
								<!-- 	<td class="tableHeader">编号</td> -->
								<td class="tableHeader">任务</td>
								<td class="tableHeader">创建人</td>
								<td class="tableHeader">接收人</td>
								<td class="tableHeader">开始时间</td>
								<td class="tableHeader">截止时间</td>
								<td class="tableHeader">任务内容</td>
								<td class="tableHeader">状态</td>
								<td class="tableHeader">是否为新任务</td>

								<td class="tableHeader">修改人</td>
								<td class="tableHeader">修改的时间</td>
								<td class="tableHeader">名称</td>
							</tr>
						</thead>
						<tbody class="tableBody">
							${links }
						
							<c:forEach items="${results }" var="task" varStatus="st">
							<c:forEach items="${createUserList }" var="user">
							<%-- <c:forEach items="${updateUserList }" var="updateUser"> --%>
							
								<c:if test="${user.id==task.createBy }">
								<tr class="odd"  onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id" value="${task.id }" /></td>
									<td>${st.count }</td>
									<%-- <td>${task.id }</td> --%>
									<td>${task.module.name }</td>
							
									<td>${user.username }</td>
									<td>${task.user.username }</td>

									<td>
										<fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd"/>
									</td>
									<td>
										<fmt:formatDate value="${task.finishDate }" pattern="yyyy-MM-dd"/>
									</td>
									<td>${task.description}</td>
									<td><c:if test="${task.state==0 }">未完成</c:if> <c:if
											test="${task.state==1 }">已完成</c:if> <c:if
											test="${task.state==2 }">已转交</c:if>
											 
										<c:if test="${task.state==3 }">已退回</c:if></td>
										
									<td><c:if test="${task.defaultState==0 }">是</c:if>
									<c:if test="${task.defaultState==1 }">否</c:if></td>
									<td id="td${task.id }">${task.updateBy }</td>
									<td>
										<fmt:formatDate value="${task.updateTime }" pattern="yyyy-MM-dd"/>
									</td>

									<td><a href="taskAction_toview?id=${task.id }">${task.module.name }</a></td>
								</tr>
								</c:if>
								</c:forEach>
								</c:forEach>
							<%--  </c:forEach> --%>
						</tbody>
					</table>
				</div>

			</div>
	</form>
</body>
</html>


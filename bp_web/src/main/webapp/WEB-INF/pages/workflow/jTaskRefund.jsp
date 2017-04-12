<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="../base.jsp"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>

	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	function choice(id){
		alert();
		$("#userId").html("<option value=''>--请选择--</option>");
		$.post("${ctx}/sysadmin/taskAction_choice.action",
				{"modId":id},
				function(data){
					
					$(data).each(function(i,n){
						$("#userId").append("<option value='"+n.id+"'>"+n.username+"</option>");
					});
					$("#userId option[value='${user.id}']").prop("selected",true)
				},
				"json")
	};
	</script>
</head>

<body>
<form name="form" method="post">
   
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('taskAction_refund','_self');this.blur();">返还</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   回退任务
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
								<td class="tableHeader">创建人的部门</td>
								<td class="tableHeader">接收人</td>
								<td class="tableHeader">开始时间</td>
								<td class="tableHeader">截止时间</td>
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
									<td>${user.dept.deptName }</td>
									<td>${task.user.username }</td>

									<td>
										<fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd"/>
									</td>
									<td>
										<fmt:formatDate value="${task.finishDate }" pattern="yyyy-MM-dd"/>
									</td>
									<td><c:if test="${task.state==0 }">未完成</c:if> <c:if
											test="${task.state==1 }">已完成</c:if> <c:if
											test="${task.state==2 }">已转交</c:if>
											 
										<c:if test="${task.state==3 }">已退回</c:if></td>
										
									<td><c:if test="${task.defaultState==0 }">是</c:if>
									<c:if test="${task.defaultState==1 }">否</c:if></td>
									<td >${task.updateBy }</td>
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


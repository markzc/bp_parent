<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<script type="text/javascript" src="${ctx}/js/jquery-1.11.3.min.js"></script>
<head>

		<c:if test="${msg !=null }">
			<script type="text/javascript">
				alert('${msg }');
				location.href = "feedbackAction_list";
			</script>
		</c:if>
		
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="formSubmit('feedbackAction_toview','_self');this.blur();">查看</a></li>
<c:if test="${degree != 4 }">
	<li id="work_assign"><a href="#" onclick="formSubmit('feedbackAction_tounderling','_self');this.blur();">下级反馈</a></li>
</c:if>
<c:if test="${degree != 0 }">
<li id="new"><a href="#" onclick="formSubmit('feedbackAction_tocreate','_self');this.blur();">新增</a></li>
</c:if>
<li id="update"><a href="#" onclick="formSubmit('feedbackAction_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('feedbackAction_delete','_self');this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    意见反馈列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">提出人</td>
		<td class="tableHeader">提出时间</td>
		<td class="tableHeader">标题</td>
		<td class="tableHeader">分类<!-- :1管理2安全3建议4其他 --></td>
		<td class="tableHeader">解决人</td>
		<td class="tableHeader">解决方式<!-- :1已修改2无需修改3重复问题4描述不完整5无法再现6其他 --></td>
		<td class="tableHeader">解决难度<!-- :1极难2比较难3有难度4一般 --></td>
		<td class="tableHeader">是否公开<!-- :0不公开1公开 --></td>
		<td class="tableHeader">状态<!-- :0未处理1已处理 --></td>
	</tr>
	</thead>
	<tbody class="tableBody" >
${links}
	
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" align="left">
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.inputBy}</td>
		<td>${o.inputTime}</td>
		<td>${o.title}</td>
		<td>
			<c:if test="${o.classType == 1}">
				管理
			</c:if>
			<c:if test="${o.classType == 2}">
				安全
			</c:if>
			<c:if test="${o.classType == 3}">
				建议
			</c:if>
			<c:if test="${o.classType == 4}">
				其他
			</c:if>
		</td>
		<td>	
			<c:if test="${o.state ==1}">
				${o.answerBy}
			</c:if>
			<c:if test="${o.state == 0}">
				<c:if test="${o.answerBy != null}">
					预计交由:${o.answerBy}
				</c:if> 
				<c:if test="${o.answerBy == null}">
					未定处理人...
				</c:if> 
			</c:if>
		</td>
		<c:if test="${o.state ==1}">
				<td>
					<c:if test="${o.resolution == 1}">
						已修改
					</c:if>
					<c:if test="${o.resolution == 2}">
						无需修改
					</c:if>
					<c:if test="${o.resolution == 3}">
						重复问题
					</c:if>
					<c:if test="${o.resolution == 4}">
						描述不完整
					</c:if>
					<c:if test="${o.resolution == 5}">
						无法再现
					</c:if>
					<c:if test="${o.resolution == 6}">
						其他
					</c:if>
				</td>
				<td>
					<c:if test="${o.difficulty == 1}">
						极难
					</c:if>
					<c:if test="${o.difficulty == 2}">
						比较难
					</c:if>
					<c:if test="${o.difficulty == 3}">
						有难度
					</c:if>
					<c:if test="${o.difficulty == 4}">
						一般
					</c:if>		
				</td>
		</c:if>
		<c:if test="${o.state ==0}">
			<td>尚未解决...</td>
			<td>尚未解决...</td>
		</c:if>
		
		<td>
			<c:if test="${o.isShare == 0}">
				不公开
			</c:if>
			<c:if test="${o.isShare == 1}">
				公开
			</c:if>
		</td>
		<td>
			<c:if test="${o.state ==0}">
				未处理
			</c:if>
			<c:if test="${o.state ==1}">
				已处理
			</c:if>
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


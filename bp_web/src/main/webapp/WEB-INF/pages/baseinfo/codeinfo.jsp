<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模块介绍</title>
  	<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/main.css" media="all"/>
</head>

<body>
<form>
<div class="textbox"></div>

	<div class="modelDiv">

        <table class="modelTable" cellspacing="1">
        	<tr>
        		<td colspan="2" class="modelTitle">系统整体架构介绍</td>
        	</tr>

        	<tr>
        		<td colspan="2" class="subModelTitle">代码</td>
        	</tr>        	
			<tr>
				<td class="model_intro_left" width="169">系统代码：</td>
				<td class="model_intro_right" width="81%">使用javaEE体系开发，采用Struts2，Spring，Hibernate进行开发，使用maven进行分模块开发。</td>
			</tr>   	

			<tr>
        		<td colspan="2" class="subModelTitle">数据库</td>
        	</tr>  
			<tr>
				<td class="model_intro_left">信息：</td>
				<td class="model_intro_right">数据库使用Oracle进行存储数据。</td>
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

</html>
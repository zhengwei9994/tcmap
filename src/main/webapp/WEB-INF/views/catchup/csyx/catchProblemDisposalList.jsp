<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程问题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csyx/catchProblemDisposal/">工程问题管理列表</a></li>
		<shiro:hasPermission name="csyx:catchProblemDisposal:edit"><li><a href="${ctx}/csyx/catchProblemDisposal/form">工程问题管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchProblemDisposal" action="${ctx}/csyx/catchProblemDisposal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<!-- 	<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>街道办名称</th>
				<th>单位名称</th>
				<th>项目名称</th>
				<shiro:hasPermission name="csyx:catchProblemDisposal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchProblemDisposal">
			<tr>
			<td>
						${catchProblemDisposal.leadership}
			</td>
			<td>
						${catchProblemDisposal.hostUnit}
			</td>
			<td>
						${catchProblemDisposal.projectName}
			</td>
<!-- 			<td> -->
<%-- 						${catchProblemDisposal.problemDisposal} --%>
<!-- 			</td> -->
				<shiro:hasPermission name="csyx:catchProblemDisposal:edit"><td>
    				<a href="${ctx}/csyx/catchProblemDisposal/form?id=${catchProblemDisposal.id}">修改</a>
					<a href="${ctx}/csyx/catchProblemDisposal/delete?id=${catchProblemDisposal.id}" onclick="return confirmx('确认要删除该问题处理率管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
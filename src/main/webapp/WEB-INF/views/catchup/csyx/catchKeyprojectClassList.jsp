<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>月进度管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchKeyprojectClass?keyprojectId.id=${catchKeyprojectClass.keyprojectId.id}&keyprojectId.totalInvestment=${catchKeyprojectClass.keyprojectId.totalInvestment}">月进度列表</a></li>
		
		<li><a href="${ctx}/csyx/catchKeyprojectClass/form?keyprojectId.id=${catchKeyprojectClass.keyprojectId.id}&keyprojectId.totalInvestment=${catchKeyprojectClass.keyprojectId.totalInvestment}">月进度添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="catchKeyprojectClass" action="${ctx}/csyx/catchKeyprojectClass/" method="post" class="breadcrumb form-search">
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
				<th>月份</th>
				<th>已完成金额</th>
				<th>单位</th>
				<th>已完成占比</th>
				<th>完成状态</th>
				<shiro:hasPermission name="csyx:catchKeyprojectClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchKeyprojectClass">
			<tr>

				<td><a href="${ctx}/csyx/catchKeyprojectClass/form?id=${catchKeyprojectClass.id}">
					${catchKeyprojectClass.month}
				</a></td>
				<td>
					${catchKeyprojectClass.amountCompleted}
				</td>
				<td>
					${fns:getDictLabel(catchKeyprojectClass.unit, 'unit', '')}
				</td>
				<td>
					${catchKeyprojectClass.completionRatio}
				</td>
				<td>
					${fns:getDictLabel(catchKeyprojectClass.projectStatus, 'project_status', '')}
				</td>
				<td>
    				<a href="${ctx}/csyx/catchKeyprojectClass/form?id=${catchKeyprojectClass.id}">修改</a>
					<a href="${ctx}/csyx/catchKeyprojectClass/delete?id=${catchKeyprojectClass.id}" onclick="return confirmx('确认要删除该月进度吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业发展管理</title>
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
		<li class="active"><a href="${ctx}/csyx/developmentIndustry/">产业发展列表</a></li>
		<shiro:hasPermission name="csyx:developmentIndustry:edit"><li><a href="${ctx}/csyx/developmentIndustry/form">产业发展添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="developmentIndustry" action="${ctx}/csyx/developmentIndustry/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>种类：</label>
				<form:input path="type" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>产业：</label>
				<form:input path="industry" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>种类</th>
				<th>产业</th>
				<th>数值</th>
				<th>年</th>
				<shiro:hasPermission name="csyx:developmentIndustry:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="developmentIndustry">
			<tr>
				<td><a href="${ctx}/csyx/developmentIndustry/form?id=${developmentIndustry.id}">
					${developmentIndustry.type}
				</a></td>
				<td>
					${developmentIndustry.industry}
				</td>
				<td>
					${developmentIndustry.num}
				</td>
				<td>
					${developmentIndustry.year}
				</td>
				<shiro:hasPermission name="csyx:developmentIndustry:edit"><td>
    				<a href="${ctx}/csyx/developmentIndustry/form?id=${developmentIndustry.id}">修改</a>
					<a href="${ctx}/csyx/developmentIndustry/delete?id=${developmentIndustry.id}" onclick="return confirmx('确认要删除该产业发展吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业现状管理</title>
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
		<li class="active"><a href="${ctx}/csyx/developmentSituation/">产业现状列表</a></li>
		<shiro:hasPermission name="csyx:developmentSituation:edit"><li><a href="${ctx}/csyx/developmentSituation/form">产业现状添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="developmentSituation" action="${ctx}/csyx/developmentSituation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>行业：</label>
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
				<th>行业</th>
				<th>户数</th>
				<th>资本</th>
				<th>大小</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:developmentSituation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="developmentSituation">
			<tr>
				<td><a href="${ctx}/csyx/developmentSituation/form?id=${developmentSituation.id}">
					${developmentSituation.industry}
				</a></td>
				<td>
					${developmentSituation.households}
				</td>
				<td>
					${developmentSituation.capital}
				</td>
				<td>
					${developmentSituation.size}
				</td>
				<td>
					${developmentSituation.year}-${developmentSituation.month}
				</td>
				<shiro:hasPermission name="csyx:developmentSituation:edit"><td>
    				<a href="${ctx}/csyx/developmentSituation/form?id=${developmentSituation.id}">修改</a>
					<a href="${ctx}/csyx/developmentSituation/delete?id=${developmentSituation.id}" onclick="return confirmx('确认要删除该产业现状吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
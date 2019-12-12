<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>影响权重管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showImpactWeight/">影响权重列表</a></li>
		<%--<shiro:hasPermission name="csyx:showImpactWeight:edit"><li><a href="${ctx}/csyx/showImpactWeight/form">影响权重添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="showImpactWeight" action="${ctx}/csyx/showImpactWeight/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<%--		<ul class="ul-form">--%>
<%--			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
<%--			<li class="clearfix"></li>--%>
<%--		</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>年</th><th>行业</th><th>人口老龄化权重</th><th>GDP影响比重</th>
				<shiro:hasPermission name="csyx:showImpactWeight:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showImpactWeight">
			<tr><td>${showImpactWeight.year}</td>
				<td><a href="${ctx}/csyx/showImpactWeight/form?id=${showImpactWeight.id}">${showImpactWeight.industry}</a></td>
				<td>${showImpactWeight.populationRate}</td>
				<td>${showImpactWeight.gdpRate}</td>
				<shiro:hasPermission name="csyx:showImpactWeight:edit"><td>
    				<a href="${ctx}/csyx/showImpactWeight/form?id=${showImpactWeight.id}">修改</a>
					<%--<a href="${ctx}/csyx/showImpactWeight/delete?id=${showImpactWeight.id}" onclick="return confirmx('确认要删除该影响权重吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
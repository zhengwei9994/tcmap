<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人口总量管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showTotalPopulation/">人口总量列表</a></li>
		<shiro:hasPermission name="csyx:showTotalPopulation:edit"><li><a href="${ctx}/csyx/showTotalPopulation/form">人口总量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showTotalPopulation" action="${ctx}/csyx/showTotalPopulation/" method="post" class="breadcrumb form-search">
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
			<tr><th>年</th><th>总量</th><th>老年人口数量</th><th>劳动人口占比</th><th>总人口抚养比</th>
				<shiro:hasPermission name="csyx:showTotalPopulation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showTotalPopulation">
			<tr><td><a href="${ctx}/csyx/showTotalPopulation/form?id=${showTotalPopulation.id}">
					${showTotalPopulation.year}</a></td>
				<td>${showTotalPopulation.totalNum}万人</td>
				<td>${showTotalPopulation.oldNum}万人</td>
				<td>${showTotalPopulation.laborPercent}%</td>
				<td>${showTotalPopulation.raisePercent}%</td>
				<shiro:hasPermission name="csyx:showTotalPopulation:edit"><td>
    				<a href="${ctx}/csyx/showTotalPopulation/form?id=${showTotalPopulation.id}">修改</a>
					<a href="${ctx}/csyx/showTotalPopulation/delete?id=${showTotalPopulation.id}" onclick="return confirmx('确认要删除该人口总量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
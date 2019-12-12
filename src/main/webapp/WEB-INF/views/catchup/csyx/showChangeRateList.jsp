<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变化率管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showChangeRate/">变化率列表</a></li>
		<shiro:hasPermission name="csyx:showChangeRate:edit"><li><a href="${ctx}/csyx/showChangeRate/form">变化率添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showChangeRate" action="${ctx}/csyx/showChangeRate/" method="post" class="breadcrumb form-search">
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
			<tr><th>年份</th><th>GDP增长率</th><th>投资增长率</th><th>储蓄率</th><th>消费支出增加率</th>
				<shiro:hasPermission name="csyx:showChangeRate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showChangeRate">
			<tr><td><a href="${ctx}/csyx/showChangeRate/form?id=${showChangeRate.id}">${showChangeRate.year}</a></td>
				<td>${showChangeRate.gdpGrowth}</td>
				<td>${showChangeRate.investmentGrowthRate}</td>
				<td>${showChangeRate.saveRate}</td>
				<td>${showChangeRate.consumptionExpenditureRate}</td>
				<shiro:hasPermission name="csyx:showChangeRate:edit"><td>
    				<a href="${ctx}/csyx/showChangeRate/form?id=${showChangeRate.id}">修改</a>
					<a href="${ctx}/csyx/showChangeRate/delete?id=${showChangeRate.id}" onclick="return confirmx('确认要删除该变化率吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
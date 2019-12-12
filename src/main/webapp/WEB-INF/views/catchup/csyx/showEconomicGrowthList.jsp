<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济成长性管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showEconomicGrowth/">经济成长性列表</a></li>
		<shiro:hasPermission name="csyx:showEconomicGrowth:edit"><li><a href="${ctx}/csyx/showEconomicGrowth/form">经济成长性添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showEconomicGrowth" action="${ctx}/csyx/showEconomicGrowth/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<%--		<ul class="ul-form">--%>
<%--			<li><label>常住人口：</label>--%>
<%--				<form:input path="population" htmlEscape="false" maxlength="255" class="input-medium"/>（万人）--%>
<%--			</li>--%>
<%--			<li><label>常住人口增长率：</label>--%>
<%--				<form:input path="populationIncrease" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>人均gdp：</label>--%>
<%--				<form:input path="capita" htmlEscape="false" maxlength="255" class="input-medium"/>（元）--%>
<%--			</li>--%>
<%--			<li><label>人均gdp增长率：</label>--%>
<%--				<form:input path="capitaIncrease" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li><label>财政收入：</label>--%>
<%--				<form:input path="revenue" htmlEscape="false" maxlength="255" class="input-medium"/>（亿元）--%>
<%--			</li>--%>
<%--			<li><label>财政收入增长率：</label>--%>
<%--				<form:input path="revenueIncrease" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
<%--			</li>--%>
<%--			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
<%--		</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>常住人口增长率</th>
				<th>人均gdp</th>
				<th>人均gdp增长率</th>
				<th>财政收入</th>
				<th>财政收入增长率</th>
				<th>常住人口</th>
				<shiro:hasPermission name="csyx:showEconomicGrowth:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showEconomicGrowth">
			<tr>
				<td><a href="${ctx}/csyx/showEconomicGrowth/form?id=${showEconomicGrowth.id}">
					${showEconomicGrowth.year}-${showEconomicGrowth.month}
				</a></td>
				<td>
					${showEconomicGrowth.populationIncrease}
				</td>
				<td>
					${showEconomicGrowth.capita}
				</td>
				<td>
					${showEconomicGrowth.capitaIncrease}
				</td>
				<td>
					${showEconomicGrowth.revenue}
				</td>
				<td>
					${showEconomicGrowth.revenueIncrease}
				</td>
				<td>
						${showEconomicGrowth.population}
				</td>
				<shiro:hasPermission name="csyx:showEconomicGrowth:edit"><td>
    				<a href="${ctx}/csyx/showEconomicGrowth/form?id=${showEconomicGrowth.id}">修改</a>
					<a href="${ctx}/csyx/showEconomicGrowth/delete?id=${showEconomicGrowth.id}" onclick="return confirmx('确认要删除该经济成长性吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
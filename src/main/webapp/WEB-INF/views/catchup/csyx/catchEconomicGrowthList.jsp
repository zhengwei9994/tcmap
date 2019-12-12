<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域经济增长管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchEconomicGrowth/">区域经济增长列表</a></li>
		<shiro:hasPermission name="csyx:catchEconomicGrowth:edit"><li><a href="${ctx}/csyx/catchEconomicGrowth/form">区域经济增长添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchEconomicGrowth" action="${ctx}/csyx/catchEconomicGrowth/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<form:input path="nyear" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>区域：</label>
				<form:input path="areaName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>经济指标</th>
				<th>年份</th>
				<th>区域</th>
				<th>额度</th>
				<th>单位</th>
				<th>占比</th>
				<shiro:hasPermission name="csyx:catchEconomicGrowth:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchEconomicGrowth">
			<tr>
				<td><a href="${ctx}/csyx/catchEconomicGrowth/form?id=${catchEconomicGrowth.id}">
					${catchEconomicGrowth.indexName}
				</a></td>
				<td>
					${catchEconomicGrowth.nyear}
				</td>
				<td>
					${catchEconomicGrowth.areaName}
				</td>
				<td>
					${catchEconomicGrowth.indicators}
				</td>
				<td>
					${fns:getDictLabel(catchEconomicGrowth.indicatorsUnit, 'unit', '')}
				</td>
				<td>
					${catchEconomicGrowth.rate}
				</td>
				<shiro:hasPermission name="csyx:catchEconomicGrowth:edit"><td>
    				<a href="${ctx}/csyx/catchEconomicGrowth/form?id=${catchEconomicGrowth.id}">修改</a>
					<a href="${ctx}/csyx/catchEconomicGrowth/delete?id=${catchEconomicGrowth.id}" onclick="return confirmx('确认要删除该区域经济增长吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济生活质量管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showQualityife/">经济生活质量列表</a></li>
		<shiro:hasPermission name="csyx:showQualityife:edit"><li><a href="${ctx}/csyx/showQualityife/form">经济生活质量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showQualityife" action="${ctx}/csyx/showQualityife/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>指标名称：</label>
				<form:input path="indexName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>指标名称</th>
				<th>指标数量</th>
				<th>指标增长</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:showQualityife:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showQualityife">
			<tr>
				<td><a href="${ctx}/csyx/showQualityife/form?id=${showQualityife.id}">
					${showQualityife.indexName}
				</a></td>
				<td>
					${showQualityife.indexNum}
				</td>
				<td>
					${showQualityife.indexIncrease}
				</td>
				<td>
					${showQualityife.year}-${showQualityife.month}
				</td>
				<shiro:hasPermission name="csyx:showQualityife:edit"><td>
    				<a href="${ctx}/csyx/showQualityife/form?id=${showQualityife.id}">修改</a>
					<a href="${ctx}/csyx/showQualityife/delete?id=${showQualityife.id}" onclick="return confirmx('确认要删除该经济生活质量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济消费状况管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showQualitylifeConsumption/">经济消费状况列表</a></li>
		<shiro:hasPermission name="csyx:showQualitylifeConsumption:edit"><li><a href="${ctx}/csyx/showQualitylifeConsumption/form">经济消费状况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showQualitylifeConsumption" action="${ctx}/csyx/showQualitylifeConsumption/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消费状况：</label>
				<form:input path="consumption" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>消费状况</th>
				<shiro:hasPermission name="csyx:showQualitylifeConsumption:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showQualitylifeConsumption">
			<tr>
				<td><a href="${ctx}/csyx/showQualitylifeConsumption/form?id=${showQualitylifeConsumption.id}">
					${showQualitylifeConsumption.year}-${showQualitylifeConsumption.month}
				</a></td>
				<td>
					${showQualitylifeConsumption.consumption}
				</td>
				<shiro:hasPermission name="csyx:showQualitylifeConsumption:edit"><td>
    				<a href="${ctx}/csyx/showQualitylifeConsumption/form?id=${showQualitylifeConsumption.id}">修改</a>
					<a href="${ctx}/csyx/showQualitylifeConsumption/delete?id=${showQualitylifeConsumption.id}" onclick="return confirmx('确认要删除该经济消费状况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
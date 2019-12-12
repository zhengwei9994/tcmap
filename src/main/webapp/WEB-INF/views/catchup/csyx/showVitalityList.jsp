<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济活力指数管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showVitality/">经济活力指数列表</a></li>
		<shiro:hasPermission name="csyx:showVitality:edit"><li><a href="${ctx}/csyx/showVitality/form">经济活力指数添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showVitality" action="${ctx}/csyx/showVitality/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活力值：</label>
				<form:input path="vitality" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>活力值</th>
				<shiro:hasPermission name="csyx:showVitality:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showVitality">
			<tr>
				<td><a href="${ctx}/csyx/showVitality/form?id=${showVitality.id}">
					${showVitality.year}-${showVitality.month}
				</a></td>
				<td>
					${showVitality.vitality}
				</td>
				<shiro:hasPermission name="csyx:showVitality:edit"><td>
    				<a href="${ctx}/csyx/showVitality/form?id=${showVitality.id}">修改</a>
					<a href="${ctx}/csyx/showVitality/delete?id=${showVitality.id}" onclick="return confirmx('确认要删除该经济活力指数吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
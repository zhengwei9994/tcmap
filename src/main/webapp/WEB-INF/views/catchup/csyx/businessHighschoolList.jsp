<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>营商高等院校数量管理</title>
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
		<li class="active"><a href="${ctx}/csyx/businessHighschool/">营商高等院校数量列表</a></li>
		<shiro:hasPermission name="csyx:businessHighschool:edit"><li><a href="${ctx}/csyx/businessHighschool/form">营商高等院校数量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="businessHighschool" action="${ctx}/csyx/businessHighschool/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机构：</label>
				<form:input path="mechanism" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机构</th>
				<th>数量</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:businessHighschool:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessHighschool">
			<tr>
				<td><a href="${ctx}/csyx/businessHighschool/form?id=${businessHighschool.id}">
					${businessHighschool.mechanism}
				</a></td>
				<td>
					${businessHighschool.num}
				</td>
				<td>
					${businessHighschool.year}-${businessHighschool.month}
				</td>
				<shiro:hasPermission name="csyx:businessHighschool:edit"><td>
    				<a href="${ctx}/csyx/businessHighschool/form?id=${businessHighschool.id}">修改</a>
					<a href="${ctx}/csyx/businessHighschool/delete?id=${businessHighschool.id}" onclick="return confirmx('确认要删除该营商高等院校数量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
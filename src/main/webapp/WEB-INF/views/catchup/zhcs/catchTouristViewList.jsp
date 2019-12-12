<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游资产管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchTouristView/">旅游资产列表</a></li>
		<shiro:hasPermission name="zhcs:catchTouristView:edit"><li><a href="${ctx}/zhcs/catchTouristView/form">旅游资产添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchTouristView" action="${ctx}/zhcs/catchTouristView/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>景区名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>景区名称</th>
				<th>景区等级</th>
				<th>景区经纬度</th>
				<th>区县</th>
				<shiro:hasPermission name="zhcs:catchTouristView:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchTouristView">
			<tr>
				<td><a href="${ctx}/zhcs/catchTouristView/form?id=${catchTouristView.id}">
					${catchTouristView.name}
				</a></td>
				<th>${fns:getDictLabel(catchTouristView.rank, 'spot_type', '')}</th>
				<td>${catchTouristView.lon},${catchTouristView.lat}</td>
				<td>${catchTouristView.areaName}</td>
				<shiro:hasPermission name="zhcs:catchTouristView:edit"><td>
    				<a href="${ctx}/zhcs/catchTouristView/form?id=${catchTouristView.id}">修改</a>
					<a href="${ctx}/zhcs/catchTouristView/delete?id=${catchTouristView.id}" onclick="return confirmx('确认要删除该旅游资产吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
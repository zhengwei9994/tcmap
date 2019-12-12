<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政务服务管理</title>
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
		<li class="active"><a href="${ctx}/csyx/liveGovernmentData/">政务服务列表</a></li>
		<%--<shiro:hasPermission name="csyx:liveGovernmentData:edit"><li><a href="${ctx}/csyx/liveGovernmentData/form">政务服务添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="liveGovernmentData" action="${ctx}/csyx/liveGovernmentData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>数据</th>
				<shiro:hasPermission name="csyx:liveGovernmentData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveGovernmentData">
			<tr>
				<td><a href="${ctx}/csyx/liveGovernmentData/form?id=${liveGovernmentData.id}">
					${liveGovernmentData.name}
				</a></td>
				<td>
					${liveGovernmentData.value}
				</td>
				<shiro:hasPermission name="csyx:liveGovernmentData:edit"><td>
    				<a href="${ctx}/csyx/liveGovernmentData/form?id=${liveGovernmentData.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>城市综合治理管理</title>
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
		<li class="active"><a href="${ctx}/csyx/liveMultipleCityManage/">城市综合治理列表</a></li>
		<shiro:hasPermission name="csyx:liveMultipleCityManage:edit"><li><a href="${ctx}/csyx/liveMultipleCityManage/form">城市综合治理添加</a></li></shiro:hasPermission>
	</ul>
	<%--<form:form id="searchForm" modelAttribute="liveMultipleCityManage" action="${ctx}/csyx/liveMultipleCityManage/" method="post" class="breadcrumb form-search">--%>
		<%--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>--%>
		<%--<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>--%>
		<%--<ul class="ul-form">--%>
			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
			<%--<li class="clearfix"></li>--%>
		<%--</ul>--%>
	<%--</form:form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>事件类型</th><th>已办</th><th>办理中</th><th>延办</th><th>代办</th><th>处置率</th>
				<shiro:hasPermission name="csyx:liveMultipleCityManage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveMultipleCityManage">
			<tr>
				<td>${liveMultipleCityManage.eventType}</td>
				<td>${liveMultipleCityManage.done}</td>
				<td>${liveMultipleCityManage.doing}</td>
				<td>${liveMultipleCityManage.extension}</td>
				<td>${liveMultipleCityManage.agency}</td>
				<td>${liveMultipleCityManage.disposalRate}</td>
				<shiro:hasPermission name="csyx:liveMultipleCityManage:edit"><td>
    				<a href="${ctx}/csyx/liveMultipleCityManage/form?id=${liveMultipleCityManage.id}">修改</a>
					<a href="${ctx}/csyx/liveMultipleCityManage/delete?id=${liveMultipleCityManage.id}" onclick="return confirmx('确认要删除该城市综合治理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
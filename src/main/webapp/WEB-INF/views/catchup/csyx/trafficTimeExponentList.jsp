<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交通时间指数管理</title>
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
		<li class="active"><a href="${ctx}/csyx/trafficTimeExponent/">交通时间指数列表</a></li>
		<shiro:hasPermission name="csyx:trafficTimeExponent:edit"><li><a href="${ctx}/csyx/trafficTimeExponent/form">交通时间指数添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="trafficTimeExponent" action="${ctx}/csyx/trafficTimeExponent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>城市</th>
				<th>指数</th>
				<th>时间</th>
				<shiro:hasPermission name="csyx:trafficTimeExponent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trafficTimeExponent">
			<tr>
				<td><a href="${ctx}/csyx/trafficTimeExponent/form?id=${trafficTimeExponent.id}">
					${trafficTimeExponent.city}
				</a></td>
				<td>
						${trafficTimeExponent.value}
				</td>
				<td>
					<fmt:formatDate value="${trafficTimeExponent.time}" pattern="yyyy-MM-dd HH"/>点
				</td>
				<shiro:hasPermission name="csyx:trafficTimeExponent:edit"><td>
    				<a href="${ctx}/csyx/trafficTimeExponent/form?id=${trafficTimeExponent.id}">修改</a>
					<a href="${ctx}/csyx/trafficTimeExponent/delete?id=${trafficTimeExponent.id}" onclick="return confirmx('确认要删除该交通时间指数吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
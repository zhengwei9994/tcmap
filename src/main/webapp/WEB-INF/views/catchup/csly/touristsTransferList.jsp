<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客流迁移管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristsTransfer/">客流迁移列表</a></li>
		<shiro:hasPermission name="csly:touristsTransfer:edit"><li><a href="${ctx}/csly/touristsTransfer/form">客流迁移添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristsTransfer" action="${ctx}/csly/touristsTransfer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>路线：</label>
				<form:input path="route" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>路线</th>
				<th>热度</th>
				<th>汽车</th>
				<th>火车</th>
				<th>飞机</th>
				<th>日期</th>
				<shiro:hasPermission name="csly:touristsTransfer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristsTransfer">
			<tr>
				<td><a href="${ctx}/csly/touristsTransfer/form?id=${touristsTransfer.id}">
					${touristsTransfer.route}
				</a></td>
				<td>
					${touristsTransfer.hot}
				</td>
				<td>
					${touristsTransfer.car}
				</td>
				<td>
					${touristsTransfer.train}
				</td>
				<td>
					${touristsTransfer.aircraft}
				</td>
				<td>
						${touristsTransfer.date}
				</td>
				<shiro:hasPermission name="csly:touristsTransfer:edit"><td>
    				<a href="${ctx}/csly/touristsTransfer/form?id=${touristsTransfer.id}">修改</a>
					<a href="${ctx}/csly/touristsTransfer/delete?id=${touristsTransfer.id}" onclick="return confirmx('确认要删除该客流迁移吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
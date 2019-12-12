<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>办理深度管理</title>
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
		<li class="active"><a href="${ctx}/csyx/liveGovementDeep/">办理深度列表</a></li>
		<shiro:hasPermission name="csyx:liveGovementDeep:edit"><li><a href="${ctx}/csyx/liveGovementDeep/form">办理深度添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>深度</th>
				<th>数值</th>
				<shiro:hasPermission name="csyx:liveGovementDeep:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveGovementDeep">
			<tr>
				<td><a href="${ctx}/csyx/liveGovementDeep/form?id=${liveGovementDeep.id}">
					${liveGovementDeep.deep}
				</a></td>
				<td>
					${liveGovementDeep.value}
				</td>
				<shiro:hasPermission name="csyx:liveGovementDeep:edit"><td>
    				<a href="${ctx}/csyx/liveGovementDeep/form?id=${liveGovementDeep.id}">修改</a>
					<a href="${ctx}/csyx/liveGovementDeep/delete?id=${liveGovementDeep.id}" onclick="return confirmx('确认要删除该办理深度吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
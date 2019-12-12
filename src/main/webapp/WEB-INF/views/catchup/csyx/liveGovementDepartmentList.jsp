<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行业主管部门事项分布管理</title>
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
		<li class="active"><a href="${ctx}/csyx/liveGovementDepartment/">行业主管部门事项分布列表</a></li>
		<shiro:hasPermission name="csyx:liveGovementDepartment:edit"><li><a href="${ctx}/csyx/liveGovementDepartment/form">行业主管部门事项分布添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>部门</th>
				<th>数量</th>
				<shiro:hasPermission name="csyx:liveGovementDepartment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveGovementDepartment">
			<tr>
				<td><a href="${ctx}/csyx/liveGovementDepartment/form?id=${liveGovementDepartment.id}">
					${liveGovementDepartment.department}
				</a></td>
				<td>
					${liveGovementDepartment.num}
				</td>
				<shiro:hasPermission name="csyx:liveGovementDepartment:edit"><td>
    				<a href="${ctx}/csyx/liveGovementDepartment/form?id=${liveGovementDepartment.id}">修改</a>
					<a href="${ctx}/csyx/liveGovementDepartment/delete?id=${liveGovementDepartment.id}" onclick="return confirmx('确认要删除该行业主管部门事项分布吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
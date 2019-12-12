<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>办理类型管理</title>
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
		<li class="active"><a href="${ctx}/csyx/liveGovermentType/">办理类型列表</a></li>
		<shiro:hasPermission name="csyx:liveGovermentType:edit"><li><a href="${ctx}/csyx/liveGovermentType/form">办理类型添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>办理类型</th>
				<th>数值</th>
				<shiro:hasPermission name="csyx:liveGovermentType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveGovermentType">
			<tr>
				<td><a href="${ctx}/csyx/liveGovermentType/form?id=${liveGovermentType.id}">
					${liveGovermentType.type}
				</a></td>
				<td>
					${liveGovermentType.value}
				</td>
				<shiro:hasPermission name="csyx:liveGovermentType:edit"><td>
    				<a href="${ctx}/csyx/liveGovermentType/form?id=${liveGovermentType.id}">修改</a>
					<a href="${ctx}/csyx/liveGovermentType/delete?id=${liveGovermentType.id}" onclick="return confirmx('确认要删除该办理类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
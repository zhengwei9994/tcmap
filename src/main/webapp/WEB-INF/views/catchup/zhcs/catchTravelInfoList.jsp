<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游相关管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchTravelInfo/">旅游相关信息列表</a></li>
		<!--<shiro:hasPermission name="zhcs:catchTravelInfo:edit"><li><a href="${ctx}/zhcs/catchTravelInfo/form">旅游相关信息添加</a></li></shiro:hasPermission> -->
	</ul>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>类型</th>
				<th>类别</th>
				<th>数值</th>
				<shiro:hasPermission name="zhcs:catchTravelInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchTravelInfo">
			<tr>
				<td><a href="${ctx}/zhcs/catchTravelInfo/form?id=${catchTravelInfo.id}">
					${catchTravelInfo.nyear}
				</a></td>
				<td>
						${catchTravelInfo.travelName}
				</td>
				<td>
					${fns:getDictLabel(catchTravelInfo.infoType, 'travel_info_type', '')}
				</td>
				<td>
					${catchTravelInfo.infoValue}
				</td>
				<shiro:hasPermission name="zhcs:catchTravelInfo:edit"><td>
    				<a href="${ctx}/zhcs/catchTravelInfo/form?id=${catchTravelInfo.id}">修改</a>
					<!--<a href="${ctx}/zhcs/catchTravelInfo/delete?id=${catchTravelInfo.id}" onclick="return confirmx('确认要删除该旅游信息吗？', this.href)">删除</a> -->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
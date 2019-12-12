<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>当日游客量管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristVolumeTime/">当日游客量列表</a></li>
		<shiro:hasPermission name="csly:touristVolumeTime:edit"><li><a href="${ctx}/csly/touristVolumeTime/form">当日游客量添加</a></li></shiro:hasPermission>
	</ul>
	<div hidden>
		<form:form id="searchForm" modelAttribute="touristVolumeTime" action="${ctx}/csly/touristVolumeTime/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
				<li class="clearfix"></li>
			</ul>
		</form:form>
	</div>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>日期</th>
				<th>指数</th>
				<th>景区</th>
				<shiro:hasPermission name="csly:touristVolumeTime:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristVolumeTime">
			<tr>
				<td><a href="${ctx}/csly/touristVolumeTime/form?id=${touristVolumeTime.id}">
					${touristVolumeTime.time}
				</a></td>
				<td>
					${touristVolumeTime.date}
				</td>
				<td>
					${touristVolumeTime.num}
				</td>
				<td>
						${touristVolumeTime.scenic}
				</td>
				<shiro:hasPermission name="csly:touristVolumeTime:edit"><td>
    				<a href="${ctx}/csly/touristVolumeTime/form?id=${touristVolumeTime.id}">修改</a>
					<a href="${ctx}/csly/touristVolumeTime/delete?id=${touristVolumeTime.id}" onclick="return confirmx('确认要删除该当日游客量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
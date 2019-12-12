<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>停留时长分布管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchStayTime/">停留时长分布列表</a></li>
		<shiro:hasPermission name="zhcs:catchStayTime:edit"><li><a href="${ctx}/zhcs/catchStayTime/form">停留时长分布添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchStayTime" action="${ctx}/zhcs/catchStayTime/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!--
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>-->
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>地点</th>
				<th>停留时长</th>
				<th>停留人数</th>
				<shiro:hasPermission name="zhcs:catchStayTime:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchStayTime">
			<tr>
				<td><a href="${ctx}/zhcs/catchStayTime/form?id=${catchTravelInfo.id}">
						${catchStayTime.nyear}
				</a></td>
				<td>
						${catchStayTime.stayPlace}
				</td>
				<td>
						${catchStayTime.stayTime}
				</td>
				<td>
						${catchStayTime.stayCount}
				</td>
				<shiro:hasPermission name="zhcs:catchStayTime:edit"><td>
    				<a href="${ctx}/zhcs/catchStayTime/form?id=${catchStayTime.id}">修改</a>
					<a href="${ctx}/zhcs/catchStayTime/delete?id=${catchStayTime.id}" onclick="return confirmx('确认要删除该停留时长分布吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
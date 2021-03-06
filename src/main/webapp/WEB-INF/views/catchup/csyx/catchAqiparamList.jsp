<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>空气质量管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchAqiparam/">空气质量列表</a></li>
		<shiro:hasPermission name="csyx:catchAqiparam:edit"><li><a href="${ctx}/csyx/catchAqiparam/form">空气质量添加</a></li></shiro:hasPermission>
	</ul>
	<%--<form:form id="searchForm" modelAttribute="catchAqiparam" action="${ctx}/csyx/catchAqiparam/" method="post" class="breadcrumb form-search">--%>
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
			<tr><th>区域名称</th><th>AQI</th><th>空气质量级别</th><th>排名</th>
				<shiro:hasPermission name="csyx:catchAqiparam:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchAqiparam">
			<tr>
				<td>${catchAqiparam.areaName}</td><td>${catchAqiparam.aqi}</td><td>${catchAqiparam.aqilevel}</td><td>${catchAqiparam.rank}</td>
				<shiro:hasPermission name="csyx:catchAqiparam:edit"><td>
    				<a href="${ctx}/csyx/catchAqiparam/form?id=${catchAqiparam.id}">修改</a>
					<a href="${ctx}/csyx/catchAqiparam/delete?id=${catchAqiparam.id}" onclick="return confirmx('确认要删除该空气质量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
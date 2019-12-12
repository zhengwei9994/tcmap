<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>热搜词汇管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchHotSearch/">热搜词汇列表</a></li>
<%--		<shiro:hasPermission name="wlkj:catchHotSearch:edit"><li><a href="${ctx}/wlkj/catchHotSearch/form">热搜词汇添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="catchHotSearch" action="${ctx}/wlkj/catchHotSearch/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%--<ul class="ul-form">--%>
			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
			<%--<li class="clearfix"></li>--%>
		<%--</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>热词名称</th><th>热词链接</th><th>浏览量</th>
				<shiro:hasPermission name="wlkj:catchHotSearch:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchHotSearch">
			<tr>
				<td>${catchHotSearch.name}</td><td>${catchHotSearch.link}</td><td>${catchHotSearch.sort}</td>
				<shiro:hasPermission name="wlkj:catchHotSearch:edit"><td>
    				<a href="${ctx}/wlkj/catchHotSearch/form?id=${catchHotSearch.id}">修改</a>
					<a href="${ctx}/wlkj/catchHotSearch/delete?id=${catchHotSearch.id}" onclick="return confirmx('确认要删除该热搜词汇吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
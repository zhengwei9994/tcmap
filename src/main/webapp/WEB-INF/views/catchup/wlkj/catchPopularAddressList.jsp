<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>热门公众号管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchPopularAddress/">热门公众号列表</a></li>
		<shiro:hasPermission name="wlkj:catchPopularAddress:edit"><li><a href="${ctx}/wlkj/catchPopularAddress/form">热门公众号添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchPopularAddress" action="${ctx}/wlkj/catchPopularAddress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>热门公号：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>热门公号</th>
				<shiro:hasPermission name="wlkj:catchPopularAddress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchPopularAddress">
			<tr>
				<td><a href="${ctx}/wlkj/catchPopularAddress/form?id=${catchPopularAddress.id}">
					${catchPopularAddress.name}
				</a></td>
				<shiro:hasPermission name="wlkj:catchPopularAddress:edit"><td>
    				<a href="${ctx}/wlkj/catchPopularAddress/form?id=${catchPopularAddress.id}">修改</a>
					<a href="${ctx}/wlkj/catchPopularAddress/delete?id=${catchPopularAddress.id}" onclick="return confirmx('确认要删除该热门公众号吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
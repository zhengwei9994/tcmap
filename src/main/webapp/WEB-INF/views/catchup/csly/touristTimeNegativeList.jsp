<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实时负面评价管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristTimeNegative/">实时负面评价列表</a></li>
		<shiro:hasPermission name="csly:touristTimeNegative:edit"><li><a href="${ctx}/csly/touristTimeNegative/form">实时负面评价添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristTimeNegative" action="${ctx}/csly/touristTimeNegative/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>景区：</label>
				<form:input path="scenic" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>摘要</th>
				<th>时间</th>
				<th>来源</th>
				<th>景区</th>
				<shiro:hasPermission name="csly:touristTimeNegative:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristTimeNegative">
			<tr>
				<td><a href="${ctx}/csly/touristTimeNegative/form?id=${touristTimeNegative.id}">
					${touristTimeNegative.abstracts}
				</a></td>
				<td>
					<fmt:formatDate value="${touristTimeNegative.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${touristTimeNegative.source}
				</td>
				<td>
					${touristTimeNegative.scenic}
				</td>
				<shiro:hasPermission name="csly:touristTimeNegative:edit"><td>
    				<a href="${ctx}/csly/touristTimeNegative/form?id=${touristTimeNegative.id}">修改</a>
					<a href="${ctx}/csly/touristTimeNegative/delete?id=${touristTimeNegative.id}" onclick="return confirmx('确认要删除该实时负面评价吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
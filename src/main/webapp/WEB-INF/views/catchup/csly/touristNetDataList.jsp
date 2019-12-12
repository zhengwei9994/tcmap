<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>品牌聆听数据管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristNetData/">互联网数据流列表</a></li>
		<shiro:hasPermission name="csly:touristNetData:edit"><li><a href="${ctx}/csly/touristNetData/form">互联网数据流添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristNetData" action="${ctx}/csly/touristNetData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>来源：</label>
				<form:input path="source" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
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
				<th>来源</th>
				<th>景区</th>
				<th>评价内容</th>
				<shiro:hasPermission name="csly:touristNetData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristNetData">
			<tr>
				<td><a href="${ctx}/csly/touristNetData/form?id=${touristNetData.id}">
					${touristNetData.source}
				</a></td>
				<td>
					${touristNetData.scenic}
				</td>
				<td>
					${touristNetData.content}
				</td>
				<shiro:hasPermission name="csly:touristNetData:edit"><td>
    				<a href="${ctx}/csly/touristNetData/form?id=${touristNetData.id}">修改</a>
					<a href="${ctx}/csly/touristNetData/delete?id=${touristNetData.id}" onclick="return confirmx('确认要删除该互联网数据流吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
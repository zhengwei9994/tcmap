<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济创新能力管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showInnovate/">经济创新能力列表</a></li>
		<shiro:hasPermission name="csyx:showInnovate:edit"><li><a href="${ctx}/csyx/showInnovate/form">经济创新能力添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showInnovate" action="${ctx}/csyx/showInnovate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>专利数量：</label>
				<form:input path="num" htmlEscape="false" maxlength="9" class="input-medium"/>
			</li>
			<li><label>科学技术占比：</label>
				<form:input path="proportion" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>专利数量</th>
				<th>科学技术占比</th>
				<shiro:hasPermission name="csyx:showInnovate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showInnovate">
			<tr>
				<td><a href="${ctx}/csyx/showInnovate/form?id=${showInnovate.id}">
					${showInnovate.year}${showInnovate.month}
				</a></td>
				<td>
						${showInnovate.num}
				</td>
				<td>
					${showInnovate.proportion}
				</td>
				<shiro:hasPermission name="csyx:showInnovate:edit"><td>
    				<a href="${ctx}/csyx/showInnovate/form?id=${showInnovate.id}">修改</a>
					<a href="${ctx}/csyx/showInnovate/delete?id=${showInnovate.id}" onclick="return confirmx('确认要删除该经济创新能力吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
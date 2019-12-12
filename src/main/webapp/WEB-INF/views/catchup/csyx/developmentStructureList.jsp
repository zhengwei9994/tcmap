<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业结构管理</title>
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
		<li class="active"><a href="${ctx}/csyx/developmentStructure/">产业结构列表</a></li>
		<shiro:hasPermission name="csyx:developmentStructure:edit"><li><a href="${ctx}/csyx/developmentStructure/form">产业结构添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="developmentStructure" action="${ctx}/csyx/developmentStructure/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>行业：</label>
				<form:input path="industry" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>行业</th>
				<th>第一产业</th>
				<th>第二产业</th>
				<th>第三产业</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:developmentStructure:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="developmentStructure">
			<tr>
				<td><a href="${ctx}/csyx/developmentStructure/form?id=${developmentStructure.id}">
					${developmentStructure.industry}
				</a></td>
				<td>
					${developmentStructure.first}
				</td>
				<td>
					${developmentStructure.second}
				</td>
				<td>
					${developmentStructure.third}
				</td>
				<td>
					${developmentStructure.year}-${developmentStructure.month}
				</td>
				<shiro:hasPermission name="csyx:developmentStructure:edit"><td>
    				<a href="${ctx}/csyx/developmentStructure/form?id=${developmentStructure.id}">修改</a>
					<a href="${ctx}/csyx/developmentStructure/delete?id=${developmentStructure.id}" onclick="return confirmx('确认要删除该产业结构吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
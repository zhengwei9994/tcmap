<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济指标名称管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchIndexName/">经济指标名称列表</a></li>
		<shiro:hasPermission name="csyx:catchIndexName:edit"><li><a href="${ctx}/csyx/catchIndexName/form">经济指标名称添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchIndexName" action="${ctx}/csyx/catchIndexName/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>指标名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr> 
				<th>指标名称</th>
				<th>英文缩写</th>
				<shiro:hasPermission name="csyx:catchIndexName:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchIndexName">
			<tr>
				<td>
				    <a href="${ctx}/csyx/catchIndexName/form?id=${catchIndexName.id}">
					${catchIndexName.name}
					</a>
				</td>
				<td>
					${catchIndexName.nameEn}
				</td>
				<shiro:hasPermission name="csyx:catchIndexName:edit"><td>
    				<a href="${ctx}/csyx/catchIndexName/form?id=${catchIndexName.id}">修改</a>
					<a href="${ctx}/csyx/catchIndexName/delete?id=${catchIndexName.id}" onclick="return confirmx('确认要删除该经济指标名称吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>劳动就业检索管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchLaborEmployment/">劳动就业检索列表</a></li>
		<shiro:hasPermission name="fzjc:catchLaborEmployment:edit"><li><a href="${ctx}/fzjc/catchLaborEmployment/form">劳动就业检索添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchLaborEmployment" action="${ctx}/fzjc/catchLaborEmployment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!-- 		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产业类型</th>
				<th>人数</th>
				<th>占比</th>
				<th>头像</th>
				<shiro:hasPermission name="fzjc:catchLaborEmployment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchLaborEmployment">
			<tr>
				<td><a href="${ctx}/fzjc/catchLaborEmployment/form?id=${catchLaborEmployment.id}">
					${catchLaborEmployment.kind}
				</a></td>
				<td>
					${catchLaborEmployment.number}
				</td>
				<td>
					${catchLaborEmployment.proportion}%
				</td>
				<td>
					${catchLaborEmployment.imagepath}
				</td>
				<shiro:hasPermission name="fzjc:catchLaborEmployment:edit"><td>
    				<a href="${ctx}/fzjc/catchLaborEmployment/form?id=${catchLaborEmployment.id}">修改</a>
					<a href="${ctx}/fzjc/catchLaborEmployment/delete?id=${catchLaborEmployment.id}" onclick="return confirmx('确认要删除该劳动就业检索吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
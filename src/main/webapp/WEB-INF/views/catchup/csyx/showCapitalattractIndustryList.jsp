<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济三产占比管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showCapitalattractIndustry/">经济三产占比列表</a></li>
		<shiro:hasPermission name="csyx:showCapitalattractIndustry:edit"><li><a href="${ctx}/csyx/showCapitalattractIndustry/form">经济三产占比添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showCapitalattractIndustry" action="${ctx}/csyx/showCapitalattractIndustry/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产业：</label>
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
				<th>产业</th>
				<th>数值</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:showCapitalattractIndustry:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showCapitalattractIndustry">
			<tr>
				<td><a href="${ctx}/csyx/showCapitalattractIndustry/form?id=${showCapitalattractIndustry.id}">
					${showCapitalattractIndustry.industry}
				</a></td>
				<td>
					${showCapitalattractIndustry.proportion}
				</td>
				<td>
					${showCapitalattractIndustry.year}${showCapitalattractIndustry.month}
				</td>
				<shiro:hasPermission name="csyx:showCapitalattractIndustry:edit"><td>
    				<a href="${ctx}/csyx/showCapitalattractIndustry/form?id=${showCapitalattractIndustry.id}">修改</a>
					<a href="${ctx}/csyx/showCapitalattractIndustry/delete?id=${showCapitalattractIndustry.id}" onclick="return confirmx('确认要删除该经济三产占比吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
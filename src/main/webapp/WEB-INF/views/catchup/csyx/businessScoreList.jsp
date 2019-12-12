<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>营商环境评分管理</title>
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
		<li class="active"><a href="${ctx}/csyx/businessScore/">营商环境评分列表</a></li>
		<shiro:hasPermission name="csyx:businessScore:edit"><li><a href="${ctx}/csyx/businessScore/form">营商环境评分添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="businessScore" action="${ctx}/csyx/businessScore/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>指数：</label>
				<form:input path="item" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>指数</th>
				<th>评分</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:businessScore:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessScore">
			<tr>
				<td><a href="${ctx}/csyx/businessScore/form?id=${businessScore.id}">
					${businessScore.item}
				</a></td>
				<td>
					${businessScore.num}
				</td>
				<td>
					${businessScore.year}-${businessScore.month}
				</td>
				<shiro:hasPermission name="csyx:businessScore:edit"><td>
    				<a href="${ctx}/csyx/businessScore/form?id=${businessScore.id}">修改</a>
					<a href="${ctx}/csyx/businessScore/delete?id=${businessScore.id}" onclick="return confirmx('确认要删除该营商环境评分吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
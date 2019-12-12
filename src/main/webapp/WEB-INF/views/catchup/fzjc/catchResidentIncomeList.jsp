<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>居民收入管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchResidentIncome/">居民收入列表</a></li>
		<shiro:hasPermission name="fzjc:catchResidentIncome:edit"><li><a href="${ctx}/fzjc/catchResidentIncome/form">居民收入添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchResidentIncome" action="${ctx}/fzjc/catchResidentIncome/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>居民类型：</label>
				<form:input path="residentClusters" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>居民类型</th>
				<th>工资收入</th>
				<th>经营净收入</th>
				<th>财产净收入</th>
				<th>转移净收入</th>
				<th>比率</th>
				<shiro:hasPermission name="fzjc:catchResidentIncome:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchResidentIncome">
			<tr>
				<td><a href="${ctx}/fzjc/catchResidentIncome/form?id=${catchResidentIncome.id}">
					${catchResidentIncome.residentClusters}
				</a></td>
				<td>
					${catchResidentIncome.wageIncome}
				</td>
				<td>
					${catchResidentIncome.operatingIncome}
				</td>
				<td>
					${catchResidentIncome.ownershipIncome}
				</td>
				<td>
					${catchResidentIncome.transferIncome}
				</td>
				<td>
					${catchResidentIncome.rate}
				</td>
				<shiro:hasPermission name="fzjc:catchResidentIncome:edit"><td>
    				<a href="${ctx}/fzjc/catchResidentIncome/form?id=${catchResidentIncome.id}">修改</a>
					<a href="${ctx}/fzjc/catchResidentIncome/delete?id=${catchResidentIncome.id}" onclick="return confirmx('确认要删除该居民收入吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
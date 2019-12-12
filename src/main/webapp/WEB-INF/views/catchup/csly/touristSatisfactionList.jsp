<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>满意度雷达图管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristSatisfaction/">满意度雷达图列表</a></li>
		<shiro:hasPermission name="csly:touristSatisfaction:edit"><li><a href="${ctx}/csly/touristSatisfaction/form">满意度雷达图添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristSatisfaction" action="${ctx}/csly/touristSatisfaction/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>景区：</label>
				<form:input path="senic" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>景区</th>
				<th>类别</th>
				<th>数值</th>
				<shiro:hasPermission name="csly:touristSatisfaction:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristSatisfaction">
			<tr>
				<td><a href="${ctx}/csly/touristSatisfaction/form?id=${touristSatisfaction.id}">
					${touristSatisfaction.senic}
				</a></td>
				<td>
					${touristSatisfaction.category}
				</td>
				<td>
					${touristSatisfaction.num}
				</td>
				<shiro:hasPermission name="csly:touristSatisfaction:edit"><td>
    				<a href="${ctx}/csly/touristSatisfaction/form?id=${touristSatisfaction.id}">修改</a>
					<a href="${ctx}/csly/touristSatisfaction/delete?id=${touristSatisfaction.id}" onclick="return confirmx('确认要删除该满意度雷达图吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
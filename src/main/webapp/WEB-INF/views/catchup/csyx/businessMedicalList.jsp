<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>营商千人医疗管理</title>
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
		<li class="active"><a href="${ctx}/csyx/businessMedical/">营商千人医疗列表</a></li>
		<shiro:hasPermission name="csyx:businessMedical:edit"><li><a href="${ctx}/csyx/businessMedical/form">营商千人医疗添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="businessMedical" action="${ctx}/csyx/businessMedical/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>指标：</label>
				<form:input path="index" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>指标</th>
				<th>数量</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:businessMedical:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessMedical">
			<tr>
				<td><a href="${ctx}/csyx/businessMedical/form?id=${businessMedical.id}">
					${businessMedical.index}
				</a></td>
				<td>
					${businessMedical.num}
				</td>
				<td>
					${businessMedical.year}-${businessMedical.month}
				</td>
				<shiro:hasPermission name="csyx:businessMedical:edit"><td>
    				<a href="${ctx}/csyx/businessMedical/form?id=${businessMedical.id}">修改</a>
					<a href="${ctx}/csyx/businessMedical/delete?id=${businessMedical.id}" onclick="return confirmx('确认要删除该营商千人医疗吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
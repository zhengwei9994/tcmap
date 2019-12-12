<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>营商人才结构管理</title>
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
		<li class="active"><a href="${ctx}/csyx/businessPersonnel/">营商人才结构列表</a></li>
		<shiro:hasPermission name="csyx:businessPersonnel:edit"><li><a href="${ctx}/csyx/businessPersonnel/form">营商人才结构添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="businessPersonnel" action="${ctx}/csyx/businessPersonnel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学历：</label>
				<form:input path="education" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学历</th>
				<th>年月</th>
				<th>数量</th>

				<shiro:hasPermission name="csyx:businessPersonnel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessPersonnel">
			<tr>
				<td><a href="${ctx}/csyx/businessPersonnel/form?id=${businessPersonnel.id}">
					${businessPersonnel.education}
				</a></td>
				<td>
						${businessPersonnel.year}-${businessPersonnel.month}
				</td>
				<td>
					${businessPersonnel.num}
				</td>

				<shiro:hasPermission name="csyx:businessPersonnel:edit"><td>
    				<a href="${ctx}/csyx/businessPersonnel/form?id=${businessPersonnel.id}">修改</a>
					<a href="${ctx}/csyx/businessPersonnel/delete?id=${businessPersonnel.id}" onclick="return confirmx('确认要删除该营商人才结构吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
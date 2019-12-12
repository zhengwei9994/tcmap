<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>厕所声量管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristWcSound/">厕所声量列表</a></li>
		<shiro:hasPermission name="csly:touristWcSound:edit"><li><a href="${ctx}/csly/touristWcSound/form">厕所声量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristWcSound" action="${ctx}/csly/touristWcSound/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>月份：</label>
				<form:input path="month" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>月份</th>
				<th>声量</th>
				<th>年</th>
				<shiro:hasPermission name="csly:touristWcSound:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristWcSound">
			<tr>
				<td><a href="${ctx}/csly/touristWcSound/form?id=${touristWcSound.id}">
					${touristWcSound.month}
				</a></td>
				<td>
					${touristWcSound.num}
				</td>
				<td>
					${touristWcSound.year}
				</td>
				<shiro:hasPermission name="csly:touristWcSound:edit"><td>
    				<a href="${ctx}/csly/touristWcSound/form?id=${touristWcSound.id}">修改</a>
					<a href="${ctx}/csly/touristWcSound/delete?id=${touristWcSound.id}" onclick="return confirmx('确认要删除该厕所声量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
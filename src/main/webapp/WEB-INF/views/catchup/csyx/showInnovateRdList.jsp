<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济rd变化管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showInnovateRd/">经济rd变化列表</a></li>
		<shiro:hasPermission name="csyx:showInnovateRd:edit"><li><a href="${ctx}/csyx/showInnovateRd/form">经济rd变化添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showInnovateRd" action="${ctx}/csyx/showInnovateRd/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>RD值：</label>
				<form:input path="rdnum" htmlEscape="false" maxlength="9" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>RD值</th>
				<shiro:hasPermission name="csyx:showInnovateRd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showInnovateRd">
			<tr>
				<td><a href="${ctx}/csyx/showInnovateRd/form?id=${showInnovateRd.id}">
					${showInnovateRd.year}-${showInnovateRd.month}
				</a></td>
				<td>
						${showInnovateRd.rdnum}
				</td>
				<shiro:hasPermission name="csyx:showInnovateRd:edit"><td>
    				<a href="${ctx}/csyx/showInnovateRd/form?id=${showInnovateRd.id}">修改</a>
					<a href="${ctx}/csyx/showInnovateRd/delete?id=${showInnovateRd.id}" onclick="return confirmx('确认要删除该经济rd变化吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
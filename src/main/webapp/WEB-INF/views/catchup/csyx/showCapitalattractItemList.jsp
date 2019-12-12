<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济财政支出管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showCapitalattractItem/">经济财政支出列表</a></li>
		<shiro:hasPermission name="csyx:showCapitalattractItem:edit"><li><a href="${ctx}/csyx/showCapitalattractItem/form">经济财政支出添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showCapitalattractItem" action="${ctx}/csyx/showCapitalattractItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>支出项目：</label>
				<form:input path="item" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>支出金额：</label>
				<form:input path="num" htmlEscape="false" maxlength="9" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年</th>
				<th>支出项目</th>
				<th>支出金额</th>
				<shiro:hasPermission name="csyx:showCapitalattractItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showCapitalattractItem">
			<tr>
				<td><a href="${ctx}/csyx/showCapitalattractItem/form?id=${showCapitalattractItem.id}">
						${showCapitalattractItem.year}
				</td>
				<td>
					${showCapitalattractItem.item}
				</a></td>
				<td>
					${showCapitalattractItem.num}
				</td>

				<shiro:hasPermission name="csyx:showCapitalattractItem:edit"><td>
    				<a href="${ctx}/csyx/showCapitalattractItem/form?id=${showCapitalattractItem.id}">修改</a>
					<a href="${ctx}/csyx/showCapitalattractItem/delete?id=${showCapitalattractItem.id}" onclick="return confirmx('确认要删除该经济财政支出吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
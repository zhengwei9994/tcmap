<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>废气排放指标管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchGasResource/">废气排放指标列表</a></li>
		<shiro:hasPermission name="csyx:catchGasResource:edit"><li><a href="${ctx}/csyx/catchGasResource/form">废气排放指标添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchGasResource" action="${ctx}/csyx/catchGasResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	    <ul class="ul-form">
		    <li><label>废气类型：</label>
		     <input name="type" type="text" maxlength="20" class="input-medium"
					   value="${catchGasResource.type}"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>废气来源类型</th>
				<th>烟（粉）尘</th>
				<th>氮氧化物</th>
				<th>二氧化碳</th>
				<shiro:hasPermission name="csyx:catchGasResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchGasResource">
			<tr>
			<td>
						${catchGasResource.type}
			</td>
			<td>
						${catchGasResource.powderValue}
			</td>
			<td>
						${catchGasResource.oxynitrideValue}
			</td>
			<td>
						${catchGasResource.coValue}
			</td>
				<shiro:hasPermission name="csyx:catchGasResource:edit"><td>
    				<a href="${ctx}/csyx/catchGasResource/form?id=${catchGasResource.id}">修改</a>
					<a href="${ctx}/csyx/catchGasResource/delete?id=${catchGasResource.id}" onclick="return confirmx('确认要删除该废气排放指标吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
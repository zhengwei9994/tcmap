<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>媒体来源管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchMediaResource/">媒体来源列表</a></li>
		<shiro:hasPermission name="wlkj:catchMediaResource:edit"><li><a href="${ctx}/wlkj/catchMediaResource/form">媒体来源添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchMediaResource" action="${ctx}/wlkj/catchMediaResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>媒体：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>媒体</th>
				<th>舆情贡献量</th>
			    <th>媒体占比</th>
				<shiro:hasPermission name="wlkj:catchMediaResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchMediaResource">
			<tr>
				<td><a href="${ctx}/wlkj/catchMediaResource/form?id=${catchMediaResource.id}">
					${catchMediaResource.name}
				</a></td>
				<td>
						${catchMediaResource.contribution}
			    </td>
			    <td>
						${catchMediaResource.proportion}
			    </td>
				<shiro:hasPermission name="wlkj:catchMediaResource:edit"><td>
    				<a href="${ctx}/wlkj/catchMediaResource/form?id=${catchMediaResource.id}">修改</a>
					<a href="${ctx}/wlkj/catchMediaResource/delete?id=${catchMediaResource.id}" onclick="return confirmx('确认要删除该媒体来源吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>媒体类型管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchMediaMtlx/">媒体类型列表</a></li>
		<shiro:hasPermission name="wlkj:catchMediaMtlx:edit"><li><a href="${ctx}/wlkj/catchMediaMtlx/form">媒体类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchMediaMtlx" action="${ctx}/wlkj/catchMediaMtlx/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="wlkj:catchMediaMtlx:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchMediaMtlx">
			<tr>
				<td><a href="${ctx}/wlkj/catchMediaMtlx/form?id=${catchMediaMtlx.id}">
					${catchMediaMtlx.name}
				</a></td>
				<td>
						${catchMediaMtlx.contribution}
			    </td>
			    <td>
						${catchMediaMtlx.proportion}
			    </td>
				<shiro:hasPermission name="wlkj:catchMediaMtlx:edit"><td>
    				<a href="${ctx}/wlkj/catchMediaMtlx/form?id=${catchMediaMtlx.id}">修改</a>
					<a href="${ctx}/wlkj/catchMediaMtlx/delete?id=${catchMediaMtlx.id}" onclick="return confirmx('确认要删除该媒体类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
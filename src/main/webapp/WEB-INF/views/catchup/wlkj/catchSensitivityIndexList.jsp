<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>敏感指数管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchSensitivityIndex/">敏感指数列表</a></li>
		<%-- <shiro:hasPermission name="wlkj:catchSensitivityIndex:edit"><li><a href="${ctx}/wlkj/catchSensitivityIndex/form">敏感指数添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSensitivityIndex" action="${ctx}/wlkj/catchSensitivityIndex/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<!-- 	<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>敏感指数</th>
				<th>非敏感指数</th>
				<shiro:hasPermission name="wlkj:catchSensitivityIndex:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSensitivityIndex">
			<tr>
			    <td>
						${catchSensitivityIndex.sensitiveIndex}
			    </td>
			    <td>
						${catchSensitivityIndex.nonSensitiveIndex}
			    </td>
				<shiro:hasPermission name="wlkj:catchSensitivityIndex:edit"><td>
    				<a href="${ctx}/wlkj/catchSensitivityIndex/form?id=${catchSensitivityIndex.id}">修改</a>
					<%-- <a href="${ctx}/wlkj/catchSensitivityIndex/delete?id=${catchSensitivityIndex.id}" onclick="return confirmx('确认要删除该敏感指数吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
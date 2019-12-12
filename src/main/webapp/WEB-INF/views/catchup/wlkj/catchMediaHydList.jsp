<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>媒体活跃度管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchMediaHyd/">媒体活跃度列表</a></li>
		<shiro:hasPermission name="wlkj:catchMediaHyd:edit"><li><a href="${ctx}/wlkj/catchMediaHyd/form">媒体活跃度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchMediaHyd" action="${ctx}/wlkj/catchMediaHyd/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>媒体</th>
				<th>活跃度</th>
				<shiro:hasPermission name="wlkj:catchMediaHyd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchMediaHyd">
			<tr> 
			    <td>
						${catchMediaHyd.legend}
			    </td>
			    <td>
						${catchMediaHyd.number}
			    </td>
				<shiro:hasPermission name="wlkj:catchMediaHyd:edit"><td>
    				<a href="${ctx}/wlkj/catchMediaHyd/form?id=${catchMediaHyd.id}">修改</a>
					<a href="${ctx}/wlkj/catchMediaHyd/delete?id=${catchMediaHyd.id}" onclick="return confirmx('确认要删除该媒体活跃度吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
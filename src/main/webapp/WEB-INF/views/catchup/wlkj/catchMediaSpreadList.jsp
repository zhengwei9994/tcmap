<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>媒体分布管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchMediaSpread/">媒体分布列表</a></li>
		<shiro:hasPermission name="wlkj:catchMediaSpread:edit"><li><a href="${ctx}/wlkj/catchMediaSpread/form">媒体分布添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchMediaSpread" action="${ctx}/wlkj/catchMediaSpread/" method="post" class="breadcrumb form-search">
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
			    <th>年份</th>
			    <th>媒体类型</th>
			    <th>占比</th>
				<shiro:hasPermission name="wlkj:catchMediaSpread:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchMediaSpread">
			<tr>
			    <td>
						${catchMediaSpread.nyear}
			    </td>
			    <td>
						${catchMediaSpread.projectType}
			   </td>
			   <td>
						${catchMediaSpread.totalInvestment}
			   </td>
				<shiro:hasPermission name="wlkj:catchMediaSpread:edit"><td>
    				<a href="${ctx}/wlkj/catchMediaSpread/form?id=${catchMediaSpread.id}">修改</a>
					<a href="${ctx}/wlkj/catchMediaSpread/delete?id=${catchMediaSpread.id}" onclick="return confirmx('确认要删除该媒体分布吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
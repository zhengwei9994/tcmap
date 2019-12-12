<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全天包抓监督实时动态管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchSuperviseDynamics/">全天包抓监督实时动态列表</a></li>
		<shiro:hasPermission name="csyx:catchSuperviseDynamic:edit"><li><a href="${ctx}/csyx/catchSuperviseDynamics/form">全天包抓监督实时动态添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSuperviseDynamics" action="${ctx}/csyx/catchSuperviseDynamics/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>内容</th>
				<th>项目开始时间</th>
				<th>操作人</th>
				<th>是否已读</th>
				<shiro:hasPermission name="csyx:catchSuperviseDynamic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSuperviseDynamic">
			<tr> 
			    <td>
					${catchSuperviseDynamic.projectName}
				</td>
			    <td>
					${catchSuperviseDynamic.content}
				</td>
				<td>
					<fmt:formatDate value="${catchSuperviseDynamic.createTime}" pattern="yyyy-MM-dd hh:mm:ss" />
				</td>
				 <td>
					${catchSuperviseDynamic.user.name}
				</td>
				<td>
					<c:if test="${catchSuperviseDynamic.hasRead=='1' }">是</c:if>
                    <c:if test="${catchSuperviseDynamic.hasRead=='0' }">否</c:if>
				</td>
				<shiro:hasPermission name="csyx:catchSuperviseDynamic:edit"><td>
    				<a href="${ctx}/csyx/catchSuperviseDynamics/form?id=${catchSuperviseDynamic.id}">修改</a>
					<a href="${ctx}/csyx/catchSuperviseDynamics/delete?id=${catchSuperviseDynamic.id}" onclick="return confirmx('确认要删除该全天包抓监督实时动态吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
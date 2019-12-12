<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>热点舆情管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchHotPublic/">热点舆情列表</a></li>
		<shiro:hasPermission name="wlkj:catchHotPublic:edit"><li><a href="${ctx}/wlkj/catchHotPublic/form">热点舆情添加</a></li></shiro:hasPermission>
	</ul>
	<div hidden>
		<form:form id="searchForm" modelAttribute="catchHotPublic" action="${ctx}/wlkj/catchHotPublic/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
				<li class="clearfix"></li>
			</ul>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>事件</th><th>排序</th>
				<shiro:hasPermission name="wlkj:catchHotPublic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchHotPublic">
			<tr>
				<td>${catchHotPublic.publicOpinion}</td><td>${catchHotPublic.sort}</td>
				<shiro:hasPermission name="wlkj:catchHotPublic:edit"><td>
    				<a href="${ctx}/wlkj/catchHotPublic/form?id=${catchHotPublic.id}">修改</a>
					<a href="${ctx}/wlkj/catchHotPublic/delete?id=${catchHotPublic.id}" onclick="return confirmx('确认要删除该热点舆情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
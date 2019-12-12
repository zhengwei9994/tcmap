<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网上办理情况管理</title>
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
		<li class="active"><a href="${ctx}/csyx/liveGovementSituation/">网上办理情况列表</a></li>
		<shiro:hasPermission name="csyx:liveGovementSituation:edit"><li><a href="${ctx}/csyx/liveGovementSituation/form">网上办理情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="liveGovementSituation" action="${ctx}/csyx/liveGovementSituation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>网上办理情况：</label>
				<form:input path="net" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>网上办理情况</th>
				<th>百分率</th>
				<shiro:hasPermission name="csyx:liveGovementSituation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveGovementSituation">
			<tr>
				<td><a href="${ctx}/csyx/liveGovementSituation/form?id=${liveGovementSituation.id}">
					${liveGovementSituation.net}
				</a></td>
				<td>
					${liveGovementSituation.netSituation}%
				</td>
				<shiro:hasPermission name="csyx:liveGovementSituation:edit"><td>
    				<a href="${ctx}/csyx/liveGovementSituation/form?id=${liveGovementSituation.id}">修改</a>
					<a href="${ctx}/csyx/liveGovementSituation/delete?id=${liveGovementSituation.id}" onclick="return confirmx('确认要删除该网上办理情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
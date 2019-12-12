<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实时预警舆情管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristTimeSentiment/">实时预警舆情列表</a></li>
		<shiro:hasPermission name="csly:touristTimeSentiment:edit"><li><a href="${ctx}/csly/touristTimeSentiment/form">实时预警舆情添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristTimeSentiment" action="${ctx}/csly/touristTimeSentiment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>景区：</label>
				<form:input path="scenic" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>摘要</th>
				<th>时间</th>
				<th>来源</th>
				<th>景区</th>
				<shiro:hasPermission name="csly:touristTimeSentiment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristTimeSentiment">
			<tr>
				<td><a href="${ctx}/csly/touristTimeSentiment/form?id=${touristTimeSentiment.id}">
					${touristTimeSentiment.abstracts}
				</a></td>
				<td>
					<fmt:formatDate value="${touristTimeSentiment.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${touristTimeSentiment.source}
				</td>
				<td>
					${touristTimeSentiment.scenic}
				</td>
				<shiro:hasPermission name="csly:touristTimeSentiment:edit"><td>
    				<a href="${ctx}/csly/touristTimeSentiment/form?id=${touristTimeSentiment.id}">修改</a>
					<a href="${ctx}/csly/touristTimeSentiment/delete?id=${touristTimeSentiment.id}" onclick="return confirmx('确认要删除该实时预警舆情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
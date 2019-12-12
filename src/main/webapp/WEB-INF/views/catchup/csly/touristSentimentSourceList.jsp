<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>舆情数据来源管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristSentimentSource/">舆情数据来源列表</a></li>
		<shiro:hasPermission name="csly:touristSentimentSource:edit"><li><a href="${ctx}/csly/touristSentimentSource/form">舆情数据来源添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristSentimentSource" action="${ctx}/csly/touristSentimentSource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>来源：</label>
				<form:input path="source" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>来源</th>
				<th>数量</th>
				<shiro:hasPermission name="csly:touristSentimentSource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristSentimentSource">
			<tr>
				<td><a href="${ctx}/csly/touristSentimentSource/form?id=${touristSentimentSource.id}">
					${touristSentimentSource.source}
				</a></td>
				<td>
					${touristSentimentSource.num}
				</td>
				<shiro:hasPermission name="csly:touristSentimentSource:edit"><td>
    				<a href="${ctx}/csly/touristSentimentSource/form?id=${touristSentimentSource.id}">修改</a>
					<a href="${ctx}/csly/touristSentimentSource/delete?id=${touristSentimentSource.id}" onclick="return confirmx('确认要删除该舆情数据来源吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
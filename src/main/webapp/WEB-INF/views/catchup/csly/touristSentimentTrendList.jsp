<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>舆情趋势管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristSentimentTrend/">舆情趋势列表</a></li>
		<shiro:hasPermission name="csly:touristSentimentTrend:edit"><li><a href="${ctx}/csly/touristSentimentTrend/form">舆情趋势添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristSentimentTrend" action="${ctx}/csly/touristSentimentTrend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>月：</label>
				<form:input path="month" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>年：</label>
				<form:input path="year" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>月</th>
				<th>数值</th>
				<th>年</th>
				<shiro:hasPermission name="csly:touristSentimentTrend:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristSentimentTrend">
			<tr>
				<td><a href="${ctx}/csly/touristSentimentTrend/form?id=${touristSentimentTrend.id}">
					${touristSentimentTrend.month}
				</a></td>
				<td>
					${touristSentimentTrend.num}
				</td>
				<td>
					${touristSentimentTrend.year}
				</td>
				<shiro:hasPermission name="csly:touristSentimentTrend:edit"><td>
    				<a href="${ctx}/csly/touristSentimentTrend/form?id=${touristSentimentTrend.id}">修改</a>
					<a href="${ctx}/csly/touristSentimentTrend/delete?id=${touristSentimentTrend.id}" onclick="return confirmx('确认要删除该舆情趋势吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
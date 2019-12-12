<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>舆情数据管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristSentimentData/">舆情数据列表</a></li>
		<shiro:hasPermission name="csly:touristSentimentData:edit"><li><a href="${ctx}/csly/touristSentimentData/form">舆情数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristSentimentData" action="${ctx}/csly/touristSentimentData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>年月</th>
				<th>地区</th>
				<th>数据</th>
				<shiro:hasPermission name="csly:touristSentimentData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristSentimentData">
			<tr>
				<td><a href="${ctx}/csly/touristSentimentData/form?id=${touristSentimentData.id}">
					${touristSentimentData.name}
				</a></td>
				<td>
						${touristSentimentData.nyear}-${touristSentimentData.nmonth}
				</td>
				<td>
						${touristSentimentData.area}
				</td>
				<td>
					${touristSentimentData.num}
				</td>
				<shiro:hasPermission name="csly:touristSentimentData:edit"><td>
    				<a href="${ctx}/csly/touristSentimentData/form?id=${touristSentimentData.id}">修改</a>
					<a href="${ctx}/csly/touristSentimentData/delete?id=${touristSentimentData.id}" onclick="return confirmx('确认要删除该舆情数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
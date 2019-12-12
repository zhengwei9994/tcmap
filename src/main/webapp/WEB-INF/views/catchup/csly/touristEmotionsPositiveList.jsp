<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>正面情绪管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristEmotionsPositive/">正面情绪列表</a></li>
		<shiro:hasPermission name="csly:touristEmotionsPositive:edit"><li><a href="${ctx}/csly/touristEmotionsPositive/form">正面情绪添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristEmotionsPositive" action="${ctx}/csly/touristEmotionsPositive/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>正面类别：</label>
				<form:input path="category" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>正面类别</th>
				<th>数量</th>
				<shiro:hasPermission name="csly:touristEmotionsPositive:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristEmotionsPositive">
			<tr>
				<td><a href="${ctx}/csly/touristEmotionsPositive/form?id=${touristEmotionsPositive.id}">
					${touristEmotionsPositive.category}
				</a></td>
				<td>
					${touristEmotionsPositive.num}
				</td>
				<shiro:hasPermission name="csly:touristEmotionsPositive:edit"><td>
    				<a href="${ctx}/csly/touristEmotionsPositive/form?id=${touristEmotionsPositive.id}">修改</a>
					<a href="${ctx}/csly/touristEmotionsPositive/delete?id=${touristEmotionsPositive.id}" onclick="return confirmx('确认要删除该正面情绪吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
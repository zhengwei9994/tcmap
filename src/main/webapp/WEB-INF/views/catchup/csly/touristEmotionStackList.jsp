<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>情绪堆叠图管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristEmotionStack/">情绪堆叠图列表</a></li>
		<shiro:hasPermission name="csly:touristEmotionStack:edit"><li><a href="${ctx}/csly/touristEmotionStack/form">情绪堆叠图添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristEmotionStack" action="${ctx}/csly/touristEmotionStack/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>情绪：</label>
				<form:input path="emotion" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>情绪</th>
				<th>月份</th>
				<th>数值</th>
				<th>年</th>
				<shiro:hasPermission name="csly:touristEmotionStack:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristEmotionStack">
			<tr>
				<td><a href="${ctx}/csly/touristEmotionStack/form?id=${touristEmotionStack.id}">
					${touristEmotionStack.emotion}
				</a></td>
				<td>
					${touristEmotionStack.month}
				</td>
				<td>
					${touristEmotionStack.num}
				</td>
				<td>
					${touristEmotionStack.year}
				</td>
				<shiro:hasPermission name="csly:touristEmotionStack:edit"><td>
    				<a href="${ctx}/csly/touristEmotionStack/form?id=${touristEmotionStack.id}">修改</a>
					<a href="${ctx}/csly/touristEmotionStack/delete?id=${touristEmotionStack.id}" onclick="return confirmx('确认要删除该情绪堆叠图吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
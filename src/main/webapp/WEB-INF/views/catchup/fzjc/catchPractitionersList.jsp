<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>劳动就业占比管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchPractitioners/">劳动就业占比列表</a></li>
		<%--<shiro:hasPermission name="fzjc:catchPractitioners:edit"><li><a href="${ctx}/fzjc/catchPractitioners/form">劳动就业占比添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="catchPractitioners" action="${ctx}/fzjc/catchPractitioners/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!-- 		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地址</th>
				<th>从业人员占比</th>
				<shiro:hasPermission name="fzjc:catchPractitioners:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchPractitioners">
			<tr>
				<td><a href="${ctx}/fzjc/catchPractitioners/form?id=${catchPractitioners.id}">
					${catchPractitioners.address}
				</a></td>
				<td>
					${catchPractitioners.proportion}
				</td>
				<shiro:hasPermission name="fzjc:catchPractitioners:edit"><td>
    				<a href="${ctx}/fzjc/catchPractitioners/form?id=${catchPractitioners.id}">修改</a>
					<a href="${ctx}/fzjc/catchPractitioners/delete?id=${catchPractitioners.id}" onclick="return confirmx('确认要删除该劳动就业占比吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
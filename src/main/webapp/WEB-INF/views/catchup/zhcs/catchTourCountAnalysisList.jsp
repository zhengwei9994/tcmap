<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游趋势分析管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchTourCountAnalysis/">旅游趋势分析列表</a></li>
		<shiro:hasPermission name="zhcs:catchTourCountAnalysis:edit"><li><a href="${ctx}/zhcs/catchTourCountAnalysis/form">旅游趋势分析添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchTourCountAnalysis" action="${ctx}/zhcs/catchTourCountAnalysis/" method="post" class="breadcrumb form-search">
		<!--<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>-->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>日期</th>
				<th>数值</th>
				<shiro:hasPermission name="zhcs:catchTourCountAnalysis:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchTourCountAnalysis">
			<tr>
				<td><a href="${ctx}/zhcs/catchTourCountAnalysis/form?id=${catchTravelInfo.id}">
						${catchTourCountAnalysis.nyear}
				</a></td>
				<td>
						${catchTourCountAnalysis.tourDay}
				</td>
				<td>
						${catchTourCountAnalysis.tourCount}
				</td>
				<shiro:hasPermission name="zhcs:catchTourCountAnalysis:edit"><td>
    				<a href="${ctx}/zhcs/catchTourCountAnalysis/form?id=${catchTourCountAnalysis.id}">修改</a>
					<a href="${ctx}/zhcs/catchTourCountAnalysis/delete?id=${catchTourCountAnalysis.id}" onclick="return confirmx('确认要删除该旅游趋势分析吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
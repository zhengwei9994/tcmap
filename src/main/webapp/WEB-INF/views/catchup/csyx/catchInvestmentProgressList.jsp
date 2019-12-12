<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目投资进度排行榜管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchInvestmentProgress/">项目投资进度排行榜列表</a></li>
		<shiro:hasPermission name="csyx:catchInvestmentProgress:edit"><li><a href="${ctx}/csyx/catchInvestmentProgress/form">项目投资进度排行榜添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchInvestmentProgress" action="${ctx}/csyx/catchInvestmentProgress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<!-- 		<ul class="ul-form"> -->
<!-- 			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
<!-- 			<li class="clearfix"></li> -->
<!-- 		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>项目名称</th>
				<th>投资金额</th>
				<th>单位</th>
				<th>累计投资占比</th>
				<shiro:hasPermission name="csyx:catchInvestmentProgress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchInvestmentProgress">
			<tr>
			    <td>
						${catchInvestmentProgress.projectName}
			    </td>
				<td>
						${catchInvestmentProgress.annualTask}
		    	</td>
		    	<td>
						${catchInvestmentProgress.unit}
		    	</td>
		    	<td>
						${catchInvestmentProgress.completedRatio}
		    	</td>
				<shiro:hasPermission name="csyx:catchInvestmentProgress:edit"><td>
    				<a href="${ctx}/csyx/catchInvestmentProgress/form?id=${catchInvestmentProgress.id}">修改</a>
					<a href="${ctx}/csyx/catchInvestmentProgress/delete?id=${catchInvestmentProgress.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
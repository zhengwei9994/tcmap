<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业演进管理</title>
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
		<li class="active"><a href="${ctx}/csyx/developmentEvolution/">产业演进列表</a></li>
		<shiro:hasPermission name="csyx:developmentEvolution:edit"><li><a href="${ctx}/csyx/developmentEvolution/form">产业演进添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="developmentEvolution" action="${ctx}/csyx/developmentEvolution/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>行业：</label>
				<form:input path="industry" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>年：</label>
				<form:input path="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>行业</th>
				<th>行业数量</th>
				<th>行业金额</th>
				<th>年</th>
				<shiro:hasPermission name="csyx:developmentEvolution:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="developmentEvolution">
			<tr>
				<td><a href="${ctx}/csyx/developmentEvolution/form?id=${developmentEvolution.id}">
					${developmentEvolution.industry}
				</a></td>
				<td>
					${developmentEvolution.industryNum}
				</td>
				<td>
					${developmentEvolution.industryMoney}
				</td>
				<td>
					${developmentEvolution.year}
				</td>
				<shiro:hasPermission name="csyx:developmentEvolution:edit"><td>
    				<a href="${ctx}/csyx/developmentEvolution/form?id=${developmentEvolution.id}">修改</a>
					<a href="${ctx}/csyx/developmentEvolution/delete?id=${developmentEvolution.id}" onclick="return confirmx('确认要删除该产业演进吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
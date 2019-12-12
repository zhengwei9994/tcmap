<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业发展管理</title>
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
		<li class="active"><a href="${ctx}/csyx/industrialDevelopment/">产业发展列表</a></li>
		<shiro:hasPermission name="csyx:industrialDevelopment:edit"><li><a href="${ctx}/csyx/industrialDevelopment/form">产业发展添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="industrialDevelopment" action="${ctx}/csyx/industrialDevelopment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年：</label>
				<form:input path="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			<li><label>月：</label>
				<form:input path="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   onclick="WdatePicker({dateFmt:'MM',isShowClear:false,isShowToday:false});"/>
			</li>
			</li>
			<li><label>行业：</label>
				<form:input path="profession" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>行业</th>
				<th>子行业</th>
				<th>子行业2</th>
				<th>资本</th>
				<th>增长率</th>
				<shiro:hasPermission name="csyx:industrialDevelopment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="industrialDevelopment">
			<tr>
				<td><a href="${ctx}/csyx/industrialDevelopment/form?id=${industrialDevelopment.id}">
					${industrialDevelopment.year}-${industrialDevelopment.month}
				</a></td>
				<td>
					${industrialDevelopment.profession}
				</td>
				<td>
					${industrialDevelopment.professionson1}
				</td>
				<td>
					${industrialDevelopment.professionson2}
				</td>
				<td>
					${industrialDevelopment.capital}
				</td>
				<td>
						${industrialDevelopment.growthrate}%
				</td>
				<shiro:hasPermission name="csyx:industrialDevelopment:edit"><td>
    				<a href="${ctx}/csyx/industrialDevelopment/form?id=${industrialDevelopment.id}">修改</a>
					<a href="${ctx}/csyx/industrialDevelopment/delete?id=${industrialDevelopment.id}" onclick="return confirmx('确认要删除该产业发展吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
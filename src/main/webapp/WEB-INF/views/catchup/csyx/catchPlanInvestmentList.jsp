<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>年计划总投资管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchPlanInvestment/">年计划总投资列表</a></li>
		<shiro:hasPermission name="csyx:catchPlanInvestment:edit"><li><a href="${ctx}/csyx/catchPlanInvestment/form">年计划总投资添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchPlanInvestment" action="${ctx}/csyx/catchPlanInvestment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${catchPlanInvestment.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>计划投资</th>
				<%--<th>累计完成投资</th>--%>
				<th>单位</th>
				<shiro:hasPermission name="csyx:catchPlanInvestment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchPlanInvestment">
			<tr>
				<td><a href="${ctx}/csyx/catchPlanInvestment/form?id=${catchPlanInvestment.id}">
					${catchPlanInvestment.nyear}
				</a></td>
				<td>
					${catchPlanInvestment.plannedInvestment}
				</td>
				<%--<td>
					${catchPlanInvestment.totalInvestment}
				</td>--%>
				<td>
					${fns:getDictLabel(catchPlanInvestment.unit, 'unit', '')}
				</td>
				<shiro:hasPermission name="csyx:catchPlanInvestment:edit"><td>
    				<a href="${ctx}/csyx/catchPlanInvestment/form?id=${catchPlanInvestment.id}">修改</a>
					<a href="${ctx}/csyx/catchPlanInvestment/delete?id=${catchPlanInvestment.id}" onclick="return confirmx('确认要删除该年计划总投资吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
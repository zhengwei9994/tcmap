<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>万元GDP能耗降低率管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchEnergyConsumption/">万元GDP能耗降低率列表</a></li>
		<!--
		<shiro:hasPermission name="fzjc:catchEnergyConsumption:edit"><li><a href="${ctx}/fzjc/catchEnergyConsumption/form">万元GDP能耗降低率添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="catchEnergyConsumption" action="${ctx}/fzjc/catchEnergyConsumption/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchEnergyConsumption.year}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>季度：</label>
				<form:select path="quarter" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('quarter')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>季度</th>
				<th>单位GDP能耗</th>
				<th>GDP能耗增速</th>
				<shiro:hasPermission name="fzjc:catchEnergyConsumption:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchEnergyConsumption">
			<tr>
				<td><a href="${ctx}/fzjc/catchEnergyConsumption/form?id=${catchEnergyConsumption.id}">
					${catchEnergyConsumption.year}
				</a></td>
				<td>
					${fns:getDictLabel(catchEnergyConsumption.quarter, 'quarter', '')}
				</td>
				<td>
					${catchEnergyConsumption.energyConsumption}
				</td>
				<td>
					${catchEnergyConsumption.energyConsumptionRate}
				</td>
				<shiro:hasPermission name="fzjc:catchEnergyConsumption:edit"><td>
    				<a href="${ctx}/fzjc/catchEnergyConsumption/form?id=${catchEnergyConsumption.id}">修改</a>
					<!--
					<a href="${ctx}/fzjc/catchEnergyConsumption/delete?id=${catchEnergyConsumption.id}" onclick="return confirmx('确认要删除该万元GDP能耗降低率吗？', this.href)">删除</a>
					-->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
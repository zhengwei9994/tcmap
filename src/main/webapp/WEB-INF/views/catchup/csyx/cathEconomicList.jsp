<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济指标统计管理</title>
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
		<li class="active"><a href="${ctx}/csyx/cathEconomic/">经济指标统计列表</a></li>
		<shiro:hasPermission name="csyx:cathEconomic:edit"><li><a href="${ctx}/csyx/cathEconomic/form">经济指标统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cathEconomic" action="${ctx}/csyx/cathEconomic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					 value="${cathEconomic.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
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
				<th>指标名称</th>
				<th>年份</th>
				<th>季度</th>
				<th>经济指标统计</th>
				<th>单位</th>
				<th>增速</th>
				<shiro:hasPermission name="csyx:cathEconomic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cathEconomic">
			<tr>
				<td><a href="${ctx}/csyx/cathEconomic/form?id=${cathEconomic.id}">
					${cathEconomic.indexId.name}
				</a></td>
				<td>
					${cathEconomic.nyear}
				</td>
				<td>
					${cathEconomic.quarter}
				</td>
				<td>
					${cathEconomic.indicators}
				</td>
				<td>
					${fns:getDictLabel(cathEconomic.indicatorsUnit, 'unit', '')}
				</td>
				<td>
					${cathEconomic.growth}
				</td>
				<shiro:hasPermission name="csyx:cathEconomic:edit"><td>
    				<a href="${ctx}/csyx/cathEconomic/form?id=${cathEconomic.id}">修改</a>
					<a href="${ctx}/csyx/cathEconomic/delete?id=${cathEconomic.id}" onclick="return confirmx('确认要删除该经济指标统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>指标数据运行状态管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchGrowthSituation/">区县经济增长情况列表</a></li>
		<shiro:hasPermission name="fzjc:catchGrowthSituation:edit"><li><a href="${ctx}/fzjc/catchGrowthSituation/form">区县经济增长情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchGrowthSituation" action="${ctx}/fzjc/catchGrowthSituation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchGrowthSituation.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>类型：</label>
				<form:select path="indexType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('economic_growth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!--
			<li><label>指标名称：</label>
				<form:input path="indexName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>-->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年份</th>
				<th>指标类型</th>
				<th>增长值</th>
				<th>月份</th>
				<shiro:hasPermission name="fzjc:catchGrowthSituation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchGrowthSituation">
			<tr>
				<td><a href="${ctx}/fzjc/catchGrowthSituation/form?id=${catchGrowthSituation.id}">
					${catchGrowthSituation.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchGrowthSituation.indexType, 'economic_growth_type', '')}
				</td>
				<td>
					${catchGrowthSituation.indexValue}
				</td>
				<td>
					${catchGrowthSituation.cmonth}
				</td>
				<shiro:hasPermission name="fzjc:catchGrowthSituation:edit"><td>
    				<a href="${ctx}/fzjc/catchGrowthSituation/form?id=${catchGrowthSituation.id}">修改</a>
					<a href="${ctx}/fzjc/catchGrowthSituation/delete?id=${catchGrowthSituation.id}" onclick="return confirmx('确认要删除该指标数据运行状态吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人才类型统计管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchResourcesCount/">人才类型统计列表</a></li>
		<shiro:hasPermission name="zhcs:catchResourcesCount:edit"><li><a href="${ctx}/zhcs/catchResourcesCount/form">人才类型统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchResourcesCount" action="${ctx}/zhcs/catchResourcesCount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchResourcesCount.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>月份：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchResourcesCount.month}" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>人才类型：</label>
				<form:select path="personnelType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('personnel_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>年月</th>
				<th>人才类型</th>
				<th>男性人数</th>
				<th>女性人数</th>
				<shiro:hasPermission name="zhcs:catchResourcesCount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchResourcesCount">
			<tr>
				<td><a href="${ctx}/zhcs/catchResourcesCount/form?id=${catchResourcesCount.id}">
					${catchResourcesCount.nyear}-${catchResourcesCount.month}
				</a></td>
				<td>
					${fns:getDictLabel(catchResourcesCount.personnelType, 'personnel_type', '')}
				</td>
				<td>
					${catchResourcesCount.male}
				</td>
				<td>
					${catchResourcesCount.female}
				</td>
				<shiro:hasPermission name="zhcs:catchResourcesCount:edit"><td>
    				<a href="${ctx}/zhcs/catchResourcesCount/form?id=${catchResourcesCount.id}">修改</a>
					<a href="${ctx}/zhcs/catchResourcesCount/delete?id=${catchResourcesCount.id}" onclick="return confirmx('确认要删除该人才类型统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
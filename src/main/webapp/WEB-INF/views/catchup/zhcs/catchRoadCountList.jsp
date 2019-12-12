<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公路统计管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchRoadCount/">公路统计列表</a></li>
		<shiro:hasPermission name="zhcs:catchRoadCount:edit"><li><a href="${ctx}/zhcs/catchRoadCount/form">公路统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchRoadCount" action="${ctx}/zhcs/catchRoadCount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchRoadCount.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>公路类型：</label>
				<form:select path="roadType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('road_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>公路类型</th>
				<th>公路里程</th>
				<shiro:hasPermission name="zhcs:catchRoadCount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchRoadCount">
			<tr>
				<td><a href="${ctx}/zhcs/catchRoadCount/form?id=${catchRoadCount.id}">
					${catchRoadCount.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchRoadCount.roadType, 'road_type', '')}
				</td>
				<td>
					${catchRoadCount.roadMileage}公里
				</td>
				<shiro:hasPermission name="zhcs:catchRoadCount:edit"><td>
    				<a href="${ctx}/zhcs/catchRoadCount/form?id=${catchRoadCount.id}">修改</a>
					<a href="${ctx}/zhcs/catchRoadCount/delete?id=${catchRoadCount.id}" onclick="return confirmx('确认要删除该公路统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
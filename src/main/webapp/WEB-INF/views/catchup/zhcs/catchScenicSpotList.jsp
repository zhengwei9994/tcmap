<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游资产管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchScenicSpot/">旅游资产列表</a></li>
		<shiro:hasPermission name="zhcs:catchScenicSpot:edit"><li><a href="${ctx}/zhcs/catchScenicSpot/form">旅游资产添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchScenicSpot" action="${ctx}/zhcs/catchScenicSpot/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchScenicSpot.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>景点等级：</label>
				<form:select path="spotType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('spot_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>景点名称：</label>
				<form:input path="spotName" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>景点等级</th>
				<th>景点名称</th>
				<th>展示位置</th>
				<shiro:hasPermission name="zhcs:catchScenicSpot:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchScenicSpot">
			<tr>
				<td><a href="${ctx}/zhcs/catchScenicSpot/form?id=${catchScenicSpot.id}">
					${catchScenicSpot.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchScenicSpot.spotType, 'spot_type', '')}
				</td>
				<td>
					${catchScenicSpot.spotName}
				</td>
				<td>
					${fns:getDictLabel(catchScenicSpot.displayPosition, 'display_position', '')}
				</td>
				<shiro:hasPermission name="zhcs:catchScenicSpot:edit"><td>
    				<a href="${ctx}/zhcs/catchScenicSpot/form?id=${catchScenicSpot.id}">修改</a>
					<a href="${ctx}/zhcs/catchScenicSpot/delete?id=${catchScenicSpot.id}" onclick="return confirmx('确认要删除该旅游资产吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
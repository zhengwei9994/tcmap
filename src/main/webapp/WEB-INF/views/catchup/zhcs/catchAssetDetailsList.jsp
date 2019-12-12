<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源详情管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchAssetDetails/">资源详情列表</a></li>
		<shiro:hasPermission name="zhcs:catchAssetDetails:edit"><li><a href="${ctx}/zhcs/catchAssetDetails/form">资源详情添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchAssetDetails" action="${ctx}/zhcs/catchAssetDetails/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchAssetDetails.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>资产类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('asset_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>资产类型</th>
				<th>详情</th>
				<shiro:hasPermission name="zhcs:catchAssetDetails:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchAssetDetails">
			<tr>
				<td><a href="${ctx}/zhcs/catchAssetDetails/form?id=${catchAssetDetails.id}">
					${catchAssetDetails.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchAssetDetails.type, 'asset_type', '')}
				</td>
				<td>
					${catchAssetDetails.cont}
				</td>
				<shiro:hasPermission name="zhcs:catchAssetDetails:edit"><td>
    				<a href="${ctx}/zhcs/catchAssetDetails/form?id=${catchAssetDetails.id}">修改</a>
					<a href="${ctx}/zhcs/catchAssetDetails/delete?id=${catchAssetDetails.id}" onclick="return confirmx('确认要删除该资源详情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
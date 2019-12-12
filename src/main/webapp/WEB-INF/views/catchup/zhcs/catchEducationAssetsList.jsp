<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教育资产管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchEducationAssets/">教育资产列表</a></li>
		<shiro:hasPermission name="zhcs:catchEducationAssets:edit"><li><a href="${ctx}/zhcs/catchEducationAssets/form">教育资产添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchEducationAssets" action="${ctx}/zhcs/catchEducationAssets/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchEducationAssets.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>月份：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchEducationAssets.month}" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>学校类别：</label>
				<form:select path="educationType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('education_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>学校类别</th>
				<th>学校数量</th>
				<th>教职人员总数</th>
				<shiro:hasPermission name="zhcs:catchEducationAssets:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchEducationAssets">
			<tr>
				<td><a href="${ctx}/zhcs/catchEducationAssets/form?id=${catchEducationAssets.id}">
					${catchEducationAssets.nyear}-${catchEducationAssets.month}
				</a></td>
				<td>
					${fns:getDictLabel(catchEducationAssets.educationType, 'education_type', '')}
				</td>
				<td>
					${catchEducationAssets.schoolNumber}
				</td>
				<td>
					${catchEducationAssets.teachingStaff}
				</td>
				<shiro:hasPermission name="zhcs:catchEducationAssets:edit"><td>
    				<a href="${ctx}/zhcs/catchEducationAssets/form?id=${catchEducationAssets.id}">修改</a>
					<a href="${ctx}/zhcs/catchEducationAssets/delete?id=${catchEducationAssets.id}" onclick="return confirmx('确认要删除该教育资产吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
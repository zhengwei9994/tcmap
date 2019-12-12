<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点项目管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchKeyproject/">工程项目列表</a></li>
		<shiro:hasPermission name="csyx:catchKeyproject:edit"><li><a href="${ctx}/csyx/catchKeyproject/form">工程项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchKeyproject" action="${ctx}/csyx/catchKeyproject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${catchKeyproject.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="entryName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目类型：</label>
				<form:select path="projectType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>项目名称</th>
				<th>项目类型</th>
				<th>总投资</th>
				<th>单位</th>
				<shiro:hasPermission name="csyx:catchKeyproject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchKeyproject">
			<tr>
				<td><a href="${ctx}/csyx/catchKeyproject/form?id=${catchKeyproject.id}">
					${catchKeyproject.nyear}
				</a></td>
				<td>
					${catchKeyproject.entryName}
				</td>
				<td>
					${fns:getDictLabel(catchKeyproject.projectType, 'project_type', '')}
				</td>
				<td>
					${catchKeyproject.totalInvestment}
				</td>
				<td>
					${fns:getDictLabel(catchKeyproject.unit, 'unit', '')}
				</td>

				<shiro:hasPermission name="csyx:catchKeyproject:edit"><td>
					<a href="${ctx}/csyx/catchKeyprojectClass?keyprojectId.id=${catchKeyproject.id}&keyprojectId.totalInvestment=${catchKeyproject.totalInvestment}">月进度</a>
    				<a href="${ctx}/csyx/catchKeyproject/form?id=${catchKeyproject.id}">修改</a>
					<a href="${ctx}/csyx/catchKeyproject/delete?id=${catchKeyproject.id}" onclick="return confirmx('确认要删除该重点项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
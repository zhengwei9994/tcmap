<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>存在问题处置率管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchApprovalMatters/">存在问题处置率列表</a></li>
		<shiro:hasPermission name="csyx:catchApprovalMatters:edit"><li><a href="${ctx}/csyx/catchApprovalMatters/form">存在问题处置率添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchApprovalMatters" action="${ctx}/csyx/catchApprovalMatters/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${catchApprovalMatters.nyear}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>部门：</label>
				<form:select path="department" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('municipal_department')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>部门</th>
				<th>审批事项数目</th>
				<shiro:hasPermission name="csyx:catchApprovalMatters:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchApprovalMatters">
			<tr>
				<td><a href="${ctx}/csyx/catchApprovalMatters/form?id=${catchApprovalMatters.id}">
					${catchApprovalMatters.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchApprovalMatters.department, 'municipal_department', '')}
				</td>
				<td>
					${catchApprovalMatters.mattersCount}
				</td>
				<shiro:hasPermission name="csyx:catchApprovalMatters:edit"><td>
    				<a href="${ctx}/csyx/catchApprovalMatters/form?id=${catchApprovalMatters.id}">修改</a>
					<a href="${ctx}/csyx/catchApprovalMatters/delete?id=${catchApprovalMatters.id}" onclick="return confirmx('确认要删除该存在问题处置率吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
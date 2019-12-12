<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行政审批事项统计管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchApprovalMatters/">行政审批事项统计列表</a></li>
		<shiro:hasPermission name="fzjc:catchApprovalMatters:edit"><li><a href="${ctx}/fzjc/catchApprovalMatters/form">行政审批事项统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchApprovalMatters" action="${ctx}/fzjc/catchApprovalMatters/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchApprovalMatters.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
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
				<shiro:hasPermission name="fzjc:catchApprovalMatters:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchApprovalMatters">
			<tr>
				<td><a href="${ctx}/fzjc/catchApprovalMatters/form?id=${catchApprovalMatters.id}">
					${catchApprovalMatters.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchApprovalMatters.department, 'municipal_department', '')}
				</td>
				<td>
					${catchApprovalMatters.mattersCount}
				</td>
				<shiro:hasPermission name="fzjc:catchApprovalMatters:edit"><td>
    				<a href="${ctx}/fzjc/catchApprovalMatters/form?id=${catchApprovalMatters.id}">修改</a>
					<a href="${ctx}/fzjc/catchApprovalMatters/delete?id=${catchApprovalMatters.id}" onclick="return confirmx('确认要删除该行政审批事项统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
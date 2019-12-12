<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>社会治理管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchGovernment/">社会治理列表</a></li>
		<shiro:hasPermission name="csyx:catchGovernment:edit"><li><a href="${ctx}/csyx/catchGovernment/form">社会治理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchGovernment" action="${ctx}/csyx/catchGovernment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${catchGovernment.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>治理类型：</label>
				<form:select path="governanceType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('governance_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>社会治理类型</th>
				<th>治理总数</th>
				<th>单位</th>
				<shiro:hasPermission name="csyx:catchGovernment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchGovernment">
			<tr>
				<td><a href="${ctx}/csyx/catchGovernment/form?id=${catchGovernment.id}">
						${catchGovernment.nyear}
				</a></td>
				<td>
						${fns:getDictLabel(catchGovernment.governanceType, 'governance_type', '')}
				</td>
				<td>
						${catchGovernment.numericalValue}
				</td>
				<td>
						${fns:getDictLabel(catchGovernment.unit, 'unit1', '')}
				</td>
				<shiro:hasPermission name="csyx:catchGovernment:edit"><td>
    				<a href="${ctx}/csyx/catchGovernment/form?id=${catchGovernment.id}">修改</a>
					<a href="${ctx}/csyx/catchGovernment/delete?id=${catchGovernment.id}" onclick="return confirmx('确认要删除该社会治理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
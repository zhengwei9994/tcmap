<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>运行指标完成率管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchIndexCompletion/">运行指标完成率列表</a></li>
		<shiro:hasPermission name="fzjc:catchIndexCompletion:edit"><li><a href="${ctx}/fzjc/catchIndexCompletion/form">运行指标完成率添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchIndexCompletion" action="${ctx}/fzjc/catchIndexCompletion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchIndexCompletion.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>指标类型：</label>
				<form:select path="indexType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('index_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>指标名称：</label>
				<form:input path="indexName" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>指标类型</th>
				<th>指标名称</th>
				<th>指标完成率</th>
				<shiro:hasPermission name="fzjc:catchIndexCompletion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchIndexCompletion">
			<tr>
				<td><a href="${ctx}/fzjc/catchIndexCompletion/form?id=${catchIndexCompletion.id}">
					${catchIndexCompletion.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchIndexCompletion.indexType, 'index_type', '')}
				</td>
				<td>
					${catchIndexCompletion.indexName}
				</td>
				<td>
					${catchIndexCompletion.completionRate}
				</td>
				<shiro:hasPermission name="fzjc:catchIndexCompletion:edit"><td>
    				<a href="${ctx}/fzjc/catchIndexCompletion/form?id=${catchIndexCompletion.id}">修改</a>
					<a href="${ctx}/fzjc/catchIndexCompletion/delete?id=${catchIndexCompletion.id}" onclick="return confirmx('确认要删除该运行指标完成率吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
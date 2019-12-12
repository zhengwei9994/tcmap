<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人力资源统计管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchHumanResources/">人力资源统计列表</a></li>
		<shiro:hasPermission name="fzjc:catchHumanResources:edit"><li><a href="${ctx}/fzjc/catchHumanResources/form">人力资源统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchHumanResources" action="${ctx}/fzjc/catchHumanResources/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchHumanResources.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>单位类型：</label>
				<form:select path="companyType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('company_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>单位类型</th>
				<th>从业人数（万）</th>
				<shiro:hasPermission name="fzjc:catchHumanResources:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchHumanResources">
			<tr>
				<td><a href="${ctx}/fzjc/catchHumanResources/form?id=${catchHumanResources.id}">
					${catchHumanResources.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchHumanResources.companyType, 'company_type', '')}
				</td>
				<td>
					${catchHumanResources.numberPeople}
				</td>
				<shiro:hasPermission name="fzjc:catchHumanResources:edit"><td>
    				<a href="${ctx}/fzjc/catchHumanResources/form?id=${catchHumanResources.id}">修改</a>
					<a href="${ctx}/fzjc/catchHumanResources/delete?id=${catchHumanResources.id}" onclick="return confirmx('确认要删除该人力资源统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
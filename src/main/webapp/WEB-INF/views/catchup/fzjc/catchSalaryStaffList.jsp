<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>薪资及人员分析管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchSalaryStaff/">薪资及人员分析列表</a></li>
		<shiro:hasPermission name="fzjc:catchSalaryStaff:edit"><li><a href="${ctx}/fzjc/catchSalaryStaff/form">薪资及人员分析添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSalaryStaff" action="${ctx}/fzjc/catchSalaryStaff/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchSalaryStaff.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>统计内容：</label>
				<form:select path="statisticalContent" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('statistical_content')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>统计内容</th>
				<th>数量</th>
				<th>单位</th>
				<th>增速</th>
				<shiro:hasPermission name="fzjc:catchSalaryStaff:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSalaryStaff">
			<tr>
				<td><a href="${ctx}/fzjc/catchSalaryStaff/form?id=${catchSalaryStaff.id}">
					${catchSalaryStaff.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchSalaryStaff.statisticalContent, 'statistical_content', '')}
				</td>
				<td>
					${catchSalaryStaff.numericalValue}
				</td>
				<td>
					${catchSalaryStaff.unti}
				</td>
				<td>
					${catchSalaryStaff.growthRate}%
				</td>
				<shiro:hasPermission name="fzjc:catchSalaryStaff:edit"><td>
    				<a href="${ctx}/fzjc/catchSalaryStaff/form?id=${catchSalaryStaff.id}">修改</a>
					<a href="${ctx}/fzjc/catchSalaryStaff/delete?id=${catchSalaryStaff.id}" onclick="return confirmx('确认要删除该薪资及人员分析吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
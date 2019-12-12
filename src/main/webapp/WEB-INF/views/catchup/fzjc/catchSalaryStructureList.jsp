<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人才结构分析管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchSalaryStructure/">人才结构分析列表</a></li>
		<shiro:hasPermission name="fzjc:catchSalaryStructure:edit"><li><a href="${ctx}/fzjc/catchSalaryStructure/form">人才结构分析添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSalaryStructure" action="${ctx}/fzjc/catchSalaryStructure/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日期：</label>
				 <input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${catchSalaryStructure.date}" onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>中专</th>
				<th>大专</th>
				<th>本科</th>
				<th>硕士以上</th>
				<shiro:hasPermission name="fzjc:catchSalaryStructure:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSalaryStructure">
			<tr>
				<td><a href="${ctx}/fzjc/catchSalaryStructure/form?id=${catchSalaryStructure.id}">
					${catchSalaryStructure.date}
				</a></td>
				<td>
					${catchSalaryStructure.junior}
				</td>
				<td>
					${catchSalaryStructure.technical}
				</td>
				<td>
					${catchSalaryStructure.college}
				</td>
				<td>
					${catchSalaryStructure.ducation}
				</td>
				<shiro:hasPermission name="fzjc:catchSalaryStructure:edit"><td>
    				<a href="${ctx}/fzjc/catchSalaryStructure/form?id=${catchSalaryStructure.id}">修改</a>
					<a href="${ctx}/fzjc/catchSalaryStructure/delete?id=${catchSalaryStructure.id}" onclick="return confirmx('确认要删除该人才结构分析吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
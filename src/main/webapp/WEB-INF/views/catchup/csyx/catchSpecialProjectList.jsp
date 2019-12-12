<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特色项目管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchSpecialProject/">特色项目列表</a></li>
		<shiro:hasPermission name="csyx:catchSpecialProject:edit"><li><a href="${ctx}/csyx/catchSpecialProject/form">特色项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSpecialProject" action="${ctx}/csyx/catchSpecialProject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					 value="${catchSpecialProject.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
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
				<th>明细</th>
				<th>项目图片路径</th>
				<shiro:hasPermission name="csyx:catchSpecialProject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSpecialProject">
			<tr>
				<td><a href="${ctx}/csyx/catchSpecialProject/form?id=${catchSpecialProject.id}">
					${catchSpecialProject.nyear}
				</a></td>
				<td>
					${catchSpecialProject.projectname}
				</td>
				<td>
					${catchSpecialProject.details}
				</td>
				<td>
					${catchSpecialProject.sort}
				</td>
				<shiro:hasPermission name="csyx:catchSpecialProject:edit"><td>
    				<a href="${ctx}/csyx/catchSpecialProject/form?id=${catchSpecialProject.id}">修改</a>
					<a href="${ctx}/csyx/catchSpecialProject/delete?id=${catchSpecialProject.id}" onclick="return confirmx('确认要删除该特色项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
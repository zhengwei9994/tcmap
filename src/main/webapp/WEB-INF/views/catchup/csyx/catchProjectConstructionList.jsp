<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点项目建设管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchProjectConstruction/">重点项目建设列表</a></li>
		<shiro:hasPermission name="csyx:catchProjectConstruction:edit"><li><a href="${ctx}/csyx/catchProjectConstruction/form">重点项目建设添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchProjectConstruction" action="${ctx}/csyx/catchProjectConstruction/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
<!-- 			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
<!-- 			<li class="clearfix"></li> -->
		</ul>
	</form:form>
	<sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr> 
			    <th>项目名称</th>
				<th>负责人</th>
				<th>项目开始时间</th>
				<th>项目金额</th>
				<th>投资金额</th>
				<th>进度</th>
				<th>描述</th>
				<th>项目单位</th>
				<th>图片路径</th>
				<shiro:hasPermission name="csyx:catchProjectConstruction:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchProjectConstruction">
			<tr>
			    <td><a href="${ctx}/csyx/catchProjectConstruction/form?id=${catchProjectConstruction.id}">
						${catchProjectConstruction.projectName}</a></td>
			    <td>${catchProjectConstruction.leadership}</td>
			    <td><fmt:formatDate value="${catchProjectConstruction.startTime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			    <td>${catchProjectConstruction.annualTask}</td>
			    <td>${catchProjectConstruction.plannedInvestment}</td>
			    <td>${catchProjectConstruction.progress}</td>
			    <td>${catchProjectConstruction.projectIntro}</td>
			    <td>${catchProjectConstruction.hostUnit}</td>
			    <td>${catchProjectConstruction.imagePath}</td>
				<shiro:hasPermission name="csyx:catchProjectConstruction:edit"><td>
    				<a href="${ctx}/csyx/catchProjectConstruction/form?id=${catchProjectConstruction.id}">修改</a>
					<a href="${ctx}/csyx/catchProjectConstruction/delete?id=${catchProjectConstruction.id}" onclick="return confirmx('确认要删除该重点项目建设吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
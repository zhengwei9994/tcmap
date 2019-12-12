<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电子证照类别管理管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchElectronicCategory/">电子证照类别管理列表</a></li>
		<shiro:hasPermission name="fzjc:catchElectronicCategory:edit"><li><a href="${ctx}/fzjc/catchElectronicCategory/form">电子证照类别管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchElectronicCategory" action="${ctx}/fzjc/catchElectronicCategory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类别：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类别</th>
				<th>使用次数</th>
				<th>证照属性</th>
				<shiro:hasPermission name="fzjc:catchElectronicCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchElectronicCategory">
			<tr>
				<td><a href="${ctx}/fzjc/catchElectronicCategory/form?id=${catchElectronicCategory.id}">
					${catchElectronicCategory.name}
				</a></td>
				<td>
					${catchElectronicCategory.value}
				</td>
				<td>
				 <c:if test="${catchElectronicCategory.type=='1' }">服务类型 </c:if>
                 <c:if test="${catchElectronicCategory.type=='2' }">行业</c:if>
				</td>
				<shiro:hasPermission name="fzjc:catchElectronicCategory:edit"><td>
    				<a href="${ctx}/fzjc/catchElectronicCategory/form?id=${catchElectronicCategory.id}">修改</a>
					<a href="${ctx}/fzjc/catchElectronicCategory/delete?id=${catchElectronicCategory.id}" onclick="return confirmx('确认要删除该电子证照类别管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>存在问题比例管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchProblemSolving/">存在问题比例列表</a></li>
		<shiro:hasPermission name="csyx:catchProblemSolving:edit"><li><a href="${ctx}/csyx/catchProblemSolving/form">存在问题比例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchProblemSolving" action="${ctx}/csyx/catchProblemSolving/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			     <th>工程类型</th>
                 <th>问题数量</th>
                 <th>是否解决</th>
				<shiro:hasPermission name="csyx:catchProblemSolving:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchProblemSolving">
			<tr> 
			    <td>
                ${catchProblemSolving.type}
                </td>
                <td>
                ${catchProblemSolving.num}
                </td>
                <td>
                 <c:if test="${catchProblemSolving.hasSolve=='0' }">是</c:if>
                 <c:if test="${catchProblemSolving.hasSolve=='1' }">否</c:if>
                </td>
				<shiro:hasPermission name="csyx:catchProblemSolving:edit"><td>
    				<a href="${ctx}/csyx/catchProblemSolving/form?id=${catchProblemSolving.id}">修改</a>
					<a href="${ctx}/csyx/catchProblemSolving/delete?id=${catchProblemSolving.id}" onclick="return confirmx('确认要删除？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
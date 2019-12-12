<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模型实例管理表管理</title>
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
		<li class="active"><a href="${ctx}/sfmk/algorithmicExample/">模型实例列表</a></li>
		<shiro:hasPermission name="sfmk:algorithmicExample:edit"><li><a href="${ctx}/sfmk/algorithmicExample/formList">模型实例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="algorithmicParameter" action="${ctx}/sfmk/algorithmicExample/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模型因子：</label>
				<form:input path="parametername" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实例名称</th>
				<th>模型</th>
				<th>模型因子</th>
				<th>数值</th>
				<th>结果</th>
				<shiro:hasPermission name="sfmk:algorithmicExample:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="algorithmicExample">
			<tr>
				<td><a href="${ctx}/sfmk/algorithmicExample/form?id=${algorithmicExample.id}">
					${algorithmicExample.name}
				</a></td>
				<td>
					<c:forEach items="${rootpage.list}" var="rootAlgorithmic">
						<c:if test="${rootAlgorithmic.id ==algorithmicExample.rootid}">
							${rootAlgorithmic.algorithmic}
						</c:if>
					</c:forEach>
						<%--${algorithmicExample.rootid}--%>
				</td>
				<td>
					<c:forEach items="${parameterpage.list}" var="algorithmicParameter">
						<c:if test="${algorithmicParameter.id ==algorithmicExample.parameterid}">
							${algorithmicParameter.parametername}
						</c:if>
					</c:forEach>
					<%--${algorithmicExample.parameterid}--%>
				</td>
				<td>
					${algorithmicExample.numvalue}
				</td>
				<td>
					${algorithmicExample.result}
				</td>
				<shiro:hasPermission name="sfmk:algorithmicExample:edit"><td>
    				<a href="${ctx}/sfmk/algorithmicExample/form?id=${algorithmicExample.id}">修改</a>
					<a href="${ctx}/sfmk/algorithmicExample/delete?id=${algorithmicExample.id}" onclick="return confirmx('确认要删除该模型实例吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
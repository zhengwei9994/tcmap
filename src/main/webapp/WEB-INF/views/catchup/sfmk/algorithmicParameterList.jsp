<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模型因子管理表管理</title>
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
		<li class="active"><a href="${ctx}/sfmk/algorithmicParameter/">模型因子列表</a></li>
		<shiro:hasPermission name="sfmk:algorithmicParameter:edit"><li><a href="${ctx}/sfmk/algorithmicParameter/form">模型因子添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="algorithmicParameter" action="${ctx}/sfmk/algorithmicParameter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>算法主表id：</label>--%>
				<%--<form:input path="algorithmicid" htmlEscape="false" maxlength="100" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>参数名：</label>
				<form:input path="parametername" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>参数类型：</label>
				<form:input path="parametertype" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>参数名</th>
				<th>模型</th>
				<th>参数类型</th>
				<shiro:hasPermission name="sfmk:algorithmicParameter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="algorithmicParameter">
			<tr>
				<td><a href="${ctx}/sfmk/algorithmicParameter/form?id=${algorithmicParameter.id}">
						${algorithmicParameter.parametername}
				</a></td>
				<td>
					<c:forEach items="${rootpage.list}" var="rootAlgorithmic">
						<c:if test="${rootAlgorithmic.id==algorithmicParameter.algorithmicid}">
							${rootAlgorithmic.algorithmic}
						</c:if>
					</c:forEach>
					<%--${algorithmicParameter.algorithmicid}--%>
				</td>
				<td>
					${algorithmicParameter.parametertype}
				</td>
				<shiro:hasPermission name="sfmk:algorithmicParameter:edit"><td>
    				<a href="${ctx}/sfmk/algorithmicParameter/form?id=${algorithmicParameter.id}">修改</a>
					<a href="${ctx}/sfmk/algorithmicParameter/delete?id=${algorithmicParameter.id}" onclick="return confirmx('确认要删除该模型因子吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
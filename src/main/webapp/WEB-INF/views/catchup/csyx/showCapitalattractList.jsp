<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济资本吸引力管理</title>
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
		<li class="active"><a href="${ctx}/csyx/showCapitalattract/">经济资本吸引力列表</a></li>
		<shiro:hasPermission name="csyx:showCapitalattract:edit"><li><a href="${ctx}/csyx/showCapitalattract/form">经济资本吸引力添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="showCapitalattract" action="${ctx}/csyx/showCapitalattract/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>从业人员：</label>
				<form:input path="practitioners" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>教育支出：</label>
				<form:input path="education" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>教育支出比重：</label>
				<form:input path="educationProportion" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>利用外资：</label>
				<form:input path="foreign" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>固定投资：</label>
				<form:input path="investment" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>从业人员</th>
				<th>教育支出</th>
				<th>教育支出比重</th>
				<th>利用外资</th>
				<th>固定投资</th>
				<shiro:hasPermission name="csyx:showCapitalattract:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="showCapitalattract">
			<tr>
				<td><a href="${ctx}/csyx/showCapitalattract/form?id=${showCapitalattract.id}">
					${showCapitalattract.year}-${showCapitalattract.month}
				</a></td>
				<td>
					${showCapitalattract.practitioners}
				</td>
				<td>
					${showCapitalattract.education}
				</td>
				<td>
					${showCapitalattract.educationProportion}
				</td>
				<td>
					${showCapitalattract.foreign}
				</td>
				<td>
					${showCapitalattract.investment}
				</td>
				<shiro:hasPermission name="csyx:showCapitalattract:edit"><td>
    				<a href="${ctx}/csyx/showCapitalattract/form?id=${showCapitalattract.id}">修改</a>
					<a href="${ctx}/csyx/showCapitalattract/delete?id=${showCapitalattract.id}" onclick="return confirmx('确认要删除该经济资本吸引力吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
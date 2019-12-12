<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>厕所正面词云管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristWcPositive/">厕所正面词云列表</a></li>
		<shiro:hasPermission name="csly:touristWcPositive:edit"><li><a href="${ctx}/csly/touristWcPositive/form">厕所正面词云添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristWcPositive" action="${ctx}/csly/touristWcPositive/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>正面词云：</label>
				<form:input path="word" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>正面词云</th>
				<th>数量</th>
				<shiro:hasPermission name="csly:touristWcPositive:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristWcPositive">
			<tr>
				<td><a href="${ctx}/csly/touristWcPositive/form?id=${touristWcPositive.id}">
					${touristWcPositive.word}
				</a></td>
				<td>
					${touristWcPositive.num}
				</td>
				<shiro:hasPermission name="csly:touristWcPositive:edit"><td>
    				<a href="${ctx}/csly/touristWcPositive/form?id=${touristWcPositive.id}">修改</a>
					<a href="${ctx}/csly/touristWcPositive/delete?id=${touristWcPositive.id}" onclick="return confirmx('确认要删除该厕所正面词云吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
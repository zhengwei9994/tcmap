<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>游客声量管理</title>
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
		<li class="active"><a href="${ctx}/csly/touristSoundVolume/">游客声量列表</a></li>
		<shiro:hasPermission name="csly:touristSoundVolume:edit"><li><a href="${ctx}/csly/touristSoundVolume/form">游客声量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristSoundVolume" action="${ctx}/csly/touristSoundVolume/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>月份：</label>
				<form:input path="month" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>年：</label>
				<form:input path="year" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>月份</th>
				<th>声量</th>
				<th>年</th>
				<shiro:hasPermission name="csly:touristSoundVolume:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristSoundVolume">
			<tr>
				<td><a href="${ctx}/csly/touristSoundVolume/form?id=${touristSoundVolume.id}">
					${touristSoundVolume.month}
				</a></td>
				<td>
					${touristSoundVolume.num}
				</td>
				<td>
					${touristSoundVolume.year}
				</td>
				<shiro:hasPermission name="csly:touristSoundVolume:edit"><td>
    				<a href="${ctx}/csly/touristSoundVolume/form?id=${touristSoundVolume.id}">修改</a>
					<a href="${ctx}/csly/touristSoundVolume/delete?id=${touristSoundVolume.id}" onclick="return confirmx('确认要删除该游客声量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
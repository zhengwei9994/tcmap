<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>红色预警管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchEarlyWarning/">红色预警列表</a></li>
		<shiro:hasPermission name="wlkj:catchEarlyWarning:edit"><li><a href="${ctx}/wlkj/catchEarlyWarning/form">红色预警添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchEarlyWarning" action="${ctx}/wlkj/catchEarlyWarning/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日期：</label>
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					 value="${catchEarlyWarning.date}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>红色预警次数</th>
				<shiro:hasPermission name="wlkj:catchEarlyWarning:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchEarlyWarning">
			<tr>
				<td><a href="${ctx}/wlkj/catchEarlyWarning/form?id=${catchEarlyWarning.id}">
					${catchEarlyWarning.date}
				</a></td>
				<td>
					${catchEarlyWarning.number}
				</td>
				<shiro:hasPermission name="wlkj:catchEarlyWarning:edit"><td>
    				<a href="${ctx}/wlkj/catchEarlyWarning/form?id=${catchEarlyWarning.id}">修改</a>
					<a href="${ctx}/wlkj/catchEarlyWarning/delete?id=${catchEarlyWarning.id}" onclick="return confirmx('确认要删除该红色预警吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
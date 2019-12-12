<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专利数量统计管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchPatentCount/">专利数量统计列表</a></li>
		<shiro:hasPermission name="zhcs:catchPatentCount:edit"><li><a href="${ctx}/zhcs/catchPatentCount/form">专利数量统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchPatentCount" action="${ctx}/zhcs/catchPatentCount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchPatentCount.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
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
				<th>专利数量</th>
				<shiro:hasPermission name="zhcs:catchPatentCount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchPatentCount">
			<tr>
				<td><a href="${ctx}/zhcs/catchPatentCount/form?id=${catchPatentCount.id}">
					${catchPatentCount.nyear}
				</a></td>
				<td>
					${catchPatentCount.patentNumber}
				</td>
				<shiro:hasPermission name="zhcs:catchPatentCount:edit"><td>
    				<a href="${ctx}/zhcs/catchPatentCount/form?id=${catchPatentCount.id}">修改</a>
					<a href="${ctx}/zhcs/catchPatentCount/delete?id=${catchPatentCount.id}" onclick="return confirmx('确认要删除该专利数量统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>社保在线办理统计</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchDataStatus/">社保在线办理统计列表</a></li>
		<shiro:hasPermission name="fzjc:catchDataStatus:edit"><li><a href="${ctx}/fzjc/catchDataStatus/form">社保在线办理统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchDataStatus" action="${ctx}/fzjc/catchDataStatus/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<form:input path="nyear" htmlEscape="false" maxlength="5" class="input-medium"/>
			</li>
			<li><label>月份：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${catchDataStatus.month}" onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
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
				<th>月份</th>
				<th>在线办理(次数)</th>
				<th>个人线下办理(次数)</th>
				<th>企业线下办理(次数)</th>
				<shiro:hasPermission name="fzjc:catchDataStatus:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchDataStatus">
			<tr>
			    <td>
					${catchDataStatus.nyear}
				</td>
				<td>
					${catchDataStatus.month}
				</td>
				<td>
					${catchDataStatus.dataDirect}
				</td>
				<td>
					${catchDataStatus.dataSearch}
				</td>
				<td>
					${catchDataStatus.dataGov}
				</td>
				<shiro:hasPermission name="fzjc:catchDataStatus:edit"><td>
    				<a href="${ctx}/fzjc/catchDataStatus/form?id=${catchDataStatus.id}">修改</a>
					<a href="${ctx}/fzjc/catchDataStatus/delete?id=${catchDataStatus.id}" onclick="return confirmx('确认要删除该指标数据运行状态吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
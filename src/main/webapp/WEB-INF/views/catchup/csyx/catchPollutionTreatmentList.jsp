<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重拳治污染管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchPollutionTreatment/">重拳治污染列表</a></li>
		<shiro:hasPermission name="csyx:catchPollutionTreatment:edit"><li><a href="${ctx}/csyx/catchPollutionTreatment/form">重拳治污染添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchPollutionTreatment" action="${ctx}/csyx/catchPollutionTreatment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					 value="${catchPollutionTreatment.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
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
				<th>全年优良天数</th>
				<%--<th>关中五市排名</th>--%>
				<th>拆除企业</th>
				<th>黄标车</th>
				<shiro:hasPermission name="csyx:catchPollutionTreatment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchPollutionTreatment">
			<tr>
				<td><a href="${ctx}/csyx/catchPollutionTreatment/form?id=${catchPollutionTreatment.id}">
					${catchPollutionTreatment.nyear}
				</a></td>
				<td>
					${catchPollutionTreatment.todaySum}
				</td>
				<%--<td>--%>
					<%--&lt;%&ndash;${fns:getDictLabel(catchPollutionTreatment.rank, 'rank', '')}&ndash;%&gt;--%>
				<%--</td>--%>
				<td>
					${catchPollutionTreatment.enterprise}
				</td>
				<td>
					${catchPollutionTreatment.markCar}
				</td>
				<shiro:hasPermission name="csyx:catchPollutionTreatment:edit"><td>
    				<a href="${ctx}/csyx/catchPollutionTreatment/form?id=${catchPollutionTreatment.id}">修改</a>
					<a href="${ctx}/csyx/catchPollutionTreatment/delete?id=${catchPollutionTreatment.id}" onclick="return confirmx('确认要删除该重拳治污染吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
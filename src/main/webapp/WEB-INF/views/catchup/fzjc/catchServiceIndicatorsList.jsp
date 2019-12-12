<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区县综合政府服务指标管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchServiceIndicators/">区县综合政府服务指标列表</a></li>
		<shiro:hasPermission name="fzjc:catchServiceIndicators:edit"><li><a href="${ctx}/fzjc/catchServiceIndicators/form">区县综合政府服务指标添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchServiceIndicators" action="${ctx}/fzjc/catchServiceIndicators/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>区县：</label>
				<form:input path="areaName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>区县</th>
				<th>光纤到户渗透率(%)</th>
				<th>宽带家庭普及率(%)</th>
				<th>医院预约诊疗率(%)</th>
				<th>社保自助开通率(%)</th>
				<th>统一入口率(%)</th>
				<th>一站式办理率(%)</th>
				<shiro:hasPermission name="fzjc:catchServiceIndicators:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchServiceIndicators">
			<tr>
				<td><a href="${ctx}/fzjc/catchServiceIndicators/form?id=${catchServiceIndicators.id}">
					${catchServiceIndicators.areaName}
				</a></td>
				<td>
					${catchServiceIndicators.fiber}
				</td>
				<td>
					${catchServiceIndicators.broadband}
				</td>
				<td>
					${catchServiceIndicators.hospital}
				</td>
				<td>
					${catchServiceIndicators.security}
				</td>
				<td>
					${catchServiceIndicators.uniform}
				</td>
				<td>
					${catchServiceIndicators.processing}
				</td>
				<shiro:hasPermission name="fzjc:catchServiceIndicators:edit"><td>
    				<a href="${ctx}/fzjc/catchServiceIndicators/form?id=${catchServiceIndicators.id}">修改</a>
					<a href="${ctx}/fzjc/catchServiceIndicators/delete?id=${catchServiceIndicators.id}" onclick="return confirmx('确认要删除该区县综合政府服务指标吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
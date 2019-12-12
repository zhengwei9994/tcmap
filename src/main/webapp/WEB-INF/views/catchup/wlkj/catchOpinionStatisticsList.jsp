<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>舆情统计管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchOpinionStatistics/">舆情统计列表</a></li>
		<shiro:hasPermission name="wlkj:catchOpinionStatistics:edit"><li><a href="${ctx}/wlkj/catchOpinionStatistics/form">舆情统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchOpinionStatistics" action="${ctx}/wlkj/catchOpinionStatistics/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>统计类型：</label>
			<form:select path="statisticalType" class="input-xlarge required">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('statistical_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>统计类型</th>
				<th>情感指数正</th>
				<th>情感指数中</th>
				<th>情感指数负</th>
				<th>总量网民</th>
				<th>总量媒体</th>
				<shiro:hasPermission name="wlkj:catchOpinionStatistics:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchOpinionStatistics">
			<tr>
				<td><a href="${ctx}/wlkj/catchOpinionStatistics/form?id=${catchOpinionStatistics.id}">
					${fns:getDictLabel(catchOpinionStatistics.statisticalType, 'statistical_type', '')}
				</a></td>
				<td>
					${catchOpinionStatistics.indexPositive}
				</td>
				<td>
					${catchOpinionStatistics.indexThe}
				</td>
				<td>
					${catchOpinionStatistics.indexNegative}
				</td>
				<td>
					${catchOpinionStatistics.totalNetizen}
				</td>
				<td>
					${catchOpinionStatistics.totalMedia}
				</td>
				<shiro:hasPermission name="wlkj:catchOpinionStatistics:edit"><td>
    				<a href="${ctx}/wlkj/catchOpinionStatistics/form?id=${catchOpinionStatistics.id}">修改</a>
					<a href="${ctx}/wlkj/catchOpinionStatistics/delete?id=${catchOpinionStatistics.id}" onclick="return confirmx('确认要删除该舆情统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
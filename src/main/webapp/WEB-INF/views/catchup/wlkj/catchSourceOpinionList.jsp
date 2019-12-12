<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>舆情来源管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchSourceOpinion/">舆情来源列表</a></li>
		<shiro:hasPermission name="wlkj:catchSourceOpinion:edit"><li><a href="${ctx}/wlkj/catchSourceOpinion/form">舆情来源添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSourceOpinion" action="${ctx}/wlkj/catchSourceOpinion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>来源类型：</label>
				<form:select path="sourceType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('source_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>舆情来源类型</th>
				<th>舆情来源流量</th>
				<th>录入日期</th>
				<shiro:hasPermission name="wlkj:catchSourceOpinion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSourceOpinion">
			<tr>
				<td><a href="${ctx}/wlkj/catchSourceOpinion/form?id=${catchSourceOpinion.id}">
					${fns:getDictLabel(catchSourceOpinion.sourceType, 'source_type', '')}
				</a></td>
				<td>
						${catchSourceOpinion.number}<span>KB</span>
				</td>
				<td>
					<fmt:formatDate value="${catchSourceOpinion.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="wlkj:catchSourceOpinion:edit"><td>
    				<a href="${ctx}/wlkj/catchSourceOpinion/form?id=${catchSourceOpinion.id}">修改</a>
					<a href="${ctx}/wlkj/catchSourceOpinion/delete?id=${catchSourceOpinion.id}" onclick="return confirmx('确认要删除该舆情来源吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
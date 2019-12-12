<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全国舆情热点管理</title>
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
		<li class="active"><a href="${ctx}/wlkj/catchSpotOpinion/">全国舆情热点列表</a></li>
		<shiro:hasPermission name="wlkj:catchSpotOpinion:edit"><li><a href="${ctx}/wlkj/catchSpotOpinion/form">全国舆情热点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSpotOpinion" action="${ctx}/wlkj/catchSpotOpinion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>热点类型：</label>
				<form:select path="chinaType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('china_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>全国热点类型</th>
				<th>热点数量</th>
				<th>录入日期</th>
				<shiro:hasPermission name="wlkj:catchSpotOpinion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSpotOpinion">
			<tr>
				<td><a href="${ctx}/wlkj/catchSpotOpinion/form?id=${catchSpotOpinion.id}">
					${fns:getDictLabel(catchSpotOpinion.chinaType, 'china_type', '')}
				</a></td>
				<td>
					${catchSpotOpinion.number}
				</td>
				<td>
					<fmt:formatDate value="${catchSpotOpinion.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="wlkj:catchSpotOpinion:edit"><td>
    				<a href="${ctx}/wlkj/catchSpotOpinion/form?id=${catchSpotOpinion.id}">修改</a>
					<a href="${ctx}/wlkj/catchSpotOpinion/delete?id=${catchSpotOpinion.id}" onclick="return confirmx('确认要删除该全国舆情热点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
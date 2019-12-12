<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交通枢纽统计管理</title>
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
		<li class="active"><a href="${ctx}/ahcs/catchHingeStatistics/">交通枢纽统计列表</a></li>
		<shiro:hasPermission name="ahcs:catchHingeStatistics:edit"><li><a href="${ctx}/ahcs/catchHingeStatistics/form">交通枢纽统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchHingeStatistics" action="${ctx}/ahcs/catchHingeStatistics/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${catchHingeStatistics.nyear}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>枢纽类型：</label>
				<form:select path="hingeType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('hinge_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>年份</th>
				<th>枢纽类型</th>
				<th>枢纽数量</th>
				<shiro:hasPermission name="ahcs:catchHingeStatistics:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchHingeStatistics">
			<tr>
				<td><a href="${ctx}/ahcs/catchHingeStatistics/form?id=${catchHingeStatistics.id}">
					${catchHingeStatistics.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchHingeStatistics.hingeType, 'hinge_type', '')}
				</td>
				<td>
					${catchHingeStatistics.hingeNumber}
				</td>
				<shiro:hasPermission name="ahcs:catchHingeStatistics:edit"><td>
    				<a href="${ctx}/ahcs/catchHingeStatistics/form?id=${catchHingeStatistics.id}">修改</a>
					<a href="${ctx}/ahcs/catchHingeStatistics/delete?id=${catchHingeStatistics.id}" onclick="return confirmx('确认要删除该交通枢纽统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
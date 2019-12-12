<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>景区旅游数据分析管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchSceniArea/">景区旅游数据分析列表</a></li>
		<shiro:hasPermission name="csyx:catchSceniArea:edit"><li><a href="${ctx}/csyx/catchSceniArea/form">景区旅游数据分析添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchSceniArea" action="${ctx}/csyx/catchSceniArea/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="${catchSceniArea.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>景区名称：</label>
				<form:select path="scenicArea" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('scenic_area')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>景区名称</th>
				<th>累计收入</th>
				<th>去年同比</th>
				<th>接待人次</th>
				<shiro:hasPermission name="csyx:catchSceniArea:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchSceniArea">
			<tr>
				<td><a href="${ctx}/csyx/catchSceniArea/form?id=${catchSceniArea.id}">
						${catchSceniArea.nyear}
				</a></td>
				<td>
						${catchSceniArea.scenicArea}
				</td>
				<td>
						${catchSceniArea.totalRevenue}元
				</td>
				<td>
						${catchSceniArea.growth}%
				</td>
				<td>
						${catchSceniArea.reception}
				</td>
				<shiro:hasPermission name="csyx:catchSceniArea:edit"><td>
    				<a href="${ctx}/csyx/catchSceniArea/form?id=${catchSceniArea.id}">修改</a>
					<a href="${ctx}/csyx/catchSceniArea/delete?id=${catchSceniArea.id}" onclick="return confirmx('确认要删除该景区旅游数据分析吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
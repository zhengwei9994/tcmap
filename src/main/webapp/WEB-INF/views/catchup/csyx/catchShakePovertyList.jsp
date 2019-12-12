<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>精准务实抓脱贫管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchShakePoverty/">精准务实抓脱贫列表</a></li>
		<shiro:hasPermission name="csyx:catchShakePoverty:edit"><li><a href="${ctx}/csyx/catchShakePoverty/form">精准务实抓脱贫添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchShakePoverty" action="${ctx}/csyx/catchShakePoverty/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					 value="${catchShakePoverty.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>区县：</label>
				<form:select path="areaName" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>区县</th>
				<th>已脱贫人口</th>
				<th>未脱贫人口</th>
				<th>脱贫原因</th>
				<th>贫困户</th>
				<shiro:hasPermission name="csyx:catchShakePoverty:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchShakePoverty">
			<tr>
				<td><a href="${ctx}/csyx/catchShakePoverty/form?id=${catchShakePoverty.id}">
					${catchShakePoverty.nyear}
				</a></td>
				<td>
					${fns:getDictLabel(catchShakePoverty.areaName, 'area_name', '')}
				</td>
				<td>
						${catchShakePoverty.outPoverty}
				</td>

				<td>
					${catchShakePoverty.totalPeople}
				</td>
				<td>
					${fns:getDictLabel(catchShakePoverty.reasonsAlleviation, 'reasons_alleviation', '')}
				</td>
				<td>
					${catchShakePoverty.poorHouseholds}
				</td>
				<shiro:hasPermission name="csyx:catchShakePoverty:edit"><td>
    				<a href="${ctx}/csyx/catchShakePoverty/form?id=${catchShakePoverty.id}">修改</a>
					<a href="${ctx}/csyx/catchShakePoverty/delete?id=${catchShakePoverty.id}" onclick="return confirmx('确认要删除该精准务实抓脱贫吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
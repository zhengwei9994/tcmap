<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务业稳中向好管理</title>
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
		<li class="active"><a href="${ctx}/csyx/catchServiceIndustry/">服务业稳中向好列表</a></li>
		<shiro:hasPermission name="csyx:catchServiceIndustry:edit"><li><a href="${ctx}/csyx/catchServiceIndustry/form">服务业稳中向好添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchServiceIndustry" action="${ctx}/csyx/catchServiceIndustry/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					 value="${catchServiceIndustry.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
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
				<th>增速</th>
				<th>百分点</th>
				<shiro:hasPermission name="csyx:catchServiceIndustry:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchServiceIndustry">
			<tr>
				<td><a href="${ctx}/csyx/catchServiceIndustry/form?id=${catchServiceIndustry.id}">
					${catchServiceIndustry.nyear}
				</a></td>
				<td>
					${catchServiceIndustry.growth}
				</td>
				<td>
					${catchServiceIndustry.point}
				</td>
				<shiro:hasPermission name="csyx:catchServiceIndustry:edit"><td>
    				<a href="${ctx}/csyx/catchServiceIndustry/form?id=${catchServiceIndustry.id}">修改</a>
					<a href="${ctx}/csyx/catchServiceIndustry/delete?id=${catchServiceIndustry.id}" onclick="return confirmx('确认要删除该服务业稳重向好吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
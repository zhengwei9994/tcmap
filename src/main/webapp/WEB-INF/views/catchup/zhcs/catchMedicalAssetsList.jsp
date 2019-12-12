<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医疗资源统计管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchMedicalAssets/">医疗资源统计列表</a></li>
		<shiro:hasPermission name="zhcs:catchMedicalAssets:edit"><li><a href="${ctx}/zhcs/catchMedicalAssets/form">医疗资源统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchMedicalAssets" action="${ctx}/zhcs/catchMedicalAssets/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchMedicalAssets.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>月份：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchMedicalAssets.month}" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>医院等级：</label>
				<form:select path="hospitalGrade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>年月</th>
				<th>医院等级</th>
				<th>医院数量</th>
				<th>医生人数</th>
				<th>护士人数</th>
				<shiro:hasPermission name="zhcs:catchMedicalAssets:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchMedicalAssets">
			<tr>
				<td><a href="${ctx}/zhcs/catchMedicalAssets/form?id=${catchMedicalAssets.id}">
					${catchMedicalAssets.nyear}-${catchMedicalAssets.month}
				</a></td>
				<td>
					${fns:getDictLabel(catchMedicalAssets.hospitalGrade, 'hospital_grade', '')}
				</td>
				<td>
					${catchMedicalAssets.hospitalNumber}
				</td>
				<td>
					${catchMedicalAssets.doctorsNumber}
				</td>
				<td>
					${catchMedicalAssets.nurseNumber}
				</td>
				<shiro:hasPermission name="zhcs:catchMedicalAssets:edit"><td>
    				<a href="${ctx}/zhcs/catchMedicalAssets/form?id=${catchMedicalAssets.id}">修改</a>
					<a href="${ctx}/zhcs/catchMedicalAssets/delete?id=${catchMedicalAssets.id}" onclick="return confirmx('确认要删除该医疗资源统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地区景点排名管理</title>
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
		<li class="active"><a href="${ctx}/zhcs/catchAreaRanking/">地区景点排名列表</a></li>
		<shiro:hasPermission name="zhcs:catchAreaRanking:edit"><li><a href="${ctx}/zhcs/catchAreaRanking/form">地区景点排名添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchAreaRanking" action="${ctx}/zhcs/catchAreaRanking/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchTravelInfo.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>类别：</label>
				<form:select path="areaType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('area_ranking_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>类型</th>
				<th>名称</th>
				<th>数量</th>
				<th>排序</th>
				<shiro:hasPermission name="zhcs:catchAreaRanking:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchAreaRanking">
			<tr>
				<td>
					<a href="${ctx}/zhcs/catchAreaRanking/form?id=${catchTravelInfo.id}">
						${catchAreaRanking.nyear}
				</a></td>
				<td>
						${catchAreaRanking.areaType}
				</td>
				<td>
						${catchAreaRanking.areaName}
				</td>
				<td>
						${catchAreaRanking.areaCount}
				</td>
				<td>
						${catchAreaRanking.areaSort}
				</td>
				<shiro:hasPermission name="zhcs:catchAreaRanking:edit"><td>
    				<a href="${ctx}/zhcs/catchAreaRanking/form?id=${catchAreaRanking.id}">修改</a>
					<a href="${ctx}/zhcs/catchAreaRanking/delete?id=${catchAreaRanking.id}" onclick="return confirmx('确认要删除该地区景点排名吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
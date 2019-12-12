<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点道路上报事件管理</title>
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
		function frmReset(){
			$(':input','#searchForm')
					.not(':button, :submit, :reset, :hidden')
					.val('')
					.removeAttr('checked')
					.removeAttr('selected');
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csyx/trafficStreetUp/">重点道路上报事件列表</a></li>
		<shiro:hasPermission name="csyx:trafficStreetUp:edit"><li><a href="${ctx}/csyx/trafficStreetUp/form">重点道路上报事件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="trafficStreetUp" action="${ctx}/csyx/trafficStreetUp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>时间：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${trafficStreetUp.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${trafficStreetUp.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="reSubmit" class="btn btn-primary" type="button" onclick="frmReset()" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>事件类型</th>
				<th>事件数量</th>
				<th>事件地点</th>
				<shiro:hasPermission name="csyx:trafficStreetUp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trafficStreetUp">
			<tr>
				<td><a href="${ctx}/csyx/trafficStreetUp/form?id=${trafficStreetUp.id}">
					<fmt:formatDate value="${trafficStreetUp.date}" pattern="yyyy-MM-dd"/>
				</a></td>
				<td>
					${trafficStreetUp.type}
				</td>
				<td>
					${trafficStreetUp.num}
				</td>
				<td>
					${trafficStreetUp.street}
				</td>
				<shiro:hasPermission name="csyx:trafficStreetUp:edit"><td>
    				<a href="${ctx}/csyx/trafficStreetUp/form?id=${trafficStreetUp.id}">修改</a>
					<a href="${ctx}/csyx/trafficStreetUp/delete?id=${trafficStreetUp.id}" onclick="return confirmx('确认要删除该重点道路上报事件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
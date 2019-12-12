<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业发展潜力内容管理</title>
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
		<li class="active"><a href="${ctx}/csyx/developmentAbsorb/">产业发展潜力内容列表</a></li>
		<shiro:hasPermission name="csyx:developmentAbsorb:edit"><li><a href="${ctx}/csyx/developmentAbsorb/form">产业发展潜力内容添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="developmentAbsorb" action="${ctx}/csyx/developmentAbsorb/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目：</label>
				<form:input path="item" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>数值：</label>
				<form:input path="num" htmlEscape="false" maxlength="9" class="input-medium"/>
			</li>
			<li><label>增长：</label>
				<form:input path="increase" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>年：</label>
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${developmentAbsorb.year}"  class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>月：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${developmentAbsorb.month}"  class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"/>
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
				<th>项目</th>
				<th>数值</th>
				<th>增长</th>
				<th>年月</th>
				<shiro:hasPermission name="csyx:developmentAbsorb:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="developmentAbsorb">
			<tr>
				<td><a href="${ctx}/csyx/developmentAbsorb/form?id=${developmentAbsorb.id}">
					${developmentAbsorb.item}
				</a></td>
				<td>
					${developmentAbsorb.num}
				</td>
				<td>
					${developmentAbsorb.increase}
				</td>
				<td>
					${developmentAbsorb.year}-${developmentAbsorb.month}
				</td>
				<shiro:hasPermission name="csyx:developmentAbsorb:edit"><td>
    				<a href="${ctx}/csyx/developmentAbsorb/form?id=${developmentAbsorb.id}">修改</a>
					<a href="${ctx}/csyx/developmentAbsorb/delete?id=${developmentAbsorb.id}" onclick="return confirmx('确认要删除该产业发展潜力内容吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
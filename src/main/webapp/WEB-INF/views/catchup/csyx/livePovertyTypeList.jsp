<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						<%--$("#searchForm").attr("action","${ctx}/safecity/safeTraffic/export?name=交通违法案件");--%>
						$("#exportForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});

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
<div  style="display:none;">
	<form:form id="exportForm" modelAttribute="livePovertyType" action="${ctx}/csyx/livePovertyType/export?name=贫困类型列表" method="post" class="breadcrumb form-search"/>
</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csyx/livePovertyType/">贫困类型列表</a></li>
		<shiro:hasPermission name="csyx:livePovertyType:edit"><li><a href="${ctx}/csyx/livePovertyType/form">贫困类型添加</a></li></shiro:hasPermission>
	</ul>
	<div >
		<form:form id="searchForm" modelAttribute="livePovertyType" action="${ctx}/csyx/livePovertyType/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li><label>贫困类型：</label>
					<form:select path="type" class="input-large">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('poverty_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li><label>地区：</label>
					<form:select path="area" class="input-large">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select></li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
					<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				</li>
				<li class="clearfix"></li>
			</ul>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地区</th><th>贫困类型</th><th>数量</th>
				<shiro:hasPermission name="csyx:livePovertyType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="livePovertyType">
			<tr>
				<td>${livePovertyType.area}</td><td>${livePovertyType.type}</td><td>${livePovertyType.num}</td>
				<shiro:hasPermission name="csyx:livePovertyType:edit"><td>
    				<a href="${ctx}/csyx/livePovertyType/form?id=${livePovertyType.id}">修改</a>
					<a href="${ctx}/csyx/livePovertyType/delete?id=${livePovertyType.id}" onclick="return confirmx('确认要删除该贫困类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
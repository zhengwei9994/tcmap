<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>帮扶受众管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
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
	<form:form id="exportForm" modelAttribute="liveHelpAudi" action="${ctx}/csyx/liveHelpAudi/export?name=帮扶受众" method="post" class="breadcrumb form-search"/>
</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csyx/liveHelpAudi/">帮扶受众列表</a></li>
		<shiro:hasPermission name="csyx:liveHelpAudi:edit"><li><a href="${ctx}/csyx/liveHelpAudi/form">帮扶受众添加</a></li></shiro:hasPermission>
	</ul>
	<div>
		<form:form id="searchForm" modelAttribute="liveHelpAudi" action="${ctx}/csyx/liveHelpAudi/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li><label>产业：</label>
					<input name="helpType" type="text"  maxlength="20" class="input-large "
						   value="${liveHelpAudi.helpType}"/>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
					<input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
				<li class="clearfix"></li>
			</ul>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>产业</th><th>户数</th><th>人口</th>
				<shiro:hasPermission name="csyx:liveHelpAudi:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveHelpAudi">
			<tr><td>${liveHelpAudi.helpType}</td><td>${liveHelpAudi.households}</td><td>${liveHelpAudi.population}</td>
				<shiro:hasPermission name="csyx:liveHelpAudi:edit"><td>
    				<a href="${ctx}/csyx/liveHelpAudi/form?id=${liveHelpAudi.id}">修改</a>
					<a href="${ctx}/csyx/liveHelpAudi/delete?id=${liveHelpAudi.id}" onclick="return confirmx('确认要删除该帮扶受众吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
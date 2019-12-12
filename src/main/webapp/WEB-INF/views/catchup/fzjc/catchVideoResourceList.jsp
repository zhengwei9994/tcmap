<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公共安全视频资源覆盖情况管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchVideoResource/">公共安全视频资源覆盖情况列表</a></li>
		<shiro:hasPermission name="fzjc:catchVideoResource:edit"><li><a href="${ctx}/fzjc/catchVideoResource/form">公共安全视频资源覆盖情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchVideoResource" action="${ctx}/fzjc/catchVideoResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchVideoResource.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
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
				<th>重点区域覆盖率</th>
				<th>重点领域覆盖率</th>
				<th>高清摄像机比率</th>
				<th>摄像机完好比率</th>
				<th>重点领域机器完好率</th>
				<shiro:hasPermission name="fzjc:catchVideoResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchVideoResource">
			<tr>
				<td><a href="${ctx}/fzjc/catchVideoResource/form?id=${catchVideoResource.id}">
					${catchVideoResource.nyear}
				</a></td>
				<td>
					${catchVideoResource.keyRegionalCoverage}
				</td>
				<td>
					${catchVideoResource.coverageKeyAreas}
				</td>
				<td>
					${catchVideoResource.highDefinitionCamera}
				</td>
				<td>
					${catchVideoResource.cameraIntegrityRatio}
				</td>
				<td>
					${catchVideoResource.machineCompletionKey}
				</td>
				<shiro:hasPermission name="fzjc:catchVideoResource:edit"><td>
    				<a href="${ctx}/fzjc/catchVideoResource/form?id=${catchVideoResource.id}">修改</a>
					<a href="${ctx}/fzjc/catchVideoResource/delete?id=${catchVideoResource.id}" onclick="return confirmx('确认要删除该公共安全视频资源覆盖情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
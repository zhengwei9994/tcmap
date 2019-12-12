<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消防安全管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/safecity/safeFire/export?name=消防安全");--%>
						$("#exportForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/safecity/safeFire/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/safecity/safeFire/import/template?name=消防安全">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/safecity/safeFire/">消防安全列表</a></li>
		<shiro:hasPermission name="safecity:safeFire:edit"><li><a href="${ctx}/safecity/safeFire/form">消防安全添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="safeFire" action="${ctx}/safecity/safeFire/export?name=消防安全" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="safeFire" action="${ctx}/safecity/safeFire/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>平安城市：</label><form:select path="safeCity" class="required input-xlarge">
				<form:option value="" label=""/>
				<form:options items="${safeCityList}" itemLabel="area" itemValue="area" htmlEscape="false"/>
			</form:select>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
<%--			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>--%>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>平安城市</th>
				<th>火灾案件数量</th>
				<th>统计年月</th>
				<shiro:hasPermission name="safecity:safeFire:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="safeFire">
			<tr>
				<td><a href="${ctx}/safecity/safeFire/form?id=${safeFire.id}">
					${safeFire.safeCity}
				</a></td>
				<td>
					${safeFire.fireNum}
				</td>
				<td>
					<fmt:formatDate value="${safeFire.countDate}" pattern="yyyy-MM"/>
				</td>
				<shiro:hasPermission name="safecity:safeFire:edit"><td>
    				<a href="${ctx}/safecity/safeFire/form?id=${safeFire.id}">修改</a>
					<a href="${ctx}/safecity/safeFire/delete?id=${safeFire.id}" onclick="return confirmx('确认要删除该消防安全吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
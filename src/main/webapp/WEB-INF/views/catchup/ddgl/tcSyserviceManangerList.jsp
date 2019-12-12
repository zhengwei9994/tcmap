<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/ddgl/tcSyserviceMananger/export?name=系统");--%>
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
		<form id="importForm" action="${ctx}/ddgl/tcSyserviceMananger/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ddgl/tcSyserviceMananger/import/template?name=系统">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ddgl/tcSyserviceMananger/">系统列表</a></li>
		<shiro:hasPermission name="ddgl:tcSyserviceMananger:edit"><li><a href="${ctx}/ddgl/tcSyserviceMananger/form">系统添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcSyserviceMananger" action="${ctx}/ddgl/tcSyserviceMananger/export?name=系统" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcSyserviceMananger" action="${ctx}/ddgl/tcSyserviceMananger/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>系统名：</label>
				<form:input path="sysName" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>系统名</th>
				<th>系统域名</th>
				<th>系统标识</th>
				<th>关键词</th>
				<th>系统简介</th>
				<shiro:hasPermission name="ddgl:tcSyserviceMananger:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcSyserviceMananger">
			<tr>
				<td><a href="${ctx}/ddgl/tcSyserviceMananger/form?id=${tcSyserviceMananger.id}">
					${tcSyserviceMananger.sysName}
				</a></td>
				<td>
					${tcSyserviceMananger.sysDomain}
				</td>
				<td>
					${tcSyserviceMananger.sysCode}
				</td>
				<td>
					${tcSyserviceMananger.keyWords}
				</td>
				<td>
					${tcSyserviceMananger.sysIntro}
				</td>
				<shiro:hasPermission name="ddgl:tcSyserviceMananger:edit"><td>
    				<a href="${ctx}/ddgl/tcSyserviceMananger/form?id=${tcSyserviceMananger.id}">修改</a>
					<a href="${ctx}/ddgl/tcSyserviceMananger/delete?id=${tcSyserviceMananger.id}" onclick="return confirmx('确认要删除该系统吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
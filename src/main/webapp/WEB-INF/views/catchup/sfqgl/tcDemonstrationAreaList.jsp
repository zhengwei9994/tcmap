<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>示范区管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/csyx/tcDemonstrationArea/export?name=示范区管理");--%>
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
		<form id="importForm" action="${ctx}/csyx/tcDemonstrationArea/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/csyx/tcDemonstrationArea/import/template?name=示范区管理">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csyx/tcDemonstrationArea/">示范区管理列表</a></li>
		<shiro:hasPermission name="csyx:tcDemonstrationArea:edit"><li><a href="${ctx}/csyx/tcDemonstrationArea/form">示范区管理添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcDemonstrationArea" action="${ctx}/csyx/tcDemonstrationArea/export?name=示范区管理" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcDemonstrationArea" action="${ctx}/csyx/tcDemonstrationArea/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>坐标来源：</label>
				<form:select path="source" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('coordinate_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>地址</th>
				<th>描述</th>
				<th>经度</th>
				<th>纬度</th>
				<th>坐标来源</th>
				<shiro:hasPermission name="csyx:tcDemonstrationArea:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcDemonstrationArea">
			<tr>
				<td><a href="${ctx}/csyx/tcDemonstrationArea/form?id=${tcDemonstrationArea.id}">
					${tcDemonstrationArea.name}
				</a></td>
				<td>
					${tcDemonstrationArea.address}
				</td>
				<td>
					${tcDemonstrationArea.details}
				</td>
				<td>
					${tcDemonstrationArea.longitude}
				</td>
				<td>
					${tcDemonstrationArea.dimension}
				</td>
				<td>
					${fns:getDictLabel(tcDemonstrationArea.source, 'coordinate_source', '')}
				</td>
				<shiro:hasPermission name="csyx:tcDemonstrationArea:edit"><td>
    				<a href="${ctx}/csyx/tcDemonstrationArea/form?id=${tcDemonstrationArea.id}">修改</a>
					<a href="${ctx}/csyx/tcDemonstrationArea/delete?id=${tcDemonstrationArea.id}" onclick="return confirmx('确认要删除该示范区管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
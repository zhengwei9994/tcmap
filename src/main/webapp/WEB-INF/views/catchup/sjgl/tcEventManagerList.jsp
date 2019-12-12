<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>事件数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/sjgl/tcEventManager/export?name=事件数据");--%>
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
		<form id="importForm" action="${ctx}/sjgl/tcEventManager/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sjgl/tcEventManager/import/template?name=事件数据">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sjgl/tcEventManager/">事件类型统计表</a></li>
<%--		<shiro:hasPermission name="sjgl:tcEventManager:edit"><li><a href="${ctx}/sjgl/tcEventManager/form">事件数据添加</a></li></shiro:hasPermission>--%>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcEventManager" action="${ctx}/sjgl/tcEventManager/export?name=事件数据" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcEventManager" action="${ctx}/sjgl/tcEventManager/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns">
			<li><label>事件类型：</label>
				<form:select path="eventType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('event_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>事件类型</th>
				<th>事件数量</th>
				<th>事件占比</th>
				<th>相关责任人</th>
				<shiro:hasPermission name="sjgl:tcEventManager:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcEventManager">
			<tr>
				<td><a href="${ctx}/sjgl/tcEventDetail/eventManager?type=${tcEventManager.eventType}">
					${tcEventManager.eventType}
				</a></td>
				<td>
					${tcEventManager.eventNumber}
				</td>
				<td>
					${tcEventManager.eventRate}
				</td>
				<td>
					${tcEventManager.eventPersonal}
				</td>
				<shiro:hasPermission name="sjgl:tcEventManager:edit"><td>
    				<a href="${ctx}/sjgl/tcEventManager/form?id=${tcEventManager.id}">修改</a>
					<a href="${ctx}/sjgl/tcEventManager/delete?id=${tcEventManager.id}" onclick="return confirmx('确认要删除该事件数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
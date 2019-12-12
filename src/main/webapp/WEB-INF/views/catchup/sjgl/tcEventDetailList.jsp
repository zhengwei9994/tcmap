<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>事件详情管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/sjgl/tcEventDetail/export?name=事件详情");--%>
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
		<form id="importForm" action="${ctx}/sjgl/tcEventDetail/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sjgl/tcEventDetail/import/template?name=事件详情">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sjgl/tcEventDetail/">事件详情列表</a></li>
		<shiro:hasPermission name="sjgl:tcEventDetail:edit"><li><a href="${ctx}/sjgl/tcEventDetail/form">事件详情添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcEventDetail" action="${ctx}/sjgl/tcEventDetail/export?name=事件详情" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcEventDetail" action="${ctx}/sjgl/tcEventDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>事件类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('event_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>相关责任人：</label>
				<form:input path="eventPerson" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>事件类型</th>
				<th>事件来源</th>
<%--				<th>事件占比</th>--%>
				<th>事件描述</th>
				<th>完成情况</th>
				<th>相关责任人</th>
				<th>创建人</th>
				<shiro:hasPermission name="sjgl:tcEventDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcEventDetail">
			<tr>
				<td><a href="${ctx}/sjgl/tcEventDetail/form?id=${tcEventDetail.id}">
					${fns:getDictLabel(tcEventDetail.type, 'event_type', '')}
				</a></td>
				<td>
					${tcEventDetail.source}
				</td>
<%--				<td>--%>
<%--					${tcEventDetail.rate}--%>
<%--				</td>--%>
				<td>
					${tcEventDetail.eventContent}
				</td>
				<td>
					${tcEventDetail.completion}
				</td>
				<td>
					${tcEventDetail.eventPerson}
				</td>
				<td>
					${tcEventDetail.createUser}
				</td>
				<shiro:hasPermission name="sjgl:tcEventDetail:edit"><td>
    				<a href="${ctx}/sjgl/tcEventDetail/form?id=${tcEventDetail.id}">修改</a>
					<a href="${ctx}/sjgl/tcEventDetail/delete?id=${tcEventDetail.id}" onclick="return confirmx('确认要删除该事件详情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
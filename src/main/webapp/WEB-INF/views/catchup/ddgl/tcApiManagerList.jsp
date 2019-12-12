<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>接口管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/ddgl/tcApiManager/export?name=接口");--%>
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
		<form id="importForm" action="${ctx}/ddgl/tcApiManager/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ddgl/tcApiManager/import/template?name=接口">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ddgl/tcApiManager/">接口列表</a></li>
		<shiro:hasPermission name="ddgl:tcApiManager:edit"><li><a href="${ctx}/ddgl/tcApiManager/form">接口添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcApiManager" action="${ctx}/ddgl/tcApiManager/export?name=接口" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcApiManager" action="${ctx}/ddgl/tcApiManager/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>接口名称：</label>
				<form:input path="apiName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>接口状态：</label>
				<form:radiobuttons path="apiStatus"  items="${fns:getDictList('api_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<input id="refresh" class="btn btn-primary" type="reset" value="重置"/>
<%--			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>--%>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>接口名称</th>
				<th>接口地址</th>
				<th>接口类型</th>
				<th>请求类型</th>
				<th>接口描述</th>
				<th>请求参数</th>
				<th>返回参数</th>
				<th>接口状态</th>
				<th>发布时间</th>
				<th>系统域名</th>
				<shiro:hasPermission name="ddgl:tcApiManager:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcApiManager">
			<tr>
				<td><a href="${ctx}/ddgl/tcApiManager/form?id=${tcApiManager.id}">
					${tcApiManager.apiName}
				</a></td>
				<td>
					${tcApiManager.apiUrl}
				</td>
				<td>
					${fns:getDictLabel(tcApiManager.apiType, 'api_type', '')}
				</td>
				<td>
					${fns:getDictLabel(tcApiManager.requestType, 'request_type', '')}
				</td>
				<td>
					${tcApiManager.apiDes}
				</td>
				<td>
					${tcApiManager.requestParam}
				</td>
				<td>
					${tcApiManager.returnParam}
				</td>
				<td>
					${fns:getDictLabel(tcApiManager.apiStatus, 'api_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${tcApiManager.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tcApiManager.sysDomain}
				</td>
				<shiro:hasPermission name="ddgl:tcApiManager:edit"><td>
    				<a href="${ctx}/ddgl/tcApiManager/form?id=${tcApiManager.id}">修改</a>
					<a href="${ctx}/ddgl/tcApiManager/delete?id=${tcApiManager.id}" onclick="return confirmx('确认要删除该接口吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
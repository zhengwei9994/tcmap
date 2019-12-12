<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/csyx/catchAssessmentManagement/export?name=考核管理");--%>
						$("#inputForm").submit();
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
		<form id="importForm" action="${ctx}/csyx/catchAssessmentManagement/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/csyx/catchAssessmentManagement/import/template?name=考核管理">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csyx/catchAssessmentManagement/">考核管理列表</a></li>
		<shiro:hasPermission name="csyx:catchAssessmentManagement:edit"><li><a href="${ctx}/csyx/catchAssessmentManagement/form">考核管理添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="inputForm" modelAttribute="catchAssessmentManagement" action="${ctx}/csyx/catchAssessmentManagement/export?name=考核管理" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="catchAssessmentManagement" action="${ctx}/csyx/catchAssessmentManagement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>考核部门：</label>
				<form:select path="department" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('municipal_department')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>下发部门：</label>
				<form:select path="sector" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('municipal_department')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>任务名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>考核部门</th>
				<th>下发部门</th>
				<th>任务名称</th>
				<th>任务内容</th>
				<th>打分周期</th>
				<th>分值</th>
				<shiro:hasPermission name="csyx:catchAssessmentManagement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchAssessmentManagement">
			<tr>
				<td><a href="${ctx}/csyx/catchAssessmentManagement/form?id=${catchAssessmentManagement.id}">
					${fns:getDictLabel(catchAssessmentManagement.department, 'municipal_department', '')}
				</a></td>
				<td>
					${fns:getDictLabel(catchAssessmentManagement.sector, 'municipal_department', '')}
				</td>
				<td>
					${catchAssessmentManagement.name}
				</td>
				<td>
					${catchAssessmentManagement.mission}
				</td>
				<td>
					<fmt:formatDate value="${catchAssessmentManagement.startDate}" pattern="yyyy-MM" /> ~ <fmt:formatDate value="${catchAssessmentManagement.endDate}" pattern="yyyy-MM" />
				</td>
				<td>
					${catchAssessmentManagement.score}
				</td>
				<shiro:hasPermission name="csyx:catchAssessmentManagement:edit"><td>
    				<a href="${ctx}/csyx/catchAssessmentManagement/form?id=${catchAssessmentManagement.id}">修改</a>
					<a href="${ctx}/csyx/catchAssessmentManagement/delete?id=${catchAssessmentManagement.id}" onclick="return confirmx('确认要删除该考核管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
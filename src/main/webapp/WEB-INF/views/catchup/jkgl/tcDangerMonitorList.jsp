<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>危险源监控管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/jkgl/tcDangerMonitor/export?name=危险源监控");--%>
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
		<form id="importForm" action="${ctx}/jkgl/tcDangerMonitor/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/jkgl/tcDangerMonitor/import/template?name=危险源监控">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/jkgl/tcDangerMonitor/">危险源监控列表</a></li>
		<shiro:hasPermission name="jkgl:tcDangerMonitor:edit"><li><a href="${ctx}/jkgl/tcDangerMonitor/form">危险源监控添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcDangerMonitor" action="${ctx}/jkgl/tcDangerMonitor/export?name=危险源监控" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcDangerMonitor" action="${ctx}/jkgl/tcDangerMonitor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>是否超标：</label>
				<form:select path="isOver" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>危险等级：</label>
				<form:select path="dangerGrade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('hazard_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>企业名称</th>
				<th>组织机构代码</th>
				<th>代表人</th>
				<th>生产地址</th>
				<th>联系方式</th>
				<th>所属行业</th>
				<th>污染物种类</th>
				<th>污染物名称</th>
				<th>排放量</th>
				<th>是否超标</th>
				<th>危险等级</th>
				<shiro:hasPermission name="jkgl:tcDangerMonitor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcDangerMonitor">
			<tr>
				<td><a href="${ctx}/jkgl/tcDangerMonitor/form?id=${tcDangerMonitor.id}">
					${tcDangerMonitor.name}
				</a></td>
				<td>
					${tcDangerMonitor.code}
				</td>
				<td>
					${tcDangerMonitor.representative}
				</td>
				<td>
					${tcDangerMonitor.adress}
				</td>
				<td>
					${tcDangerMonitor.contactInformation}
				</td>
				<td>
					${tcDangerMonitor.industry}
				</td>
				<td>
					${tcDangerMonitor.pollutantsType}
				</td>
				<td>
					${tcDangerMonitor.pollutantsName}
				</td>
				<td>
					${tcDangerMonitor.emissions}
				</td>
				<td>
					${fns:getDictLabel(tcDangerMonitor.isOver, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(tcDangerMonitor.dangerGrade, 'hazard_grade', '')}
				</td>
				<shiro:hasPermission name="jkgl:tcDangerMonitor:edit"><td>
    				<a href="${ctx}/jkgl/tcDangerMonitor/form?id=${tcDangerMonitor.id}">修改</a>
					<a href="${ctx}/jkgl/tcDangerMonitor/delete?id=${tcDangerMonitor.id}" onclick="return confirmx('确认要删除该危险源监控吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
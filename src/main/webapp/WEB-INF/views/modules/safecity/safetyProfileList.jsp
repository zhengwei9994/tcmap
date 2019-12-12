<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安全概况管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/safecity/safetyProfile/export?name=安全概况");--%>
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
		<form id="importForm" action="${ctx}/safecity/safetyProfile/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/safecity/safetyProfile/import/template?name=安全概况">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/safecity/safetyProfile/">安全概况列表</a></li>
		<shiro:hasPermission name="safecity:safetyProfile:edit"><li><a href="${ctx}/safecity/safetyProfile/form">安全概况添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="safetyProfile" action="${ctx}/safecity/safetyProfile/export?name=安全概况" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="safetyProfile" action="${ctx}/safecity/safetyProfile/" method="post" class="breadcrumb form-search">
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
				<th>公共安全指数</th>
				<th>药品合格率</th>
				<th>食品合格率</th>
				<th>刑事案件破案率</th>
				<th>刑事案件案发率</th>
				<th>民事案件发案率</th>
				<th>交通违法案件数量</th>
				<th>交通违法同比</th>
				<th>交通违法环比</th>
				<th>火灾案件数量</th>
				<th>消防车数量</th>
				<th>消防队伍</th>
				<th>统计日期</th>
				<shiro:hasPermission name="safecity:safetyProfile:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="safetyProfile">
			<tr>
				<td><a href="${ctx}/safecity/safetyProfile/form?id=${safetyProfile.id}">
						${safetyProfile.safeCity}
				</a></td>
				<td>
					${safetyProfile.safetyIndex}
				</td>
				<td>
					${safetyProfile.drugRate}
				</td>
				<td>
					${safetyProfile.foodRate}
				</td>
				<td>
					${safetyProfile.crimelSolveRate}
				</td>
				<td>
					${safetyProfile.crimeRate}
				</td>
				<td>
					${safetyProfile.civilRate}
				</td>
				<td>
					${safetyProfile.trafficNum}
				</td>
				<td>
					${safetyProfile.yearRate}
				</td>
				<td>
					${safetyProfile.chainRate}
				</td>
				<td>
					${safetyProfile.fireNum}
				</td>
				<td>
					${safetyProfile.fireTruckNum}
				</td>
				<td>
					${safetyProfile.fireBrigade}
				</td>
				<td>
					<fmt:formatDate value="${safetyProfile.statisticDate}" type="both"/>
				</td>
				<shiro:hasPermission name="safecity:safetyProfile:edit"><td>
    				<a href="${ctx}/safecity/safetyProfile/form?id=${safetyProfile.id}">修改</a>
					<a href="${ctx}/safecity/safetyProfile/delete?id=${safetyProfile.id}" onclick="return confirmx('确认要删除该安全概况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
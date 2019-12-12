<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域感知数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/csgz/tcCityFeel/export?name=区域感知数据");--%>
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
		<form id="importForm" action="${ctx}/csgz/tcCityFeel/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/csgz/tcCityFeel/import/template?name=区域感知数据">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csgz/tcCityFeel/">区域感知数据列表</a></li>
		<shiro:hasPermission name="csgz:tcCityFeel:edit"><li><a href="${ctx}/csgz/tcCityFeel/form">区域感知数据添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcCityFeel" action="${ctx}/csgz/tcCityFeel/export?name=区域感知数据" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcCityFeel" action="${ctx}/csgz/tcCityFeel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>区域：</label>
			<sys:treeselect id="focusArea" name="focusArea" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
							title="区域" url="/sys/area/treeData" cssClass="required"/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
<%--			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>--%>
<%--			</li>--%>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>区域</th>
				<th>重点出入口交通</th>
				<th>治安卡口</th>
				<th>区域覆盖</th>
				<th>高清摄像机占比</th>
				<th>摄像机完好率</th>
				<shiro:hasPermission name="csgz:tcCityFeel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcCityFeel">
			<tr>
				<td><a href="${ctx}/csgz/tcCityFeel/form?id=${tcCityFeel.id}">
					${tcCityFeel.focusArea}
				</a></td>
				<td>
					${tcCityFeel.keyEntranceTraffic}
				</td>
				<td>
					${tcCityFeel.securityCheckpoint}
				</td>
				<td>
					${tcCityFeel.focusAreaCover}
				</td>
				<td>
					${tcCityFeel.proportionHdCameras}
				</td>
				<td>
					${tcCityFeel.cameraIntactRate}
				</td>
				<shiro:hasPermission name="csgz:tcCityFeel:edit"><td>
    				<a href="${ctx}/csgz/tcCityFeel/form?id=${tcCityFeel.id}">修改</a>
					<a href="${ctx}/csgz/tcCityFeel/delete?id=${tcCityFeel.id}" onclick="return confirmx('确认要删除该区域感知数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
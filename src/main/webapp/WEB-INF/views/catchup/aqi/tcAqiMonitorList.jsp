<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>气象数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
					<%--$("#searchForm").attr("action","${ctx}/aqi/tcAqiMonitor/export?name=气象数据");--%>
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
		<form id="importForm" action="${ctx}/aqi/tcAqiMonitor/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/aqi/tcAqiMonitor/import/template?name=气象数据">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/aqi/tcAqiMonitor/">气象数据列表</a></li>
<%--		<shiro:hasPermission name="aqi:tcAqiMonitor:edit"><li><a href="${ctx}/aqi/tcAqiMonitor/form">气象数据添加</a></li></shiro:hasPermission>--%>
	</ul>
	<div  style="display:none;">
		<form:form id="exportForm" modelAttribute="tcAqiMonitor" action="${ctx}/aqi/tcAqiMonitor/export?name=气象数据" method="post" class="breadcrumb form-search"/>
	</div>
	<form:form id="searchForm" modelAttribute="tcAqiMonitor" action="${ctx}/aqi/tcAqiMonitor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>数据更新日期：</label>
<%--				<form:input path="ymd" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
				<input name="ymd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${tcAqiMonitor.ymd}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowToday:false,isShowClear:false});"/>
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
<%--				<th>id</th>--%>
				<th>城市</th>
				<th>空气质量指数</th>
				<th>风力</th>
				<th>风向</th>
				<th>最高温</th>
				<th>最低温</th>
				<th>温馨提示</th>
				<th>日出</th>
				<th>日落</th>
				<th>星期</th>
				<th>天气</th>
				<th>PM25</th>
				<th>PM10</th>
	            <th>入库时间</th>
				<th>数据更新时间</th>
				<shiro:hasPermission name="aqi:tcAqiMonitor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tcAqiMonitor">
			<tr>
<%--				<td><a href="${ctx}/aqi/tcAqiMonitor/form?id=${tcAqiMonitor.id}">--%>
<%--					${tcAqiMonitor.id}--%>
<%--				</a></td>--%>
				<td>
					${tcAqiMonitor.city}
				</td>
				<td>
					${tcAqiMonitor.aqi}
				</td>
				<td>
					${tcAqiMonitor.fl}
				</td>
				<td>
					${tcAqiMonitor.fx}
				</td>
				<td>
					${tcAqiMonitor.highTemprature}
				</td>
				<td>
					${tcAqiMonitor.lowTemprature}
				</td>
				<td>
					${tcAqiMonitor.notice}
				</td>
				<td>
					${tcAqiMonitor.sunRise}
				</td>
				<td>
					${tcAqiMonitor.sunSet}
				</td>
				<td>
					${tcAqiMonitor.week}
				</td>
				<td>
					${tcAqiMonitor.weather}
				</td>
				<td>
					${tcAqiMonitor.pm25}
				</td>
				<td>
					${tcAqiMonitor.pm10}
				</td>
	            <td>
					<fmt:formatDate value="${tcAqiMonitor.createTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${tcAqiMonitor.ymd}  ${tcAqiMonitor.updateTime}
				</td>
				<shiro:hasPermission name="aqi:tcAqiMonitor:edit"><td>
    				<a href="${ctx}/aqi/tcAqiMonitor/form?id=${tcAqiMonitor.id}">修改</a>
					<a href="${ctx}/aqi/tcAqiMonitor/delete?id=${tcAqiMonitor.id}" onclick="return confirmx('确认要删除该气象数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>美誉度趋势管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/csly/touristReputation/export?name=美誉度趋势");
						$("#searchForm").submit();
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
		<form id="importForm" action="${ctx}/csly/touristReputation/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/csly/touristReputation/import/template?name=美誉度趋势">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csly/touristReputation/">美誉度趋势列表</a></li>
		<shiro:hasPermission name="csly:touristReputation:edit"><li><a href="${ctx}/csly/touristReputation/form">美誉度趋势添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristReputation" action="${ctx}/csly/touristReputation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年：</label>
				<input name="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${touristReputation.year}"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>月：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${touristReputation.month}"
					onclick="WdatePicker({dateFmt:'MM',isShowClear:false,isShowToday:false});"/>
			</li>
			<li><label>景点：</label>
				<form:input path="scenery" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th><th>景点</th><th>美誉度</th>
				<shiro:hasPermission name="csly:touristReputation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristReputation">
			<tr>
				<td>${touristReputation.year}-${touristReputation.month}</td><td>${touristReputation.scenery}</td><td>${touristReputation.reputation}</td>
				<shiro:hasPermission name="csly:touristReputation:edit"><td>
    				<a href="${ctx}/csly/touristReputation/form?id=${touristReputation.id}">修改</a>
					<a href="${ctx}/csly/touristReputation/delete?id=${touristReputation.id}" onclick="return confirmx('确认要删除该美誉度趋势吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
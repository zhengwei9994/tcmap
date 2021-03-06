<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>金融机构本外币存款余额管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出报名信息数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						<%--$("#inputForm").attr("action","${ctx}/csyx/businessBalance/export");--%>
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
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/csyx/businessBalance/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
    <div id="importBox" class="hide">
        <form id="importForm" action="${ctx}/csyx/businessBalance/import" method="post" enctype="multipart/form-data"
              class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
            <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
            <a href="${ctx}/csyx/businessBalance/import/template">下载模板</a>
        </form>
    </div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csyx/businessBalance/">金融机构本外币存款余额列表</a></li>
		<shiro:hasPermission name="csyx:businessBalance:edit"><li><a href="${ctx}/csyx/businessBalance/form">金融机构本外币存款余额添加</a></li></shiro:hasPermission>
	</ul>
	<div  style="display:none;">
		<form:form id="inputForm" modelAttribute="businessBalance" action="${ctx}/csyx/businessBalance/export" method="post" class="breadcrumb form-search"/>
	</div>

	<form:form id="searchForm" modelAttribute="businessBalance" action="${ctx}/csyx/businessBalance/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>余额：</label>--%>
				<%--<form:input path="balance" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>月：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${businessBalance.month}" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"/>
			</li>
			<li><label>年：</label>
				<input name="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${businessBalance.year}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>余额</th>
				<shiro:hasPermission name="csyx:businessBalance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessBalance">
			<tr>
				<td><a href="${ctx}/csyx/businessBalance/form?id=${businessBalance.id}">
					${businessBalance.year}-${businessBalance.month}
				</a></td>
				<td>
						${businessBalance.balance}
				</td>
				<shiro:hasPermission name="csyx:businessBalance:edit"><td>
    				<a href="${ctx}/csyx/businessBalance/form?id=${businessBalance.id}">修改</a>
					<a href="${ctx}/csyx/businessBalance/delete?id=${businessBalance.id}" onclick="return confirmx('确认要删除该金融机构本外币存款余额吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
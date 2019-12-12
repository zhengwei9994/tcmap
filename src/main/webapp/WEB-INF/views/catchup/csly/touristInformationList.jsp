<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>景区资讯排行管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/csly/touristInformation/export?name=景区资讯排行");
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
		<form id="importForm" action="${ctx}/csly/touristInformation/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/csly/touristInformation/import/template?name=景区资讯排行">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/csly/touristInformation/">景区资讯排行列表</a></li>
		<shiro:hasPermission name="csly:touristInformation:edit"><li><a href="${ctx}/csly/touristInformation/form">景区资讯排行添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="touristInformation" action="${ctx}/csly/touristInformation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资讯：</label>
				<form:input path="information" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>媒体：</label>
				<form:input path="media" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>时间：</label>
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${touristInformation.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
<%--			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>--%>
<%--			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>--%>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资讯</th>
				<th>媒体</th>
				<th>新闻量</th>
				<th>时间</th>
				<shiro:hasPermission name="csly:touristInformation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="touristInformation">
			<tr>
				<td><a href="${ctx}/csly/touristInformation/form?id=${touristInformation.id}">
					${touristInformation.information}
				</a></td>
				<td>
					${touristInformation.media}
				</td>
				<td>
					${touristInformation.volume}
				</td>
				<td>
					<fmt:formatDate value="${touristInformation.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="csly:touristInformation:edit"><td>
    				<a href="${ctx}/csly/touristInformation/form?id=${touristInformation.id}">修改</a>
					<a href="${ctx}/csly/touristInformation/delete?id=${touristInformation.id}" onclick="return confirmx('确认要删除该景区资讯排行吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
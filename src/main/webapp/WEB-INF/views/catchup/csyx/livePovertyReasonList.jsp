<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>致贫原因管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        <%--$("#searchForm").attr("action","${ctx}/csyx/catchAssessmentManagement/export?name=考核管理");--%>
                        $("#inputForm").submit();
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
            $("#btnImport").click(function () {
                $.jBox($("#importBox").html(), {
                    title: "导入数据", buttons: {"关闭": true},
                    bottomText: "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
                });
            });
        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/csyx/livePovertyReason/import" method="post"
          enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <a href="${ctx}/csyx/livePovertyReason/import/template?name=考核管理">下载模板</a>
    </form>
</div>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/csyx/livePovertyReason/">致贫原因列表</a></li>
    <shiro:hasPermission name="csyx:livePovertyReason:edit">
        <li><a href="${ctx}/csyx/livePovertyReason/form">致贫原因添加</a></li>
    </shiro:hasPermission>
</ul>
<div  style="display:none;">
	<form:form id="inputForm" modelAttribute="livePovertyReason" action="${ctx}/csyx/livePovertyReason/export?name=致贫原因" method="post" class="breadcrumb form-search"/>
</div>
<form:form id="searchForm" modelAttribute="livePovertyReason" action="${ctx}/csyx/livePovertyReason/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>地区：</label>
            <form:select path="area" class="input-medium">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>致贫原因：</label>
            <form:select path="reason" class="input-medium">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('poverty_reason')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li class="btns">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
            <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
<%--            <input id="btnImport" class="btn btn-primary" type="button" value="导入"/>--%>
        </li>

        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>地区</th>
        <th>致贫原因</th>
        <th>数量</th>
        <shiro:hasPermission name="csyx:livePovertyReason:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="livePovertyReason">
        <tr>
            <td>${livePovertyReason.area}</td>
            <td>${livePovertyReason.reason}</td>
            <td>${livePovertyReason.num}人</td>
            <shiro:hasPermission name="csyx:livePovertyReason:edit">
                <td>
                    <a href="${ctx}/csyx/livePovertyReason/form?id=${livePovertyReason.id}">修改</a>
                    <a href="${ctx}/csyx/livePovertyReason/delete?id=${livePovertyReason.id}"
                       onclick="return confirmx('确认要删除该致贫原因吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
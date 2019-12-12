<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>模型管理主表管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

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
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sfmk/algorithmicRoot/">模型管理</a></li>
    <shiro:hasPermission name="sfmk:algorithmicRoot:edit">
        <li><a href="${ctx}/sfmk/algorithmicRoot/form">模型添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="algorithmicRoot" action="${ctx}/sfmk/algorithmicRoot/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>模型名称：</label>
            <form:input path="algorithmic" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>模型类型：</label>
            <form:input path="type" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>模型名称</th>
        <th>模型类型</th>
        <th>结果类型</th>
        <shiro:hasPermission name="sfmk:algorithmicRoot:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="algorithmicRoot">
        <tr>
            <td>
                <a href="${ctx}/sfmk/algorithmicRoot/form?id=${algorithmicRoot.id}">
                        ${algorithmicRoot.algorithmic}
                </a>
            </td>
            <td>
                    ${algorithmicRoot.type}
            </td>
            <td>
                    ${algorithmicRoot.result}
            </td>
            <shiro:hasPermission name="sfmk:algorithmicRoot:edit">
                <td>
                    <a href="${ctx}/sfmk/algorithmicRoot/form?id=${algorithmicRoot.id}">修改</a>
                    <a href="${ctx}/sfmk/algorithmicRoot/delete?id=${algorithmicRoot.id}"
                       onclick="return confirmx('确认要删除该模型吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
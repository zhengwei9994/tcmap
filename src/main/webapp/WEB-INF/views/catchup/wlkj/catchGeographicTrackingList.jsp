<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>热点舆情管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {

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
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/wlkj/catchGeographicTracking/">社情地理追踪列表</a></li>
    <shiro:hasPermission name="wlkj:catchGeographicTracking:edit"><li><a href="${ctx}/wlkj/catchGeographicTracking/form">社情地理追踪添加</a></li></shiro:hasPermission>
</ul>
<div hidden>
    <form:form id="searchForm" modelAttribute="catchGeographicTracking" action="${ctx}/wlkj/catchGeographicTracking/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
</div>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>区域</th>
        <th>舆情数量</th>
        <shiro:hasPermission name="wlkj:catchGeographicTracking:edit"><th>操作</th></shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="catchGeographicTracking">
    <tr>
        <td>
                ${catchGeographicTracking.areaName}
        </td>
        <td>
                ${catchGeographicTracking.trackNum}
        </td>
        <shiro:hasPermission name="wlkj:catchGeographicTracking:edit"><td>
            <a href="${ctx}/wlkj/catchGeographicTracking/form?id=${catchGeographicTracking.id}">修改</a>
            <a href="${ctx}/wlkj/catchGeographicTracking/delete?id=${catchGeographicTracking.id}" onclick="return confirm('确认要删除该数据吗？', this.href)">删除</a>
        </td></shiro:hasPermission>
    </tr>
    </c:forEach>
</table>
<div class="pagination">${page}</div>
</body>
</html>
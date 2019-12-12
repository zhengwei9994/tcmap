<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>水质监测数据管理</title>
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
    <li class="active"><a href="${ctx}/csyx/catchSwageTreat/">水质监测数据管理列表</a></li>
    <shiro:hasPermission name="csyx:catchSwageTreat:edit"><li><a href="${ctx}/csyx/catchSwageTreat/form">水质监测数据管理添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="catchSwageTreat" action="${ctx}/csyx/catchSwageTreat/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>年份：</label>
            <input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${catchSwageTreat.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>年份</th>
        <th>月份</th>
        <th>等级</th>
        <th>污染指数</th>
        <shiro:hasPermission name="csyx:catchSwageTreat:edit"><th>操作</th></shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="catchSwageTreat">
    <tr>
        <td>
                ${catchSwageTreat.nyear}
        </td>
        <td>
                ${catchSwageTreat.month}
        </td>
        <td>
                ${catchSwageTreat.rank}
        </td>
        <td>
                ${catchSwageTreat.swageValue}
        </td>
        <shiro:hasPermission name="csyx:catchSwageTreat:edit"><td>
            <a href="${ctx}/csyx/catchSwageTreat/form?id=${catchSwageTreat.id}">修改</a>
            <a href="${ctx}/csyx/catchSwageTreat/delete?id=${catchSwageTreat.id}" onclick="return confirm('确认要删除该数据吗？', this.href)">删除</a>
        </td></shiro:hasPermission>
    </tr>
    </c:forEach>
</table>
<div class="pagination">${page}</div>
</body>
</html>
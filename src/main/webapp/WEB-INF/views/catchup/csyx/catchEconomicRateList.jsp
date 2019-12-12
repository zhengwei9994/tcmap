<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>经济指标管理</title>
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
    <li class="active"><a href="${ctx}/csyx/catchEconomicRate/">经济指标管理列表</a></li>
    <shiro:hasPermission name="csyx:catchEconomicRate:edit"><li><a href="${ctx}/csyx/catchEconomicRate/form">经济指标管理添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="catchEconomicRate" action="${ctx}/csyx/catchEconomicRate/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>年份：</label>
            <input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${catchEconomicRate.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>年份</th>
        <th>区域</th>
        <th>指标项</th>
        <th>数据</th>
        <th>单位</th>
        <shiro:hasPermission name="csyx:catchEconomicRate:edit"><th>操作</th></shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="catchEconomicRate">
    <tr>
        <td>
                ${catchEconomicRate.nyear}
        </td>
        <td>
                ${catchEconomicRate.areaName}
        </td>
        <td>
                ${catchEconomicRate.indexName}
        </td>
        <td>
                ${catchEconomicRate.indicators}
        </td>
        <td>
                ${catchEconomicRate.indicatorsUnit}
        </td>
        <shiro:hasPermission name="csyx:catchEconomicRate:edit"><td>
            <a href="${ctx}/csyx/catchEconomicRate/form?id=${catchEconomicRate.id}">修改</a>
            <a href="${ctx}/csyx/catchEconomicRate/delete?id=${catchEconomicRate.id}" onclick="return confirm('确认要删除该数据吗？', this.href)">删除</a>
        </td></shiro:hasPermission>
    </tr>
    </c:forEach>
</table>
<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>经济指标管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
//                     loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/csyx/catchEconomicRate/">经济指标管理列表</a></li>
    <li class="active"><a href="${ctx}/csyx/catchEconomicRate/form?id=${catchEconomicRate.id}">经济指标管理<shiro:hasPermission name="csyx:catchEconomicRate:edit">${not empty catchEconomicRate.id?'修改':'添加'}</shiro:hasPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="catchEconomicRate" action="${ctx}/csyx/catchEconomicRate/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">年份：</label>
        <div class="controls">
            <input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                   value="${catchEconomicRate.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">区县：</label>
        <div class="controls">
            <form:select path="areaName" class="input-xlarge required">
                <form:option value="铜川市" label="铜川市"/>
                <form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">指标项：</label>
        <div class="controls">
            <form:input path="indexName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">数据：</label>
        <div class="controls">
            <form:input path="indicators" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">单位：</label>
        <div class="controls">
            <form:select path="indicatorsUnit" htmlEscape="false"   class="input-xlarge required">
                <form:option value="%" label="%"/>
                <form:option value="元" label="元"/>
                <form:option value="万元" label="万元"/>
                <form:option value="亿元" label="亿元"/>
                <form:option value="人" label="人"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="csyx:catchEconomicRate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
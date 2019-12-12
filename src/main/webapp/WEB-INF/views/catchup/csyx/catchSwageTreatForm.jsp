<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>水质监测数据管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
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
    <li><a href="${ctx}/csyx/catchSwageTreat/">水质监测数据列表</a></li>
    <li class="active"><a href="${ctx}/csyx/catchSwageTreat/form?id=${catchSwageTreat.id}">水质监测数据<shiro:hasPermission name="csyx:catchSwageTreat:edit">${not empty catchSwageTreat.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchSwageTreat:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="catchSwageTreat" action="${ctx}/csyx/catchSwageTreat/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">年份：</label>
        <div class="controls">
             <input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
               value="${catchSwageTreat.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">月份：</label>
        <div class="controls">
            <input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${catchSwageTreat.month}" onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
    <label class="control-label">等级：</label>
    <div class="controls">
        <form:select path="rank" class="input-xlarge required">
            <form:option value="0" label="断流"/>
            <form:option value="1" label="Ⅰ-Ⅱ优"/>
            <form:option value="2" label="Ⅲ良"/>
            <form:option value="3" label="Ⅳ污"/>
            <form:option value="4" label="Ⅴ污"/>
            <form:option value="5" label="劣Ⅴ"/>
        </form:select>
        <span class="help-inline"><font color="red">*</font> </span>
    </div>
</div>
    <div class="control-group">
        <label class="control-label">达标占比：</label>
        <div class="controls">
            <form:input path="swageValue" htmlEscape="false" maxlength="20" class="input-xlarge required digits"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="csyx:catchSwageTreat:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
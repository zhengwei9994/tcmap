<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>地理舆情追踪</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
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
    <li><a href="${ctx}/wlkj/catchGeographicTracking/">社情地理追踪列表</a></li>
    <li class="active"><a href="${ctx}/wlkj/catchGeographicTracking/form?id=${catchGeographicTracking.id}">社情地理追踪<shiro:hasPermission name="wlkj:catchGeographicTracking:edit">${not empty catchGeographicTracking.id?'修改':'添加'}</shiro:hasPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="catchGeographicTracking" action="${ctx}/wlkj/catchGeographicTracking/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">区县：</label>
        <div class="controls">
            <c:if test="${empty catchGeographicTracking.id}">
                <form:select path="areaName" class="input-xlarge required">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty catchGeographicTracking.id}">
                <input name="areaName" type="text" readonly="readonly" maxlength="20" value="${catchGeographicTracking.areaName}" class="text"/>
            </c:if>

            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">舆情数量：</label>
        <div class="controls">
            <form:input path="trackNum" htmlEscape="false" maxlength="6" class="input-xlarge required digits"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="wlkj:catchGeographicTracking:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
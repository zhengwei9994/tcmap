<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>模型实例管理表管理主表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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
    <li><a href="${ctx}/sfmk/algorithmicExample/">模型实例管理表列表</a></li>
    <li class="active"><a
            href="${ctx}/sfmk/algorithmicExample/form?id=${algorithmicExample.id}">模型实例管理表<shiro:hasPermission
            name="sfmk:algorithmicExample:edit">${not empty algorithmicExample.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="sfmk:algorithmicExample:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="algorithmicExample" action="${ctx}/sfmk/algorithmicExample/form" method="post"
           class="form-horizontal">
    <div class="control-group">
        <label class="control-label">模型主表id：</label>
        <div class="controls">
            <form:select id="rootid" path="rootid" class="input-xlarge required"
                         onchange="rootidChange($('#rootid option:selected').val())">
                <form:option value="" label=""/>
                <form:options items="${rootpage.list}" itemLabel="algorithmic" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="sfmk:algorithmicExample:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                        type="submit"
                                                                        value="查 询"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
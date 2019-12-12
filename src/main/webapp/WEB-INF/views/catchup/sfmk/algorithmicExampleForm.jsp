<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>模型实例管理表管理</title>
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

        function rootidChange(id) {
            <c:set var = "rootid" value = 'id' />
            alert(${rootid});

            // $('#courseList').replace();
            // $('#courseList').reload();
            console.log('${parameterpage.list}')
            alert(${rootid}+"后面");
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/sfmk/algorithmicExample/">模型实例</a></li>
    <li class="active"><a
            href="${ctx}/sfmk/algorithmicExample/form?id=${algorithmicExample.id}">模型实例<shiro:hasPermission
            name="sfmk:algorithmicExample:edit">${not empty algorithmicExample.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="sfmk:algorithmicExample:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="algorithmicExampleList" action="${ctx}/sfmk/algorithmicExample/saveList"
           method="post"
           class="form-horizontal">
    <sys:message content="${message}"/>
    <div id=courseList>
        <div class="control-group">
            <label class="control-label">实例名称：</label>
            <div class="controls">
                <input type="text" name = "name" value="${name}">
            </div>
        </div>
        <c:forEach items="${parameterpage.list}" var="parameter" varStatus="status">
            <div class="control-group">
                <label class="control-label">${example.id}${parameter.parametername}</label>
                <div class="controls">
                    <input type="hidden" name="algorithmicExampleList[${status.index}].rootid"
                           value="${parameter.algorithmicid}"/>
                    <input type="hidden" name="algorithmicExampleList[${status.index}].parameterid"
                           value="${parameter.id}"/>
                    <input type="text" class="input-xlarge" id ="numvalue${status.index}"
                           name="algorithmicExampleList[${status.index}].numvalue">
                    <c:forEach items="${examplepage.list}" var="example">
                        <c:if test="${parameter.id==example.parameterid}">
                            <input type="hidden" name="algorithmicExampleList[${status.index}].id"
                                   value="${example.id}"/>
                            <script>
                                $('#numvalue${status.index}').val(${example.numvalue})
                            </script>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="sfmk:algorithmicExample:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                        type="submit"
                                                                        value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
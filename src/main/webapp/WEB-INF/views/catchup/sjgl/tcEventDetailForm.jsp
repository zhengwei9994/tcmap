<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>事件详情管理</title>
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
		<li><a href="${ctx}/sjgl/tcEventDetail/">事件详情列表</a></li>
		<li class="active"><a href="${ctx}/sjgl/tcEventDetail/form?id=${tcEventDetail.id}">事件详情<shiro:hasPermission name="sjgl:tcEventDetail:edit">${not empty tcEventDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sjgl:tcEventDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tcEventDetail" action="${ctx}/sjgl/tcEventDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">事件类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge  " required="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('event_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">事件来源：</label>
			<div class="controls">
				<form:input path="source" htmlEscape="false" maxlength="25" required="true" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">事件占比：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="rate" htmlEscape="false" maxlength="11"  class="input-xlarge number "/>--%>
<%--				<span class="help-inline"><font color="#6b8e23">%</font> </span>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="control-group">
			<label class="control-label">完成情况：</label>
			<div class="controls">
				<form:input path="completion" htmlEscape="false" maxlength="5" required="true" class="input-xlarge number "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">相关责任人：</label>
			<div class="controls">
				<form:input path="eventPerson" htmlEscape="false" maxlength="8" required="true" class="input-xlarge  "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">事件描述：</label>
            <div class="controls">
                    <%--				<form:input path="eventContent" htmlEscape="false"  class="input-xlarge "/>--%>
                <form:textarea id="eventContent" htmlEscape="true" path="eventContent" rows="4" maxlength="200" class="input-xxlarge"/>
                <sys:ckeditor replace="eventContent" uploadPath="/sjgl/tcEventDetail" />
            </div>
        </div>
		<div class="form-actions">
			<shiro:hasPermission name="sjgl:tcEventDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模型管理主表管理</title>
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
		<li><a href="${ctx}/sfmk/algorithmicRoot/">模型管理</a></li>
		<li class="active"><a href="${ctx}/sfmk/algorithmicRoot/form?id=${algorithmicRoot.id}">模型<shiro:hasPermission name="sfmk:algorithmicRoot:edit">${not empty algorithmicRoot.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sfmk:algorithmicRoot:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="algorithmicRoot" action="${ctx}/sfmk/algorithmicRoot/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模型名称：</label>
			<div class="controls">
				<form:input path="algorithmic" htmlEscape="false" required="true" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模型类型：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="255" required="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结果类型：</label>
			<div class="controls">
				<form:input path="result" htmlEscape="false" maxlength="255" required="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sfmk:algorithmicRoot:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
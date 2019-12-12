<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统管理</title>
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
		<li><a href="${ctx}/ddgl/tcSyserviceMananger/">系统列表</a></li>
		<li class="active"><a href="${ctx}/ddgl/tcSyserviceMananger/form?id=${tcSyserviceMananger.id}">系统<shiro:hasPermission name="ddgl:tcSyserviceMananger:edit">${not empty tcSyserviceMananger.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ddgl:tcSyserviceMananger:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tcSyserviceMananger" action="${ctx}/ddgl/tcSyserviceMananger/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">系统名：</label>
			<div class="controls">
				<form:input path="sysName" htmlEscape="false" maxlength="255" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统域名：</label>
			<div class="controls">
				<form:input path="sysDomain" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统标识：</label>
			<div class="controls">
				<form:input path="sysCode" htmlEscape="false" maxlength="255" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键词：</label>
			<div class="controls">
				<form:input path="keyWords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统简介：</label>
			<div class="controls">
				<form:textarea path="sysIntro" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ddgl:tcSyserviceMananger:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
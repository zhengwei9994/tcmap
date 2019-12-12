<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公共安全视频资源覆盖情况管理</title>
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
		<li><a href="${ctx}/fzjc/catchVideoResource/">公共安全视频资源覆盖情况列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchVideoResource/form?id=${catchVideoResource.id}">公共安全视频资源覆盖情况<shiro:hasPermission name="fzjc:catchVideoResource:edit">${not empty catchVideoResource.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchVideoResource:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchVideoResource" action="${ctx}/fzjc/catchVideoResource/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20"  value="${catchVideoResource.nyear}"
						<c:if test="${empty catchVideoResource.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if> />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重点区域覆盖率：</label>
			<div class="controls">
				<form:input path="keyRegionalCoverage" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重点领域覆盖率：</label>
			<div class="controls">
				<form:input path="coverageKeyAreas" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高清摄像机比率：</label>
			<div class="controls">
				<form:input path="highDefinitionCamera" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">摄像机完好比率：</label>
			<div class="controls">
				<form:input path="cameraIntegrityRatio" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重点领域机器完好率：</label>
			<div class="controls">
				<form:input path="machineCompletionKey" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchVideoResource:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
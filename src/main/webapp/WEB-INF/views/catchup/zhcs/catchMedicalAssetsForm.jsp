<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医疗资源统计管理</title>
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
		<li><a href="${ctx}/zhcs/catchMedicalAssets/">医疗资源统计列表</a></li>
		<li class="active"><a href="${ctx}/zhcs/catchMedicalAssets/form?id=${catchMedicalAssets.id}">医疗资源统计<shiro:hasPermission name="zhcs:catchMedicalAssets:edit">${not empty catchMedicalAssets.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhcs:catchMedicalAssets:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchMedicalAssets" action="${ctx}/zhcs/catchMedicalAssets/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchMedicalAssets.nyear}" <c:if test="${ empty catchMedicalAssets.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${catchMedicalAssets.month}" <c:if test="${ empty catchMedicalAssets.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医院等级：</label>
			<div class="controls">
				<form:select path="hospitalGrade" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医院数量：</label>
			<div class="controls">
				<form:input path="hospitalNumber" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医生人数：</label>
			<div class="controls">
				<form:input path="doctorsNumber" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">护士人数：</label>
			<div class="controls">
				<form:input path="nurseNumber" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhcs:catchMedicalAssets:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
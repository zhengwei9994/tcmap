<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核管理管理</title>
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
		<li><a href="${ctx}/csyx/catchAssessmentManagement/">考核管理列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchAssessmentManagement/form?id=${catchAssessmentManagement.id}">考核管理<shiro:hasPermission name="csyx:catchAssessmentManagement:edit">${not empty catchAssessmentManagement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchAssessmentManagement:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchAssessmentManagement" action="${ctx}/csyx/catchAssessmentManagement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">考核部门：</label>
			<div class="controls">
				<form:select path="department" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('municipal_department')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下发部门：</label>
			<div class="controls">
				<form:select path="sector" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('municipal_department')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务内容：</label>
			<div class="controls">
				<form:textarea id="mission" htmlEscape="true" path="mission" rows="3" maxlength="200" class="input-large"/>
				<sys:ckeditor replace="mission" height="300px" uploadPath="/csyx/catchAssessmentManagement" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起止时间：</label>
			<div class="controls">
			<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
				   value="<fmt:formatDate value="${catchAssessmentManagement.startDate}" pattern="yyyy-MM"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,isShowToday:false});"/>	-
			<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
				   value="<fmt:formatDate value="${catchAssessmentManagement.endDate}" pattern="yyyy-MM"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,isShowToday:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分值：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" maxlength="3" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchAssessmentManagement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
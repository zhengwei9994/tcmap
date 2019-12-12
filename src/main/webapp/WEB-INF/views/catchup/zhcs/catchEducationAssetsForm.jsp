<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教育资产管理</title>
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
		<li><a href="${ctx}/zhcs/catchEducationAssets/">教育资产列表</a></li>
		<li class="active"><a href="${ctx}/zhcs/catchEducationAssets/form?id=${catchEducationAssets.id}">教育资产<shiro:hasPermission name="zhcs:catchEducationAssets:edit">${not empty catchEducationAssets.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhcs:catchEducationAssets:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchEducationAssets" action="${ctx}/zhcs/catchEducationAssets/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchEducationAssets.nyear}" <c:if test="${ empty catchEducationAssets.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${catchEducationAssets.month}" <c:if test="${ empty catchEducationAssets.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">学校类别：</label>
			<div class="controls">
				<c:choose>
				<c:when test="${ empty catchEducationAssets.id }">
				<form:select path="educationType"  class="input-xlarge required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('education_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
					</c:when>
					<c:when test="${ ! empty catchEducationAssets.id }">
				<form:select path="educationType"   disabled="true"  class="input-xlarge required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('education_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
					</c:when>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学校数量：</label>
			<div class="controls">
				<form:input path="schoolNumber" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教职人员总数：</label>
			<div class="controls">
				<form:input path="teachingStaff" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhcs:catchEducationAssets:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
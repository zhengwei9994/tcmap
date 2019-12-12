<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>万元GDP能耗降低率管理</title>
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
		<li><a href="${ctx}/fzjc/catchEnergyConsumption/">万元GDP能耗降低率列表</a></li>
		<!--
		<li class="active"><a href="${ctx}/fzjc/catchEnergyConsumption/form?id=${catchEnergyConsumption.id}">万元GDP能耗降低率<shiro:hasPermission name="fzjc:catchEnergyConsumption:edit">${not empty catchEnergyConsumption.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchEnergyConsumption:edit">查看</shiro:lacksPermission></a></li>
		-->
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchEnergyConsumption" action="${ctx}/fzjc/catchEnergyConsumption/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"  value="${catchEnergyConsumption.year}"
						<c:if test="${empty catchEnergyConsumption.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if> />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">季度：</label>
			<div class="controls">
				<c:if test="${empty catchEnergyConsumption.id}">
					<form:select path="quarter" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('quarter')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty catchEnergyConsumption.id}">
					<input name="quarter" type="text" readonly="readonly" maxlength="20"  value="${catchEnergyConsumption.quarter}">
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位GDP能耗：</label>
			<div class="controls">
				<form:input path="energyConsumption" htmlEscape="false" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GDP能耗增速：</label>
			<div class="controls">
				<form:input path="energyConsumptionRate" htmlEscape="false" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchEnergyConsumption:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
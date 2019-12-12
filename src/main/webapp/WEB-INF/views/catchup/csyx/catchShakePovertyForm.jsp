<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>精准务实抓脱贫管理</title>
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
		<li><a href="${ctx}/csyx/catchShakePoverty/">精准务实抓脱贫列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchShakePoverty/form?id=${catchShakePoverty.id}">精准务实抓脱贫<shiro:hasPermission name="csyx:catchShakePoverty:edit">${not empty catchShakePoverty.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchShakePoverty:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchShakePoverty" action="${ctx}/csyx/catchShakePoverty/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchShakePoverty.nyear}"<c:if test="${empty catchShakePoverty.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区县：</label>
			<div class="controls">
				<c:if test="${empty catchShakePoverty.id}">
					<form:select path="areaName" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty catchShakePoverty.id}">
					<input name="areaName" type="text" readonly="readonly" maxlength="20"
						   value="${catchShakePoverty.areaName}">
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已脱贫人口：</label>
			<div class="controls">
				<form:input path="outPoverty" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">未脱贫人口：</label>
			<div class="controls">
				<form:input path="totalPeople" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">脱贫原因：</label>
			<div class="controls">
				<form:select path="reasonsAlleviation" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('reasons_alleviation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">贫困户：</label>
			<div class="controls">
				<form:input path="poorHouseholds" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchShakePoverty:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
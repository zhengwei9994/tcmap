<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游相关管理</title>
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
		<li><a href="${ctx}/zhcs/catchTravelInfo/">旅游相关信息列表</a></li>
		<li class="active"><a href="${ctx}/zhcs/catchTravelInfo/form?id=${catchTravelInfo.id}">旅游相关信息<shiro:hasPermission name="zhcs:catchTravelInfo:edit">${not empty catchTravelInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhcs:catchTravelInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchTravelInfo" action="${ctx}/zhcs/catchTravelInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchTravelInfo.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="infoType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('travel_info_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:input path="travelName" htmlEscape="false" maxlength="20" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">数据：</label>
			<div class="controls">
				<form:input path="infoValue" htmlEscape="false" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhcs:catchTravelInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
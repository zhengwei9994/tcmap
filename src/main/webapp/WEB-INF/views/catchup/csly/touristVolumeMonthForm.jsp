<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>按月流客量管理</title>
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
		<li><a href="${ctx}/csly/touristVolumeMonth/">按月流客量列表</a></li>
		<li class="active"><a href="${ctx}/csly/touristVolumeMonth/form?id=${touristVolumeMonth.id}">按月流客量<shiro:hasPermission name="csly:touristVolumeMonth:edit">${not empty touristVolumeMonth.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csly:touristVolumeMonth:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="touristVolumeMonth" action="${ctx}/csly/touristVolumeMonth/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">月份：</label>
			<div class="controls">
				<form:input path="month" htmlEscape="false" maxlength="255" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<form:input path="year" htmlEscape="false" maxlength="255" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="255" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csly:touristVolumeMonth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
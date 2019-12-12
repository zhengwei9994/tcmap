<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>当日游客量管理</title>
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
		<li><a href="${ctx}/csly/touristVolumeTime/">当日游客量列表</a></li>
		<li class="active"><a href="${ctx}/csly/touristVolumeTime/form?id=${touristVolumeTime.id}">当日游客量<shiro:hasPermission name="csly:touristVolumeTime:edit">${not empty touristVolumeTime.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csly:touristVolumeTime:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="touristVolumeTime" action="${ctx}/csly/touristVolumeTime/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<%--<form:input path="time" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
				<input name="time" type="text" readonly="readonly" maxlength="20"
					   value="${touristVolumeTime.time}" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowToday:false,isShowClear:false});"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日期：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20"
					   value="${touristVolumeTime.date}" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy/MM/dd',isShowToday:false,isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">指数：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="255" class="input-xlarge digits required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">景区：</label>
			<div class="controls">
				<form:input path="scenic" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csly:touristVolumeTime:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
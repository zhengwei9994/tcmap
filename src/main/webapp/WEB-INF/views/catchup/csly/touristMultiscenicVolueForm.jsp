<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>多景区品牌聆听气泡图管理</title>
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
		<li><a href="${ctx}/csly/touristMultiscenicVolue/">多景区品牌聆听气泡图列表</a></li>
		<li class="active"><a href="${ctx}/csly/touristMultiscenicVolue/form?id=${touristMultiscenicVolue.id}">多景区品牌聆听气泡图<shiro:hasPermission name="csly:touristMultiscenicVolue:edit">${not empty touristMultiscenicVolue.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csly:touristMultiscenicVolue:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="touristMultiscenicVolue" action="${ctx}/csly/touristMultiscenicVolue/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${touristMultiscenicVolue.month}"
					onclick="WdatePicker({dateFmt:'MM',isShowClear:false,isShowToday:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${touristMultiscenicVolue.year}"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌值：</label>
			<div class="controls">
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-xlarge required number"/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">景区名：</label>
			<div class="controls">
				<form:input path="scenic" htmlEscape="false" maxlength="255" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">声量：</label>
			<div class="controls">
				<form:input path="volue" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csly:touristMultiscenicVolue:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>营商设施完成度管理</title>
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
		<li><a href="${ctx}/csyx/businessFacilities/">营商设施完成度列表</a></li>
		<li class="active"><a href="${ctx}/csyx/businessFacilities/form?id=${businessFacilities.id}">营商设施完成度<shiro:hasPermission name="csyx:businessFacilities:edit">${not empty businessFacilities.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:businessFacilities:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="businessFacilities" action="${ctx}/csyx/businessFacilities/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设施：</label>
			<div class="controls">
				<form:input path="item" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">完成度：</label>
			<div class="controls">
				<form:input path="completion" htmlEscape="false" maxlength="3" max="300" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${businessFacilities.month}" <c:if test="${ empty businessFacilities.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${businessFacilities.year}" <c:if test="${ empty businessFacilities.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:businessFacilities:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
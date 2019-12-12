<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业现状管理</title>
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
		<li><a href="${ctx}/csyx/developmentSituation/">产业现状列表</a></li>
		<li class="active"><a href="${ctx}/csyx/developmentSituation/form?id=${developmentSituation.id}">产业现状<shiro:hasPermission name="csyx:developmentSituation:edit">${not empty developmentSituation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:developmentSituation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="developmentSituation" action="${ctx}/csyx/developmentSituation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">行业：</label>
			<div class="controls">
				<form:input path="industry" htmlEscape="false" maxlength="255" required="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">户数：</label>
			<div class="controls">
				<form:input path="households" htmlEscape="false" maxlength="255" class="input-xlarge digits "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资本：</label>
			<div class="controls">
				<form:input path="capital" htmlEscape="false" maxlength="255" class="input-xlarge number "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">大小：</label>
			<div class="controls">
				<form:input path="size" htmlEscape="false" required="true"   class="input-xlarge number "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${developmentSituation.month}" <c:if test="${ empty developmentSituation.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${developmentSituation.year}" <c:if test="${ empty developmentSituation.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:developmentSituation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
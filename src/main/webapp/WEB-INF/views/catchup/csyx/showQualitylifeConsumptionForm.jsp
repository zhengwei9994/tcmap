<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济消费状况管理</title>
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
		<li><a href="${ctx}/csyx/showQualitylifeConsumption/">经济消费状况列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showQualitylifeConsumption/form?id=${showQualitylifeConsumption.id}">经济消费状况<shiro:hasPermission name="csyx:showQualitylifeConsumption:edit">${not empty showQualitylifeConsumption.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showQualitylifeConsumption:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showQualitylifeConsumption" action="${ctx}/csyx/showQualitylifeConsumption/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${showQualitylifeConsumption.year}"<c:if test="${empty showQualitylifeConsumption.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"</c:if>/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${showQualitylifeConsumption.month}"<c:if test="${empty showQualitylifeConsumption.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消费状况：</label>
			<div class="controls">
				<form:input path="consumption" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showQualitylifeConsumption:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
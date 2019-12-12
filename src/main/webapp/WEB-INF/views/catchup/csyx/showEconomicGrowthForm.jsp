<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济成长性管理</title>
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
		<li><a href="${ctx}/csyx/showEconomicGrowth/">经济成长性列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showEconomicGrowth/form?id=${showEconomicGrowth.id}">经济成长性<shiro:hasPermission name="csyx:showEconomicGrowth:edit">${not empty showEconomicGrowth.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showEconomicGrowth:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showEconomicGrowth" action="${ctx}/csyx/showEconomicGrowth/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">常住人口：</label>
			<div class="controls">
				<form:input path="population" htmlEscape="false" maxlength="255" class="input-xlarge "/>（万人）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">常住人口增长率：</label>
			<div class="controls">
				<form:input path="populationIncrease" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人均gdp：</label>
			<div class="controls">
				<form:input path="capita" htmlEscape="false" maxlength="255" class="input-xlarge "/>（元）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人均gdp增长率：</label>
			<div class="controls">
				<form:input path="capitaIncrease" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">财政收入：</label>
			<div class="controls">
				<form:input path="revenue" htmlEscape="false" maxlength="255" class="input-xlarge required"/>（亿元）
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">财政收入增长率：</label>
			<div class="controls">
				<form:input path="revenueIncrease" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${showEconomicGrowth.year}" <c:if test="${empty showEconomicGrowth.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});" </c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${showEconomicGrowth.month}" <c:if test="${empty showEconomicGrowth.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showEconomicGrowth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
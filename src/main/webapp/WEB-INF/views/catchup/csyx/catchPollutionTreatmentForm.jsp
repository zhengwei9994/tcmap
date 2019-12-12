<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重拳治污染管理</title>
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
		<li><a href="${ctx}/csyx/catchPollutionTreatment/">重拳治污染列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchPollutionTreatment/form?id=${catchPollutionTreatment.id}">重拳治污染<shiro:hasPermission name="csyx:catchPollutionTreatment:edit">${not empty catchPollutionTreatment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchPollutionTreatment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchPollutionTreatment" action="${ctx}/csyx/catchPollutionTreatment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<%--<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
					 <%--value="${catchPollutionTreatment.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>--%>

				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchPollutionTreatment.nyear}" <c:if test="${ empty catchPollutionTreatment.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
						<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">全年优良天数：</label>
			<div class="controls">
				<form:input path="todaySum" htmlEscape="false" maxlength="4" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">关中五市排名：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select path="rank" class="input-xlarge required">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:options items="${fns:getDictList('rank')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">拆除企业：</label>
			<div class="controls">
				<form:input path="enterprise" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">黄标车：</label>
			<div class="controls">
				<form:input path="markCar" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchPollutionTreatment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
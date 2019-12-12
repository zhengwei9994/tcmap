<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业发展管理</title>
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
		<li><a href="${ctx}/csyx/industrialDevelopment/">产业发展列表</a></li>
		<li class="active"><a href="${ctx}/csyx/industrialDevelopment/form?id=${industrialDevelopment.id}">产业发展<shiro:hasPermission name="csyx:industrialDevelopment:edit">${not empty industrialDevelopment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:industrialDevelopment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="industrialDevelopment" action="${ctx}/csyx/industrialDevelopment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${industrialDevelopment.year}"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${industrialDevelopment.month}"
					   onclick="WdatePicker({dateFmt:'MM',isShowClear:false,isShowToday:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行业：</label>
			<div class="controls">
				<form:input path="profession" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子行业：</label>
			<div class="controls">
				<form:input path="professionson1" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子行业2：</label>
			<div class="controls">
				<form:input path="professionson2" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资本：</label>
			<div class="controls">
				<form:input path="capital" htmlEscape="false" maxlength="255" class="input-xlarge number required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增长率：</label>
			<div class="controls">
				<form:input path="growthrate" htmlEscape="false" maxlength="255" class="input-xlarge number required"/>
			</div>
		</div>

<%--		<div class="control-group">--%>
<%--			<label class="control-label">预留1：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="reserve1" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">预留2：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="reserve2" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">预留3：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="reserve3" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">预留4：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="reserve4" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">预留5：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="reserve5" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:industrialDevelopment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
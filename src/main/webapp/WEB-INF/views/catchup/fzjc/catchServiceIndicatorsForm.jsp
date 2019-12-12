<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区县综合政府服务指标管理</title>
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
		<li><a href="${ctx}/fzjc/catchServiceIndicators/">区县综合政府服务指标列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchServiceIndicators/form?id=${catchServiceIndicators.id}">区县综合政府服务指标<shiro:hasPermission name="fzjc:catchServiceIndicators:edit">${not empty catchServiceIndicators.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchServiceIndicators:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchServiceIndicators" action="${ctx}/fzjc/catchServiceIndicators/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">区县：</label>
			<div class="controls">
				<form:select path="areaName" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">光纤到户渗透率(%)：</label>
			<div class="controls">
				<form:input path="fiber" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽带家庭普及率(%)：</label>
			<div class="controls">
				<form:input path="broadband" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医院预约诊疗率(%)：</label>
			<div class="controls">
				<form:input path="hospital" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">社保自助开通率(%)：</label>
			<div class="controls">
				<form:input path="security" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统一入口率(%)：</label>
			<div class="controls">
				<form:input path="uniform" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一站式办理率(%)：</label>
			<div class="controls">
				<form:input path="processing" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchServiceIndicators:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
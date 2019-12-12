<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模型因子管理表管理</title>
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
		<li><a href="${ctx}/sfmk/algorithmicParameter/">模型因子</a></li>
		<li class="active"><a href="${ctx}/sfmk/algorithmicParameter/form?id=${algorithmicParameter.id}">模型因子<shiro:hasPermission name="sfmk:algorithmicParameter:edit">${not empty algorithmicParameter.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sfmk:algorithmicParameter:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="algorithmicParameter" action="${ctx}/sfmk/algorithmicParameter/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">模型：</label>
			<div class="controls">
				<form:select path="algorithmicid" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${rootpage.list}" itemLabel="algorithmic" itemValue="id" required="true" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数名：</label>
			<div class="controls">
				<form:input path="parametername" htmlEscape="false" maxlength="255" required="true"  class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数类型：</label>
			<div class="controls">
				<form:input path="parametertype" htmlEscape="false" maxlength="255" required="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sfmk:algorithmicParameter:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
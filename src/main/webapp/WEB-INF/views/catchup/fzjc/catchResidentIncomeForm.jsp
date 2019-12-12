<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>居民收入管理</title>
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
		<li><a href="${ctx}/fzjc/catchResidentIncome/">居民收入列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchResidentIncome/form?id=${catchResidentIncome.id}">居民收入<shiro:hasPermission name="fzjc:catchResidentIncome:edit">${not empty catchResidentIncome.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchResidentIncome:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchResidentIncome" action="${ctx}/fzjc/catchResidentIncome/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">居民类型：</label>
			<div class="controls">
				<form:select path="residentClusters" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:option value="全体居民" label="全体居民"/>
					<form:option value="城镇居民" label="城镇居民"/>
					<form:option value="农村居民" label="农村居民"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工资收入：</label>
			<div class="controls">
				<form:input path="wageIncome" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经营净收入：</label>
			<div class="controls">
				<form:input path="operatingIncome" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">财产净收入：</label>
			<div class="controls">
				<form:input path="ownershipIncome" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转移净收入：</label>
			<div class="controls">
				<form:input path="transferIncome" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">比率：</label>
			<div class="controls">
				<form:input path="rate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchResidentIncome:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
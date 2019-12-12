<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目投资进度排行榜管理</title>
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
		<li><a href="${ctx}/csyx/catchInvestmentProgress/">项目投资进度排行榜列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchInvestmentProgress/form?id=${catchInvestmentProgress.id}">项目投资进度排行榜<shiro:hasPermission name="csyx:catchInvestmentProgress:edit">${not empty catchInvestmentProgress.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchInvestmentProgress:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchInvestmentProgress" action="${ctx}/csyx/catchInvestmentProgress/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投资金额：</label>
			<div class="controls">
				<form:input path="annualTask" htmlEscape="false" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<%--<form:input path="unit" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
				<form:select path="unit" class="input-xlarge required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('china_money')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">累计投资占比：</label>
			<div class="controls">
				<form:input path="completedRatio" htmlEscape="false" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchInvestmentProgress:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
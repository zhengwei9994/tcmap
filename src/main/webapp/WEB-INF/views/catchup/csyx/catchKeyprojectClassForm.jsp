<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>月进度管理</title>
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
		<li><a href="${ctx}/csyx/catchKeyprojectClass?keyprojectId.id=${catchKeyprojectClass.keyprojectId.id}&keyprojectId.totalInvestment=${catchKeyprojectClass.keyprojectId.totalInvestment}">月进度列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchKeyprojectClass/form?id=${catchKeyprojectClass.id}">月进度${not empty catchKeyprojectClass.id?'修改':'添加'}<shiro:lacksPermission name="csyx:catchKeyprojectClass:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchKeyprojectClass" action="${ctx}/csyx/catchKeyprojectClass/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%--<div class="control-group">
			<label class="control-label">keyproject_id：</label>
			<div class="controls">
				<form:input path="keyprojectId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>--%>
		<form:input path="keyprojectId.totalInvestment"  type="hidden" value = "${catchKeyprojectClass.keyprojectId.totalInvestment}"/>
		<form:input  path="keyprojectId.id" type="hidden" value="${catchKeyprojectClass.keyprojectId.id}"/>
		<div class="control-group">
			<label class="control-label">月份：</label>
			<div class="controls">
				<%-- <form:select path="month" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('month')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
            <form:input path="month"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${catchKeyprojectClass.month}" onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已完成金额：</label>
			<div class="controls">
				<form:input path="amountCompleted" htmlEscape="false" maxlength="10" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:select path="unit" class="input-xlarge required">
					<form:option value="2" label="万元"/>
<%-- 					<form:options items="${fns:getDictList('unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">已完成占比：</label>
			<div class="controls">
				<form:input path="completionRatio" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">完成状态：</label>
			<div class="controls">
				<form:select path="projectStatus" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
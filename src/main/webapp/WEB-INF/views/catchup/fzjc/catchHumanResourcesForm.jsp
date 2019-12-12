<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人力资源统计管理</title>
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
		<li><a href="${ctx}/fzjc/catchHumanResources/">人力资源统计列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchHumanResources/form?id=${catchHumanResources.id}">人力资源统计<shiro:hasPermission name="fzjc:catchHumanResources:edit">${not empty catchHumanResources.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchHumanResources:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchHumanResources" action="${ctx}/fzjc/catchHumanResources/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchHumanResources.nyear}" <c:if test="${empty catchHumanResources.id}">  class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位类型：</label>
			<div class="controls">
				<c:if test="${empty catchHumanResources.id}">
					<form:select path="companyType" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('company_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty catchHumanResources.id}">
					<input name="companyType" type="text" readonly="readonly" maxlength="20" value="${catchHumanResources.companyType}">
				</c:if>

				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">从业人数（万）：</label>
			<div class="controls">
				<form:input path="numberPeople" htmlEscape="false" maxlength="6" class="input-xlarge required numble"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchHumanResources:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
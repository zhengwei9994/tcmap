<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>薪资及人员分析管理</title>
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
		<li><a href="${ctx}/fzjc/catchSalaryStaff/">薪资及人员分析列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchSalaryStaff/form?id=${catchSalaryStaff.id}">薪资及人员分析<shiro:hasPermission name="fzjc:catchSalaryStaff:edit">${not empty catchSalaryStaff.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchSalaryStaff:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchSalaryStaff" action="${ctx}/fzjc/catchSalaryStaff/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchSalaryStaff.nyear}" <c:if test="${empty catchSalaryStaff.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});</c:if> "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计内容：</label>
			<div class="controls">
				<c:if test="${empty catchSalaryStaff.id }">
				<form:select path="statisticalContent" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('statistical_content')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				</c:if>
				<c:if test="${!empty catchSalaryStaff.id}">
					<input name="statisticalContent" type="text" readonly="readonly" maxlength="20" value="${catchSalaryStaff.statisticalContent}">
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="numericalValue" htmlEscape="false" maxlength="7" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:input path="unti" htmlEscape="false" maxlength="5" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增速：</label>
			<div class="controls">
				<form:input path="growthRate" htmlEscape="false" maxlength="4" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchSalaryStaff:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
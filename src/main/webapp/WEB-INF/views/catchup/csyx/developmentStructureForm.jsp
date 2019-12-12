<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业结构管理</title>
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
		<li><a href="${ctx}/csyx/developmentStructure/">产业结构列表</a></li>
		<li class="active"><a href="${ctx}/csyx/developmentStructure/form?id=${developmentStructure.id}">产业结构<shiro:hasPermission name="csyx:developmentStructure:edit">${not empty developmentStructure.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:developmentStructure:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="developmentStructure" action="${ctx}/csyx/developmentStructure/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">行业：</label>
			<div class="controls">
				<form:input path="industry" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第一产业：</label>
			<div class="controls">
				<form:input path="first" htmlEscape="false" maxlength="9" class="input-xlarge required number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第二产业：</label>
			<div class="controls">
				<form:input path="second" htmlEscape="false" maxlength="9" class="input-xlarge required number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第三产业：</label>
			<div class="controls">
				<form:input path="third" htmlEscape="false" maxlength="9" class="input-xlarge required number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${developmentStructure.month}" <c:if test="${ empty developmentStructure.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${developmentStructure.year}" <c:if test="${ empty developmentStructure.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:developmentStructure:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
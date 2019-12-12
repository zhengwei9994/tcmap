<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济创新能力管理</title>
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
		<li><a href="${ctx}/csyx/showInnovate/">经济创新能力列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showInnovate/form?id=${showInnovate.id}">经济创新能力<shiro:hasPermission name="csyx:showInnovate:edit">${not empty showInnovate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showInnovate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showInnovate" action="${ctx}/csyx/showInnovate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">专利数量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="9" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">科学技术占比：</label>
			<div class="controls">
				<form:input path="proportion" htmlEscape="false" maxlength="255" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<form:input path="year" htmlEscape="false" maxlength="255" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
<%--				<input name="year" type="text" readonly="readonly" maxlength="20"--%>
<%--					   value="${showInnovate.year}"<c:if test="${empty showInnovate.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<form:input path="month" htmlEscape="false" maxlength="255" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
<%--				<input name="year" type="text" readonly="readonly" maxlength="20"--%>
<%--					   value="${showInnovate.month}" <c:if test="${empty showInnovate.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>--%>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showInnovate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
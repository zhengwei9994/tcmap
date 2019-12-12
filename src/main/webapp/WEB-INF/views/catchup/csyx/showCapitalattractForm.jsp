<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济资本吸引力管理</title>
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
		<li><a href="${ctx}/csyx/showCapitalattract/">经济资本吸引力列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showCapitalattract/form?id=${showCapitalattract.id}">经济资本吸引力<shiro:hasPermission name="csyx:showCapitalattract:edit">${not empty showCapitalattract.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showCapitalattract:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showCapitalattract" action="${ctx}/csyx/showCapitalattract/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">从业人员：</label>
			<div class="controls">
				<form:input path="practitioners" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教育支出：</label>
			<div class="controls">
				<form:input path="education" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教育支出比重：</label>
			<div class="controls">
				<form:input path="educationProportion" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利用外资：</label>
			<div class="controls">
				<form:input path="foreign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">固定投资：</label>
			<div class="controls">
				<form:input path="investment" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${showCapitalattract.year}" <c:if test="${empty showCapitalattract.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"</c:if>/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${showCapitalattract.month}" <c:if test="${empty showCapitalattract.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowClear:false,isShowToday:false});"</c:if>/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showCapitalattract:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
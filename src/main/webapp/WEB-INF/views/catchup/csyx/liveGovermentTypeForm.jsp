<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>办理类型管理</title>
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
		<li><a href="${ctx}/csyx/liveGovermentType/">办理类型列表</a></li>
		<li class="active"><a href="${ctx}/csyx/liveGovermentType/form?id=${liveGovermentType.id}">办理类型<shiro:hasPermission name="csyx:liveGovermentType:edit">${not empty liveGovermentType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:liveGovermentType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="liveGovermentType" action="${ctx}/csyx/liveGovermentType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">办理类型：</label>
			<div class="controls">
				<%--<form:input path="type" htmlEscape="false" maxlength="50" class="input-xlarge "/>--%>
				<input name="type" type="text"  maxlength="20" class="input-xlarge"
					   value="${liveGovermentType.type}" <c:if test="${ !empty liveGovermentType.id }"> readonly="readonly"</c:if>/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数值：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="9" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:liveGovermentType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
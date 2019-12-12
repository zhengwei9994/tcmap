<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>空气质量管理</title>
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
		<li><a href="${ctx}/csyx/catchAqiparam/">空气质量列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchAqiparam/form?id=${catchAqiparam.id}">空气质量<shiro:hasPermission name="csyx:catchAqiparam:edit">${not empty catchAqiparam.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchAqiparam:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchAqiparam" action="${ctx}/csyx/catchAqiparam/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">区域名称：</label>
			<div class="controls">
				<input name="areaName" type="text"  maxlength="20" class="text"
					   value="${catchAqiparam.areaName}" <c:if test="${!empty catchAqiparam.id}">readonly="readonly"</c:if> />
				<%--<form:input path="areaName" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">AQI：</label>
			<div class="controls">
				<form:input path="aqi" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">空气质量级别：</label>
			<div class="controls">
				<form:input path="aqilevel" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排名：</label>
			<div class="controls">
				<form:input path="rank" htmlEscape="false" maxlength="4" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchAqiparam:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
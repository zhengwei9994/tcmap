<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困类型管理</title>
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
		<li><a href="${ctx}/csyx/livePovertyType/">贫困类型列表</a></li>
		<li class="active"><a href="${ctx}/csyx/livePovertyType/form?id=${livePovertyType.id}">贫困类型<shiro:hasPermission name="csyx:livePovertyType:edit">${not empty livePovertyType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:livePovertyType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="livePovertyType" action="${ctx}/csyx/livePovertyType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">地区：</label>
			<div class="controls">
				<c:if test="${empty livePovertyType.id}">
					<form:select path="area" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty livePovertyType.id}">
					<input name="area" type="text" readonly="readonly" maxlength="20" class="text" value="${livePovertyType.area}" />
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">贫困类型：</label>
			<div class="controls">
				<c:if test="${empty livePovertyType.id}">
					<form:select path="type" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('poverty_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty livePovertyType.id}">
					<input name="type" type="text" readonly="readonly" maxlength="20" class="text" value="${livePovertyType.type}" />
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="9" class="input-xlarge  digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:livePovertyType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
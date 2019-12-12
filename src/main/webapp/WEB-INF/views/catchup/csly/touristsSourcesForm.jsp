<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>游客来源地管理</title>
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
		<li><a href="${ctx}/csly/touristsSources/">游客来源地列表</a></li>
		<li class="active"><a href="${ctx}/csly/touristsSources/form?id=${touristsSources.id}">游客来源地<shiro:hasPermission name="csly:touristsSources:edit">${not empty touristsSources.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csly:touristsSources:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="touristsSources" action="${ctx}/csly/touristsSources/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">省份：</label>
			<div class="controls">
				<%--<c:if test="${empty touristsSources.id}">--%>
					<%--<form:input path="province" htmlEscape="false" maxlength="255" class="input-xlarge required"/>--%>
				<%--</c:if>--%>
					<input name="province" type="text" <c:if test="${!empty touristsSources.id}"> readonly="readonly"</c:if> maxlength="20"  class="input-xlarge required" value="${touristsSources.province}"/>

				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="255" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csly:touristsSources:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
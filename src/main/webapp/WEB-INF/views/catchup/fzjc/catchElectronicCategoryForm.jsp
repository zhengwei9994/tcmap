<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电子证照类别管理管理</title>
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
		<li><a href="${ctx}/fzjc/catchElectronicCategory/">电子证照类别管理列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchElectronicCategory/form?id=${catchElectronicCategory.id}">电子证照类别管理<shiro:hasPermission name="fzjc:catchElectronicCategory:edit">${not empty catchElectronicCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchElectronicCategory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchElectronicCategory" action="${ctx}/fzjc/catchElectronicCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用次数：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证照属性：</label>
			<div class="controls">
			    <form:select path="type" class="input-xlarge required">
			    <form:option value="1">服务类型</form:option>
			    <form:option value="2">行业</form:option>
			    </form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchElectronicCategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
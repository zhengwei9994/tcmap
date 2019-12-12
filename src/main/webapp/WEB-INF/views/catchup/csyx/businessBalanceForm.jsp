<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>金融机构本外币存款余额管理</title>
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
		<li><a href="${ctx}/csyx/businessBalance/">金融机构本外币存款余额列表</a></li>
		<li class="active"><a href="${ctx}/csyx/businessBalance/form?id=${businessBalance.id}">金融机构本外币存款余额<shiro:hasPermission name="csyx:businessBalance:edit">${not empty businessBalance.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:businessBalance:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="businessBalance" action="${ctx}/csyx/businessBalance/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">余额：</label>
			<div class="controls">
				<form:input path="balance" htmlEscape="false" maxlength="9" class="input-xlarge number required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
			<input name="month" type="text" readonly="readonly" maxlength="20"
										 value="${businessBalance.month}" <c:if test="${ empty businessBalance.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
					<input name="year" type="text" readonly="readonly" maxlength="20"
						value="${businessBalance.year}" <c:if test="${ empty businessBalance.id }"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:businessBalance:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
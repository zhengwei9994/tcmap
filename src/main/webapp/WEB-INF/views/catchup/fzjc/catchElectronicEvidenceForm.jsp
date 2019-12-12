<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电子证照使用率管理</title>
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
		<li><a href="${ctx}/fzjc/catchElectronicEvidence/">电子证照使用率列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchElectronicEvidence/form?id=${catchElectronicEvidence.id}">电子证照使用率<shiro:hasPermission name="fzjc:catchElectronicEvidence:edit">${not empty catchElectronicEvidence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchElectronicEvidence:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchElectronicEvidence" action="${ctx}/fzjc/catchElectronicEvidence/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<form:input path="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchGrowthSituation.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月份：</label>
			<div class="controls">
				<form:input path="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							value="${catchGrowthSituation.month}" onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金融(次数)：</label>
			<div class="controls">
				<form:input path="banking" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">置业(次数)：</label>
			<div class="controls">
				<form:input path="house" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教育(次数)：</label>
			<div class="controls">
				<form:input path="education" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医疗(次数)：</label>
			<div class="controls">
				<form:input path="medical" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交通(次数)：</label>
			<div class="controls">
				<form:input path="traffic" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">旅游(次数)：</label>
			<div class="controls">
				<form:input path="travel" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购物(次数)：</label>
			<div class="controls">
				<form:input path="shop" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchElectronicEvidence:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>指标数据运行状态管理</title>
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
		<li><a href="${ctx}/fzjc/catchDataStatus/">社保在线办理统计列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchDataStatus/form?id=${catchDataStatus.id}">社保在线办理统计<shiro:hasPermission name="fzjc:catchDataStatus:edit">${not empty catchDataStatus.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchDataStatus:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchDataStatus" action="${ctx}/fzjc/catchDataStatus/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月份：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在线办理(次数)：</label>
			<div class="controls">
				<form:input path="dataDirect" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">个人线下办理(次数)：</label>
			<div class="controls">
				<form:input path="dataSearch" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业线下办理(次数)：</label>
			<div class="controls">
				<form:input path="dataGov" htmlEscape="false" maxlength="9" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchDataStatus:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
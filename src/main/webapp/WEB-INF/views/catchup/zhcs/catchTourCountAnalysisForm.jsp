<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游趋势分析管理</title>
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
		<li><a href="${ctx}/zhcs/catchTourCountAnalysis/">旅游趋势分析列表</a></li>
		<li class="active"><a href="${ctx}/zhcs/catchTourCountAnalysis/form?id=${catchTourCountAnalysis.id}">旅游趋势分析<shiro:hasPermission name="zhcs:catchTourCountAnalysis:edit">${not empty catchTourCountAnalysis.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhcs:catchTourCountAnalysis:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchTourCountAnalysis" action="${ctx}/zhcs/catchTourCountAnalysis/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchTourCountAnalysis.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">日期：</label>
			<div class="controls">
				<input name="tourDay" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchTourCountAnalysis.tourDay}" onclick="WdatePicker({dateFmt:'MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">旅游人数：</label>
			<div class="controls">
				<form:input path="tourCount" htmlEscape="false" maxlength="255" class="input-xlarge number required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhcs:catchTourCountAnalysis:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>停留时长分布管理</title>
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
		<li><a href="${ctx}/zhcs/catchStayTime/">停留时长分布列表</a></li>
		<li class="active"><a href="${ctx}/zhcs/catchStayTime/form?id=${catchStayTime.id}">停留时长分布<shiro:hasPermission name="zhcs:catchStayTime:edit">${not empty catchStayTime.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhcs:catchStayTime:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchStayTime" action="${ctx}/zhcs/catchStayTime/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchStayTime.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地点：</label>
			<div class="controls">
				<form:select path="stayPlace" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:option value="酒店" label="酒店"/>
					<form:option value="景区" label="景区"/>
					<form:option value="商场" label="商场"/>
					<form:option value="商场" label="餐饮"/>
					<form:option value="商业街" label="商业街"/>
					<form:option value="其他" label="其他"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">停留时长：</label>
			<div class="controls">
				<form:input path="stayTime" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">停留人数：</label>
			<div class="controls">
				<form:input path="stayCount" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhcs:catchStayTime:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
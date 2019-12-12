<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>景区旅游数据分析管理</title>
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
		<li><a href="${ctx}/csyx/catchSceniArea/">景区旅游数据分析列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchSceniArea/form?id=${catchSceniArea.id}">景区旅游数据分析<shiro:hasPermission name="csyx:catchSceniArea:edit">${not empty catchSceniArea.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchSceniArea:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchSceniArea" action="${ctx}/csyx/catchSceniArea/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchSceniArea.nyear}" <c:if test="${empty catchSceniArea.id}">class="input-medium Wdate required"  onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">景区名称：</label>
			<div class="controls">
				<c:if test="${empty catchSceniArea.id}">
					<form:select path="scenicArea" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('scenic_area')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty catchSceniArea.id}">
					<input name="scenicArea" type="text" readonly="readonly" maxlength="20"  value="${catchSceniArea.scenicArea}"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">累计收入：</label>
			<div class="controls">
				<form:input path="totalRevenue" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">去年同比：</label>
			<div class="controls">
				<form:input path="growth" htmlEscape="false" maxlength="9" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接待人次：</label>
			<div class="controls">
				<form:input path="reception" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchSceniArea:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
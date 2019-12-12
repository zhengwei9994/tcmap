<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>舆情统计管理</title>
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
		<li><a href="${ctx}/wlkj/catchOpinionStatistics/">舆情统计列表</a></li>
		<li class="active"><a href="${ctx}/wlkj/catchOpinionStatistics/form?id=${catchOpinionStatistics.id}">舆情统计<shiro:hasPermission name="wlkj:catchOpinionStatistics:edit">${not empty catchOpinionStatistics.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="wlkj:catchOpinionStatistics:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchOpinionStatistics" action="${ctx}/wlkj/catchOpinionStatistics/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">统计类型：</label>
			<div class="controls">
				<c:if test="${empty catchOpinionStatistics.id}">
					<form:select path="statisticalType" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('statistical_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty catchOpinionStatistics.id}">
					<input name="statisticalType" type="text" readonly="readonly" maxlength="20"
						   value="${catchOpinionStatistics.statisticalType}"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">情感指数正：</label>
			<div class="controls">
				<form:input path="indexPositive" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">情感指数中：</label>
			<div class="controls">
				<form:input path="indexThe" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">情感指数负：</label>
			<div class="controls">
				<form:input path="indexNegative" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总量网民：</label>
			<div class="controls">
				<form:input path="totalNetizen" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总量媒体：</label>
			<div class="controls">
				<form:input path="totalMedia" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="wlkj:catchOpinionStatistics:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
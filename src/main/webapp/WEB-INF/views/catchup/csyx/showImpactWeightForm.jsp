<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>影响权重管理</title>
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
		<li><a href="${ctx}/csyx/showImpactWeight/">影响权重列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showImpactWeight/form?id=${showImpactWeight.id}">影响权重<shiro:hasPermission name="csyx:showImpactWeight:edit">${not empty showImpactWeight.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showImpactWeight:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showImpactWeight" action="${ctx}/csyx/showImpactWeight/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${showImpactWeight.year}"<c:if test="${empty showImpactWeight.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行业：</label>
			<div class="controls">
				<c:choose>
					<c:when test ="${  empty showTotalPopulation.id }">
						<form:select path="industry" class="input-xlarge ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</c:when>
					<c:when test ="${ ! empty showTotalPopulation.id }">
						<form:select path="industry" class="input-xlarge " disabled="disabled">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人口老龄化权重：</label>
			<div class="controls">
				<form:input path="populationRate" htmlEscape="false" maxlength="9" required="true" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GDP影响比重：</label>
			<div class="controls">
				<form:input path="gdpRate" htmlEscape="false" maxlength="9" required="true" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showImpactWeight:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
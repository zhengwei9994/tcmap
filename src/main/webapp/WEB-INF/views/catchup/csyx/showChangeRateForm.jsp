<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变化率管理</title>
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
		<li><a href="${ctx}/csyx/showChangeRate/">变化率列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showChangeRate/form?id=${showChangeRate.id}">变化率<shiro:hasPermission name="csyx:showChangeRate:edit">${not empty showChangeRate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showChangeRate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showChangeRate" action="${ctx}/csyx/showChangeRate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<c:choose>
				<c:when test ="${ ! empty showChangeRate.id }">
					<input name="year" type="text" disabled="disabled" maxlength="20" class="input-medium Wdate required"
						   value="${showChangeRate.year}" onclick="WdatePicker({dateFmt:'yyyy',readOnly:true,isShowClear:false,isShowToday:false});"/>
				</c:when>
					<c:when test ="${ empty showChangeRate.id }">
						<input name="year" type="text"  maxlength="20" class="input-medium Wdate required"
							   value="${showChangeRate.year}" onclick="WdatePicker({dateFmt:'yyyy',readOnly:true,isShowClear:false,isShowToday:false});"/>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GDP增长率：</label>
			<div class="controls">
				<form:input path="gdpGrowth" htmlEscape="false" maxlength="6" class="input-xlarge required  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投资增长率：</label>
			<div class="controls">
				<form:input path="investmentGrowthRate" htmlEscape="false"  maxlength="6" class="input-xlarge  required number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">储蓄率：</label>
			<div class="controls">
				<form:input path="saveRate" htmlEscape="false" maxlength="6"  class="input-xlarge required  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消费支出增加率：</label>
			<div class="controls">
				<form:input path="consumptionExpenditureRate" htmlEscape="false" maxlength="6" class="input-xlarge   required number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showChangeRate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
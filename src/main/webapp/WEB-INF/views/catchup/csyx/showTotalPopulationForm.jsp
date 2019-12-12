<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人口总量管理</title>
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
		<li><a href="${ctx}/csyx/showTotalPopulation/">人口总量列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showTotalPopulation/form?id=${showTotalPopulation.id}">人口总量<shiro:hasPermission name="csyx:showTotalPopulation:edit">${not empty showTotalPopulation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showTotalPopulation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showTotalPopulation" action="${ctx}/csyx/showTotalPopulation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<c:choose>
					<c:when test ="${ ! empty showTotalPopulation.id }">
						<input name="year" type="text" disabled="disabled" maxlength="20" class="input-medium Wdate required"
							   value="${showTotalPopulation.year}" onclick="WdatePicker({dateFmt:'yyyy',readOnly:true,isShowClear:false,isShowToday:false});"/>
					</c:when>
					<c:when test ="${ empty showTotalPopulation.id }">
						<input name="year" type="text"  maxlength="20" class="input-medium Wdate required"
							   value="${showTotalPopulation.year}" onclick="WdatePicker({dateFmt:'yyyy',readOnly:true,isShowClear:false,isShowToday:false});"/>
					</c:when>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总量：</label>
			<div class="controls">
				<form:input path="totalNum" htmlEscape="false" maxlength="9" class="input-xlarge required number"/>万人
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">老年人口数量：</label>
			<div class="controls">
				<form:input path="oldNum" htmlEscape="false" maxlength="9" class="input-xlarge required  number"/>万人
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">劳动人口占比：</label>
			<div class="controls">
				<form:input path="laborPercent" htmlEscape="false" maxlength="9" class="input-xlarge required number"/>%
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总人口抚养比：</label>
			<div class="controls">
				<form:input path="raisePercent" htmlEscape="false" maxlength="9" class="input-xlarge  required number"/>%
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showTotalPopulation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
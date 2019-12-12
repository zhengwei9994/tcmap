<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济三产占比管理</title>
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
		<li><a href="${ctx}/csyx/showCapitalattractIndustry/">经济三产占比列表</a></li>
		<li class="active"><a href="${ctx}/csyx/showCapitalattractIndustry/form?id=${showCapitalattractIndustry.id}">经济三产占比<shiro:hasPermission name="csyx:showCapitalattractIndustry:edit">${not empty showCapitalattractIndustry.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:showCapitalattractIndustry:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="showCapitalattractIndustry" action="${ctx}/csyx/showCapitalattractIndustry/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产业：</label>
			<div class="controls">
				<form:select path="industry" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('index_status_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数值：</label>
			<div class="controls">
				<form:input path="proportion" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="year" type="text" readonly="readonly" maxlength="20"
					   value="${showCapitalattractIndustry.year}" <c:if test="${empty showCapitalattractIndustry.id}"> class="input-medium Wdate required"  onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月：</label>
			<div class="controls">
				<input name="month" type="text" readonly="readonly" maxlength="20"
					   value="${showCapitalattractIndustry.month}" <c:if test="${empty showCapitalattractIndustry.id}"> class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'MM',isShowToday:false,isShowClear:false});"</c:if>/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:showCapitalattractIndustry:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
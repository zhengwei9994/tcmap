<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行政审批事项统计管理</title>
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
		<li><a href="${ctx}/fzjc/catchApprovalMatters/">行政审批事项统计列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchApprovalMatters/form?id=${catchApprovalMatters.id}">行政审批事项统计<shiro:hasPermission name="fzjc:catchApprovalMatters:edit">${not empty catchApprovalMatters.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchApprovalMatters:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchApprovalMatters" action="${ctx}/fzjc/catchApprovalMatters/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<%--<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
					   <%--value="${catchApprovalMatters.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>--%>
				<input name="nyear" type="text" readonly="readonly" maxlength="20"
					   value="${catchApprovalMatters.nyear}" <c:if test="${ empty catchApprovalMatters.id }"> class="input-medium Wdate required"
					onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"</c:if>/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">部门：</label>
			<div class="controls">
				<c:if test="${ empty catchApprovalMatters.id }">
					<form:select path="department" class="input-xlarge required" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('municipal_department')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${ !empty catchApprovalMatters.id }">
					<input name ="department" type="text" readonly="readonly" maxlength="20" value="${catchApprovalMatters.department}">
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批事项数目：</label>
			<div class="controls">
				<form:input path="mattersCount" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchApprovalMatters:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安全概况管理</title>
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
		<li><a href="${ctx}/safecity/safetyProfile/">安全概况列表</a></li>
		<li class="active"><a href="${ctx}/safecity/safetyProfile/form?id=${safetyProfile.id}">安全概况<shiro:hasPermission name="safecity:safetyProfile:edit">${not empty safetyProfile.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="safecity:safetyProfile:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="safetyProfile" action="${ctx}/safecity/safetyProfile/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">平安城市：</label>
			<div class="controls">
				<form:select path="safeCityId" class="required input-xlarge">
					<form:option value="" label=""/>
					<form:options items="${safeCityList}" itemLabel="area" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公共安全指数：</label>
			<div class="controls">
				<form:input path="safetyIndex" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">药品合格率：</label>
			<div class="controls">
				<form:input path="drugRate" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">食品合格率：</label>
			<div class="controls">
				<form:input path="foodRate" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">刑事案件破案率：</label>
			<div class="controls">
				<form:input path="crimelSolveRate" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">刑事案件案发率：</label>
			<div class="controls">
				<form:input path="crimeRate" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">民事案件发案率：</label>
			<div class="controls">
				<form:input path="civilRate" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交通违法案件数量：</label>
			<div class="controls">
				<form:input path="trafficNum" htmlEscape="false" maxlength="10" class="input-xlarge digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交通违法同比：</label>
			<div class="controls">
				<form:input path="yearRate" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交通违法环比：</label>
			<div class="controls">
				<form:input path="chainRate" htmlEscape="false" maxlength="10" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">火灾案件数量：</label>
			<div class="controls">
				<form:input path="fireNum" htmlEscape="false" maxlength="10" class="input-xlarge digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消防车数量：</label>
			<div class="controls">
				<form:input path="fireTruckNum" htmlEscape="false" maxlength="10" class="input-xlarge digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消防队伍：</label>
			<div class="controls">
				<form:input path="fireBrigade" htmlEscape="false" maxlength="10" class="input-xlarge digits"/>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">统计日期：</label>--%>
<%--			<div class="controls">--%>
<%--				<input name="statisticDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
<%--					value="<fmt:formatDate value="${safetyProfile.statisticDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="safecity:safetyProfile:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
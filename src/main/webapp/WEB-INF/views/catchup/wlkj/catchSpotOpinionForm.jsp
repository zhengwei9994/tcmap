<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全国舆情热点管理</title>
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
		<li><a href="${ctx}/wlkj/catchSpotOpinion/">全国舆情热点列表</a></li>
		<li class="active"><a href="${ctx}/wlkj/catchSpotOpinion/form?id=${catchSpotOpinion.id}">全国舆情热点<shiro:hasPermission name="wlkj:catchSpotOpinion:edit">${not empty catchSpotOpinion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="wlkj:catchSpotOpinion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchSpotOpinion" action="${ctx}/wlkj/catchSpotOpinion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">全国热点类型：</label>
			<div class="controls">
				<c:if test="${empty catchSpotOpinion.id}">
					<form:select path="chinaType" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('china_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty catchSpotOpinion.id}">
					<input name="chinaType" type="text" readonly="readonly" maxlength="20" class="text" value="${catchSpotOpinion.chinaType}"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">热点数量：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="9" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">录入日期：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${catchSpotOpinion.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="wlkj:catchSpotOpinion:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>舆情来源管理</title>
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
		<li><a href="${ctx}/wlkj/catchSourceOpinion/">舆情来源列表</a></li>
		<li class="active"><a href="${ctx}/wlkj/catchSourceOpinion/form?id=${catchSourceOpinion.id}">舆情来源<shiro:hasPermission name="wlkj:catchSourceOpinion:edit">${not empty catchSourceOpinion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="wlkj:catchSourceOpinion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchSourceOpinion" action="${ctx}/wlkj/catchSourceOpinion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">舆情来源类型：</label>
			<div class="controls">
				<c:if test="${empty catchSourceOpinion.id}">
					<form:select path="sourceType" class="input-xlarge ">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('source_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty catchSourceOpinion.id}">
					<input name="sourceType" type="text" readonly="readonly" maxlength="20"
						   value="${catchSourceOpinion.sourceType}"/>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">舆情来源流量：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="8" class="input-xlarge  number"/><span> KB</span>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">录入日期：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${catchSourceOpinion.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="wlkj:catchSourceOpinion:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
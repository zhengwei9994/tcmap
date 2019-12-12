<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单一景区资讯管理</title>
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
		<li><a href="${ctx}/csly/touristInformationOnly/">单一景区资讯列表</a></li>
		<li class="active"><a href="${ctx}/csly/touristInformationOnly/form?id=${touristInformationOnly.id}">单一景区资讯<shiro:hasPermission name="csly:touristInformationOnly:edit">${not empty touristInformationOnly.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csly:touristInformationOnly:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="touristInformationOnly" action="${ctx}/csly/touristInformationOnly/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">景区资讯排行：</label>
			<div class="controls">
				<form:input path="pid" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地区：</label>
			<div class="controls">
				<form:select path="area" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传播力：</label>
			<div class="controls">
				<form:input path="communication" htmlEscape="false" maxlength="255" class="input-xlarge required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传播总量：</label>
			<div class="controls">
				<form:input path="total" htmlEscape="false" maxlength="255" class="input-xlarge required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转发次数：</label>
			<div class="controls">
				<form:input path="forward" htmlEscape="false" maxlength="255" class="input-xlarge required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly"  class="input-medium Wdate "
					value="${touristInformationOnly.date}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csly:touristInformationOnly:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
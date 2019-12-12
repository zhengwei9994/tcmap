<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>接口管理</title>
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
		<li><a href="${ctx}/ddgl/tcApiManager/">接口列表</a></li>
		<li class="active"><a href="${ctx}/ddgl/tcApiManager/form?id=${tcApiManager.id}">接口<shiro:hasPermission name="ddgl:tcApiManager:edit">${not empty tcApiManager.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ddgl:tcApiManager:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tcApiManager" action="${ctx}/ddgl/tcApiManager/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">接口名称：</label>
			<div class="controls">
				<form:input path="apiName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接口地址：</label>
			<div class="controls">
				<form:input path="apiUrl" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请求参数：</label>
			<div class="controls">
				<form:input path="requestParam" htmlEscape="false" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">返回参数：</label>
			<div class="controls">
				<form:input path="returnParam" htmlEscape="false" maxlength="255" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接口类型：</label>
			<div class="controls">
				<form:select path="apiType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('api_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请求类型：</label>
			<div class="controls">
				<form:select path="requestType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('request_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接口状态：</label>
			<div class="controls">
				<form:radiobuttons path="apiStatus" items="${fns:getDictList('api_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布时间：</label>
			<div class="controls">
				<input name="releaseTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tcApiManager.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">系统域名：</label>
            <div class="controls">
<%--                <form:input path="sysDomain" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
                <sys:treeselect id="tcSyserviceMananger" name="tcSyserviceMananger.id" value="${tcApiManager.tcSyserviceMananger.id}" labelName="tcSyserviceMananger.sysName" labelValue="${tcApiManager.sysDomain}"
                                title="域名" url="/ddgl/tcApiManager/treeData" cssClass="required" />
<%--                <sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"--%>
<%--                                title="区域" url="/sys/area/treeData" cssClass="required"/>--%>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">接口描述：</label>
			<div class="controls">
				<form:textarea path="apiDes" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ddgl:tcApiManager:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
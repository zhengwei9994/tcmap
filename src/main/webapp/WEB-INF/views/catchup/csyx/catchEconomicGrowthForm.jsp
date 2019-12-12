<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域经济增长管理</title>
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
		<li><a href="${ctx}/csyx/catchEconomicGrowth/">区域经济增长列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchEconomicGrowth/form?id=${catchEconomicGrowth.id}">区域经济增长<shiro:hasPermission name="csyx:catchEconomicGrowth:edit">${not empty catchEconomicGrowth.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchEconomicGrowth:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchEconomicGrowth" action="${ctx}/csyx/catchEconomicGrowth/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">经济指标：</label>
			<div class="controls"> 
			<form:select path="indexId" class="input-medium">
					<form:options items="${fns:getEcomicList()}" itemLabel="name"  itemValue="id" htmlEscape="false"/> 
				</form:select>
			</div> 
		</div>
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<form:input path="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					  onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false,isShowToday:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域：</label>
			<div class="controls">
				<form:select path="areaName" class="input-xlarge required">
                <form:options items="${fns:getDictList('area_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">额度：</label>
			<div class="controls">
				<form:input path="indicators" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:select path="indicatorsUnit" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">占比：</label>
			<div class="controls">
				<form:input path="rate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchEconomicGrowth:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
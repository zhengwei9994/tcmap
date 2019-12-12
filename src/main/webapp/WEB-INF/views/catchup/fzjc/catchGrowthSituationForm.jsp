<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>运行指标完成率管理</title>
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
		<li><a href="${ctx}/fzjc/catchGrowthSituation/">区县经济增长情况列表</a></li>
		<li class="active"><a href="${ctx}/fzjc/catchGrowthSituation/form?id=${catchGrowthSituation.id}">区县经济增长情况<shiro:hasPermission name="fzjc:catchGrowthSituation:edit">${not empty catchGrowthSituation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="fzjc:catchGrowthSituation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchGrowthSituation" action="${ctx}/fzjc/catchGrowthSituation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年份：</label>
			<div class="controls">
				<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${catchGrowthSituation.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="indexType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('economic_growth_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月份：</label>
			<div class="controls">
				<form:select path="cmonth" class="input-xlarge required">
					<form:option value="一月份" label="一月份"/>
					<form:option value="二月份" label="二月份"/>
					<form:option value="三月份" label="三月份"/>
					<form:option value="四月份" label="四月份"/>
					<form:option value="五月份" label="五月份"/>
					<form:option value="六月份" label="六月份"/>
					<form:option value="七月份" label="七月份"/>
					<form:option value="八月份" label="八月份"/>
					<form:option value="九月份" label="九月份"/>
					<form:option value="十月份" label="十月份"/>
					<form:option value="十一月份" label="十一月份"/>
					<form:option value="十二月份" label="十二月份"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增长值：</label>
			<div class="controls">
				<form:input path="indexValue" htmlEscape="false" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="fzjc:catchGrowthSituation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
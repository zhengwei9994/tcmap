<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点项目建设管理</title>
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
		<li><a href="${ctx}/csyx/catchProjectConstruction/">重点项目建设列表</a></li>
		<li class="active"><a href="${ctx}/csyx/catchProjectConstruction/form?id=${catchProjectConstruction.id}">重点项目建设<shiro:hasPermission name="csyx:catchProjectConstruction:edit">${not empty catchProjectConstruction.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csyx:catchProjectConstruction:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="catchProjectConstruction" action="${ctx}/csyx/catchProjectConstruction/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目单位：</label>
			<div class="controls">
				<form:input path="hostUnit" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${catchProjectConstruction.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目金额：</label>
			<div class="controls">
				<form:input path="annualTask" htmlEscape="false" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投资金额：</label>
			<div class="controls">
				<form:input path="plannedInvestment" htmlEscape="false" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进度：</label>
			<div class="controls">
				<form:input path="progress" htmlEscape="false" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:input path="projectIntro" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:input path="leadership" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人头像路径：</label>
				<%-- 	<div class="controls">
                        <form:input path="imagePath" htmlEscape="false" maxlength="255" class="input-xlarge "/>
                    </div> --%>
			<div class="controls">
				<form:hidden id="leaderImagePath" path="leaderImagePath" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<sys:ckfinder input="leaderImagePath" type="images" uploadPath="/csyx/catchProjectConstruction" selectMultiple="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片路径：</label>
		<%-- 	<div class="controls">
				<form:input path="imagePath" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div> --%>	
			<div class="controls">
				<form:hidden id="imagePath" path="imagePath" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<sys:ckfinder input="imagePath" type="images" uploadPath="/csyx/catchProjectConstruction" selectMultiple="false"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csyx:catchProjectConstruction:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
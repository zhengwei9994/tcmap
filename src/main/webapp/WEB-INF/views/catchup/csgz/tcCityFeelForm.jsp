<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域感知数据管理</title>
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
		<li><a href="${ctx}/csgz/tcCityFeel/">区域感知数据列表</a></li>
		<li class="active"><a href="${ctx}/csgz/tcCityFeel/form?id=${tcCityFeel.id}">区域感知数据<shiro:hasPermission name="csgz:tcCityFeel:edit">${not empty tcCityFeel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="csgz:tcCityFeel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tcCityFeel" action="${ctx}/csgz/tcCityFeel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">重点区域：</label>
			<div class="controls">
 				<sys:treeselect id="focusArea" name="focusArea" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重点出入口交通：</label>
			<div class="controls">
				<form:input path="keyEntranceTraffic" htmlEscape="false" maxlength="6" class="input-xlarge number "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">治安卡口：</label>
			<div class="controls">
				<form:input path="securityCheckpoint" htmlEscape="false" maxlength="6" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域覆盖：</label>
			<div class="controls">
				<form:input path="focusAreaCover" htmlEscape="false" maxlength="6" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高清摄像机占比：</label>
			<div class="controls">
				<form:input path="proportionHdCameras" htmlEscape="false" maxlength="6" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">摄像机完好率：</label>
			<div class="controls">
				<form:input path="cameraIntactRate" htmlEscape="false" maxlength="6" class="input-xlarge number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="csgz:tcCityFeel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
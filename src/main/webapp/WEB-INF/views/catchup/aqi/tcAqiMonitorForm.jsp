<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>气象数据管理</title>
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
		<li><a href="${ctx}/aqi/tcAqiMonitor/">气象数据列表</a></li>
		<li class="active"><a href="${ctx}/aqi/tcAqiMonitor/form?id=${tcAqiMonitor.id}">气象数据<shiro:hasPermission name="aqi:tcAqiMonitor:edit">${not empty tcAqiMonitor.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="aqi:tcAqiMonitor:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tcAqiMonitor" action="${ctx}/aqi/tcAqiMonitor/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
<%--		<div class="control-group">--%>
<%--			<label class="control-label">城市：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="city" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="control-group">
			<label class="control-label">空气质量指数：</label>
			<div class="controls">
				<form:input path="aqi" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风力：</label>
			<div class="controls">
				<form:input path="fl" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风向：</label>
			<div class="controls">
				<form:input path="fx" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最高温：</label>
			<div class="controls">
				<form:input path="highTemprature" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最低温：</label>
			<div class="controls">
				<form:input path="lowTemprature" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温馨提示：</label>
			<div class="controls">
				<form:input path="notice" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">日出：</label>--%>
<%--			<div class="controls">--%>
<%--				<input name="sunRise" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
<%--					value="<fmt:formatDate value="${tcAqiMonitor.sunRise}" pattern="mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'mm:ss',isShowClear:false});"/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">日落：</label>--%>
<%--			<div class="controls">--%>
<%--				<input name="sunSet" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
<%--					value="<fmt:formatDate value="${tcAqiMonitor.sunSet}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">星期：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="week" htmlEscape="false" maxlength="4" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="control-group">
			<label class="control-label">天气：</label>
			<div class="controls">
				<form:input path="weather" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">数据更新时间：</label>--%>
<%--			<div class="controls">--%>
<%--				<input name="updateTime" type="text"  maxlength="20" class="input-medium Wdate "--%>
<%--					value="<fmt:formatDate value="${tcAqiMonitor.updateTime}" pattern="mm:ss"/>"--%>
<%--					onclick="WdatePicker({dateFmt:'mm:ss',isShowClear:false});"/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">数据存入时间：</label>--%>
<%--			<div class="controls">--%>
<%--				<input name="createTime"   readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
<%--					value="<fmt:formatDate value="${tcAqiMonitor.createTime}" pattern="yyyy-MM-dd "/>"--%>
<%--					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false});"/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="control-group">
			<label class="control-label">PM25：</label>
			<div class="controls">
				<form:input path="pm25" htmlEscape="false" maxlength="8" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">PM10：</label>
			<div class="controls">
				<form:input path="pm10" htmlEscape="false"  maxlength="8" class="input-xlarge number "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">气象数据日期：</label>
			<div class="controls">
				<input name="ymd" type="text"  maxlength="20"
					   value="${tcAqiMonitor.ymd}" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowToday:false,isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="aqi:tcAqiMonitor:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
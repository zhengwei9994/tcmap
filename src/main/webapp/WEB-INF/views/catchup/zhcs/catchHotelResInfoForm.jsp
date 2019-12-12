<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游相关管理</title>
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
	<li><a href="${ctx}/zhcs/catchHotelResInfo/">酒店资源信息列表</a></li>
	<li class="active"><a href="${ctx}/zhcs/catchHotelResInfo/form?id=${catchHotelResInfo.id}">酒店资源信息<shiro:hasPermission name="zhcs:catchHotelResInfo:edit">${not empty catchHotelResInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhcs:catchHotelResInfo:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="catchHotelResInfo" action="${ctx}/zhcs/catchHotelResInfo/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">年份：</label>
		<div class="controls">
			<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
				   value="${catchHotelResInfo.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">类型：</label>
		<div class="controls">
			<form:select path="hotelType" class="input-medium">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('hotel_res_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">级别：</label>
		<div class="controls">
			<form:select path="hotelLevel" class="input-xlarge required">
				<form:option value="" label=""/>
				<form:option value="五星级" label="五星级"/>
				<form:option value="四星级" label="四星级"/>
				<form:option value="三星级" label="三星级"/>
				<form:option value="二星级" label="二星级"/>
				<form:option value="一星级" label="一星级"/>
			</form:select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">酒店数量：</label>
		<div class="controls">
			<form:input path="hotelCount" htmlEscape="false" class="input-xlarge required number"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">入住数量：</label>
		<div class="controls">
			<form:input path="hotelCheckCount" htmlEscape="false" class="input-xlarge required number"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">排名前三：</label>
		<div class="controls">
			<form:input path="hotelTopNames" htmlEscape="false" class="input-xlarge"/>
			<span class="help-inline">请用&号分隔</span>
		</div>
	</div>
	<div class="form-actions">
		<shiro:hasPermission name="zhcs:catchHotelResInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>旅游相关管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/zhcs/catchHotelResInfo/">酒店资源信息列表</a></li>
	<shiro:hasPermission name="zhcs:catchHotelResInfo:edit"><li><a href="${ctx}/zhcs/catchHotelResInfo/form">酒店资源信息添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="catchHotelResInfo" action="${ctx}/zhcs/catchHotelResInfo/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
		<li><label>年份：</label>
			<input name="nyear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
				   value="${catchHotelResInfo.nyear}" onclick="WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});"/>
		</li>
		<li><label>类别：</label>
			<form:select path="hotelType" class="input-medium">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('hotel_res_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>年份</th>
		<th>类型</th>
		<th>级别</th>
		<th>酒店数</th>
		<th>入住数</th>
		<th>排名前三</th>
		<shiro:hasPermission name="zhcs:catchHotelResInfo:edit"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="catchHotelResInfo">
		<tr>
			<td>
				<a href="${ctx}/zhcs/catchHotelResInfo/form?id=${catchHotelResInfo.id}">
					${catchHotelResInfo.nyear}
				</a>
			</td>
			<td>
					${catchHotelResInfo.hotelType}
			</td>
			<td>
					${catchHotelResInfo.hotelLevel}
			</td>
			<td>
					${catchHotelResInfo.hotelCount}
			</td>
			<td>
					${catchHotelResInfo.hotelCheckCount}
			</td>
			<td>
					${catchHotelResInfo.hotelTopNames}
			</td>
			<shiro:hasPermission name="zhcs:catchHotelResInfo:edit"><td>
				<a href="${ctx}/zhcs/catchHotelResInfo/form?id=${catchHotelResInfo.id}">修改</a>
				<a href="${ctx}/zhcs/catchHotelResInfo/delete?id=${catchHotelResInfo.id}" onclick="return confirmx('确认要删除该旅游信息吗？', this.href)">删除</a>
			</td></shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>aqi实时数据</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
        function getaqi() {
			var city="";
			var aqi="";
			var fl="";
			var fx="";
			var high="";
			var low="";
			var notice="";
			var sunrise="";
			var sunset="";
			var type="";
			var week="";
			var date="";
			var updateTime="";
            $.ajax({
                type:"get",
                url:"${ctx}/aqi/getaqi",
                dataType : "json",
                success: function(data) {
					city=data.cityInfo.city;
					aqi=data.data.yesterday.aqi;
					fl=data.data.yesterday.fl;
					 fx=data.data.yesterday.fx;
					 high=data.data.yesterday.high;
					 low=data.data.yesterday.low;
					 notice=data.data.yesterday.notice;
					 sunrise=data.data.yesterday.sunrise;
					 sunset=data.data.yesterday.sunset;
					 type=data.data.yesterday.type;
					 week=data.data.yesterday.week;
					 date=data.data.yesterday.ymd;
					updateTime=data.cityInfo.updateTime;
                     $("#cityair").html(
							 "<td>"+city+"</td>"+
							 "<td>"+aqi+"</td>"+
							 "<td>"+fl+"</td>"+
							 "<td>"+fx+"</td>"+
							 "<td>"+high+"</td>"+
							 "<td>"+low+"</td>"+
							 "<td>"+notice+"</td>"+
							 "<td>"+sunrise+"</td>"+
							 "<td>"+sunset+"</td>"+
							 "<td>"+type+"</td>"+
							 "<td>"+week+"</td>"+
							 "<td>"+date+"</td>"+
							 "<td>"+updateTime+"</td>"
					 );
                    console.log(data);
                },
                error:function (e) {
                    console.log(e),
                    alert("请求失败"),
                    alert();
                }
            });
        };
	</script>
</head>
<body>
		<ul class="ul-form">
            <shiro:hasPermission name="aqi:view"><input class="btn btn-primary" onclick="getaqi()" type="button" value="查询"/></shiro:hasPermission>
		</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>城市</th>
				<th>aqi</th>
				<th>风力</th>
				<th>风向</th>
				<th>最高温</th>
				<th>最低温</th>
				<th>温馨提示</th>
				<th>日出时间</th>
				<th>日落时间</th>
				<th>天气</th>
				<th>星期</th>
				<th>日期</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody id="cityair">
<%--		<c:forEach items="${model.air}" var="testCode">--%>
<%--			<tr>--%>
<%--				<td><a href="${ctx}/testcode/testCode/form?id=${testCode.id}">--%>
<%--					${testCode.name}--%>
<%--				</a></td>--%>

<%--			</tr>--%>
<%--		</c:forEach>--%>
		</tbody>
	</table>
</body>
</html>
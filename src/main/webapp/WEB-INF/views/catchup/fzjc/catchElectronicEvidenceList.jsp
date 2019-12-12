<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电子证照使用率管理</title>
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
		<li class="active"><a href="${ctx}/fzjc/catchElectronicEvidence/">电子证照使用率列表</a></li>
		<shiro:hasPermission name="fzjc:catchElectronicEvidence:edit"><li><a href="${ctx}/fzjc/catchElectronicEvidence/form">电子证照使用率添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="catchElectronicEvidence" action="${ctx}/fzjc/catchElectronicEvidence/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<form:input path="nyear" htmlEscape="false" maxlength="10" class="input-medium"/>
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
				<th>月份</th>
				<th>金融(次数)</th>
				<th>置业(次数)</th>
				<th>教育(次数)</th>
				<th>医疗(次数)</th>
				<th>交通(次数)</th>
				<th>旅游(次数)</th>
				<th>购物(次数)</th>
				<shiro:hasPermission name="fzjc:catchElectronicEvidence:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="catchElectronicEvidence">
			<tr>
				<td><a href="${ctx}/fzjc/catchElectronicEvidence/form?id=${catchElectronicEvidence.id}">
					${catchElectronicEvidence.nyear}
				</a></td>
				<td>
						${catchElectronicEvidence.month}
				</td>
				<td>
					${catchElectronicEvidence.banking}
				</td>
				<td>
					${catchElectronicEvidence.house}
				</td>
				<td>
					${catchElectronicEvidence.education}
				</td>
				<td>
					${catchElectronicEvidence.medical}
				</td>
				<td>
					${catchElectronicEvidence.traffic}
				</td>
				<td>
					${catchElectronicEvidence.travel}
				</td>
				<td>
					${catchElectronicEvidence.shop}
				</td>
				<shiro:hasPermission name="fzjc:catchElectronicEvidence:edit"><td>
    				<a href="${ctx}/fzjc/catchElectronicEvidence/form?id=${catchElectronicEvidence.id}">修改</a>
					<a href="${ctx}/fzjc/catchElectronicEvidence/delete?id=${catchElectronicEvidence.id}" onclick="return confirmx('确认要删除该电子证照使用率吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
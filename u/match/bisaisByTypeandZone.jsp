<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--
#match-list-box {
	height: 300px;
}

#match-list-table-box{
	width:100%;
	text-align: center;
}
 
#match-list-table-box thead th {
	border:1px solid gray;
	background-color: #E6E6E6;
	height: 30px;
}

#match-list-table-box tbody td {
	border:1px solid gray;
	height: 30px;
}
 
-->
</style>

<div id="match-list-box">
<table id="match-list-table-box" width="100%">
	<thead>
		<tr>
			<th>比赛名称</th>
			<th>创建者</th>
			<th>创建时间</th>
			<th>战区</th>
			<c:if test='${ACTOR.username eq "eg_admin"}'>
			<th>操作</th>
			</c:if>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="bisai" items="${bisais}">
		<tr>
			<td>
				<a href="http://www.51bisai.com/u/<c:out value="${bisai.id}" />/bisaiPortal/bisai.jsplayout.vi">
					<c:out value="${bisai.title}" />
				</a>
			</td>
			<td>
				<c:out value="${bisai.ownerUser.dispName}" />
			</td>
			<td>
				<c:out value="${bisai.createDate }" />
			</td>
			<td>
				<c:out value="${bisai.warZone.name }" />
			</td>
			<c:if test='${ACTOR.username eq "eg_admin"}'>
			<td>
				<c:if test="${bisai.owner eq ACTOR.username || 'eg_admin' eq ACTOR.username}">
					<a href="javascript:" onclick="Bisai_bisaisByTypeandZone_Page.del('<c:out value="${bisai.id }" />')">
						删除比赛
					</a>
				</c:if>
			</td>
			</c:if>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>

<script type="text/javascript">

var Bisai_bisaisByTypeandZone_Page = {
	warzoneid : '<c:out value="${warzoneid}" />',
	del: function(id) {
		Bisai.del(id);
		var url ='../match/bisaisByTypeandZone.jsp.vi?matchtypeid=' + Bisai_bisaisByType_Page.warzoneid + 'warzoneid='+ Bisai_bisaisByTypeandZone_Page.warzoneid;
		$("#tabs-match-box").load(url);
	},
};

</script>

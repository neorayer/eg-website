<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">

</style>



<c:if test="${not empty me}">
	<div id="team-bbses-top-box">
		<a class="button" href="javascript:" onclick="Team_Bbses_Page.vi_bbs_add();">发表新贴</a>
	</div>
</c:if>

<table id="team-bbses-list">
	<thead>
		<tr>
			<th >主题</th>
			<th width="120px">作者</th>
			<th width="120px">发布时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach  var="bbs" items="${bbses}">
		<tr>
			<td class="team-bbs-title-list">
				<a href="javascript:" onclick="<c:out value='Team_Bbses_Page.vi_bbs("${bbs.uuid }");'/>"><c:out value='${bbs.title}'/></a>
			</td>
			<td>
				<a href="javascript:"><c:out value='${bbs.authingUser.nickname}'/></a>
			</td>
			<td>
				<c:out value='${bbs.createDateTime}'/>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>

<script type="text/javascript">
Team_Bbses_Page = {
	vi_bbs_add: function() {
		Team_Coll_Page.load('../team/bbs.addOrMod.jsp.vi');
	},
	
	vi_bbs: function(uuid) {
		Team_Coll_Page.load('../team/bbs.jsp.vi?teamid=' + Team.id + '&uuid=' + uuid);
	}
}

$(document).ready(function() {
	// 分页
	$('#page-team-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href');
		url += "&teamid=" + Team.id;
		Team_Coll_Page.load(url);		
		return false;
	});
});

</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--

-->
</style>

<table id="manage-bisai-list-table-box" width="100%">
	<thead>
		<tr>
			<th>比赛名称</th>
			<th>创建者</th>
			<th>创建时间</th>
			<th>战区</th>
			<th>首页是否可见</th>
			<th>操作</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="bisai" items="${bisais}">
			<tr>
				<td>
					<c:out value="${bisai.title }" />
				</td>
				<td>
					<c:out value="${bisai.owner }" />
				</td>
				<td>
					<c:out value="${bisai.createDate }" />
				</td>
				<td>
					<c:out value="${bisai.warZone.name }" />
				</td>
				<td>
						<c:if test="${!bisai.viewed}">
							<a href="javascript:" onclick="Bisai_manage_Page.setFirst('<c:out value="${bisai.id }" />','true')">
								设置为首页
							</a>
						</c:if>
						<c:if test="${bisai.viewed}">
							<a href="javascript:" onclick="Bisai_manage_Page.setFirst('<c:out value="${bisai.id }" />','false')">
								取消首页
							</a>
						</c:if>

				</td>
				<td>
					<c:if test="${bisai.owner eq ACTOR.username || 'eg_admin' eq ACTOR.username}">
						<a href="javascript:" onclick="Bisai_manage_Page.del('<c:out value="${bisai.id }" />')">
							删除比赛
						</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div id="page-bisai-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>

<script type="text/javascript">


(function($){
	// 分页
	$('#page-bisai-pagebar-box a')
		.click(function() {
			var url = $(this).attr('href');
			url += "&" + $('#manage-search-form').formSerialize();
			$('#manage-bisai-list').load(url);		
			return false;
		});
	
})(jQuery);

</script>
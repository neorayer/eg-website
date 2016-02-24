<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
#team-bbs-replys-box {
	margin-top: 20px;
}

#team-bbs-reply-form h3{
	margin: 20px 0px 10px 0px;
	width: 500px;
	border-bottom: 1px solid gray;
}

</style>

<div id="team-bbs-top-box">
	<div style="text-align: right; padding-right: 10px;">
		<c:if test="${ACTOR.username == bbs.author}">
			<a href="javascript:" onclick="<c:out value='Team_Bbs_Page.mod_bbs("${bbs.uuid }");'/>">修改</a> &nbsp;
			<a href="javascript:" onclick="<c:out value='Team_Bbs_Page.delete_bbs("${bbs.uuid }");'/>">删除</a> &nbsp;
		</c:if>
		<a href="javascript:" onclick="Team_Bbs_Page.back();">返回</a>
	</div>
	<h3 class="bbs-detail-title-box">
			标题： <c:out value='${bbs.title}'/>
		</h3>
		<h3 class="bbs-detail-author">作者:<c:out value='${bbs.author}'/>&nbsp;(<c:out value='${bbs.createDateTime}'/>)</h3>
	
</div>

<div id="team-bbs-middle-box">
	<c:out value='${bbs.content}' escapeXml="false"/>
</div>

<div id="team-bbs-replys-box">
	<h3 style="border-bottom:1px solid gray;">所有回复：</h3>
	<c:forEach var="reply" items="${bbs_replys}">
		<div class="team-bbs-rep-list">
			<div style="float:left;"><c:out value='${reply.author}'/></div>
			<div style="float:right;"><c:out value='${reply.createDateTime}'/></div>
			<div style="clear:both;">
				<c:out value='${reply.content}'/>
			</div>
		</div>
	</c:forEach>
</div>
<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>

<c:if test="${not empty me}">
	<form id="team-bbs-reply-form" method="post" action="../team/bbs_reply.add.json.do" onsubmit="Team_Bbs_Page.add_bbs_reply(this);return false;">
		<h3>回复：</h3>
		<input type="hidden" name="rootid" value="<c:out value='${bbs.uuid }'/>">
		<textarea name="content" rows="4" cols="50"></textarea>
		<div>
			<input type="submit" value="发表"/>&nbsp;
			<input type="reset" value="重置"/>
		</div>
	</form>
</c:if>

<script type="text/javascript">
Team_Bbs_Page = {
	back: function() {
		Team_Coll_Page.load('../team/bbses.jsp.vi?teamid=' + Team.id);
	},
	
	reload: function() {
		Team_Coll_Page.load('../team/bbs.jsp.vi?uuid=<c:out value='${bbs.uuid}'/>');
	},
	
	add_bbs_reply: function(form) {
		if($.trim(form.content.value) == '') {
			alert('回复内容不能为空!');
			return false;
		}
	
		$(form).ajaxSubmit(function() {
			Team_Bbs_Page.reload();
		});
	},
	
	delete_bbs: function(uuid) {
		if(!confirm('您确定要删除吗'))
			return;
	
		$.post('../team/bbs.del.json.do', {uuid: uuid}, function() {
			Team_Bbs_Page.back();
		});
	},
	
	mod_bbs: function(uuid) {
		Team_Coll_Page.load('../team/bbs.addOrMod.jsp.vi?uuid=' + uuid + '&act=mod');
	}
}

$(document).ready(function() {
	// 分页
	$('#page-team-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href');
		url += "&uuid=<c:out value='${bbs.uuid}'/>";
		Team_Coll_Page.load(url);		
		return false;
	});
});

</script>
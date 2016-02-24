<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<c:if test="${not empty me}">
	<c:set var="isTeamLeader" value="${me.isLeader}" /><!-- 是否是队长 -->
	<c:set var="hasAApply" value="${me.hasAApply}" /><!-- 是否有审批申请权限 -->
</c:if>

<table id="portlet-message-top10-box">
	<thead>
	<tr class="portlet-message-top10-head-box">
		<td width="110">时间</td>
		<td>内容</td>
		<td></td>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="message" items="${messages}">
		<tr>
			<td>
				<c:out value="${message.createDateTime}"/>
			</td>
			<td>
				<c:out value='${message.content}' escapeXml="false"/>
			</td>
			<td width="22%">
				<c:if test="${message.status && message.msgType == 'USER_APPLY'}">
					<c:if test="${hasAApply}">
						<a href="javascript:" style="color: red;" onclick="<c:out value='Team_Msgs_Page.pass_apply("${message.srcUsername }", "${message.id }")'/>">[批准]</a>
						<a href="javascript:" style="color: red;" onclick="<c:out value='Team_Msgs_Page.refuse_apply("${message.srcUsername }", "${message.id }")'/>">[拒绝]</a>
					</c:if>
				</c:if>	
				<c:if test="${isTeamLeader}">
					<a href="javascript:" onclick="Team_Msgs_Page.del_message('<c:out value='${message.id}'/>');" style="color: #cccccc">删除</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>


<script type="text/javascript">
Team_Msgs_Page = {
	url: '../teamMsg/msgs.jsp.vi',
	
	del_message: function(messageId) {
		TeamMsg.del(messageId, function() {
			Team_Msgs_Page.reload();
		});
	},
	
	reload: function() {
		Team_Coll_Page.load(this.url);
	},
	
	vi_msg: function(msgId, msgType, isReaded) {
		TeamMsg.vi_msg(msgId, msgType, function() {
			if (!isReaded)
				Team_Msgs_Page.reload();
		});
	},
	
	pass_apply: function(username, msgId) {
		if(!confirm("您确定要批准该成员加入战队吗?"))
			return false;
		Team.pass_apply(username, function() {
			TeamMsg.do_invalid(msgId);
			Team_Msgs_Page.reload();
		});
	},
	
	refuse_apply: function(username, msgId) {
		if(!confirm("您确定要拒绝该成员加入战队吗?"))
			return false;
		Team.refuse_apply(username, function() {
			TeamMsg.do_invalid(msgId);
			Team_Msgs_Page.reload();
		});
	}
}

$(document).ready(function() {
	$('#page-team-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href');
		url += "&teamid=" + Team.id;
		Team_Coll_Page.load(url);		
		return false;
	});
});	
</script>

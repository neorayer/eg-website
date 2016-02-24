<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="box">
	<div class="tp-box"><div></div></div>
	<div class="hd-box">
		<h3 class="green">所有信息
			<a href="javascript: Message_More_Page.back();">[返回]</a>
		</h3>
	</div>
	<div class="bd-box">
		<table id="portlet-message-more-box">
			<tr class="portlet-message-more-head-box">
				<td width="110px">时间</td>
				<td>发信人</td>		
				<td>主题</td>
				<td></td>
			</tr>
			<c:forEach var="message" items="${ACTOR.messagesTop10}">
				<tr>
					<td>
						<c:out value="${message.createDate}"/>
						<c:if test="${!message.readed}">
							<span style="color:red">
									[新]
							</span>
						</c:if>
					</td>
					<td>
						<c:if test="${message.isSys}">
							<span style="color:#000;">
								[系统消息]
							</span>	
						</c:if>
						<c:if test="${!message.isSys}">
							<c:set var="senderUser" value="${message.senderUser}" />
							<a href="javascript:Message.vi_user('<c:out value='${senderUser.username }'/>');">
								<c:out value='${senderUser.nickname}'/>
							</a>
						</c:if>
					</td>
					<td class="<c:if test="${message.readed}"> readed-message </c:if>">
						<c:out value='${message.subject}' escapeXml="false"/>
					</td>
					<td>
						<a href="javascript:" onclick="<c:out value='Message_More_Page.vi_msg("${message.id }", "${message.msgType }", ${message.readed})'/>">[详情]</a>
						<a href="javascript:" onclick=" Message_Portal_Page.del_message('<c:out value='${message.id}'/>');" style="color: #cccccc">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="message-more-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
	</div>
	<div class="bt-box" ><div></div></div>
</div>

<script type="text/javascript">
var Message_More_Page = {
	del_message: function(messageId) {
		Message.del(messageId, function() {
			$('#PL_message').load('../message/more.jsp.vi');
		});
	},
	
	back: function() {
		$('#PL_message').load('../message/portlet.jsp.vi');
	},
	
	vi_msg: function(msgId, msgType, isReaded) {
		Message.vi_msg(msgId, msgType, function() {
			if (!isReaded)
				Message_More_Page.reload();
		});
	},
	
	reload: function() {
		$('#PL_message').load('../message/more.jsp.vi');
	}
}

$(document).ready(function() {

	//分页局部刷新 
	$('#message-more-pagebar-box a').click(function() {
		$('#PL_message').load($(this).attr('href'));
		return false;
	});
});
</script>

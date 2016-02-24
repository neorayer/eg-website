<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>
#portlet-message-box{
	padding: 10px 10px 10px 10px;
	width: 97%;
}
#portlet-message-box a.more{
	text-align: right;
	color: #71A4B9;
}
#portlet-message-top-box {
	width: 100%;
	line-height: 162%;
}
#portlet-message-top-box thead{
	background-color:#D2E99A;
}
#portlet-message-top-box tbody{
	background-color: #eee;
}
#portlet-message-top-box thead th {
	color:black;
	height:22px;
	line-height:22px;
}
 
</style>
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>我的消息--<c:out value="${article.title}"/></strong>
		</div>
		<div class="floatRight">
			<a href="../user/index.jsplayout.vi">返回>></a>
		</div>
	</div>




<div id="portlet-message-box">
 	<c:if test="${empty messages}">
		<div align="center" class="empty-box">
			你当前没有任何消息
		</div>
	</c:if>
	<c:if test="${not empty messages}">
	<table id="portlet-message-top-box" cellspacing="0" cellpadding="5" bordercolor="#ffffff" border="1" >
		<thead>
			<tr>
				<th width="110px">时间</th>
				<th>发信人</th>		
				<th>主题</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="message" items="${messages}">
				<tr>
					<td align=center>
						<c:out value="${message.createDate}"/>
						<c:if test="${!message.readed}">
							<span style="color:red">
									[新]
							</span>
						</c:if>
					</td>
					<td width="65">
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
					<td width="15%" align=center>
						<!--<a href="javascript:" onclick="<c:out value='Message_Portal_Page.vi_msg("${message.id }", "${message.msgType }", ${message.readed})'/>">[详情]</a>
						--><a href="javascript:" onclick="Message_Portal_Page.del_message('<c:out value='${message.id}'/>');">删除</a>
					</td>
				</tr>
			</c:forEach>
	 	</tbody>
	</table>
	<div align="right" >
		<!--<a href="javascript:Message_Portal_Page.read_allmessage();">全部标记已读</a>
		--><a href="javascript:Message_Portal_Page.del_allmessage();">全部删除</a>
	</div>	
	<div id="message-more-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>	
	</c:if>
</div>
<script type="text/javascript">
var Message_Portal_Page = {
	del_message: function(messageId) {
		UserMsg.del(messageId, function() {
			Message_Portal_Page.reload();
		});
	},
	del_allmessage: function(messageId) {
		UserMsg.delall(messageId, function() {
			Message_Portal_Page.reload();
		});
	},
	read_allmessage: function(messageId) {
		UserMsg.readall(messageId, function() {
			Message_Portal_Page.reload();
		});
	},
	reload: function() {
		window.location = '../userMsg/msgs.jsplayout.vi';
	},
	
	vi_msg: function(msgId, msgType, isReaded) {
		UserMsg.vi_msg(msgId, msgType, function() {
			if (!isReaded)
				Message_Portal_Page.reload();
		});
	}
}
$(document).ready(function() {
 
});
</script>

</script>

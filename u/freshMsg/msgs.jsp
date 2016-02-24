<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>
#page-freshmsgs-box{
}
#page-freshmsgs-body-box{
	padding: 10px 10px 10px 10px;
}
#page-freshmsgs-top-box{
}
#page-freshmsgs-top-box td{
	border-bottom:1px solid #ECEFF5;
}
#page-freshmsgs-top-box a.dellink{
	color: #71A4B9;
	width: 13px;
	height: 13px;
	display: block;
	text-align: center;
	line-height: 13px;
}
#page-freshmsgs-top-box a.dellink:hover{
	color: #cccccc;
	background-color: #71A4B9;
}
.photos-img{
	width: 50px;
	height: 50px;
	border: 1px solid black;
	padding: 2px;
}
 
</style>
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>新鲜事</strong>
		</div>
		<div class="floatRight">
			<a href="javascript:history.back(-1)">返回>></a>
		</div>
	</div>
	<div style="clear: both;"></div>
	<br> 
	<div id="page-freshmsgs-body-box">
		<c:if test="${empty freshmsgs}">
			<div align="center" class="empty-box">
				当你的朋友发表日志、照片,这里会有提示。
			</div>
		</c:if>
		<c:if test="${not empty freshmsgs}">
			<table id="page-freshmsgs-top-box" cellspacing="0" cellpadding="3" bordercolor="#ffffff" border="0" width="100%">
				<c:forEach var="message" items="${freshmsgs}">
					<tr>
						<td width="5%" valign="top">
							<img src="../_css/images/fleshmsg/<c:out value='${message.msgType}'/>-feed.gif" width="16" height="16"/>
						</td>
						<td>
							<div class="index-page-user-freshtitle">
								<c:out value='${message.subject}' escapeXml="false" />
							</div>
							<c:if test="${message.msgType eq 'AVATOR'}">
								<div class="index-page-user-freshbody">
								<a href="<c:out value='../player/index.jsplayout.vi?username=${message.sender}'/>">	
									<img src="<c:out value='${message.receiverAvator}'/>" class="logo-small-image">
								</a>
								</div>
							</c:if>
							<c:if test="${message.msgType eq 'ALBUM'}">
								<div class="index-page-user-freshbody">
								<c:forEach var="photo" items="${message.photo}">
									<a href="<c:out value="../player/photo.jsplayout.vi?username=${message.sender}&id=${photo.id}"/>">
										<img class="photos-img" src="<c:out value="${photo.scalePhotoimg}" />" />
									</a>							
								</c:forEach>
								</div>						
							</c:if>
							<c:if test="${message.msgType eq 'ARTICLE'}">
								<div class="index-page-user-freshbody">
								&#34;<c:out value="${message.article}" escapeXml="false"/>&#34;
								</div>
							</c:if>
						</td>
						<td width="5%" valign="top"><a title="删除" class="dellink" href="javascript:" onclick="FreshMsg.del('<c:out value='${message.id}'/>');" >删</a></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<div align="left" ><a href="javascript:FreshMsg.delallmsg('<c:out value="${ACTOR.username}" />');">全部删除</a></div>	
			<div align="right" id="message-freshmsgs-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
		</c:if>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	
});
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
.page-player-restore-box {
	display: none;
}
</style>

<div id="page-player-messageBoard-list-box">
	<c:forEach var="message" items="${messageBoards}">
	
	<div class="messageBoard-page-list">
		<div class="messageBoard-page-list-left floatLeft">
			<a href="../player/index.jsplayout.vi?username=<c:out value='${message.guest}'/>">
				<img src="<c:out value="${message.avatorSmallUrl}" />" width="50px" height="50px"/>
			</a>
		</div>
		<div class="messageBoard-page-list-right floatRight">
			<div class="messageBoard-list-name">
				<div class="floatLeft">
					<a href="../player/index.jsplayout.vi?username=<c:out value='${message.guest}'/>">
					<c:out value="${message.guestUser.dispName}" /></a> (<c:out value="${message.createDateTime}" />)
				</div>
				<div class="floatRight">
					<c:if test="${ACTOR.username == player.username}">
						<a href="javascript:" onclick="Player_MessageBoard_Page.del('<c:out value='${message.id }'/>');">删除</a>
					</c:if>
				</div>
			</div>
			<div style="clear:both">
				<c:out value="${message.message}" />
			</div>
			
			<c:forEach var="parentMessage" items="${message.parentMessages}">
				<div class="backmessage-list-box">
					<div class="backmessage-list-top-box">
						<div class="floatLeft">
						<a href="../player/index.jsplayout.vi?username=<c:out value='${parentMessage.guest}'/>">
							<c:out value="${parentMessage.guestUser.dispName}" /> </a>的回复：(<c:out value="${parentMessage.createDateTime}" />)</span>
						</div>
						<div class="floatRight">
							<c:if test="${ACTOR.username == player.username}">
								<a href="javascript:" onclick="Player_MessageBoard_Page.del('<c:out value='${parentMessage.id }'/>');">删除</a>
							</c:if>
						</div>	
					</div>
					<div class="backmessage-list-body-box">
						<c:out value="${parentMessage.message}" />
					</div>
				</div>
			</c:forEach>
			<c:if test='${not empty ACTOR}'>
				<a href="javascript:" onclick="Player_MessageBoard_Page.vi_restore('<c:out value='${message.id }'/>');">回复</a>
			</c:if>
			<form class="page-player-restore-box" id="<c:out value='${message.id }'/>" action="../player/message.add.json.do" method="post">
				<input type="hidden" name="owner" value="<c:out value='${player.username }' />">
				<input type="hidden" name="guest" value="<c:out value='${ACTOR.username }' />">
				<input type="hidden" name="parentid" value="<c:out value='${message.id }' />">
				<textarea id="message" tabindex="2" cols="80" rows="4" name="message" class="playerindex-callback-message"></textarea>
				<input type="submit" class="button" value="回复" onclick="Player_MessageBoard_Page.do_restore('<c:out value='${message.id }'/>');"/>
				<input type="button" class="button" value="取消" onclick="Player_MessageBoard_Page.cancel('<c:out value='${message.id }'/>');">
				
			</form>
			
			
		</div>
		<div style="clear:both"></div>
	</div>
	</c:forEach>
	
	
</div>
<div style="clear:both"></div>


<script type="text/javascript">
;$(document).ready(function() {
	
});

</script>
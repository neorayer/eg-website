<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
.page-player-restore-box {
	display: none;
}
.page-myMessageBoard-body{
	padding:10px;
}
</style>


<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>


<!-- 留言 -->
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>留言板</strong>
		</div>
		<div class="floatRight">
			<a href="../player/index.jsplayout.vi?username=<c:out value='${username}'/>">返回>></a>
		</div>
	</div>
	
	<div class="page-myMessageBoard-body">
		<c:forEach var="message" items="${messageBoards}">
		<div class="messageBoard-page-list">
			<div class="messageBoard-page-list-left floatLeft">
				<a href="../player/index.jsplayout.vi?username=<c:out value='${message.guest}'/>">
					<img src="<c:out value="${message.avatorSmallUrl}" />"/>
				</a>
			</div>
			 
			 
			<div class="messageBoard-page-list-right2 floatRight">
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
				<a href="javascript:" onclick="Player_MyMessageBoard_Page.vi_restore('<c:out value='${message.id }'/>');">回复</a>
			</c:if>
			<form class="page-player-restore-box" id="<c:out value='${message.id }'/>" action="../player/message.add.json.do" method="post">
				<input type="hidden" name="owner" value="<c:out value='${player.username }' />">
				<input type="hidden" name="guest" value="<c:out value='${ACTOR.username }' />">
				<input type="hidden" name="parentid" value="<c:out value='${message.id }' />">
				<textarea id="message" tabindex="2" cols="80" rows="4" name="message" class="playerindex-callback-message"></textarea>
				<input type="submit" class="button" value="回复" onclick="Player_MyMessageBoard_Page.do_restore('<c:out value='${message.id }'/>');"/>
				<input type="button" class="button" value="取消" onclick="Player_MyMessageBoard_Page.cancel('<c:out value='${message.id }'/>');">
				
			</form>
			
			
		</div>
			<!--  
			
			<span style="color: blue;"><c:out value="${message.guest}" /> 的留言：</span>
			
			<c:out value="${message.message}" />
			&nbsp;
			<c:if test="${ACTOR.username == player.username}">
				<a href="javascript:" onclick="Player_MyMessageBoard_Page.del('<c:out value='${message.id }'/>');">删除</a>
			</c:if>
			<br/>
			
			<c:forEach var="parentMessage" items="${message.parentMessages}">
				<div style="margin-left: 20px;">
					<img class="logo-small-image" src="<c:out value="${parentMessage.avatorSmallUrl}" />"/>
					<span style="color: blue;"><c:out value="${parentMessage.guest}" /> 的回复：</span>
					<span style="color: red;"><c:out value="${parentMessage.message}" /></span>
					<c:if test="${ACTOR.username == player.username}">
						<a href="javascript:" onclick="Player_MyMessageBoard_Page.del('<c:out value='${parentMessage.id }'/>');">删除</a>
					</c:if>
				</div>
				
			</c:forEach>
			
			
			<a href="javascript:" onclick="Player_MyMessageBoard_Page.vi_restore('<c:out value='${message.id }'/>');">回复</a>
			<form class="page-player-restore-box" id="<c:out value='${message.id }'/>" action="../player/message.add.json.do" method="post">
				<c:if test="${empty username}">
					<c:set var="username" value="${ACTOR.username}"></c:set>
				</c:if>
				<input type="hidden" name="owner" value="<c:out value='${player.username }' />">
				<input type="hidden" name="guest" value="<c:out value='${ACTOR.username }' />">
				<input type="hidden" name="parentid" value="<c:out value='${message.id }' />">
				<textarea id="message" tabindex="2" cols="80" rows="4" name="message"></textarea>
				<input type="submit" class="button" value="回复" onclick="Player_MyMessageBoard_Page.do_restore('<c:out value='${message.id }'/>');"/>
				<input type="button" class="button" value="取消" onclick="Player_MyMessageBoard_Page.cancel('<c:out value='${message.id }'/>');">
			</form>
			 -->
			 <div style="clear:both"></div>
		</div>
			
		</c:forEach>
		<div style="clear:both"></div>
		<div id="page-mymessageboard-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
	</div>
</div>

	


<script type="text/javascript">

var Player_MyMessageBoard_Page = {
	username: '<c:out value='${player.username }'/>',
	load: function() {
		var url = window.location.href;
		window.location =url;
	},
	del: function(id) {
		if(!confirm("您确实要删除该留言吗?"))
			return false;
		var url ='../player/message.del.json.do?id='+id;
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				Player_MyMessageBoard_Page.load();
			}
		});
		return false;
	},
	vi_restore: function(id) {
		$("#"+id).css("display","block");
	},
	cancel: function(id) {
		$("#"+id).css("display","none");
	},
	do_restore: function(id) {
		$("#"+id).validate({
			errorClass: "errorClass",
			errorElement: "div",
		 	rules: {
				message: {
					required: true,
					maxlength: 200,
					minlength: 1
				}
			},
			messages: {
				message: {
					required: "用户名不可以为空！",
					maxlength: "超出字符长度",
					minlength: "字符长度不足"
				} 
			},
		 	submitHandler: function(form) {
		 		var action = $(form).attr('action');			
				action = action.replace(/jsp(layout)?/i,'json');
		   		$(form).ajaxSubmit({
		   			type: 'post',
					dataType: 'json',
		   			success: function(data) {
		   				if(data.res == 'no') {
		   					alert(data.data);
		   					return;
		  				}
		  				$("#message").val('');
						Player_MyMessageBoard_Page.load();
		 			
		   			}
		   		});
		   	}
		});
		
	}
}




;$(document).ready(function() {
	
	// 分页
	$('#page-mymessageboard-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href').replace('jsp','jsplayout');;
		window.location=url;
		return false;
		
	});
});


</script>
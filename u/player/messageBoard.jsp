<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
textarea {
	border:1px solid #BDC7D8;
	font-size:12px;
	overflow:auto;
	padding:3px;
	overflow: hidden; 
	height: 30px;
}

</style>

<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>

<!-- 留言 -->
<div id="index-page-player-life">
	<div class="player-page-portlet-title">
		<div class="player-page-portlet-titlefont floatLeft">留言板</div>
	</div>
	<c:if test="${not empty ACTOR }">
	<div id="player-page-message-body" >
		<form id="page-player-message-form" action="../player/message.add.json.do" method="post">
			<input type="hidden" name="owner" value="<c:out value='${player.username }' />">
			<input type="hidden" name="guest" value="<c:out value='${ACTOR.username }' />">
			<textarea id="message" tabindex="2" cols="70" rows="4" name="message" class="playerindex-message-textarea"></textarea>
			<input type="submit" class="button" value="留言" />
		</form>
	</div>
	</c:if>
</div>



<!-- 最近留言 -->
<div id="index-page-player" style="margin-bottom: 20px;">
	<div id="player-page-messages">
		<c:import url="../player/messages.jsp.vi?username=${player.username }"></c:import>
	</div>
</div>
<!-- 
<div id="message-bottom-bar-box">
	<a href="../player/myMessageBoard.jsplayout.vi?username=<c:out value='${player.username }' />"><c:out value="${player.dispName}"/>的留言板>>></a>
</div>
 -->
<script type="text/javascript">

$('form#page-player-message-form').validate({
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
				Player_MessageBoard_Page.load();
 			
   			}
   		});
   	}
});


Player_MessageBoard_Page = {
	username: '<c:out value='${player.username }'/>',
	load: function() {
		$("#player-page-messages").load('../player/messages.jsp.vi?username='+Player_MessageBoard_Page.username);
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
				Player_MessageBoard_Page.load();
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
						Player_MessageBoard_Page.load();
		 			
		   			}
		   		});
		   	}
		});
		
	}
}


;$(document).ready(function() {
	
});

</script>
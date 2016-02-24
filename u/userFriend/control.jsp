<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>好友权限</strong>
		</div>
		<div class="floatRight">
			<a href="javascript:history.back(-1)">返回>></a>
		</div>
	</div>
	<div style="clear: both;"></div>
	
	<div class="allvisitor-page-body">	
		

		
			
			<form action="../FriendSetting/user.modprivacy.json.do" id="page-modprivacy-form" method="post" enctype="multipart/form-data">
				<p>谁可以向我发送好友请求: </p>
				<input class="inptext" name="username" value="<c:out value="${ACTOR.username}" />" type="hidden" />
				<input class="inptext" name="privacy"  value="ANYONE" type="radio" <c:if test="${userfriend.privacy eq 'ANYONE'}">checked</c:if> />&nbsp;任何人 <br/>
				<input class="inptext" name="privacy"  value="SOMEONE" type="radio" <c:if test="${userfriend.privacy eq 'SOMEONE'}">checked</c:if> />&nbsp;需经过本人同意 <br/>
				<input class="inptext" name="privacy"  value="NOBODY" type="radio" <c:if test="${userfriend.privacy eq 'NOBODY'}">checked</c:if> />&nbsp;拒绝任何人 <br/>
				<br>
				<p>好友提示语:  </p>
				<input class="inptext" id="usingtip-buttom" type="checkbox"  name="usingtip" <c:if test="${userfriend.usingtip eq true}">checked  value="true" </c:if> />设置提示语
				<input class="inptext" id="friendreqtip-text" type="text" value="<c:out value="${userfriend.friendreqtip}" />"  name="friendreqtip" style="display: <c:if test="${userfriend.usingtip eq false}">none</c:if>;"/> 
				 <br/>	<br/>	
				<input type="submit" value="提交" id="page-modprivacy-btton-box" class="button" onclick="UserFriend.modprivacy('<c:out value="${ACTOR.username}" />');return false;" />
				<input type="reset" value="返回" onclick="UserFriend.reload('<c:out value="${ACTOR.username}" />');" class="button"/>
			</form>
			
			<div id="SOR_EXCEPTION" style="color: red"><c:out value="${REASON}" /></div>
		
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('form#page-modprivacy-form').validate({
		errorClass: "errorClass",
		errorElement: "span",
  		rules: {
			friendreqtip:{
				maxlength: 15
			}
		},
		messages: {
			friendreqtip:"字符限制15个字符！" 
		},
  		submitHandler: function(form) {
  			var text=$("#friendreqtip-text");
  			if($.trim(text[0].value)==''){
  				$("#usingtip-buttom").val('false');
  			}
			var action = $(form).attr('action');
			action = action.replace(/jsp(layout)?/i,'json');
			$(form).attr('action',action);
	   		$(form).ajaxSubmit({
	   			success: function(data) {
					var responseText = data.replace(/<\/pre>/i, '').replace(/<pre>/i, '');
					var data = eval('(' +responseText + ')');
	   				if(data.res == 'no') {
	   					alert(data.data);
	   					return;
   					}else{
						UserFriend.reload();
   					}
	   			}
	   		});
	   	}
 	});
});


$('#usingtip-buttom').click(function() {
	if($("#usingtip-buttom").attr('checked')) {
		$("#usingtip-buttom").val('true');
		$("#friendreqtip-text").show();
	}else{
		$("#usingtip-buttom").val('false');
		$("#friendreqtip-text").hide();
	}
});
</script>

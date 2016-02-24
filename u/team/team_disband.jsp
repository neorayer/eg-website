<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<h3>战队解散</h3>
<form id="team-disband-form" action="../team/team.disband.json.do" method="post" onsubmit="Team_TeamDisband_page.submit(this);return false;"> 
	<input type="hidden" name="id" value="<c:out value='${team.id }'/>">
	
	<div>
  		目前您的战队中有 <span style="color:red;"><c:out value='${team.memberCount}'/></span> 个成员!
	</div>
	
	<div>
		<div>请填写解散理由:</div>	
		<textarea id="reason" name="reason" rows="4" cols="40"></textarea>
	</div>
	
	<div>
		<div>
			您确定要解散战队吗?
		</div>
		<div style="margin-left: 100px;">
			<input class="button" type="submit" value="确定"/>
			<input class="button" type="button" value="取消" onclick="PopupWinX.close(this);">
		</div>
	</div>
</form>

<script type="text/javascript">
var Team_TeamDisband_page = {
	submit: function(form) {
		$(form).ajaxSubmit({
			beforeSubmit: function() {
				var $reason = $('#reason');
				if($.trim($reason.val()) == '') {
					alert("请填写解散理由!");
					$reason.focus();
					return false;
				}
			},
   			success: function(data) {
	   			if(data.res == 'no') {
	   				alert(data.data);
	   				return;
	   			}else{
	   				window.location.reload();
	   			}
   			}
   		});
	}
}

$(document).ready(function() {
});
</script>
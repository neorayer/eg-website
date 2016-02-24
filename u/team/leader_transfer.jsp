<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--

#team-leader-transfer-form h3 {
	border-bottom:1px solid #818181;
	margin-bottom:5px;
	padding-bottom:2px;
}

#leader-transfer-buttons-box {
	display: none;
}
-->
</style>

<form id="team-leader-transfer-form" action="../team/leader.transfer.json.do" method="post" onsubmit="return false;">
	<h3>
		替换队长
	</h3>
	<div>
		<p>您是否决定将队长之位交给其他玩家，让战队更加发扬光大．</p>
		<p style="color: red;">新队长必须为本战队的成员．替换后您的职位会变为新手．</p>
	</div>
	<div style="margin-top: 10px;">
		新队长用户名：<input type="text" id="new-leader-username" name="username" value="" />
	</div>
	<div id="leader-transfer-userinfo-box">
		
	</div>
	<div id="leader-transfer-buttons-box" style="margin-top: 10px;text-align: center;">
		<input type="hidden" name="teamid" value="<c:out value='${team.id }'/>"/>
		<input name="submit" class="button" type="button" value="替换" onclick="Team_Leader_Transfer_Page.do_leader_transfer('#team-leader-transfer-form');"/>
		<input class="button" type="button" value="取消" onclick="PopupWinX.close(this);"/>
	</div>
</form>


<script type="text/javascript">
var Team_Leader_Transfer_Page = {
	do_leader_transfer: function(form) {
		if(!confirm("您确定要将该战队转让给 "+ $('#new-leader-username').val() +" 吗?"))
			return false;
			
		$(form).ajaxSubmit(function(data) {
			alert('替换成功!');
			window.location.reload();
		});
	}
}

$(document).ready(function(){
	
	$("#leader-transfer-userinfo-box").ajaxStart(function() {
		$(this).html('loading...');
	});
	
 	$('#new-leader-username')
 		.focus(function() {
			//保留原数据
			$(this).data('oldVal', $(this).val());
		})
 		.blur(function(e) {
 			$input = $(this);
 			var val = $.trim($input.val());
 			var oldVal = $input.data('oldVal');
 			
			if (oldVal == val) {
				return;
			}
			
			TeamMember.checkMemberExists(Team.id, val, function(data) {
				if(data.res != 'yes') {
					$('#leader-transfer-buttons-box').hide();
					$('#leader-transfer-userinfo-box').html(data.data);
					return;
				}
			
				if(data.data.exists != 'true') {
					$('#leader-transfer-buttons-box').hide();
					$('#leader-transfer-userinfo-box').html('您的战队不存在这个用户名的玩家。');
					return;
				}
				$('#leader-transfer-buttons-box').show();
 				$("#leader-transfer-userinfo-box").load("../team/userInfo.jsp.vi?teamid=" + Team.id + "&username="+ val);
			});
 				
 		})
 		.keypress(function(e) {
			//回车
			if (13 == e.which)
				$(this).blur();
		});
});
</script>
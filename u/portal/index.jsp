<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>
#page-portal-index-left-box{
	float: left;
	width: 252px;
}

#page-portal-index-main-box {
	width: 746px; 
	float: left;
	background-color: #969696;
}

#portal-index-body-box {
	margin-top: 10px;
}

#portal-index-users-box {
	float: left;
	width: 372px;

}
#portal-index-teams-box {
	float: left;
	width: 374px;
}

</style>
<div class="portalindex-box">
	<div id="page-portal-index-left-box">
		<c:import url="../portal/login.jsp.vi"></c:import>	
	</div>
	
	
	<div id="page-portal-index-main-box">
		
		<div>
			<div class="hd-box">
				<h3 class="green">
					网站简介
				</h3>
			</div>
			<div class="login-box">
				EG网站是一个专业的针对电子竞技用户的社区，同时与游戏平台、电子竞技比赛相结合，你可以在这里开创另外一片天地。
			</div>
		</div>
		
		<div id="portal-index-body-box">
			<div id="portal-index-users-box">
				<div class="hd-box">
					<h3 class="green">
						平台用户
					</h3>
				</div>
				<div class="login-box">
					<c:import url="../portal/users.jsp.vi" />
				</div>
			</div>
			
			<div id="portal-index-teams-box">
				<div class="hd-box">
					<h3 class="green">
						平台战队
					</h3>
				</div>
				<div class="login-box">
					<c:import url="../portal/teams.jsp.vi" />
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

$('form#page-leftbar-loginarea-form').validate({
	errorClass: "errorClass",
	errorElement: "div",
 		rules: {
		username: {
			required: true,
			maxlength: 20,
			minlength: 6
		},
		password: {
			required: true,
			maxlength: 16,
			minlength: 6
		}
	},
	messages: {
		username: {
			required: "用户名不可以为空！",
			maxlength: "超出字符长度",
			minlength: "字符长度不足"
		},
		password: {
			required: "请输入密码",
			maxlength: "超出字符长度",
			minlength: "字符长度不足"
		} 
	},
 		submitHandler: function(form) {
 			var saveCookie = $(form).attr('saveCookie');
		var action = $(form).attr('action');			
		
		action = action.replace(/jsp(layout)?/i,'json');
		$(form).attr('action',action);
   		$(form).ajaxSubmit({
   			type: 'post',
			dataType: 'json',
   			success: function(data) {
   				if(data.res == 'no') {
   					alert(data.data);
  					}
  					else {
					if (saveCookie.checked) {
						setCookie("username", $(form).attr('username').value, 336, "/");
						setCookie("password", $(form).attr('password').value, 336, "/");
						setCookie("saveCookie", "true", 336, "/");
					}else{
						deleteCookie("username", "/");
						deleteCookie("password", "/");
						deleteCookie("saveCookie","/");
					}
					window.location = '../user/index.jsplayout.vi';
  					}
   			}
   		});
   	}
	});
 	
</script>
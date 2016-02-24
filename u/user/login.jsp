<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>
#page-leftbar-loginarea-box{
	float: left;
	width: 252px;
}

#page-add-userinfor-box {
	margin-left:20px;
	width: 580px; 
	float: left;
}
</style>
<div id="page-leftbar-loginarea-box">
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="green">
			登录信息
		</h3>
	</div>
	<div class="bd-box">
		<form action="../user/user.login.json.do" method="post" id="page-leftbar-loginarea-form" >
		
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" id="username" size="15" /></td>
				</tr>
				<tr>
					<td>密　码：</td>
					<td><input type="password" name="password" id="password" size="15"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="saveCookie" id="saveCookie"/>&nbsp;<span id="page-leftbar-loginarea-span">自动登录</span>&nbsp;&nbsp;<a href="finduser.jsplayout.vi" id="page-leftbar-loginarea-a">忘记密码</a>
					</td>
				</tr>
			</table>
			<input type="submit" title="登陆" value="登陆" id="page-leftbar-login-button"/>
		</form>
		
	</div>
	<div class="bt-box"><div></div></div>
</div>


<div id="page-add-userinfor-box">
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="green">
			平台简介
		</h3>
	</div>
	<div class="bd-box">
		&nbsp;
	</div>
	<div class="bt-box"><div></div></div>

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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div>
	
	<div class="hd-box">
		<h3 class="green">
			登录信息
		</h3>
	</div>
	<c:if test='${empty ACTOR}'>
		<div class="login-box">
			<form action="../portal/login.json.do" method="post" id="page-leftbar-loginarea-form" >
			
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
							<input type="checkbox" checked="checked" name="saveCookie" id="saveCookie"/>&nbsp;<span id="page-leftbar-loginarea-span">记住登录</span>&nbsp;&nbsp;<a href="finduser.jsplayout.vi" id="page-leftbar-loginarea-a">忘记密码</a>
						</td>
					</tr>
				</table>
				<input type="submit" title="登陆" value="登陆" id="page-leftbar-login-button"/>
			</form>
		</div>
	</c:if>
	<c:if test='${not empty ACTOR}'>
		<div class="login-box">
			<form action="../portal/login.json.do" method="post" id="page-leftbar-loginarea-form" >
			
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td>用户：</td>
						<td><c:out value='${ACTOR.dispName}'/></td>
					</tr>
				</table>
			
			</form>
		</div>
	</c:if>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var usernameValue = getCookieValue("username");
	$("table #username").attr("value",usernameValue);
	var passwordValue = getCookieValue("password");
	$("table #password").attr("value",passwordValue);
	var saveCookieValue = getCookieValue("saveCookie");
	$("table #saveCookie").attr("checked",saveCookieValue);
	if(usernameValue  && passwordValue  && saveCookieValue ){
		$("#page-leftbar-loginarea-a").css("display","none");
		$("#page-leftbar-loginarea-span").text("自动登录中...");
		User.do_login($("#page-leftbar-loginarea-form"));
	}
});

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
   		});
   	}
	});
 	
</script>
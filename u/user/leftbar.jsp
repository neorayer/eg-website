<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style type="text/css">

</style>
<div id="page-leftbar-main-box">
	<c:if test="${empty ACTOR}">
		<form action="user.login.json.do" method="post" id="page-leftbar-loginarea-form" >
			<div class="tp-box">
				<div>
				</div>
			</div>
			<div class="hd-box" style="height:  1px;">
			</div>
			<div class="bd-box">
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
			</div>
			<div class="bt-box"><div></div></div>
		</form>
			
		<br>
		<div id="page-leftbar-registarea-box">
			<div class="tp-box">
				<div>
				</div>
			</div>
			<div class="hd-box" style="height:  1px;">
			</div>
			<div class="bd-box" id="page-leftbar-registarea-body-box">
				<span>你还没有注册？</span>
				<div></div>
				<p>快来体验电子竞技游戏吧</p>
				<p>这里有众多强劲的战队</p>
				<p>精彩绝伦的赛事</p>
				<input type="button" onclick="location.href='../user/reg.jsplayout.vi';" title="注册" value="注册" id="page-leftbar-reg-button"/>
			</div>
			<div class="bt-box"><div></div></div>
		</div>
 	</c:if>
	 	
	 	
 	<c:if test="${not empty ACTOR}">
 		<div id="page-leftbar-userinfo-box">
			<div class="tp-box">
				<div>
				</div>
			</div>
			
			<div class="hd-box">
				<h3 class="green">基本信息</h3>
			</div>
			
 			<div class="bd-box" id="page-leftbar-userinfo-body-box">
				<c:if test='${not empty ACTOR}'>
					<div id="page-leftbar-avator-box">
							<img width="170" height="232" src="<c:out value='${ACTOR.avatorUrl}'/>" align="bottom" />
						<br />
						<a href="user_avator.edit.jsplayout.vi">[修改头像]</a>
						<a href="avator.upload.jsplayout.vi">[上传头像]</a>
					</div>
				</c:if>
				<table id="page-leftbar-info-box" cellpadding="0" cellspacing="0" border="0" >
					<tr>
						<td class="label" nowrap>用户名：</td>
						<td class="content"><a href="index.jsplayout.vi"><c:out value="${ACTOR.username}" /></a></td>
					</tr>
					<tr>
						<td class="label" nowrap>游戏名：</td>
						<td class="content">
							<img width="34" height="34" style="border: 1px solid gray;" src="<c:out value='${ACTOR.avatorSmallUrl}'/>" align="bottom" />
						<c:out value="${ACTOR.nickname}" /></td>
					</tr>
					<tr>
						<td class="label" nowrap>性 别：</td><td class="content"><c:out value="${ACTOR.sex}" /></td>
					</tr>
					<tr>
						<td class="label" nowrap>来 自：</td><td class="content"><c:out value="${ACTOR.province}" /></td>
					</tr>
					<tr>
						<td class="label" nowrap>加入时间：</td>
						<td class="content"><c:out value="${ACTOR.createDate}" /><c:out value="${ACTOR.createDateTime1}" />
						</td>
					</tr>
					<tr>
						<td class="label" nowrap>Email：</td>
						<td  class="content" title="<c:out value="${ACTOR.email}" />">
							<div><c:out value="${ACTOR.email}" /></div>
						</td>
					</tr>
					<tr>
						<td class="label" nowrap>个人简介：</td>
						<td>
						</td>
					</tr>
					<c:if test='${not empty ACTOR.signature}'>
						<tr>
							<td colspan="2" class="signature" nowrap title="<c:out value="${ACTOR.signature}" />" >
								 <c:out value="${ACTOR.signature}" /> 
							</td>
						</tr>
					</c:if>
				</table>
				<div id="page-leftbar-setup-box">
					<a href="userinfo.mod.jsplayout.vi">[修改信息]</a>
					<a href="userpassword.mod.jsplayout.vi">[修改密码]</a>
					<br />
					<a href="userquestion.mod.jsplayout.vi">[密码保护]</a>
					<a href="javascript: User.do_logout();" id="page-leftbar-info-logout-button">[安全退出]</a>
				</div>
				
			</div>
			
			<div class="bt-box"><div></div></div>
			
		</div>
		
		<!--<div id="page-leftbar-setup-box" >	
			<div class="tp-box">
				<div>
				</div>
			</div>
			<div class="hd-box">
				<h3 class="green">设置信息</h3>
			</div>
			<div class="bd-box" id="page-leftbar-setup-body-box" >
				<ul id="page-leftbar-setup-menu" >
					<li>
						<a href="user.modinfo.jsplayout.vi">&nbsp;设置基本信息</a>
					</li>
					<li>
						<a href="user.modpassword.jsplayout.vi">&nbsp;修改密码</a>
					</li>
					<li>
						<a href="user.modquestion.jsplayout.vi">&nbsp;修改密码保护</a>
					</li>
				</ul>
			</div>
			<div class="bt-box"><div></div></div>
		</div>
	-->
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
						window.location = 'index.jsplayout.vi';
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

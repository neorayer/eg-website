<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div id='page-head-box'>
	<div id="page-head-top-box">
		<div id="page-head-top-left-box">
		</div>
		
		<div id="page-head-top-right-box">
			<c:if test="${empty ACTOR}">
				<form id="page-head-login-form" action="../portal/login.json.do" method="post" >
					<ul>
						<li>
							<label>用户名:</label>
							<input type="text" id="username" name="username" class='inputText'/>
						</li>
						<li>
							<label>密码:</label>
							<input type="password"id="password" name="password" class='inputText'/>
						</li>
						<li>
							<input class="button" type="submit" value="登录"/>
							<input type="button" class="button" onclick="window.location.href='../portal/reg.jsplayout.vi'" value="注册" />		
						</li>
					</ul>
				</form>
			</c:if>
			<c:if test="${not empty ACTOR}">
				<span>欢迎 <c:out value="${ACTOR.nickname}"/>(<c:out value='${ACTOR.username}'/>)！</span>
				<a class="button" href="javascript: User.do_logout();">退出</a>
			</c:if>
		</div>
		
		<div style="clear:both;"></div>
	</div>
	
	<div id="page-head-middel-box">
		<div id="page-head-nav-box">
			<div id="eg-index-click-box" class="floatLeft"></div>
		</div>
		
			<ul id="page-head-label-box">
				<c:if test="${not empty ACTOR}">
				<li>
					<a href="../user/index.jsplayout.vi">首页</a>
				</li>
				<li>
					<a href="../player/index.jsplayout.vi">个人主页</a>
				</li>
				<li>
					<a href="../team/all.jsp.vi">所有战队</a>
				</li>
				</c:if>
			</ul>
	</div>
	
	<div style="clear: both;"></div>
</div>
 

<div id="WIN_Manage" class="WIN">
	<div class="WIN_Header" id="WIN_Header">
		<input value="关闭" class="button2 BTN_Close" type="button" onclick="PopupWinX.close(this);">
	</div>
	<div class="WIN_Body" id="WIN_Body">
	</div>
	<div class="WIN_Tail">
	</div>
</div> 

<iframe id='page-upLoadIframe' name='page-upLoadIframe' src='about: blank;' style="position:absolute;left:-1000px;"></iframe>

<script type="text/javascript">
 
$(document).ready(function(){
	$("#eg-index-click-box").click(function(){
		window.location.href="../portal/index.jsplayout.vi";
	})
	$('form#page-head-login-form').validate({
		errorClass: "errorClass",
		errorElement: "span",
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
						window.location = '../user/index.jsplayout.vi';
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

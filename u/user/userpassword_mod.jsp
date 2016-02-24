<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>


<div>	
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="green">
			修改密码
			<a href="../user/index.jsplayout.vi"  style="color: white;margin-left: 650px;">【返回】</a>
		</h3>
	</div>
	<div class="bd-box">
 
	<form id="page-body-modpassword-form" action="userpassword.mod.json.do" method="post">
		<table border="0" cellpadding="0" cellspacing="0" id="page-modpassword-info-box">
			<c:if test="${not empty REASON}">
				<tr>
					<td colspan="3">
							<DIV id="SOR_EXCEPTION" style="color:red" align="center"><c:out value="${REASON}" /></DIV>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="label">用户名：</td>
				<td class="content"><c:out value="${ACTOR.username}" /></td>			
				<td class="msg">* 不可修改</td>
			</tr>
			<tr>
				<td class="label"> 原密码： </td>
				<td class="content">
					<input name="oldpassword" id="oldpassword" size="26"	type="password"  />
				</td>
				<td  class="msg">
					必填！ 密码长度6～16位，字母区分大小写
				</td>
			</tr>
			<tr>
				<td class="label"> 新密码： </td>
				<td class="content">
					<input name="password" id="password" size="26" type="password"  />
				</td>
				<td  class="msg">
					必填！ 密码长度6～16位，字母区分大小写
				</td>
			</tr>
			<tr>
				<td class="label"> 新密码确认：	 </td>
				<td class="content">
					<input name="repassword" type="password" size="26" >
				</td>
				<td class="msg"> </td>
			</tr>
			<tr>
				<td class="label">验证码：</td>
				<td class="content">
					<input  name="imgCode" value="" type="text" size="10" maxlength="4"/>
					<img id="AuthImg" align="top" src="../user/authImg.authImage.vi" />
				</td>
				<td class="msg">
					<a href="javascript:Util.refreshAuthImage();"  style="text-decoration: none;color: red;" >[刷新]</a>
					请输入验证码！
				</td>
			</tr>					
		</table>
			
		<div align="center" id="page-modpassword-buttons">
			<input type="submit" title="确认" value="确认" id="page-modpassword-submit-button" <c:if test='${empty ACTOR}'>disabled</c:if> >
			<input type="button" title="返回" value="返回" id="page-modquestion-reset-button" onclick="javasrcipt:passwordMod.cancel();">
		</div>	
				
	</form>
 
	</div>
	<div class="bt-box"><div></div></div>	
</div>


<script type="text/javascript">
var passwordMod = {
	cancel: function() {
		window.location ='../user/index.jsplayout.vi';
	}
};
$(document).ready(function(){
	$('form#page-body-modpassword-form').validate({
		errorClass: "errorClass",
		errorElement: "div",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td"));
  		},
  		rules: {
  			oldpassword	: {
				required: true,
				rangelength: [6,16]
			},
			password: {
				required: true,
				rangelength: [6,16]
			},
			repassword: {
				required: true,
				rangelength: [6,16],
				equalTo: "#password"
			},
			imgCode:{
				required: true,
				maxlength: 4,
				minlength: 4,
				number:true
			}
		},
		messages: {
			oldpassword: {
				required: "请输入密码",
				rangelength: "6~16位字符长度"
			},
			password: {
				required: "请输入密码",
				rangelength: "6~16位字符长度"
			},
			repassword: {
				required: "请输入确认密码",
				rangelength: "6~16位字符长度",
				equalTo: "两次输入不正确"
			},
			imgCode:{
				required: "请输入验证码",
				maxlength: "超出字符长度",
				minlength: "字符长度不足",
				number:"数字类型"
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
	   					Util.refreshAuthImage();
   					}
   					else {
   						window.location = 'index.jsplayout.vi';
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>
<div id="page-finduser-box">
	<div id="page-finduser-aide-box">
		<c:import url="leftbar.jsp.vi"></c:import>
	</div>
	
	<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
	<div id="page-finduser-main-box">
			<div class="tp-box">
				<div>
				</div>
			</div>
			<div class="hd-box">
				<h3 class="green">密码找回</h3>
			</div>
			<div class="bd-box">
				<form id="page-body-finduser-form" action="user.find.json.do" method="post">
					<table border="0" cellpadding="0" cellspacing="0" id="page-finduser-info-box">
						<tr>
							<td class="label">您的用户名：</td>
							<td class="content"><input name="username" type="text" size="26" value="<c:out value="${ACTOR.username}" />" validate="required:true,username:true" /></td>			
							<td class="msg">必填！6-20个字符，由字母、数字、减号或下划线组成，只能以数字或字母开头和结尾。</td>
						</tr>
						<tr>
							<td class="label">密码问题： </td>
							<td class="content">
								<select name="question" id="page-finduser-question-text">
									<option value="">---请选择---</option>
									<option value="我的宠物名字?">我的宠物名字?</option>
									<option value="我最好的朋友是谁?">我最好的朋友是谁?</option>
									<option value="我最喜爱的颜色?">我最喜爱的颜色?</option>
									<option value="我最喜爱的电影?">我最喜爱的电影?</option>
									<option value="我最喜爱的影星?">我最喜爱的影星?</option>
									<option value="我最喜爱的歌曲?">我最喜爱的歌曲?</option>
									<option value="我最喜爱的食物?">我最喜爱的食物?</option>
									<option value="我最大的爱好?">我最大的爱好?</option>
									<option value="我中学校名全称是什么?">我中学校名全称是什么?</option>
									<option value="我的座右铭是?">我的座右铭是?</option>
									<option value="我最喜欢的小说的名字?">我最喜欢的小说的名字?</option>
									<option value="我最喜欢的卡通人物名字?">我最喜欢的卡通人物名字?</option>
									<option value="我母亲父亲的生日?">我母亲父亲的生日?</option>
									<option value="我最欣赏的一位名人的名字?">我最欣赏的一位名人的名字?</option>
									<option value="我最喜欢的运动队全称?">我最喜欢的运动队全称?</option>
									<option value="我最喜欢的一句影视台词?">我最喜欢的一句影视台词?</option>	
								</select>
							</td>			
							<td  class="msg">
								必选！
							</td>
						</tr>
						<tr>
							<td class="label">密码答案：</td>
							<td class="content"><input type="text"  name="answer" size="26"/></td>			
							<td class="msg" >必填！ </td>
						</tr>
						<tr>
							<td class="label">新密码：</td>
							<td class="content">
								<input type="password"  name="password" id="page-finduser-password-text" size="26" />
							</td>			
							<td class="msg" >必填！密码长度6～16位，字母区分大小写</td>
						</tr>
						<tr>
							<td class="label">确认新密码:</td>
							<td class="content"><input type="password"  name="repassword" size="26" /></td>			
							<td class="msg"></td>
						</tr>
						<tr>
							<td class="label">验证码：</td>
							<td class="content">
								<input  name="imgCode" value="" type="text" size="10"/>
								<img id="AuthImg" align="top" src="../user/authImg.authImage.vi" />
							</td>
							<td class="msg">
								<a href="javascript:Util.refreshAuthImage();"  style="text-decoration: none;color: red;" >[刷新]</a>
								请输入验证码！
							</td>
						</tr>					
						 
					</table>			
					<div align="center" id="page-finduser-buttons">
						<input type="submit" title="确认" value="确认" id="page-finduser-submit-button" >
						<input type="reset" title="重新输入" value="返回" id="page-finduser-reset-button" onclick="location.href='index.jsplayout.vi';">
					</div>	
				</form>
			</div>
		<div class="bt-box"><div></div></div>	
	</div>
	<div style="clear: both;"></div>
</div>				
<script type="text/javascript">

$(document).ready(function(){
	$.metadata.setType("attr", "validate");
	
	$.validator.addMethod("username", function(value) {
			var ptn_uid =/^[\da-zA-Z][\w\.\+\-]{4,18}[\da-zA-Z]$/;
			return ptn_uid.test(value);
	},'由字母、数字、减号或下划线组成，只能以数字或字母开头和结尾，用户名长度为6～20个字符');
	
	
	$('form#page-body-finduser-form').validate({
		errorClass: "errorClass",
		errorElement: "div",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td"));
  		},
  		rules: {
			password: {
				required: true,
				rangelength: [6,16]
			},
			repassword: {
				required: true,
				rangelength: [6,16],
				equalTo: "#page-finduser-password-text"
			},
			question: {
				required: true
			},
			answer: {
				required: true
			},
			imgCode:{
				required: true,
				maxlength: 4,
				minlength: 4,
				number:true
			}
		},
		messages: {
			password: {
				required: "请输入正确的密码",
				rangelength: "6~16位字符长度"
			},
			repassword: {
				required: "请输入确认密码",
				rangelength: "6~16位字符长度",
				equalTo: "两次输入不正确"
			},
			question: "请输入密码问题",
			answer: "请输入密码答案",
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
   						alert("修改成功" );
						window.location = 'index.jsplayout.vi';
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

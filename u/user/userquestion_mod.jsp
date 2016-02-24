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
			密码保护
			<a href="../user/index.jsplayout.vi" style="color: white;margin-left: 650px;">【返回】</a>
		</h3>
	</div>
	<div class="bd-box">
 
	<form id="page-body-modquestion-form" action="userquestion.mod.json.do" method="post">
		<table border="0" cellpadding="0" cellspacing="0" id="page-modquestion-info-box">
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
				<td class="label">原密码问题：</td>
				<td class="content">
					<input type="text" disabled value="<c:out value="${ACTOR.question}" />" name="oldquestion" size="26"/>
				</td>
				<td class="msg">
				</td>
			</tr>
			<tr>
				<td class="label">原密码答案： </td>
				<td class="content">
					<input name="oldanswer" type="text"size="26" />
				</td>
				<td class="msg">必填！</td>
			</tr>
			 
			<tr>
				<td class="label">新密码问题：</td>
				<td class="content">
					<select name="question">
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
				<td class="msg">
					必选！
				</td>
			</tr>
			<tr>
				<td class="label">新密码答案： </td>
				<td class="content">
					<input name="answer" type="text" size="26" />
				</td>
				<td class="msg">必填！
				</td>
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
				
		<div align="center" id="page-modquestion-buttons">
			<input type="submit" title="确认" value="确认" id="page-modquestion-submit-button"<c:if test='${empty ACTOR}'>disabled</c:if> >
			<input type="button" title="返回" value="返回" id="page-modquestion-reset-button" onclick="javasrcipt:questionMod.cancel();">
		</div>	
				
	</form>

	</div>
	<div class="bt-box"><div></div></div>	
</div>

			 
<script type="text/javascript">
var questionMod = {
	cancel: function() {
		window.location ='../user/index.jsplayout.vi';
	}
};

$(document).ready(function(){
	$('form#page-body-modquestion-form').validate({
		errorClass: "errorClass",
		errorElement: "div",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td"));
  		},
  		rules: {
			question: {
				required: true
			},
			answer: {
				required: true
			},
			oldanswer: {
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
			question: "请输入密码问题",
			answer: "请输入密码答案",
			oldanswer: "请输入密码答案",
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<style>
</style>


<div id="page-reg-box" align="center">
	<div id="page-reg-main-box" >	
			
			<div class="hd-box">
				<h3 class="green" align="left">
					用户注册
					<a href="../portal/index.jsplayout.vi" class='button' style="margin-left: 450px;">返回</a>
				</h3>
			</div>
			<div class="bd-box">
				<form action="../portal/user.reg.json.do" method="post" id="page-body-reg-form">
					<table border="0" cellpadding="0" cellspacing="0" id="page-reg-info-box">
						<c:if test="${not empty REASON}">
							<tr>
								<td colspan="3">
									<DIV id="SOR_EXCEPTION" style="color:red;" align="center"><c:out value="${REASON}" /></DIV>
								</td>
							</tr>
						</c:if>
						<tr>
							<td class="label" >用户名：</td>
							<td class="content">
								<input type="text" name="username"   id="page-reg-user-text" validate="required:true,username:true" size="26" />
							</td>
							<td class="msg">
								必填！6-20个字符，由字母、数字、减号或下划线组成，只能以数字或字母开头和结尾。
							</td>
						</tr>
						<tr>
							<td class="label">游戏名：</td>
							<td class="content">
								<input type="text" name="nickname" id="page-reg-nickname-text" validate="required:true,nickname:true"   size="26"  />
							</td>
							<td class="msg">
								必填！2-15个字符，5个汉字以内。
							</td>
						</tr>
					 
						<tr>
							<td class="label">密码：</td>
							<td class="content">
								<input name="password" type="password"   id="page-reg-password-text" size="26" />
							</td>
							<td class="msg">
								必填！密码长度6～16位，字母区分大小写。
							</td>
						</tr>
						<tr>
							<td class="label">密码确认：</td>
							<td class="content">
								<input name="repassword" type="password" id="page-reg-repassword-text"size="26"/>
							</td>
							<td class="msg"></td>
						</tr>
						 <tr>
							<td class="label"> QQ：</td>
							<td class="content">
								<input type="text" name="qq" size="26"/>
							</td>
							<td class="msg">
								
							</td>
						</tr>
						<tr>
							<td class="label">密码问题：</td>
							<td class="content">
								<select name="question" id="page-reg-question-text">
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
							<td class="label">密码答案：</td>
							<td class="content">
								<input type="text" name="answer" id="page-reg-answer-text" size="26"/>
							</td>
							<td class="msg"">必填！</td>
						</tr>
						<tr>
							<td class="label">性别：</td>
							<td class="content">
								<input name="sex" id="sex" value="女" type="radio" />&nbsp;女
								<input name="sex" id="sex" value="男" type="radio"  />&nbsp;男
								<input name="sex" id="sex" value="保密" type="radio" checked/>&nbsp;保密
							</td>
							<td class="msg">必选！ 
							</td>
						</tr>
						
						<tr>
							<td class="label"> E-mail： </td>
							<td class="content">
								<input type="text"  name="email" id="page-reg-email-text"size="26"/>
							</td>
							<td class="msg">必填！例如：example@163.com</td>
						</tr>
						
						<tr>
							<td class="label" >职业：</td>
							<td class="content">
								<input name="profession" value="Player" type="radio" checked/>&nbsp;玩家
								<input name="profession" value="Referee" type="radio" />&nbsp;裁判
								<input name="profession" value="Organizer" type="radio" />&nbsp;组织者
							</td>
							<td class="msg">
							</td>
						</tr>
						
						<tr>
							<td class="label">出生日期：</td>
							<td class="content">
								<input type="text" class="Wdate" name="birthday" size="26"  validate="required:true"  onfocus="new WdatePicker(this,null,false,'whyGreen')"/>
							</td>
							<td class="msg">
								必选！
							</td>
						</tr>
						
						<tr>
							<td class="label">战区：</td>
							<td class="content">
								<select name="warzoneid" id="warzoneid">
									<c:forEach var="warzone" items="${warzones}">
									<option value="<c:out value='${warzone.id }'/>" title="<c:out value='${warzone.memo }'/>"><c:out value='${warzone.name }'/></option>
									</c:forEach>
								</select>
							</td>
							<td class="msg" style="color: orange;">
							</td>
						</tr>
						
						<tr>
							<td class="label"> 验证码： </td>
							<td class="content">
								<input id="imgCode" name="imgCode" value="" type="text" size="10" maxlength="4"/>
								<img id="AuthImg" align="top" src="../portal/authImg.authImage.vi" />
							</td>
							<td class="msg">
									<a href="javascript:Util.refreshAuthImage();"  style="text-decoration: none;color: red;" >[刷新]</a>
									请输入验证码！
							</td>
						</tr>
					</table>
					<div align="center" id="page-reg-buttons">
						<input type="submit" id="page-reg-submit-button" value="注册" />
						<input type="reset" id="page-reg-reset-button" value="重置" />
					</div>
				</form>
			
			</div>
	</div>
	
	<div style="clear: both;"></div>
</div>
<script type="text/javascript">
var Nickname = {
	checkLength: function(text) {
		var val = text;
		var valLength=0;
		valLength = val.replace(/[^\x00-\xff]/g,"***").length;
		if(valLength>15 || valLength<2){
			return false;
		}else return true;
	}
};

$(document).ready(function(){

	$('#warzoneid')
		.change(function() {
			var $option = $(':selected', this);
			$(this).parent("td").next().html($option.attr('title'));
		})
		//强行执行change事件
		.trigger('change');
	
	$.metadata.setType("attr", "validate");
	
	$.validator.addMethod("username", function(value) {
			var ptn_uid =/^[\da-zA-Z][\w\.\+\-]{4,18}[\da-zA-Z]$/;
			return ptn_uid.test(value);
	},'由字母、数字、减号或下划线组成，只能以数字或字母开头和结尾，用户名长度为6～20个字符');
	
	$.validator.addMethod("nickname", function(value) {
			return Nickname.checkLength(value);
	},'2-15个字符，5个汉字以内。');
	
	$('form#page-body-reg-form').validate({
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
				equalTo: "#page-reg-password-text"
			},
			qq: {
				required: true,
				number:true
			},
			question: {
				required: true
			},
			answer: {
				required: true
			},
			email: {
				required: true,
				email: true
			} ,
			sex: {
				required: true
			},
			profession: {
				required: true
			},
		
			birthday: {
				required: true
			},
			warzoneid: {
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
			nickname: {
				required: "游戏名不可以为空！",
				rangelength: "2~10位字符长度"
			},
			password: {
				required: "请输入正确的密码",
				rangelength: "6~16位字符长度"
				
			},
			repassword: {
				required: "请输入确认密码",
				rangelength: "6~16位字符长度",
				equalTo: "两次输入不正确"
			},
			qq: {
				required: "QQ号不能为空",
				number: "请输入数字类型"
			},
			question: "请输入密码问题",
			answer: "请输入密码答案",
			email: "邮件格式错误",
			sex: "请选择性别",
			profession: "请输入您的职业",
			birthday: "请输入您的出生年月日",
			warzoneid: "请输入您的战区",
			imgCode:{
				required: "请输入验证码",
				maxlength: "超出字符长度",
				minlength: "字符长度不足",
				number: "请输入数字类型"
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
	   					return;
   					}
   					
   					var bsid = "<c:out value='${bsid}'/>";
   					var bstype = "<c:out value='${bstype}'/>";
   					if(bsid && bstype) {
						// 团体赛
   						if('r' == bstype) {
	   						var $page = $('<div class="message-win"/>');
	   						$page
	   							.append($('<div style="color:red;">用户已创建! 团体赛需要战队才能报名，请填写您的战队信息</div>'))
	   							.append($('<div/>').load('../user/team.create.jsp.vi?bsid=' + bsid + '&bstype=' + bstype));
							PopupWinX.create($page).show();	  		
   						}
   						// 个体赛 
   						else {
   							window.location.href = "http://www.51bisai.com/u/"+ bsid +"/bisaiSignup/signup.jsplayout.vi";
   						}
   					}
   					else {
						//window.location = '../user/index.jsplayout.vi';
						window.location = '../user/user_avator.jsplayout.vi';
					}	
	   			}	
	   		});
	   	}
 	});
});

</script>


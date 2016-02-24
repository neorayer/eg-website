<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>
<!--
#page-leftbar-userinfo-box{
	float: left;
	width: 252px;
}

#page-main-userinfo-box {
	margin-left:20px;
	width: 580px; 
	float: left;
}


#userInfor-add-box{
	width: 80%;
	margin: 10px auto;
	
}
#userInfor-add-box tr{
	border-bottom:1px dashed #818181;
}

#userInfor-add-box td{
	padding:10px 5px;
}
#userInfor-add-box td.label{
	width: 150px;
	text-align:right;
	color: #73A61D;
	font-size: 12px;
	font-weight: bolder;
}
#userInfor-add-box td.content{
}
#userInfor-add-box td.msg{
	color: #818181;
	width: 250px;
	font-size: 12px;
	font-weight: lighter;
}
-->
</style>



<div id="page-leftbar-userinfo-box">
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="green">
				用户信息
		</h3>
	</div>
	<div class="bd-box">
		
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
	</div>
	<div class="bt-box"><div></div></div>
</div>




<div id="page-main-userinfo-box">
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="green">
			你的信息不完整，请填写表单，补充你的信息
		</h3>
	</div>
	<div class="bd-box">
		<form action="../user/userInfor.add.json.do" method="post" id="page-add-userinfor-form">
			<table border="0" cellpadding="0" cellspacing="0" id="userInfor-add-box">
				<c:if test="${not empty REASON}">
					<tr>
						<td colspan="3">
							<DIV id="SOR_EXCEPTION" style="color:red;" align="center"><c:out value="${REASON}" /></DIV>
						</td>
					</tr>
				</c:if>
				
				<tr>
					<td class="label" >职业：</td>
					<td class="content">
						<input name="profession" value="Player" type="radio" checked/>&nbsp;玩家
						<input name="profession" value="Referee" type="radio" />&nbsp;裁判
						<input name="profession" value="Organizer" type="radio" />&nbsp;组织者
					</td>
					<td class="msg">
						必选！
					</td>
				</tr>
				
				<tr>
					<td class="label">出生日期：</td>
					<td class="content">
							<input type="text" class="Wdate" name="birthday" size="26" validate="required:true"  onfocus="new WdatePicker(this,null,false,'whyGreen')"/>
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
					<td class="label"></td>
					<td class="content">
						<input type="submit" title="下一步" value="下一步" class="button"/>
					</td>
					<td class="msg">
					</td>
				</tr>		 
			</table>
		</form>
		
	</div>
	<div class="bt-box"><div></div></div>

</div>




<script type="text/javascript">

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
	
	$('form#page-add-userinfor-form').validate({
		errorClass: "errorClass",
		errorElement: "div",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td"));
  		},
  		rules: {
			profession: {
				required: true
			},
		
			birthday: {
				required: true
			},
			warzoneid: {
				required: true
			}
		},
		messages: {
			profession: "请输入您的职业",
			birthday: "请输入您的出生年月日",
			warzoneid: "请输入您的战区"
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
   					
 					alert("补充信息成功！");
					window.location = 'index.jsplayout.vi';
					
	   			}
	   		});
	   	}
 	});
});

</script>

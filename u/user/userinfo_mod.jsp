<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>


<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>修改信息</strong>
		</div>
		<div class="floatRight">
			<a href="../user/user_avator.jsplayout.vi">修改头像</a>&nbsp;
			<a href="../user/index.jsplayout.vi">返回>></a>
		</div>
	</div>
	<div id="allvisitor-page-body">

	<form id="page-body-moduser-form" action="../user/userinfo.mod.json.do" method="post">
		<table border="0" cellpadding="0" cellspacing="0" id="page-moduser-info-box">
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
				<td class="label">游戏名：</td>
				<td class="content"><input type="text" name="nickname" size="26"  id="nickname" value="<c:out value='${ACTOR.nickname}'/>"/></td>			
				<td class="msg">2-10个字符</td>
			</tr>
			<tr>
				<td class="label">真实姓名：</td>
				<td class="content"><input type="text"  name="realname" size="26" maxlength="10" id="realname" value="<c:out value='${ACTOR.realname}'/>"></td>			
				<td class="msg">推荐汉字格式</td>
			</tr>
			<tr>						
				<td class="label">性别：</td>
				<td class="content">
					<input name="sex" id="sex" value="女" type="radio" <c:if test="${ACTOR.sex eq '女'}">checked</c:if> />&nbsp;女
					<input name="sex" id="sex" value="男" type="radio" <c:if test="${ACTOR.sex eq '男'}">checked</c:if> />&nbsp;男
					<input name="sex" id="sex" value="保密" type="radio" <c:if test="${ACTOR.sex eq '保密'}">checked</c:if> />&nbsp;保密
				</td>
				<td class="msg">必选！</td>
			</tr>
			<tr>
				<td class="label">Email：</td>
				<td class="content"><input value="<c:out value='${ACTOR.email}'/>" type="text" id="email"size="26"   name="email" /></td>
				<td class="msg">例如：example@163.com</td>
			</tr>
			<tr>
				<td class="label">身份证：</td>
				<td class="content"><input type="text" id="idcard"  size="26" 
					name="idcard" value="<c:out value='${ACTOR.idcard}'/>" validate="idcard:true" /> </td>
				<td class="msg">15或18位数字</td>
			</tr>
			<tr>
				<td class="label">QQ：</td>
				<td class="content"><input type="text"  name="qq" size="26"
					id="qq" value="<c:out value='${ACTOR.qq}'/>"></td>
				<td class="msg">数字格式</td>
			</tr>
			<tr>
				<td class="label">电话：</td>
				<td class="content"><input type="text"  name="telphone" size="26"
					id="telphone" value="<c:out value='${ACTOR.telphone}'/>"></td>
				<td class="msg">数字格式</td>
			</tr>
			<tr>
				<td class="label">手机号：</td>
				<td class="content"><input type="text" maxlength="11"  size="26"
					name="phone" id="phone" value="<c:out value='${ACTOR.phone}'/>" ></td>
				<td class="msg">11位数字</td>
			</tr>			
			<tr>
				<td class="label">来自：</td>
				<td class="content">
					<select id="province"  name="province" >			
						<option value="">---请选择---</option>
						<option value="北京" <c:if test="${ACTOR.province eq '北京'}">selected</c:if>>北京</option>
						<option value="天津" <c:if test="${ACTOR.province eq '天津'}">selected</c:if>>天津</option>
						<option value="河北省" <c:if test="${ACTOR.province eq '河北省'}">selected</c:if>>河北省</option>
						<option value="内蒙古自治区" <c:if test="${ACTOR.province eq '内蒙古自治区'}">selected</c:if>>内蒙古自治区</option>
						<option value="山西省" <c:if test="${ACTOR.province eq '山西省'}">selected</c:if>>山西省</option>
						
						<option value="上海" <c:if test="${ACTOR.province eq '上海'}">selected</c:if>>上海</option>
						<option value="安徽省" <c:if test="${ACTOR.province eq '安徽省'}">selected</c:if>>安徽省</option>
						<option value="江苏省" <c:if test="${ACTOR.province eq '江苏省'}">selected</c:if>>江苏省</option>
						<option value="浙江省" <c:if test="${ACTOR.province eq '浙江省'}">selected</c:if>>浙江省</option>
						<option value="山东省" <c:if test="${ACTOR.province eq '山东省'}">selected</c:if>>山东省</option>
						<option value="福建省" <c:if test="${ACTOR.province eq '福建省'}">selected</c:if>>福建省</option>
						<option value="江西省" <c:if test="${ACTOR.province eq '江西省'}">selected</c:if>>江西省</option>
						
						<option value="广东省" <c:if test="${ACTOR.province eq '广东省'}">selected</c:if>>广东省</option>
						<option value="广西壮族自治区" <c:if test="${ACTOR.province eq '广西壮族自治区'}">selected</c:if>>广西壮族自治区</option>
						<option value="海南省" <c:if test="${ACTOR.province eq '海南省'}">selected</c:if>>海南省</option>
						
						<option value="河南省" <c:if test="${ACTOR.province eq '河南省'}">selected</c:if>>河南省</option>
						<option value="湖北省" <c:if test="${ACTOR.province eq '湖北省'}">selected</c:if>>湖北省</option>
						<option value="湖南省" <c:if test="${ACTOR.province eq '湖南省'}">selected</c:if>>湖南省</option>
						
						<option value="辽宁省" <c:if test="${ACTOR.province eq '辽宁省'}">selected</c:if>>辽宁省</option>
						<option value="吉林省" <c:if test="${ACTOR.province eq '吉林省'}">selected</c:if>>吉林省</option>
						<option value="黑龙江省" <c:if test="${ACTOR.province eq '黑龙江省'}">selected</c:if>>黑龙江省</option>
						
						<option value="陕西省" <c:if test="${ACTOR.province eq '陕西省'}">selected</c:if>>陕西省</option>
						<option value="甘肃省" <c:if test="${ACTOR.province eq '甘肃省'}">selected</c:if>>甘肃省</option>
						<option value="青海省" <c:if test="${ACTOR.province eq '青海省'}">selected</c:if>>青海省</option>
						<option value="宁夏回族自治区" <c:if test="${ACTOR.province eq '宁夏回族自治区'}">selected</c:if>>宁夏回族自治区</option>
						<option value="新疆维吾尔自治区" <c:if test="${ACTOR.province eq '新疆维吾尔自治区'}">selected</c:if>>新疆维吾尔自治区</option>
						
						<option value="重庆市" <c:if test="${ACTOR.province eq '重庆市'}">selected</c:if>>重庆市</option>
						<option value="四川省" <c:if test="${ACTOR.province eq '四川省'}">selected</c:if>>四川省</option>
						<option value="贵州省" <c:if test="${ACTOR.province eq '贵州省'}">selected</c:if>>贵州省</option>
						<option value="云南省" <c:if test="${ACTOR.province eq '云南省'}">selected</c:if>>云南省</option>
						<option value="西藏自治区" <c:if test="${ACTOR.province eq '西藏自治区'}">selected</c:if>>西藏自治区</option>
						
						<option value="香港特别行政区" <c:if test="${ACTOR.province eq '香港特别行政区'}">selected</c:if>>香港特别行政区</option>
						<option value="澳门特别行政区" <c:if test="${ACTOR.province eq '澳门特别行政区'}">selected</c:if>>澳门特别行政区</option>
						<option value="台湾省" <c:if test="${ACTOR.province eq '台湾省'}">selected</c:if>>台湾省</option>
						<option value="海外" <c:if test="${ACTOR.province eq '海外'}">selected</c:if>>海外</option>
					</select>
				</td>
				<td  class="msg">可选填，想体验此功能的用户填写</td>
			</tr>
			<tr>
				<td class="label">签名：</td>
				<td width="30%">
					<textarea  name="signature"><c:out value="${ACTOR.signature}" /></textarea>
				</td>
				<td class="msg">100个字符</td>
			</tr>
			
			<tr>
				<td class="label">验证码：</td>
				<td class="content">
					<input  name="imgCode" value="" type="text" size="10"  maxlength="4"/>
					<img id="AuthImg" align="top" src="../user/authImg.authImage.vi" />
				</td>
				<td class="msg">
						<a href="javascript:Util.refreshAuthImage();"  style="text-decoration: none;color: red;" >[刷新]</a>
					请输入验证码！
				</td>
			</tr>					
	 
		</table>		
		<div align="center" id="page-moduser-buttons">
			<input type="submit" title="确认" value="确认"   class="button"
				<c:if test='${empty ACTOR}'>
					disabled
				</c:if>
			>
			<input type="button" title="返回" value="返回"  onclick="javasrcipt:userInforMod.cancel();" class="button">
		</div>	
	</form>

	</div>
</div>



<script type="text/javascript">

var userInforMod = {
	cancel: function() {
		window.location ='../user/index.jsplayout.vi';
	}
};


;$(document).ready(function(){
	$.metadata.setType("attr", "validate");
		$.validator.addMethod("idcard", function(value) {
			if($.trim(value) == '')
				return true;
			var ptn_idcard =/^[0-9]{15}$|^[0-9]{17}[0-9x|X]$/;
			return ptn_idcard.test(value); 
		},'身份证格式为15位或者18位!');
		
		$('#page-body-moduser-form').validate({
		errorClass: "errorClass",
		errorElement: "div",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td"));
  		},
  		rules: {
			nickname: {
				required: true,
				rangelength: [2,10]
			},
			email: {
				required: true,
				email: true
			} ,
			 
			signature:{
				rangelength: [0,100]
			},
			avatar:{
				accept:"jpg|jpeg|gif|png" 
			},
			phone:{
				number:true,
				maxlength: 11,
				minlength: 11
			},
			telephone:{
				number:true
			},
			sexy: {
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
			phone:{
				maxlength: "超出字符长度",
				minlength: "字符长度不足",
				number: "请输入数字类型"
			},
			telephone:{
				number: "请输入数字类型"
			},
			nickname: {
				required: "游戏名不可以为空！",
				rangelength: "2~10位字符长度"
			},
			avatar:{
				accept:"格式:JPG|JPEG|GIF|PNG" 
			},
			signature:"字符限制100个字符！",
			email: "邮件格式错误",
			sexy: "请选择性别",
			imgCode:{
				required: "请输入验证码",
				minlength:"4位数字",
				maxlength:"4位数字",
				number: "请输入数字类型"
			}
		},
  		submitHandler: function(form) {
			var action = $(form).attr('action');
			action = action.replace(/jsp(layout)?/i,'json');
			$(form).attr('action',action);
	   		$(form).ajaxSubmit({
	   			success: function(data) {
					var responseText = data.replace(/<\/pre>/i, '').replace(/<pre>/i, '');
					var data = eval('(' +responseText + ')');
	   				if(data.res == 'no') {
	   					alert(data.data);
	   					Util.refreshAuthImage();
	   					return;
   					}
   					
					userInforMod.cancel();
   					
	   			}
	   		});
	   	}
 	});
	
});
</script>

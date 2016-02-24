<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--

#page-team-mod-box {
	height: 300px;
	padding-top: 20px;
}

#team-image-mod-box {
	float: left;	
	margin: 20px 10 10px 10px;
	border: 1px solid gray;
	padding: 2px;
	padding-bottom: 0px;
	background-color: #eeeeee;
	width: 170px;
}

#page-team-mod-form {
	float:left;
	height: 262px;
	width:600px;
	margin-left:20px;
	border: 1px solid gray;
	overflow: hidden;
}
#team-mod-box {
	margin: 10px 0px 0px 10px;
}
#team-mod-box td {
	text-align: left;
	padding: 5px;
}

#team-mod-box td.label {
	vertical-align: top;
}

#page-team-back-button {
	color: white;
	margin-right: 20px;
}

#team-logo-mod-button-box {
	text-align: center;
	height: 24px;
	line-height: 24px;
}
-->
</style>

<div class="box">
	<div class="tp-box"><div></div></div>
	<div class="hd-box">
		<h3 class="blue" style="text-align: left;">
			修改战队信息
		</h3>
	</div>
	
	<div class="bd-box" id="page-team-mod-box">
		<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
		
		<div id="team-image-mod-box">
			<img id="team-logo-image" width="170" height="232"  src="<c:out value='${team.logoPath}'/>">
			<div id="team-logo-mod-button-box">
				<a href="../team/team.upload.jsplayout.vi">[修改徽标]</a>
			</div>
		</div>

		<form id='page-team-mod-form' action='../team/team.mod.json.do' method="post" 
			enctype="multipart/form-data" target="page-upLoadIframe" >
			<input type="hidden" name="id" value="<c:out value="${team.id }" />">
		
			<table id="team-mod-box">
				<tr>
					<td class="label">战队名称：</td>
					<td>
						<c:out value='${team.teamName}'/>
					</td>
				</tr>
				
				<tr>
					<td class="label">队 长：</td>
					<td>
						<c:out value='${team.teamLeader.nickname }'/>
					</td>
				</tr>
				
				<tr>
					<td class="label">QQ群号：</td>
					<td>
						<input type='text' name='qq' value='<c:out value="${team.qq }" />'/>
					</td>
				</tr>
				
				<tr>
					<td class="label">战队简介：</td>
					<td>
						<textarea id="memo" name="memo" style="height: 80px; width: 380px;"><c:out value="${team.memo }"/></textarea>
					</td>
				</tr>
				<tr>
					<td class="label"></td>
					<td>
						<input type="submit" class='button' value="保存" />
						<input type="button" class='button' onclick="Team_TeamMod_Page.do_back('<c:out value="${team.id }" />');" value="返回">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div class="bt-box"><div></div></div>
</div>


<script type="text/javascript">
var Team_TeamMod_Page = {
	do_back: function() {
		window.location ="../team/index.jsplayout.vi";
	},
	team_uploadImg: function(teamId) {
		$("#page-team-mod-box").load("../team/team.upload.jsp.vi?id=" + teamId);
	}
};

$(document).ready(function(){
	$('#memo').val($('#memo').val().replace(/<br\/>/gi, '\n'));

	$('#page-team-mod-form').validate({
		errorClass: "errorClass",
		errorElement: "span",
 		errorPlacement: function(error, element) {
    		error.insertAfter(element);
  		},
  		rules: {
			qq: {
				required: true,
				maxlength: 16,
				minlength: 3,
				number:true
			},
			memo: {
				required: true
			}
		},
		messages: {
			qq: {
				required: "请输入您的QQ号码",
				maxlength: "超出字符长度",
				minlength: "字符长度不足",
				number: "请输入数字类型"
			},
			memo: "请输入您的战队描述"
		},
  		submitHandler: function(form) {
  			var $memo = $(form.memo);
  			$memo.val($memo.val().replace(/\r|\n|\r\n/gi,'<br/>'));
  		
	   		$(form).ajaxSubmit(function(data) {
	   			var responseText = data.replace(/<\/pre>/i, '').replace(/<pre>/i, '');
				var data = eval('(' +responseText + ')');
	   		
	   			if(data.res == 'no') {
	   				alert(data.data);
	   				return;
	   			}
	   			
	   			alert('修改成功!');
	   			window.location.href = '../team/index.jsplayout.vi';
			});
	   	}
 	});
});
</script>
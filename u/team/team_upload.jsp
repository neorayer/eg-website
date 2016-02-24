<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>

#team-cur-logo-box{
	float: left;
	width:200px;
}

#team-portlet-upload-box {
	float: left;
	margin-left: 30px;
}

#team-cur-logo-title-box, 
#team-portlet-upload-title-box {
	border-bottom: 1px solid #71a4b9;
	margin: 5px 0px 15px 0px;
}

#logo-file-input {
	margin-right: 15px;
	background-color: #eeeeee;
	color: black;
}
</style>


<div>	
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="blue">修改徽标</h3>
	</div>
	<div class="bd-box">
		<div id="team-cur-logo-box">
			<div id="team-cur-logo-title-box">当前徽标:</div>
			<img style="border:1px solid gray; padding: 2px;" width="170" height="232" src="<c:out value='${team.logoPath}'/>" />
		</div>
	
		<div id="team-portlet-upload-box">
			<div id="team-portlet-upload-title-box">上传战队徽标:</div>
			<form id="page-upload-form" action="../team/team.upload.json.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="<c:out value="${team.id }" />">
				<input type="file" name="logo" class="button" id="logo-file-input"/>
				<div style="margin: 5px 0px;">
					<input class="button" type="submit" value="上传"/>
					<input class="button" type="button" value="返回" onclick="javascript: history.back()"/>
				</div>		
			</form>
		 	<p style="margin-top: 20px">*注: 图片格式:JPG。 </p>
		</div>	
		
		<div style="clear:both;"></div>
	</div>
	<div class="bt-box"><div></div></div>	
</div>		
			
<script type="text/javascript">

var Team_TeamUpload_page = {
	back: function() {
		window.location="../team/team.mod.jsplayout.vi?id=<c:out value='${team.id }' />";
	},
	
	team_logo_edit: function(teamId) {
		$("#page-team-mod-box").load("../team/team_logo.edit.jsp.vi?id=<c:out value='${team.id }'/>");
	}
}

$(document).ready(function(){
	$('form#page-upload-form').validate({
		errorClass: "errorClass",
		errorElement: "span",
		errorPlacement: function(error, element) {
    		error.insertAfter(element);
  		},
  		rules: {
			logo:{
				required: true,
				accept:"jpg|jpeg" 
			} 
		},
		messages: {
			logo:{
				required: "请选择图片",
				accept:"格式:JPG|JPEG" 
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
	   					return;
   					}
   					window.location.href = "../team/team_logo.edit.jsplayout.vi";
	   			}
	   		});
	   	}
 	});
});
</script>

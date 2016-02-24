<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>
 


<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>修改头像</strong>
		</div>
		<div class="floatRight"><a href="../album/album_avator.jsplayout.vi;">头像相册>></a></div>
	</div>
	<div id="page-photos-body-box">
		<ul id="page-photos-toolbar-box">
			<li><a href="../user/user_avator_edit.jsplayout.vi" class="">修改头像</a></li>
			<li><a href="../album/album_avator.jsplayout.vi;" class="">其他头像</a></li>
		</ul>
		
		<div style="clear: both;"></div>
		<div id="page-avator-main-box">
		 
			<div id="page-curavator-box">
				<h3 class="avator-title">当前大头像</h3>
				<div>
					<img width="170" height="232"   src="<c:out value='${ACTOR.avatorUrl}'/>" />
				</div>
			</div>
			
			<div id="page-cursmallavator-box">
				<h3 class="avator-title">当前小头像  </h3>
				<div>
					<img width="50" height="50" src="<c:out value='${ACTOR.avatorSmallUrl}'/>"/>
				</div>
			</div>
			
			<div id="page-uploadavator-box">
				<h3 class="avator-title">上传头像</h3>
				<form id="page-upload-form" action="../album/avator.upload.json.do" method="post" enctype="multipart/form-data">
					<input  type="hidden" name="id" value="<c:out value='${album.id}'/>" />
					<input  type="file" name="avatar" id="avatar" />
					<input type="submit" class="button" name="submit_button" value="上传"/>
				</form>
				<br/>
				<p>*图片格式:JPG|JPEG|GIF|PNG。</p>
				<p>你可以在上传的照片中编辑你的小头像，小头像尺寸为50X50像素。 </p>
			</div>
			<div style="clear: both;"></div>
		</div>

	</div>
		
	</div>
</div>

 
<script type="text/javascript">
 
</script><!--


<div>	
	
	<div class="hd-box">
		<h3 class="green">
			修改头像
		</h3>
	</div>
	
	<div class="bd-box">
		<span >
			<a href="../user/user_avator_edit.jsplayout.vi" class="button">修改头像</a>
			<a href="../album/album_avator.jsplayout.vi" class="button">头像相册</a>
			<a href="../user/index.jsplayout.vi" class="button">返回首页</a>
		</span>
		
		<div id="page-avator-main-box">
		 
			<div id="page-curavator-box">
				<h3 class="avator-title">当前大头像</h3>
				<div>
					<img width="170" height="232"   src="<c:out value='${ACTOR.avatorUrl}'/>" />
				</div>
			</div>
			
			<div id="page-cursmallavator-box">
				<h3 class="avator-title">当前小头像  </h3>
				<div>
					<img width="50" height="50" src="<c:out value='${ACTOR.avatorSmallUrl}'/>"/>
				</div>
			</div>
			
			<div id="page-uploadavator-box">
				<h3 class="avator-title">上传头像</h3>
				<form id="page-upload-form" action="../album/avator.upload.json.do" method="post" enctype="multipart/form-data">
					<input  type="hidden" name="id" value="<c:out value='${album.id}'/>" />
					<input  type="file" name="avatar" id="avatar" />
					<input type="submit" class="button" name="submit_button" value="上传"/>
				</form>
				<br/>
				<p>*图片格式:JPG|JPEG|GIF|PNG。</p>
				<p>你可以在上传的照片中编辑你的小头像，小头像尺寸为50X50像素。 </p>
			</div>
			<div style="clear: both;"></div>
		</div>

	</div>
	
</div>
	
	
--><script type="text/javascript">
 
var Avator={
	albumId: '<c:out value='${album.id}'/>',

	editavator:function(){
	 	window.location="../user/user_avator_edit.jsplayout.vi";
 	
	}
	
};
$(document).ready(function(){
	$('form#page-upload-form').validate({
		errorClass: "errorClass",
		errorElement: "span",
		errorPlacement: function(error, element) {
    		error.insertAfter(element.next());
  		},
  		rules: {
			avatar:{
				required: true,
				accept:"jpg|jpeg|gif|png" 
			} 
		},
		messages: {
			avatar:{
				required: "请选择图片",
				accept:"格式:JPG|JPEG|GIF|PNG" 
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
   					}else{
   						//$("#page-avator-main-box").parent().load("../album/user_avator.edit.jsp.vi?id=<c:out value='${album.id}'/>");
   						window.location='../user/user_avator_edit.jsplayout.vi';
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

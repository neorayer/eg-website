<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
	
</style>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<a href="../album/albums.jsplayout.vi"><strong>相册</strong></a>--
			<a href="../album/album_avator.jsplayout.vi"><c:out value="${album.title}"/></a>
		</div>
		<div class="floatRight"><a href="javascript:Album.realbums();">相册列表&#62;&#62;</a></div>
	</div>
	<div id="page-photos-body-box">
		<ul id="page-photos-toolbar-box">
			<li>
				<a href="javascript:" onclick="Album.delavator('<c:out value="${photo.id}"/>');"  class="">删除图片</a>
			</li>
			<li>
				<a href="javascript:" onclick="Album.setavator('<c:out value="${photo.id}"/>');"  class="">设置头像</a>
			</li>
		</ul>
		
		<div style="clear: both;"></div>
		<div id="page-photo-list" align="center">
			 <img src="<c:out value="${photo.photoimg}" />" class="photo-img"  />
			<br>
			<div align="center" id="page-photo-avator-description-box">
				<a  id="page-modphoto-link" href="JavaScript:Album.modPhotoDesc();" title="单击此处添加照片描述">
					<c:if test='${empty photo.description}'>单击此处添加照片描述
					</c:if>
					<c:if test='${not empty photo.description}'>
						<c:out value='${photo.description}'/>
					</c:if>
				</a>
				
				<form action="../album/photo.modDesc.json.do" id="page-modphoto-form" style="display: none;">
					<input type="hidden" name="id" value="<c:out value='${photo.id}'/>" />
					<textarea   name="description" id="description-textinput">
						<c:out value='${photo.description}' />
					</textarea>
					<br>
					<input id="page-modphoto-submit-button" type="submit" value="保存"/>
					<input id="page-modphoto-cancel-button" type="button" value="取消" onclick="JavaScript:Album.cancelPhotoDesc();"/>
				</form>
		</div>
		
		<div style="clear: both;"></div>
		
		
		<div align="left" id="page-photos-album-info-box">
			<ul id="page-photo-footer-box">
				<li>
					来自我的相册:	<a href="javascript:Album.openalbum('<c:out value="${album.id}" />');"><c:out value="${album.title}" /></a>
				</li>
				<li>
					上传于:	<c:out value='${photo.createDateTime}'/>
				</li>
			</ul>
			<div style="clear: both;"></div>
		</div>
		
	</div>
</div>



 
<script type="text/javascript">

$(document).ready(function(){
	$('form#page-modphoto-form').validate({
		errorClass: "errorClass",
		errorElement: "div",
  		rules: {
			description:{
				required: true,
				rangelength: [0,100]
			} 
		},
		messages: {
			description:{
				required: "请填写描述！",
				rangelength:"字符限制100个字符！"
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
						window.location.reload();
   					}
	   			}
	   		});
	   	}
 	});
});
 
</script>
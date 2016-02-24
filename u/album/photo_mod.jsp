<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
	
</style>
<div id="page-modphoto-box" class="page-box">
	<h3 class="h3-title hd-box green">
		<span class="h3-left">
			<img src="<c:out value="${curalbum.coverPicUrl}" />" width="50" height="50" align="top"/> 
		</span>
		<span class="h3-center">
			<a href="../album/albums.jsplayout.vi">我的相册</a>&nbsp;&#45;&#45;&nbsp;<c:out value="${curalbum.title}"/>
		</span>
		<span class="h3-right">
			<input class="button" type="button" onclick="javascript:Album.create();" value="上传照片"/>
		</span>
	</h3>
	<div style="clear: both;"></div>
	
	<div class="bd-box">
		<ul id="page-modphoto-toolbar-box">
			 <li><a href="../album/photo_upload.jsplayout.vi?id=<c:out value="${curalbum.id}"/>" class="button">上传图片</a></li>
			 <li><a href="../album/album_mod.jsplayout.vi" class="button">编辑相册</a></li>
			 <li><a href="../album/album_mod.jsplayout.vi" class="button">删除相册</a></li>
		</ul>
		
		<div style="clear: both;"></div>
		
		
	 	<form id="page-modphoto-form" action="../album/photo.mod.json.do" method="post" >
	 		<input type="hidden" name="id" value="<c:out value='${photo.id}'/>" />
			<img src="<c:out value="${photo.scalePhotoimg}" />" class="modphoto-left-img"/>
	 		<table id="page-modphoto-right-box" cellpadding="5" cellspacing="0"  >
	 			<tr>
	 				<td align="right">
	 					描述：
	 				</td>
	 				<td align="left">
						<textarea   name="description" id="description-textinput">
							<c:out value='${photo.description}' />
						</textarea>
					</td>
	 			</tr>
	 			<tr>
	 				<td align="right">
	 					设置封面:
	 				</td>
	 				<td align="left">
						<input type="checkbox" name="iscover" id="iscover-button"/>  
					</td>
	 			</tr>
	 			<!--<tr>
	 				<td align="right">
	 					转移到:
	 				</td>
	 				<td align="left">
						<select  name="albumid">
							<c:forEach var="album" items="${albums}">
								<option <c:if test="${album.id eq curalbum.id}">selected </c:if> value="<c:out value="${album.id}"/>"><c:out value="${album.title}"/></option>
							</c:forEach>
						</select>
					</td>
	 			</tr>
	 			--><tr>
	 				<td colspan="2">
						<input class="button" type="submit" value="提交"/>
						<input class="button" type="button" value="取消"/>
					</td>
	 			</tr>
	 			
	 		</table>
			<div style="clear: both;"></div>
	 	</form>
 	</div>
</div>
<script type="text/javascript">

$('#iscover-button').click(function() {
	if($('#iscover-button').attr('checked')) {
		$('#iscover-button').val('true');
	}else{
		$('#iscover-button').val('false');
	}
});

$(document).ready(function(){
 
	$('form#page-modphoto-form').validate({
		errorClass: "errorClass",
		errorElement: "label",
  		rules: {
			description: {
				rangelength: [0,100]
			} 
		},
		messages: {
			description: {
				rangelength: "0~100位字符长度"
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
   					}
   					else {
						window.location = '../album/photos.jsplayout.vi?id=<c:out value="${curalbum.id}"/>';
   					}
	   			}
	   		});
	   	}
 	});
});



</script>

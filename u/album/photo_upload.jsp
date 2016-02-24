<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
	
</style>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<a href="../album/albums.jsplayout.vi"><strong>相册</strong></a>--<c:out value="${album.title}"/>
		</div>
		<div class="floatRight"><a href="javascript:Album.realbums();">相册列表&#62;&#62;</a></div>
	</div>
	<div id="allvisitor-page-body">
		<form id="page-photo-upload-form" action="../album/photo.upload.json.do" method="post" enctype="multipart/form-data">
			<h3>上传照片:  你可以上传JPG, JPEG, GIF, PNG或BMP文件。</h3>
			<input type="hidden"   value="<c:out value="${album.id}"/>" name="id"/>
			<div style="width: 80%;">
				<input  type="file" name="file0"  align="top"/> 描述：
				<textarea rows="1" cols="20"  name="description0" ></textarea>
			</div>
			<div style="width: 80%;">
				<input  type="file" name="file1"  align="top"/> 描述：
				<textarea rows="1" cols="20"   name="description1" ></textarea>
			</div>
			<div style="width: 80%;">
				<input  type="file" name="file2"  align="top"/> 描述：
				<textarea rows="1" cols="20"   name="description2" ></textarea>
			</div>
			<div style="width: 80%;">
				<input  type="file" name="file3"  align="top"/> 描述：
				<textarea rows="1" cols="20"   name="description3" ></textarea>
			</div>
			<div style="width: 80%;">
				<input  type="file" name="file4"  align="top"/> 描述：
				<textarea rows="1" cols="20"   name="description4" ></textarea>
			</div>
			<br/>
			<input type="submit" class="button" name="submit_button" value="上传"/>
			<input type="button"  class="button" onclick="Album.rephotos('<c:out value="${album.id}"/>');" value="返回"/>
		</form>
	</div>
</div>

 
<script type="text/javascript">
$(document).ready(function(){
	$('form#page-photo-upload-form').validate({
		errorClass: "errorClass",
		errorElement: "span",
  		rules: {
			file:{
				required: true,
				accept:"jpg|jpeg|gif|png" 
			},
			file1:{
				accept:"jpg|jpeg|gif|png" 
			} , 
			file2:{
				accept:"jpg|jpeg|gif|png" 
			} ,
			file3:{
				accept:"jpg|jpeg|gif|png" 
			} ,
			file4:{
				accept:"jpg|jpeg|gif|png" 
			} 
		},
		messages: {
			file:{
				required: "请选择图片",
				accept:"格式:JPG|JPEG|GIF|PNG" 
			},
			file1:{
				accept:"格式:JPG|JPEG|GIF|PNG" 
			} , 
			file2:{
				accept:"格式:JPG|JPEG|GIF|PNG" 
			} ,
			file3:{
				accept:"格式:JPG|JPEG|GIF|PNG" 
			} ,
			file4:{
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
						Album.rephotos('<c:out value="${album.id}"/>');
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

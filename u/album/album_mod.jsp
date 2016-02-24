<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
	
</style>
<!-- 
<div id="page-modalbum-box" class="album-box">
	<h3 class="h3-title hd-box green">
		<span class="h3-left">
			<img src="<c:out value="${album.coverPicUrl}" />" width="50" height="50" align="top"/> 
		</span>
		<span class="h3-center">
			<a href="../album/albums.jsplayout.vi">我的相册</a>&#45;&#45;<c:out value="${album.title}"/>
		</span>
		<span class="h3-right">
		</span>
	</h3>
	<div style="clear: both;"></div>
 
	<div class="bd-box">
		<ul id="page-modalbum-toolbar-box">
			<li><a href="../album/photo_upload.jsplayout.vi?id=<c:out value="${album.id}"/>"  class="button">上传图片</a></li>
			<li><a class="button" href="../album/photos.jsplayout.vi?id=<c:out value="${album.id}"/>" > 返回相册</li>
			<li><a href="javascript:" onclick="Album.delalbum('<c:out value="${album.id}"/>');" class="button">删除相册</a></li>
		</ul>
		
		<div style="clear: both;"></div>
		<form id="page-modalbum-form" action="../album/album.mod.json.do" method="post" >
			<table class="page-album-create-box" cellspacing="0" cellpadding="5"   border="0"  width="80%">
				<input type="hidden"   value="<c:out value="${album.id}"/>" name="id"/>
				<tr>
					<td align="right" width="200">相册名称：</td>
					<td align="left">
						<input class="text" type="text" maxlength="10" value="<c:out value="${album.title}"/>" name="title"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="200">相册描述：</td>
					<td align="left">
						<textarea name="description" style="height:50px;width:269px;">
							 <c:out value="${album.description}" escapeXml="false"/>
						</textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="200">访问权限：</td>
					<td align="left">
						<input type="radio"  name="type" value="Normal" <c:if test="${album.type eq 'Normal'}">checked</c:if>   onclick="javascript:Album.seltype('Normal');"/>
							<label>公开</label>
						<input type="radio"  name="type" value="CodedLock" <c:if test="${album.type eq 'CodedLock'}">checked</c:if>  onclick="javascript:Album.seltype('CodedLock');"/>
							<label>密码访问</label>
						<input type="radio"  name="type" value="Personal" <c:if test="${album.type eq 'Personal'}">checked</c:if>  onclick="javascript:Album.seltype('Personal');"/>
							<label>私有</label>
					</td>
				</tr>
				<tr id="password-tr" style="display: none;">
					<td >密码：</td>
					<td align="left">
						<input class="text" type="password" value="<c:out value="${album.password}"/>" maxlength="12" name="password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="button" type="submit" value="修改"/>
						<input class="button" type="button" onclick="javascript:Album.rephotos('<c:out value="${album.id}"/>');" value="取消"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
 -->
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<a href="../album/albums.jsplayout.vi">
				<strong>相册修改</strong>
			</a>
			&#45;&#45;<c:out value="${album.title}"/>
		</div>
		<div class="floatRight"><a href="javascript:Album.realbums();">相册首页&#62;&#62;</a></div>
	</div>
	<div id="allvisitor-page-body">
		
		<form id="page-modalbum-form" action="../album/album.mod.json.do" method="post" >
			<table class="page-album-create-box" cellspacing="0" cellpadding="5"   border="0"  width="80%">
				<input type="hidden"   value="<c:out value="${album.id}"/>" name="id"/>
				<tr>
					<td align="right" width="200">相册名称：</td>
					<td align="left">
						<input class="text" type="text" maxlength="10" value="<c:out value="${album.title}"/>" name="title"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="200">相册描述：</td>
					<td align="left">
						<textarea name="description" style="height:50px;width:269px;"><c:out value="${album.description}" escapeXml="false"/></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="200">访问权限：</td>
					<td align="left">
						<input type="radio"  name="type" value="Normal" <c:if test="${album.type eq 'Normal'}">checked</c:if>   onclick="javascript:Album.seltype('Normal');"/>
							<label>公开</label>
						<!--<input type="radio"  name="type" value="CodedLock" <c:if test="${album.type eq 'CodedLock'}">checked</c:if>  onclick="javascript:Album.seltype('CodedLock');"/>
							<label>密码访问</label>
						--><input type="radio"  name="type" value="Personal" <c:if test="${album.type eq 'Personal'}">checked</c:if>  onclick="javascript:Album.seltype('Personal');"/>
							<label>私有</label>
					</td>
				</tr>
				<tr id="password-tr" style="display: none;">
					<td >密码：</td>
					<td align="left">
						<input class="text" type="password" value="<c:out value="${album.password}"/>" maxlength="12" name="password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="button" type="submit" value="保存"/>
						<input class="button" type="button" onclick="history.back(-1)" value="返回"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>





<script type="text/javascript">
 
$(document).ready(function(){
 
	$('form#page-modalbum-form').validate({
		errorClass: "errorClass",
		errorElement: "label",
  		rules: {
			title: {
				required: true,
				rangelength: [1,16]
			},
			description: {
				rangelength: [0,100]
			} ,
			type:{
				required: true
			}
		},
		messages: {
			title: {
				required: "请输入相册名称！",
				rangelength: "1~16位字符长度！"
			},
			description: {
				rangelength: "0~100位字符长度"
			},
			type:{
				required: "请选择！"
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
   						Album.realbums();
						//Album.rephotos('<c:out value="${album.id}"/>');
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

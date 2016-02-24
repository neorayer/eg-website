<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
	
</style>
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft"><strong>新建相册</strong></div>
		<div class="floatRight"><a href="javascript:Album.realbums();">返回相册&#62;&#62;</a></div>
	</div>
	<div id="allvisitor-page-body">
		<div class="allvisitor-page-top-bar">
		</div>
		<form id="page-album-create-form" action="../album/album.create.json.do" method="post">
			<table class="page-album-create-box" cellspacing="0" cellpadding="5"   border="0"  width="80%">
				<tr>
					<td align="right" width="200">相册名称：</td>
					<td align="left">
						<input class="text" type="text" maxlength="10" value="" name="title"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="200">相册描述：</td>
					<td align="left">
						<textarea name="description" style="height:50px;width:269px;"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right" width="200">访问权限：</td>
					<td align="left">
						<input type="radio"  name="type" value="Normal" checked="checked"  />
							<label>公开</label>
						<input type="radio"  name="type" value="Personal"  />
							<label>私有</label>
					</td>
				</tr>
				<tr id="password-tr" style="display: none;">
					<td >密码：</td>
					<td align="left">
						<input class="text" type="password"  maxlength="12" name="password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="button" type="submit" value="保存"/>
						<input class="button" type="button" onclick="javascript:Album.realbums();" value="取消"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>


<script type="text/javascript">
  $(document).ready(function(){
	$('form#page-album-create-form').validate({
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
				rangelength: "1~100位字符长度！"
			},
			description: {
				rangelength: "0~16位字符长度"
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
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

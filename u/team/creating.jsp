<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<form id='page-team-creating-form-box' action='../team/creating.json.do' method="post" 
		enctype="multipart/form-data" target="page-upLoadIframe" >
	<ul>
		<li>
			<div class='label'>战队名称：</div>
			<div class='input-box'><input type='text' name='teamname' value=''/></div>
			<div class='tips'>
			</div>
		</li>
		<li>
			<div class="label">战区：</div>
			<div class='input-box'>
				<select name="areaid" id="warzoneid">
					<c:forEach var="warzone" items="${warzones}">
					<option value="<c:out value='${warzone.id }'/>" title="<c:out value='${warzone.memo }'/>"><c:out value='${warzone.name }'/></option>
					</c:forEach>
				</select>
			</div>
			<div class='tips' style="color: orange;">
			</div>
		</li>
		<li>
			<div class='label'>QQ群号：</div>
			<div class='input-box'>
				<input type='text' name='qq' value=''/>
			</div>
			<div class='tips'>
			</div>
		</li>
		<li>
			<div class='label'>战队简介：</div>
			<div class='input-box'>
				<textarea id="memo" name="memo"></textarea>
			</div>
			<div class='tips'>
			</div>
		</li>
	</ul>
	
	<div id='page-team-creating-form-bottom-box'>
		<input type="submit" class='button' value="保存" />
		<input type="reset" class='button' value="重置" />
	</div>
</form>

<script type="text/javascript">

$(document).ready(function(){

	$('#warzoneid')
		.change(function() {
			var $option = $(':selected', this);
			$(this).parent("div").next().html($option.attr('title'));
		})
		//强行执行change事件
		.trigger('change');
		
	$('#page-team-creating-form-box').validate({
		errorClass: "errorClass",
		errorElement: "span",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("div").next("div"));
  		},
  		rules: {
			teamname: {
				required: true,
				maxlength: 16,
				minlength: 2
			},
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
			teamname: {
				required: "2~8个汉字以内！",
				maxlength: "超出字符长度",
				minlength: "字符长度不足"
			},
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
  			
	   		$(form).ajaxSubmit({
	   			dataType: 'json',
	   			success: function(data) {
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
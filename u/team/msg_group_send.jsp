<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">

#member-send-msg-head-box {
	border-bottom: 1px solid #818181;
	margin-bottom: 5px;
	padding-bottom: 2px;
}

#member-send-msg-middle-box {
	width: 100%;
}

#member-send-msg-middle-box td {
	padding: 4px;
	border-bottom: 1px solid #cccccc;
	vertical-align: top;
}

#member-send-msg-middle-box .label {
	width: 70px;
}

#member-send-msg-tail-box {
	text-align: center;
	padding-top: 10px;
}

</style>

<form id="page-groupsendmsg-form" action="../team/msg.group_send.json.do" method="post" >
	<input type="hidden" name="teamid" value="<c:out value='${team.id }'/>">

	<h3 id="member-send-msg-head-box">战队群发消息</h3>
	<table id="member-send-msg-middle-box">
		<tr>
			<td class="label">收信人:</td>
			<td>
				<select name="roleid">
					<option value="">所有成员</option>
					<c:forEach var="role" items="${team.roles}">
						<option value="<c:out value='${role.id }'/>">
							<c:out value='${role.name}'/>
						</option>
					</c:forEach>
				</select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td  class="label">主 题:</td>
			<td><input type="text" value="" id="subject" name="subject"/>（２０个字）</td>
			<td></td>
		</tr>
		<tr>
			<td  class="label">内 容:<br/>500字以内</td>
			<td>
				<textarea rows="4" cols="35" id="content" name="content"/>
			</td>
			<td></td>
		</tr>
	</table>
	<div id="member-send-msg-tail-box">
		<input class="button"  type="submit" name="submit" value="发送" />
		<input class="button" type="button" value="取消" onclick="PopupWinX.close(this);" />
	</div>
</form>

<script>

$(document).ready(function(){
	$("#page-groupsendmsg-form").validate({
		errorClass: "errorClass",
		errorElement: "span",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td").next("td"));
  		},
  		rules: {
			subject: {
				required: true,
				maxlength: 40,
				minlength: 4
			},
			content: {
				required: true,
				maxlength: 1000,
				minlength: 4
			}
		},
		messages: {
			subject: {
				required: "2~20个汉字以内！",
				maxlength: "超出字符长度",
				minlength: "字符长度不足"
			},
			content: {
				required: "2~500个汉字以内！",
				maxlength: "超出字符长度",
				minlength: "字符长度不足"
			}
		},
  		submitHandler: function(form) {
	   		$(form).ajaxSubmit({
	   			success: function(data) {
	   				if(data.res == 'no') {
		   				alert(data.data);
		   				return;
		   			}else{
		   				PopupWinX.close($(form.submit));
		   				Message_Portal_Page.reload();
		   			}
	   			}
	   		});
	   	}
 	});
});

</script>
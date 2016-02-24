<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>
<!--
#teamInfor-add-box{
	width: 70%;
	margin: 10px auto;
	
}
#teamInfor-add-box tr{
	border-bottom:1px dashed #818181;
}

#teamInfor-add-box td{
	padding:10px 5px;
}
#teamInfor-add-box td.label{
	width: 150px;
	text-align:right;
	color: #73A61D;
	font-size: 12px;
	font-weight: bolder;
}
#teamInfor-add-box td.content{
}
#teamInfor-add-box td.msg{
	color: #818181;
	width: 250px;
	font-size: 12px;
	font-weight: lighter;
}
#team-map-box{
	padding:10px;
	height:500px;
	background:url(../_css/images/portal/china.jpg) center center no-repeat;
}
-->
</style>

<div>	
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="blue">
			补充战队信息
			<a href="../team/index.jsplayout.vi" style="color: white;margin-left: 650px;">【返回】</a>
		</h3>
	</div>
	<div class="bd-box">
	
	
	<form action="../team/teamInfor.add.json.do" method="post" id="page-add-teamInfor-form">
		<table border="0" cellpadding="0" cellspacing="0" id="teamInfor-add-box">
			<c:if test="${not empty REASON}">
				<tr>
					<td colspan="3">
						<DIV id="SOR_EXCEPTION" style="color:red;" align="center"><c:out value="${REASON}" /></DIV>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td class="label">战区：</td>
				<td class="content">
					<select name="areaId" id="warzoneid">
						<c:forEach var="warzone" items="${warzones}">
						<option value="<c:out value='${warzone.id }'/>" title="<c:out value='${warzone.memo }'/>"><c:out value='${warzone.name }'/></option>
						</c:forEach>
						</select>
					</td>
					<td class="msg" style="color: orange;">
					</td>
			</tr>
			<tr>
				<td class="label"></td>
				<td class="content">
					<input type="submit" title="提交" value="提交" class="button"/>
				</td>
				<td class="msg">
				</td>
			</tr>		 
		</table>
	</form>
				
	<div id="team-map-box">
	
	</div>

	</div>
	<div class="bt-box"><div></div></div>	
</div>
	
<script type="text/javascript">

$(document).ready(function(){
	$('#warzoneid')
		.change(function() {
			var $option = $(':selected', this);
			$(this).parent("td").next().html($option.attr('title'));
		})
		//强行执行change事件
		.trigger('change');
	
	$.metadata.setType("attr", "validate");
	
	$('form#page-add-teamInfor-form').validate({
		errorClass: "errorClass",
		errorElement: "div",
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td"));
  		},
  		rules: {
			warzoneid: {
				required: true
			}
		},
		messages: {
			warzoneid: "请输入您的战区"
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
	   					Util.refreshAuthImage();
	   					return;
   					}else{
   						alert("补充信息成功！");
						window.location = 'index.jsplayout.vi';
					}
	   			}
	   		});
	   	}
 	});
});

</script>

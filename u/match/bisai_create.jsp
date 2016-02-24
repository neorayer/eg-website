<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--
#page-create-box .errorClass{
	
}
#page-create-middle-box {
	margin-top: 10px;
}
#page-create-middle-box .inpbut { 
	background-color:#f2e6b2;
	border:0 none;
	cursor:pointer;
	height:22px;
	margin-left:15px;
	margin-top:11px;
	width:37px;
	color: white;
}
 
#page-create-info-table-box{
	width: 80%;
	margin: 10px auto;
	line-height: 30px;
}

#page-create-alllist-table-box tr{
}
#page-create-alllist-table-box td{
}

#page-create-buttons-box .inpbut{ 
	background-color:#DAB606;
	border:0px ;
	color:#FFFFFF;
	cursor:pointer;
	font-size:12px;
	text-align:center;
	width: 60px;
	height: 25px;
	line-height: 25px;
}
-->
</style>
<div id="page-create-box">

	 <div id="page-create-middle-box" class="box">
		<div class="tp-box"><div></div></div>
	 	<div class="hd-box">
	 		<h3 class="yellow" style="text-align: left;">
	 			创建赛事
	 			<span style="font-weight: normal;">
	 				(当前已有赛事： <span id="page-createnum"><c:out value="${matchsnum}"/></span>)
	 			</span>
	 			<div class="hd-button-box">
		 			<a href="index.jsplayout.vi" class="button" style="line-height: 30px;margin-right: 30px;">【返回】</a>
	 			</div>
	 		</h3>
	 	</div>
	 	
		<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
	 	<div class="bd-box">
	 		<form  method="post" enctype="multipart/form-data" action="../match/bisai.create.jsp.do" id="page-create-form">
		 		<table id='page-create-info-table-box' border="1" cellpadding="5" cellspacing="0" bordercolor="#DAB606" bgcolor="#F2E6B2">
				 	
				 	<tr>
				 		<td width="120" align="center">比赛名称<span style="color:red">(中文）</span></td>
				 		<td><input type="text" name="title" size="25" /></td>
					</tr>
					<tr>
						<td width="120" align="center">
							赛事域名:<span style="color:red">(全英文)</span>
						</td>
						<td>
							<input type="text" name="id" size="18" />.51bisai.com
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							赛程方式:
						</td>
						<td>
							<input type="radio" name="schedulemethod" value="signup" checked>报名
							<input type="radio" name="schedulemethod" value="report">报道
						</td>
					</tr>
					<tr>
				 		<td width="120" align="center">比赛类型</td>
				 		<td>
				 			<select name="type">
								<c:forEach var="type" items="${matchtypes}">
									<option value="<c:out value='${type.id }'/>">
										<c:out value='${type.name }'/>
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
				 		<td width="120" align="center">战区</td>
				 		<td>
				 			<select name="warzoneid" id="warzoneid">
								<c:forEach var="warzone" items="${warzones}">
									<option value="<c:out value='${warzone.id }'/>" title="<c:out value='${warzone.memo }'/>">
										<c:out value='${warzone.name }'/>
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div id="page-create-buttons-box" align="center">
					<input type="submit" value="提交" class="inpbut"/>
					<input type="button" value="返回" class="inpbut" onclick="window.location='index.jsplayout.vi'" />
				</div>
			</form>
	 	</div>
	 	<div class="bt-box"><div></div></div>
	 </div>
</div>

<script type="text/javascript">
 
$(document).ready(function(){
	$('#warzoneid')
		.change(function() {
			var $option = $(':selected', this);
			$(this).parent("td").append("<span style='color:orange;'>"+$option.attr('title')+"</span>");
		})
		//强行执行change事件
		.trigger('change');
		
	$('form#page-create-form').validate({
		errorClass: "errorClass",
		errorElement: "span",
		
 		errorPlacement: function(error, element) {
    		error.appendTo( element.parent("td"));
  		},
  		
  		rules: {
			title: {
				required: true,
				rangelength: [2,20]
			},
			id: {
				required: true
			}
		},
		messages: {
			title:{
				required: "赛事标题不可以为空！",
				rangelength: "2~20位字符长度"
			},
			id:{
				required: "赛事链接不可以为空！"
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
	   					Util.refreshAuthImage();
	   					return;
   					}else{
   						alert("创建成功" );
						window.location = 'index.jsplayout.vi';
   					}
	   			}
	   		});
	   	}
 	});
});
</script>

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
		<div class="floatRight"><a href="javascript:history.back(-1)">返回>></a></div>
	</div>
	
	
	
	<div class="page-photos-body-box">
		<!-- 
		<span >
			<a href="../user/user_avator.jsplayout.vi" class="button">上一页</a>
			<a href="../album/album_avator.jsplayout.vi" class="button">头像相册</a>
			<a href="../user/index.jsplayout.vi" class="button">返回首页</a>
		</span>
	 -->
		<br><br>	
		<div id="page-edit-avator-main-box">
			<div id="page-edit-avator-left-box">	
				<h3 class="avator-title">大头像</h3>
				<div id="img-cutter" style="border:2px solid gray" >
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="3" height="60"><br />&nbsp;</td>
						</tr>
						<tr>
							<td width="100" ><br />&nbsp;</td>
							<td width="170" height="232"><br />&nbsp;</td>
							<td width="100"><br />&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3" height="60"><br />&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="page-edit-avator-right-box">	
				<h3 class="avator-title">小头像</h3>
				<div id="img-cutterSmall" style="border: 2px solid gray; overflow: hidden; position: relative;" >
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="3" height="60"><br />&nbsp;</td>
						</tr>
						<tr>
							<td width="60" ><br />&nbsp;</td>
							<td width="50" height="50"><br />&nbsp;</td>
							<td width="60"><br />&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3" height="60"><br />&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>		
			<div style="clear:both" ></div>
			<br>	<br>	
			<input type="button" class="button" value="保存" onclick="avatorSubmit()" />
			<input type="button" class="button" value="返回" onclick="EditAvator.back();" />
		</div>
				 

	</div>
	
</div>
	
<script type="text/javascript">

 
var EditAvator={
	back:function(){
		window.location='../user/user_avator.jsplayout.vi';
	} 
	 
};

function avatorSubmit() {
	$("#img-cutter").imgCutter("cut");
	$("#img-cutterSmall").imgCutter("cut");
}

$(document).ready(function() {
	var imgPath = '<c:out value="../user/photo.img.vi?id=${photo.id}" />';
	var id = '<c:out value="${photo.id}"/>';
	$("#img-cutter").imgCutter({
		img: imgPath,
		cut: function(persent, x, y, w, h) {
			var params = {
				persent: persent,
				x: x,
				y: y,
				w: w,
				h: h,
				id:id
			};
			$.post("../album/user_avator.edit.json.do", params, function(data) {
			}, 'json');
		}
	});
	
	$("#img-cutterSmall").imgCutter({
		img: imgPath,
		cut: function(persent, x, y, w, h) {
			var params1 = {
				persent1: persent,
				x1: x,
				y1: y,
				w1: w,
				h1: h,
				id: id
			};
			$.post("../album/user_avatorSmall.edit.json.do", params1, function(data) {
				//回调函数跳转
				EditAvator.back();
			}, 'json');
		}
	});
});
</script>

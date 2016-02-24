<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>

<div class="box">	
	<div class="tp-box">
		<div>
		</div>
	</div>
	<div class="hd-box">
		<h3 class="blue">修改徽标</h3>
		<div class="hd-button-box">
		</div>
	</div>
	<div class="bd-box">
		<div style="background-color:#ffffff;padding-left: 20px;padding-top: 20px;">
			<div id="team-img-cutter" style="border:2px solid gray;" >
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
			<div style="margin-top: 20px;">
				<input class="button" type="button" value="保存" onclick="avatorSubmit()" />
			</div>
		</div>
	</div>
	<div class="bt-box"><div></div></div>	
</div>	


<script type="text/javascript">
function avatorSubmit() {
	$("#team-img-cutter").imgCutter("cut");
}

$(document).ready(function() {
	$("#team-img-cutter").imgCutter({
		img: '<c:out value="${team.logoPath}" />',
		cut: function(persent, x, y, w, h) {
			var params = {
				persent: persent,
				x: x,
				y: y,
				w: w,
				h: h
			};
			$.post("../team/team_logo.edit.json.do?id=<c:out value='${team.id}'/>", params, function(data) {
				//Team_Portlet_Page.reload();
				window.location="../team/index.jsplayout.vi";
			}, 'json');
		}
	});
});

</script>

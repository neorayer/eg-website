<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--

#team-role-mod-form h3 {
	border-bottom:1px solid #818181;
	margin-bottom:5px;
	padding-bottom:2px;
}

#team-role-mod-form {
	line-height: 20px;
	padding: 10px 10px;
	background-color: #FFFFCC;
}
#team-role-mod-form table td {
	border-bottom: 1px dotted #969696;
}
-->
</style>


<form id="team-role-mod-form" action="../team/teamRolePower.mod.json.do" method="post" 
	onsubmit="Team_RolePower_Page.do_saveRolePower(this);return false;">
	
	<h3>编辑职位</h3>
	
	<div id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}"/></div>
	<input type="hidden" name="teamid" value="<c:out value="${role.teamId }" />">
	<input type="hidden" name="roleid" value="<c:out value="${role.id }" />">
	
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td style="text-align: center;">职位:</td>
			<td style="font-weight: bold;">
				<c:out value="${role.name}" />
			</td>
		</tr>
		<c:forEach var="authority" items="${authoritys}" varStatus="status">
			<tr>
				<td style="text-align: center;">
					<c:if test="${status.index == 0}">
						权限:
					</c:if>
				</td>
				<td>
					<c:set var="flag" value="0" />
					<c:forEach var="power" items="${role.powers}">
						<c:if test="${authority.code == power.powerId}">
							<c:set var="flag" value="${flag + 1}" />
						</c:if>
					</c:forEach>
					
					<input type="checkbox" name="powerid" value="<c:out value='${authority.code}'/>" 
						<c:if test="${flag > 0}">
							checked
						</c:if>
					 />
					<c:out value="${authority.desc}" />
				</td>
			</tr>
			
		</c:forEach>
		<tr>
			<td>
			</td>
			<td>
				<input class="button" type="submit" name="submit" value="保存">
			</td>
		</tr>
	</table>
</form>


<script type="text/javascript">
var Team_RolePower_Page = {
	do_saveRolePower: function(form) {
		$(form).ajaxSubmit(function(data) {
			alert('修改成功!');
			PopupWinX.close($(form.submit));
			Team_Roles_Page.load();
		});
	}
}

$(document).ready(function(){
	
	
});
</script>
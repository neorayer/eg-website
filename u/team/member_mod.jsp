<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--

#page-teammember-modinfo-form h3 {
	border-bottom:1px solid #818181;
	margin-bottom:5px;
	padding-bottom:2px;
}

#page-teammember-modinfo-form{
	line-height: 20px;
	padding: 10px 10px;
	background-color: #FFFFCC;
}
#page-teammember-modinfo-form table td{
	border-bottom: 1px dotted #969696;
}
-->
</style>


<form id="page-teammember-modinfo-form" action="../team/member.mod.json.do" method="post" onsubmit="Team_Member_Mod_Page.do_mod(this);return false;">
	<h3>成员修改</h3>
	
	<div id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}"/></div>
	<input type="hidden" name="teamid" value="<c:out value="${member.teamId }" />">
	<input type="hidden" name="username" value="<c:out value="${member.username }" />">
	
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td class="label" width="80px">
				用户名：
			</td>
			<td>
				<c:out value="${member.username }" />
			</td>
		</tr>
		<tr>
			<td class="label">
				游戏名：
			</td>
			<td>
				<c:out value="${member.user.nickname }" />
			</td>
		</tr>
		<tr>
			<td class="label">
				加入时间：
			</td>
			<td>
				<c:out value="${member.createDateTime }" />
			</td>
		</tr>
		<tr>
			<td class="label">
				职位：
			</td>
			<td>
				<select name="roleid">
					<c:forEach var="role" items="${team.roles }">
						<c:if test="${!role.isLeader}">
							<option value="<c:out value="${role.id }" />"
								<c:if test='${role.id == member.role.id }'>
									selected
								</c:if>						
							>
								<c:out value="${role.name }" />
							</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="label">
				成员类型：
			</td>
			<td>
				<select name="membertype">
					
					<option value="first" <c:if test="${member.memberType == 'first'}">selected</c:if>>一线队员</option>
					<option value="second" <c:if test="${member.memberType == 'second'}">selected</c:if>>二线队员</option>
					<option value="normal" <c:if test="${member.memberType == 'normal'}">selected</c:if>>普通</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="label">
			</td>
			<td>
				<input id="team-modinfo-save-button" class="button" type="submit" name="submit"  value="保存" >
				<input id="team-modinfo-cancel-button" class="button" type="button" value="取消" onclick="PopupWinX.close(this);">
			</td>
		</tr>
	</table>
</form>


<script type="text/javascript">
var Team_Member_Mod_Page = {
	do_mod: function(form) {
		$(form).ajaxSubmit(function(data) {
			alert('修改成功!');
			Team_Members_Page.reload();
			Team_MOF_Any_Page.reload();
			Team_MOS_Any_Page.reload();
			PopupWinX.close(form.submit);
		});
	}
}

$(document).ready(function(){
	
	
});
</script>
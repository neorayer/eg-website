<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<div id="user-usereghistorys-box">
	<c:if test="${user.profession == 'Player'}">
		<div id="user-usereghistorys-header-box">
			<c:out value="${user.dispName }" /> 成长史。
		</div>
		<table id="user-usereghistorys-list-box">
			<thead>
				<tr>
					<td align=center width="30%">时间</td>
					<td align=center>事件</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="history" items="${historys }">
					<tr class="main">
						<td align=center><c:out value="${history.createDate }" /></td>
						<td style="padding-left:10px;"><c:out value="${history.event }" /></td>
					</tr>
				</c:forEach>
				
				<tr>
					<td colspan="4" id="user-usereghistorys-pagebar-box">
						<c:out value='${pageBar}' escapeXml="false"/>
					</td>
				</tr>
			</tbody>
		</table>
		
	</c:if>
	<c:if test="${user.profession == 'Referee'}">
		裁判
	</c:if>
	<c:if test="${user.profession == 'Organizer'}">
		组织者
	</c:if>
</div>


<script type="text/javascript">
<!--
$(document).ready(function(){
   $("#user-usereghistorys-pagebar-box a").click(function(){
   		var username ='<c:out value="${user.username }"/>';
		var url = $(this).attr('href') +'&username='+ username;
		console.debug(url);
		$('.certificate-album').load(url);		
		return false;
   });
})
//-->
</script>

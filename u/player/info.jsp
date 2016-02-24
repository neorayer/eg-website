<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div id="playerhome-info-box">
	<div id="playerhome-album">
		<a href="../player/index.jsplayout.vi?username=<c:out value='${player.username}'/>">
			<img src="<c:out value='${player.avatorUrl}'/>" width="121px" height="170px"/>
		</a>
	</div>
	<table width="100%" cellspacing="0" cellpadding="0" id="playerhome-info-msgtable" align="center">
		<tr>
			<td width="45%" align="right" class="t">职业:</td>
			<td align="left"><c:out value='${player.strProfession}'/></td>
		</tr>
		<tr>
			<td align="right" class="t">所属战队:</td>
			<td align="left" class="v">
				<div class="playerhome-info-longinfo" title="<c:out value='${player.team.teamName}'/>">
				<nobr><a href="../team/index.jsplayout.vi?teamid=<c:out value='${player.team.id}'/>"><c:out value='${player.team.teamName}'/></a></nobr>
				</div>
			</td>
		</tr>
		<tr>
			<td align="right" class="t">所属战区:</td>
			<td align="left"><c:out value='${player.warZone.name}'/></td>
		</tr>
		<tr>
			<td align="right" class="t">个人信誉:</td>
			<td align="left">
				<c:forEach begin="1" end="${player.kulou}">
					<img src="../_css/images/portal/kulou.jpg" title="<c:out value='${player.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${player.signHeart}">
					<img src="../_css/images/portal/heart.jpg" title="<c:out value='${player.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${player.doubleHeart}">
					<img src="../_css/images/portal/doubleheart.jpg" title="<c:out value='${player.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${player.diamond}">
					<img src="../_css/images/portal/diamond.jpg" title="<c:out value='${player.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${player.crown}">
					<img src="../_css/images/portal/crown.jpg" title="<c:out value='${player.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${player.sword}">
					<img src="../_css/images/portal/sword.jpg" title="<c:out value='${player.reputation}'/>分">
				</c:forEach>
			</td>
		</tr>
		
	</table>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<c:set var="team" value="${teamProInfo.team}"/>
<div id="certi-index-top-box">
	<div id="certi-index-left-box">
		<div class="certi-index-left-album">
			<img src="<c:out value='${team.logoPath }'/>"/>
		</div>
		<c:if test="${not empty me && me.isLeader}">
			<div style="text-align: center;margin-top: 5px;">
				<a href="../team/team.upload.jsplayout.vi" class="mod-user-link">修改徽标</a>
				<a href="../team/team.mod.jsplayout.vi" class="mod-user-link">修改信息</a>
			</div>
		</c:if>
	</div>
	
	<div id="certi-index-right-box">
		<table width="100%" class="certi-index-right-table" align="center">
			<tr>
				<td width="30%" class="certi-index-right-table-title">战队名称：</td>
				<td class="certi-index-right-table-info"><c:out value='${team.teamName}'/></td>
			</tr>
			<tr>
				<td class="certi-index-right-table-title">参赛项目：</td>
				<td class="certi-index-right-table-info"><c:out value='${teamProInfo.project.name}'/></td>
			</tr>
			<tr>
				<td class="certi-index-right-table-title">电竞等级：</td>
				<td class="certi-index-right-table-info">未开放</td>
			</tr>
		</table>
	</div>
</div>
<div id="certi-index-bottom-box">
	<table width="100%" style="margin-top: 10px;">
		<tr>
			<td class="certi-index-bottom-table-title" width="20%">战队信誉：</td>
			<td class="certi-index-bottom-table-info" width="30%">
				<c:forEach begin="1" end="${team.kulou}">
					<img src="../_css/images/portal/kulou.jpg" title="<c:out value='${team.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${team.signHeart}">
					<img src="../_css/images/portal/heart.jpg" title="<c:out value='${team.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${team.doubleHeart}">
					<img src="../_css/images/portal/doubleheart.jpg" title="<c:out value='${team.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${team.diamond}">
					<img src="../_css/images/portal/diamond.jpg" title="<c:out value='${team.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${team.crown}">
					<img src="../_css/images/portal/crown.jpg" title="<c:out value='${team.reputation}'/>分">
				</c:forEach>
				<c:forEach begin="1" end="${team.sword}">
					<img src="../_css/images/portal/sword.jpg" title="<c:out value='${team.reputation}'/>分">
				</c:forEach>
			</td>
			<td class="certi-index-bottom-table-title" width="20%">所属战区：</td>
			<td class="certi-index-bottom-table-info" width="30%">
				<c:if test='${not empty team.areaId}'>
					<c:out value="${team.warZone.name}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>	
	</table>
	<div class="certi-index-number-box">战队参赛证编号：<c:out value='${teamProInfo.certiId}' default="无"/></div>
</div>

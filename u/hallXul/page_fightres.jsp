<?xml version="1.0"  encoding="UTF-8"?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />
<sor:xss url="skin/${skin}/fightres/style.css"  />

<sor:xol url="ol-dialog.xul" />
<dialog
	id="fightres-dialog"
	title="KO竞技游戏平台-比赛结果"
	xmlns:html="http://www.w3.org/1999/xhtml"
	xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
	buttons="accept,cancel"
	buttonlabelaccept="确定"
	buttonlabelcancel="取消"
	hidechrome="true"
	width="450"
	height="350"
	onkeydown="if (event.keyCode == 116) window.location.reload()"
	>
	<sor:js url="js/jquery.js" />
	<sor:js url="js/lib_dbg.js" />
	<sor:js url="js/lib_g.js" />
	<sor:js url="js/lib_io.js" />
	<sor:js url="js/lib_json.js" />
 
	<box id="dialog-head-box" />
	
	<vbox id="" flex="1" pack="center"> 
		<vbox id="fightres-title-box" >
			<label id="fightres-title-label" value="<c:out value="[${fightLog.typeName}]" />"  />
		</vbox>
		<grid id="fightres-grid" flex="1">
			<columns>
				<column id="fightres-label-col" />
				<column flex="1" />
				<column flex="1" />
			</columns>
			<rows id="fightres-rows" >
				<row id="fightres-team-row" >
					<box class="fightres-grid-label-box" >
						<label value="战队" />
					</box>
					<box>
						<label id="fightres-hostteam-label" value="<c:out value="${fightLog.hostTeamName}" />" />
					</box>
					<box>
						<label id="fightres-otherteam-label" value="<c:out value="${fightLog.otherTeamName}" />" />
					</box>
				</row>
				<row id="fightres-winlose-row">
					<box class="fightres-grid-label-box" >
						<label value="胜负"/>
					</box>
					<box>
						<c:if test="${fightLog.res eq 1}">
							<label id="fightres-win-label" value="胜 (<c:out value="${fightLog.resReasonWord}" />)"/>
						</c:if>
						<c:if test="${fightLog.res eq 2}">
							<label id="fightres-lose-label" value="负"/>
						</c:if>
					</box>
					<box>
						<c:if test="${fightLog.res eq 2}">
							<label id="fightres-win-label" value="胜 (<c:out value="${fightLog.resReasonWord}" />)"/>
						</c:if>
						<c:if test="${fightLog.res eq 1}">
							<label id="fightres-lose-label" value="负"/>
						</c:if>
					</box>
				</row>
				<row id="fightres-host-row">
					<box  class="fightres-grid-label-box" >
						<label value="主客"/>
					</box>
					<box >
							<label value="主机"/>
					</box>
					<box >
						<label value="客场"/>
					</box>
				</row>
				<row id="fightres-race-row">
					<box  class="fightres-grid-label-box" >
						<label value="种族"/>
					</box>
					<box >
						<label value="<c:out value="${fightLog.hostRaceName}" />"/>
					</box>
					<box >
						<label value="<c:out value="${fightLog.otherRaceName}" />"/>
					</box>
				</row>
				<row class="fightres-player-row">
					<box  class="fightres-grid-label-box" >
						<label value="选手"/>
					</box>
					<vbox >
						<c:forEach var="player" items="${fightLog.hostUsers}" >
							<c:set var="label" value="${player.nickname}" />
							<c:if test="${player.username eq fightLog.hostUsername}" >
								<c:set var="label" value="${player.nickname}(主机)" />
							</c:if>
							<label value="<c:out value="${label}" />" />
						</c:forEach>
					</vbox>
					<vbox >
						<c:forEach var="player" items="${fightLog.otherUsers}" >
							<label value="<c:out value="${player.nickname}" />" />
						</c:forEach>
					</vbox>
				</row>
			</rows>
		</grid>
	</vbox>
</dialog>

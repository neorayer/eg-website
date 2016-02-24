<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />
<sor:xss url="skin/${skin}/main/style.css"  />

<sor:xol url="ol-body-hall-left.xul" />
<sor:xol url="ol-body-hall.xul" />
<sor:xol url="ol-body-room.xul" />
<sor:xol url="ol-body-aide.xul" />

<overlay
	xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">
	<window  
		id="main-window"
		title="KO竞技游戏平台"
		xmlns:html="http://www.w3.org/1999/xhtml"
		xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
		width="1001"
		height="700"
		hidechrome="true"
		windowtype="eghall:client"
		onload="Main.onLoad()"
		onunload="Main.onUnload()"
		onkeydown="Main.onWindowKeyDown(event)"
		>
		<sor:js url="js/jquery.js" />
		<sor:js url="js/lib_dbg.js" />
		<sor:js url="js/lib_g.js" />
		<sor:js url="js/lib_io.js" />
		<sor:js url="js/lib_json.js" />
		<sor:js url="js/app_ver.js" />
		<sor:js url="js/app_const.js" />
		<sor:js url="js/pg_setting.js" />
		<sor:js url="js/pg_main.js" />

		<stack flex="1">
			<vbox>
				<!-- 头部 Begin -->
				<vbox id="head-box" >
					<hbox id="winhead-top-box">
						<titlebar flex="1" />
						<hbox id="winhead-buttons-box" align="right" >
							<button id="winhead-min-button" oncommand="WinTool.min()" tooltiptext="最小化" />
							<button id="winhead-max-button" oncommand="WinTool.max()" tooltiptext="最大化" />
							<button id="winhead-normal-button" oncommand="WinTool.normal()" hidden="true" tooltiptext="窗口还原" />
							<button id="winhead-close-button"  oncommand="Main.hideWin()" tooltiptext="隐藏到桌面右下角" />
						</hbox>
					</hbox>
					<stack flex="1" >
						<box id="head-bg-box" flex="1">
						</box>
						<vbox flex="1" style="right:0px" >
							<vbox  >
								<hbox id="head-menu-bg-box" flex="1" align="right">
								</hbox>
							</vbox>
						</vbox>
						<box id="logo" >
						</box>
						<vbox >
							<hbox id="head-menu-box"  align="right" >
								<c:forEach var="mi" items="${hall.menuItems}">
									<button label="<c:out value="${mi.name}" />" oncommand="BrowserTool.openWithSys('<c:out value="${mi.url}" />')" />
								</c:forEach>
								<button label="设置" oncommand="Main.openSetting()" />
								<button label="退出" oncommand="Main.logout()" />
							</hbox>
							<hbox id="neck-box" align="right" >
								<label flex="1" />
								<label id="msg-label" value="" hidden="true" onclick="Main.viewLastMsg()" />
								<label id="logininfo-label">登陆时间:</label>
							</hbox>
							<hbox id="neck2-box" align="right" >
							</hbox>
						</vbox>
					</stack>
				</vbox>
				<!-- 头部 End -->	
		
				<!-- 主体 Begin -->
				<hbox id="body-box"  flex="1">
					<tabbox id="body-tabbox" flex="4" >
						<hbox >
							<tabs id="body-tabs">
								<tab id="body-hall-tab" label="大厅" />
								<tab id="body-room-tab" label="房间" disabled="true" />
								<tab label="约战" />
								<tab label="录像" />
								<tab label="比赛" />
							</tabs>
						</hbox>
						<tabpanels id="body-tabpanels" flex="1" >
							<tabpanel id="hall-tabpanel" />
							<tabpanel id="room-tabpanel" />

							<!-- 约战 Begin -->
							<tabpanel>
								<label>约战</label>
							</tabpanel>
							<!-- 约战 End -->

							<!-- 录像 Begin -->
							<tabpanel>
								<label>录像</label>
							</tabpanel>
							<!-- 录像 End -->

							<!-- 比赛 Begin -->
							<tabpanel>
								<label>比赛</label>
							</tabpanel>
							<!-- 比赛 End -->

						</tabpanels>
					</tabbox>
					<box id="body-right-box" />
					<vbox id="aide-box" />
				</hbox>	
				<!-- 主体 End -->
			</vbox>
		</stack>
	</window>
</overlay>
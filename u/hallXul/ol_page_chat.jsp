<?xml version="1.0"  encoding="UTF-8"?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />
<sor:xss url="skin/${skin}/chat/style.css"  />

<sor:xol url="ol-dialog.xul" />

<overlay xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">
	<dialog
		id="chat-dialog"
		title="聊天"
		xmlns:html="http://www.w3.org/1999/xhtml"
		xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
		buttons="none"
		buttonlabelaccept="确定"
		hidechrome="true"
		width="550"
		height="550"
		ondialogaccept="return false"
		onload="Chat.onLoad()"
		onkeydown="onWindowReloadKeyDown(event)" 

		>
		<sor:js url="js/lib_dbg.js" />
		<sor:js url="js/lib_g.js" />
		<sor:js url="js/lib_io.js" />
		<sor:js url="js/lib_json.js" />
		<sor:js url="js/app_const.js" />
		<sor:js url="js/pg_chat.js" />
		<script type="text/javascript" src="chrome://global/content/globalOverlay.js"></script>
		<popupset>
			<menupopup id="chat-menupopup" >
				<menuitem label="清屏" oncommand="Chat.clearChat()" />
			</menupopup>
		</popupset>  

		<vbox flex="1">
			<hbox id="win-top-resizer-box" >
				<resizer dir="topleft" width="2" style="cursor: nw-resize;"/>
				<resizer dir="top" flex="1" style="cursor: n-resize;"/>
				<resizer dir="topright"  style="cursor: sw-resize;"/>
			</hbox>
			<hbox flex="1">
				<resizer id="win-left-resizer" dir="left" style="cursor: w-resize;"/>

				<!-- 真正内容的开始 -->
				<vbox flex="1" style="overflow:hidden">
					<hbox id="winhead-top-box">
						<resizer dir="topleft" width="10" style="cursor: nw-resize;"/>
						<titlebar flex="1" ><label id="win-title-label" /></titlebar>
						<hbox id="winhead-buttons-box" align="right" >
							<button id="winhead-min-button" oncommand="WinTool.min()" tooltiptext="最小化" />
							<button id="winhead-max-button" oncommand="WinTool.max()" tooltiptext="最大化" />
							<button id="winhead-normal-button" oncommand="WinTool.normal()" hidden="true" tooltiptext="窗口还原" />
							<button id="winhead-close-button"  oncommand="WinTool.close()" tooltiptext="关闭" />
						</hbox>
					</hbox>
					
					<hbox id="toolbar-box">
					</hbox>
					
					<hbox id="body-box" flex="1">
						<vbox id="main-box" flex="1" > 
							<iframe id="chat-iframe" context="chat-menupopup" src="about:blank" flex="4" />
							<splitter height="3"/>
							<vbox id="send-box" flex="1" >
								<editor id="chat-editor" editortype="html" src="about:blank"  flex="1" onkeydown="return Chat.onEditorKeydown(event);">
								</editor>
								<hbox align="right" >
									<checkbox id="send-enter-checkbox" label="按Enter键发送" /> 
									<button id="send-button" label="发送" oncommand="Chat.sendChatMsg()"/>
									<button id="face-button" label="表情" onclick="Chat.showFace(event);"/>
								</hbox>
							</vbox>
						</vbox>
						<vbox id="side-box">
							<box id="avator-box">
								<vbox>
								<image id="avator-image" />
								</vbox>
							</box>
				
							<grid id="person-info-grid">
								<columns>
									<column />
									<column />
								</columns>
								<rows>
									<row>
										<label value="帐号" class="person-info-name-label" />
										<description id="person-username-desc"  value="" />
									</row>
									<row>
										<label value="昵称" class="person-info-name-label" />
										<description id="person-nickname-desc"   value="" />
									</row>
									<row>
										<label value="等级" class="person-info-name-label"  />
										<description id="person-level-desc"   value="1" />
									</row>
									<row>
										<label value="积分" class="person-info-name-label" />
										<description id="person-point-desc"   value="" />
									</row>
									<row>
										<label value="类型" class="person-info-name-label" />
										<description id="person-usertype-desc"   value="" />
									</row>
									<row hidden="true" >
										<label value="IP" class="person-info-name-label" />
										<description id="person-realip-desc"  crop="end"   value="" />
									</row>
									<row>
										<label value="地点" class="person-info-name-label"  />
										<description id="person-iplocation-desc" crop="end"   value="" />
									</row>
								</rows>
							</grid>
						</vbox>
					</hbox>
				</vbox>
				<!-- 真正内容的结束 -->
				
				<resizer id="win-right-resizer" dir="right" style="background-color: transparent; cursor: e-resize;"/>
			</hbox>
			
			<hbox id="bottom-box"  >
				<hbox id="bottom-left-box" />
				<vbox id="bottom-center-box" flex="1" >
					<resizer dir="bottom" flex="1" style="cursor: s-resize;"/>
				</vbox>
				<hbox id="bottom-right-box">
					<resizer dir="bottomleft"  width="8" style="cursor: sw-resize;"/>
					<resizer dir="bottom" flex="1" style="cursor: s-resize;"/>
					<resizer id="bottom-right-resizer" dir="bottomright" width="8" style="cursor: se-resize;"/>
				</hbox>
			</hbox>
		</vbox>


		
	</dialog>
</overlay>

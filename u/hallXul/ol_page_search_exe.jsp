<?xml version="1.0"  encoding="UTF-8"?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />
<sor:xss url="skin/${skin}/search-exe/style.css"  />

<sor:xol url="ol-dialog.xul" />

<overlay
	xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">

	<dialog 
		id="search-exe-dialog"
		xmlns:html="http://www.w3.org/1999/xhtml"
		xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
		width="500"
		height="239"
		hidechrome="true"
		buttons="none"
		onload="SearchExe.onLoad();"
		onkeydown="onWindowReloadKeyDown(event)" 
		>

		<sor:js url="js/jquery.js" />
		<sor:js url="js/lib_dbg.js" />
		<sor:js url="js/lib_g.js" />
		<sor:js url="js/lib_io.js" />
		<sor:js url="js/lib_json.js" />
		<sor:js url="js/app_ver.js" />
		<sor:js url="js/app_const.js" />
		<sor:js url="js/pg_setting.js" />
		<sor:js url="js/pg_search_exe.js" />
	
		<box id="dialog-head-box" />
  	
		<vbox id="search-exe-content-box" flex="1">
			<grid id="search-exe-content-grid" flex="1">
				<columns>
					<column></column>
					<column flex="1"></column>
				</columns>
				<rows>
					<row >
						<label value="游戏名称："/>
						<label id="game-name-label" />
					</row>
					<row >
						<label value="文件名称："/>
						<label id="game-filenames-label"></label>
					</row>
					<row id="search-exe-disk-row" align="center" >
						<label value="选择盘符："/>
						<hbox>
						<menulist tabindex="1" id="disk-list" editable="false">
							<menupopup>
								<menuitem label="所有本地硬盘" value=""></menuitem>
								<menuitem label="C" value="C:\"></menuitem>
								<menuitem label="D" value="D:\"></menuitem>
								<menuitem label="E" value="E:\"></menuitem>
								<menuitem label="F" value="F:\"></menuitem>
								<menuitem label="G" value="G:\"></menuitem>
								<menuitem label="H" value="H:\"></menuitem>
							</menupopup>
						</menulist>
						</hbox>
					</row>
					<row>
						<label value="当前位置："/>
						<label id="cur-path-label" value="" crop="end"></label>
					</row>
				</rows>
			</grid>
			<hbox pack="end">
				<button id="start-search-button" class="dialog-button" label="开始" onclick="SearchExe.start();" tooltiptext="开始" />
				<button id="stop-search-button"  class="dialog-button" label="停止" onclick="SearchExe.stop();" tooltiptext="停止" />
				<button id="exit-search-button"  class="dialog-button" label="退出" onclick="SearchExe.exit();" tooltiptext="退出" />
			</hbox>
		</vbox>
	</dialog>
</overlay>
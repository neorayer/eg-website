<?xml version="1.0"  encoding="UTF-8"?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />


<sor:xol url="ol-dialog.xul" />

<overlay xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">
	<dialog
		id="alert-dialog"
		title="KO竞技游戏平台-警告"
		xmlns:html="http://www.w3.org/1999/xhtml"
		xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
		onload="AlertOnLoad();"
		buttons="accept"
		buttonlabelaccept="确定"
		hidechrome="true"
		width="450"
		height="200"
		ondialogaccept=""
		onkeydown="onWindowReloadKeyDown(event)" 
		>

		<sor:js url="js/lib_dbg.js" />
		<sor:js url="js/lib_g.js" />
		<sor:js url="js/lib_io.js" />
		<sor:js url="js/lib_json.js" />

		<box id="dialog-head-box" />

		<hbox id="alert-content-box" flex="1" pack="center"> 
			<label id="alert-content-label" value="" />
		</hbox>
		
	</dialog>
</overlay>

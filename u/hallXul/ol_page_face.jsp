<?xml version="1.0"  encoding="UTF-8"?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />
<sor:xss url="skin/${skin}/face/style.css"  />

<sor:xol url="ol-dialog.xul" />

<overlay xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">
	<window
		id="face-window"
		xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
		xmlns:html="http://www.w3.org/1999/xhtml"
		hidechrome="true"
		width="230"
		height="230">

		<sor:js url="js/lib_dbg.js" />
		<sor:js url="js/lib_g.js" />
		<sor:js url="js/lib_io.js" />
		<sor:js url="js/lib_json.js" />
		<sor:js url="js/app_ver.js" />
		<sor:js url="js/app_const.js" />
		<sor:js url="js/pg_face.js" />
		
		<box id="dialog-head-box" />
		
		<arrowscrollbox id="faces-scrollbox" flex="1" align="center" orient="vertical" pack="start" >
		<html:div id="faces-box"  >
			<c:forEach var="face" items="${faces}">
				<html:img src="<c:out value="${face}" />" onclick="Face.sendChatFace(this)" style="float:left" />
			</c:forEach>
		</html:div>
		</arrowscrollbox>
	</window>
</overlay>

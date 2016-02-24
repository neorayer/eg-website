<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />
<sor:xss url="skin/${skin}/login/style.css"  />
<overlay xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">
	<window
		id="login-window"
		title="KO竞技游戏平台"
		xmlns:html="http://www.w3.org/1999/xhtml"
		xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
		onload="Login.onLoad()"
		hidechrome="true"
		width="638"
		height="494"
		windowtype="eghall:client"
		onkeydown="Login.onWindowKeyDown(event)"
		>
		
		<sor:js url="js/jquery.js" />
		<sor:js url="js/lib_dbg.js" />
		<sor:js url="js/lib_g.js" />
		<sor:js url="js/lib_io.js" />
		<sor:js url="js/lib_json.js" />
		<sor:js url="js/app_const.js" />
		<sor:js url="js/app_ver.js" />
		<sor:js url="js/pg_login.js" />
  
		<vbox id="page-box" flex="1">
			<vbox id="page-head-box">
				<hbox id="page-tools-box"  flex="1">
					<titlebar flex="1" />
					<vbox>
						<spacer flex="1" />
						<hbox id="winhead-buttons-box" >
							<button id="winhead-close-button" tooltiptext="关闭" oncommand="window.close()"  />
						</hbox>
					</vbox>
				</hbox>
			</vbox>
			<vbox id="page-body-box" flex="1">
				<vbox id="page-body-top-box">
					<browser id="login-browser" type="content" src="about:blank" style="overflow:hidden !important;" />
					<label id="version-label" value="" />
					<label id="loading-label" value="正在登录..." />		
				</vbox>
				<vbox id="login-form-box"  flex="1">
					<grid id="login-form-grid" >
						<columns>
							<column id="login-form-label-column" />
							<column id="login-form-input-column" />
						</columns>	
						<rows>
							<row align="center" flex="1">
								<label value="用户名:" control="username" />
								<menulist id="username-menulist" onselect="Login.onSelectAccount();"  tabindex="1" editable="true" onkeydown="if(event.keyCode==13)return Login.doLogin();">
									<menupopup id="username-menupopup"></menupopup>
								</menulist>
							</row>
							<row align="center" flex="1">
								<label value="密  码:" control="password" />
								<textbox id="password-textbox" tabindex="2" type="password" value="" onkeydown="if(event.keyCode==13)return Login.doLogin();" />
							</row>
						</rows>
					</grid> 
					<hbox id="login-form-buttons-box" >
						<checkbox id="autologin-checkbox" label="自动登录" tooltiptext="注：在网吧或公共场所请慎用此功能，以免帐号被盗！" />	
						<checkbox id="rempassword-checkbox" label="记住密码" tooltiptext="注：在网吧或公共场所请慎用此功能，以免帐号被盗！" />	
						<button id="clearhistory-button" tooltiptext="清除本机的所有历史记录！" label="清除历史记录" oncommand="Login.removeHistory();"  />
						<button id="getpassword-button" label="忘记密码?" oncommand="Login.doUserForgotPass();"  />
					</hbox>
				</vbox>
			</vbox>
			<hbox id="login-buttons-box" >
				<vbox flex="1" >
					<hbox id="nav-buttons-box">
						<button  id="userreg-button" tabindex="6" label="注册" tooltiptext="用户注册" oncommand="Login.doUserReg(); return false;" />
						<button id="userexit-button" tabindex="5" label="退出" tooltiptext="退出" oncommand="window.close();return false;"  />
						<button id="getpassword2-button" label="忘记密码" oncommand="Login.doUserForgotPass();"  />
					</hbox>
				</vbox>
				<box  flex="1" align="right" >
					<button id="userlogin-button" tabindex="3" label="登录" tooltiptext="登录" oncommand="return Login.doLogin();" /> 
				</box>
			</hbox>
			<vbox id="update-box" hidden="true">
				<label id="updating-label" value="正在下载..." />
				<vbox id="process-box">
					<progressmeter mode="determined" id="downloadprocessbar" value="0%"/>
				</vbox>
			</vbox>
		</vbox>
	
	</window>
</overlay>
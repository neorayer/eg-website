<?xml version="1.0"  encoding="UTF-8"?>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.skymiracle.com/dev/java/taglibs/sor.tld" prefix="sor" %>  
<%@page language="java" contentType="application/vnd.mozilla.xul+xml;charset=UTF-8" pageEncoding="UTF-8" %>

<?xml-stylesheet  type="text/css" href="chrome://global/skin" ?>
<sor:xss url="skin/${skin}/default.css"  />
<sor:xss url="skin/${skin}/global/style.css"  />
<sor:xss url="skin/${skin}/setting/style.css"  />

<sor:xol url="ol-dialog.xul" />

<overlay
	xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul">
	<dialog
		id="setting-dialog"
		title="KO竞技游戏平台设置"
		xmlns:html="http://www.w3.org/1999/xhtml"
		xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
		onload="Setting.onLoad();"
		width="450"
		height="620"
		hidechrome="true"
		buttons="accept,cancel"
		buttonlabelaccept="确定"
		buttonlabelcancel="取消"
		ondialogaccept="return Setting.doSubmit()"
		onkeydown="Setting.onWindowKeyDown(event)"
		>
	
		<sor:js url="js/jquery.js" />
		<sor:js url="js/lib_dbg.js" />
		<sor:js url="js/lib_g.js" />
		<sor:js url="js/lib_io.js" />
		<sor:js url="js/lib_json.js" />
		<sor:js url="js/app_ver.js" />
		<sor:js url="js/app_const.js" />
		<sor:js url="js/pg_setting.js" />
		
		<box id="dialog-head-box" />
		
		<hbox > 
			<vbox>
				<listbox id="setting-games-list" onselect="Setting.onSelectGame();" >
				</listbox>
				<groupbox id="setting-common-box">
					<checkbox id="autologin-checkbox" label="自动登录" tooltiptext="注：在网吧或公共场所请慎用此功能，以免帐号被盗！" />	
					<checkbox id="rempassword-checkbox" label="记住密码" tooltiptext="注：在网吧或公共场所请慎用此功能，以免帐号被盗！" />	
				</groupbox>
			</vbox>
			<vbox id="setting-info-box" flex="1">
				<vbox id="game-startmodal-box" >
					<caption label="启动游戏模式设置" tooltiptext="启动游戏模式设置" />
					<radiogroup id="game-startmodal-radiogroup" flex="1">
						<radio selected="true" label="普通模式" />
						<description>
    						默认使用的模式，效率较高，推荐使用。
						</description>
						<radio  label="特殊模式" />
						<description >
    						增强兼容性的模式，如果普通模式无法启动游戏可以尝试使用此模式。
						</description>
					</radiogroup>
				</vbox>
				
				<vbox id="game-exe-box">
					<label value="1.游戏可执行文件或快截方式：" />
					<hbox >
						<textbox  id="game-exefile-textbox" flex="1" />
						<vbox>
							<hbox>
								<button id="setting-browserfile-button" label="浏览" oncommand="Setting.browserFile();" tooltiptext="浏览" />
								<button id="setting-searchfile-button" label="搜索" oncommand="Setting.searchFile();" tooltiptext="搜索" />    				
							</hbox>
						</vbox>					
					</hbox>
					<label id="game-exefileparms-label" value="2.游戏运行参数：" />
					<hbox >
						<textbox id="game-exefileparms-textbox" />
						<vbox>
						<button id="setting-apply-button" label="应用" oncommand="Setting.apply()" tooltiptext="应用" />
						</vbox>
					</hbox>
				</vbox>
				
				<vbox id="setting-tip-box">
					<caption label="提示" tooltiptext="提示"></caption>
					<description >
    				首先选择左边的游戏，然后，可以通过“浏览”定位各个游戏的可执行程序。“游戏运行参数”请手动修改。点击“应用”提交此项修改，“确定”提交所有修改设置，“取消”取消所有修改设置。
					</description>
				</vbox>
				
			</vbox>
			
		</hbox>
		
	</dialog>
</overlay>
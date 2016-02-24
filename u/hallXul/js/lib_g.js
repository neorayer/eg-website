function _G(element) {
	if (typeof element == 'string')
		element = document.getElementById(element);
	return element;
}

function _C(ctr, tagName, attrs) {
	var el = document.createElement(tagName);
	for(var k in attrs) {
		var v = attrs[k];
		el.setAttribute(k, v);
	}
	if (ctr)
		_G(ctr).appendChild(el);
	return el;
}

function _D(el) {
	var el = _G(el);
	if (!el)
		return;
	el.parentNode.removeChild(el);
}

var _OBJ = {
	clone: function(object) {
		var a = {};
		this.extend(a, object);
		return a;
	},
	extend: function(obj, extObj) {
		for (var k in extObj) {
			var p = extObj[k];
			if (p instanceof Object 
					&& !(p instanceof Function) )
				obj[k] = this.clone(p);
			else{
				obj[k] = p;
			}
		}
	}
}
function cloneObj(object){
}

/* ------------------- richEval -----------------*/
/**
 * Do eval a script string. It be used in transforming a http 
 * request response text into JSON Object, always.
 * 
 * @param s	{String}	the script string will be eval.
 * 
 * @return	{JSONObject}	a JSON object with specal format.
 * 		format: {
 * 			res: {Integer}	1 or 0,
 * 			data:	{Object | Object[]} result data or datas.
 * 			msg:	{String}	other information.	
 * 		}
 * 
 * @author zhourui
 * 
 * @NOTE: !!! richEval() need to be modified, becouse it has more tight coupling with special application. !!!
 */
 // NOTE bug exist maybe.
var APP_SUCC_VAL = APP_SUCC_VAL || "yes";
function richEval(s) {
	//try
	 {
		var ts = s.trim();
		var rv;
		if (ts.indexOf(0) == '[')
			rv = eval(s);
		rv = eval('('  + s + ')');
		
		// TODO different application different response format
//		var v = Object.extend({}, rv);
		var v = _OBJ.clone(rv);
		_OBJ.extend(v,{
			res: rv.res == "yes" || rv.res == 1? 1 : 0,
//			msg: "NO_AUTH" == rv.desc ? "AUTHFAILED" : rv.desc,
			msg: rv.desc || rv.data,
			data:rv.data
		});
		
		return v;
	}
	/*
	catch(e) {
		DEBUG("************************************************");
		DEBUG(" Ajax Request Response JSON Exception");
		DEBUG("************************************************");
		DEBUG(e);
		DEBUG("************** Response Text *******************");
		DEBUG(s);
		DEBUG("************** Response Text [END] *************");
		return {res: 0, msg: e, data: {}};
	}
	*/
}

/*------------------ String ------------------- */
/**
 * trim() method for String Object.
 */
String.prototype.trim = function()
{
	return this.replace(/(^\s*)|(\s*$)/g, "");
}


var WinTool = {
	min: function() {
		window.minimize();
	},
	
	max: function() {
		window.moveTo(0,0);
		window._PREWIDTH = window.outerWidth;
		window._PREHEIGHT = window.outerHeight;
		window.resizeTo(screen.availWidth,screen.availHeight);	
		_G("winhead-normal-button").setAttribute("hidden", "false");
		_G("winhead-max-button").setAttribute("hidden", "true");
	},
	
	normal: function() {
		var _cL = (screen.availWidth - window._PREWIDTH)/2;
		var _cT = (screen.availHeight - window._PREHEIGHT)/2;
		window.moveTo(_cL,_cT);
		window.resizeTo(window._PREWIDTH,window._PREHEIGHT);
		_G("winhead-normal-button").setAttribute("hidden", "true");
		_G("winhead-max-button").setAttribute("hidden", "false");
	},
	
	close: function() {
		window.close();
	},

	/*
	 * name 就是 open的时候,url后面那个参数
	 */
	findByName: function(name) {
		var wm = Components.classes["@mozilla.org/appshell/window-mediator;1"]
           .getService(Components.interfaces.nsIWindowMediator);
		var enumerator = wm.getEnumerator(null);
		while(enumerator.hasMoreElements()) {
			var win = enumerator.getNext();
			if (win.name == name)
				return win;
		}
		return null;
	}
}


var Shell = {
	execFile: function(exeFilePath,parmsArr,isBlocked) {
		DBG("execFile", exeFilePath);
		var file =Components.classes["@mozilla.org/file/local;1"].createInstance(Components.interfaces.nsILocalFile);
		
		file.initWithPath(exeFilePath);
		
		var process =Components.classes["@mozilla.org/process/util;1"].createInstance(Components.interfaces.nsIProcess);
		
		process.init(file);
		
		var argv = (parmsArr.constructor != Array ? [] : parmsArr );
	
		isBlocked=!!isBlocked;
		
		process.run(isBlocked, argv, argv.length);
		return process;
	}
}

var BrowserTool = {
	openWithSys: function(url) {
		var ioservice = Components.classes["@mozilla.org/network/io-service;1"]
		                          .getService(Components.interfaces.nsIIOService);
		var uriToOpen = ioservice.newURI(url, null, null);
		var extps = Components.classes["@mozilla.org/uriloader/external-protocol-service;1"]
		                      .getService(Components.interfaces.nsIExternalProtocolService);
		extps.loadURI(uriToOpen, null);
	}
	
}

var _UC = {
	u2c: function(text, charset) {
		var conv = Components.classes["@mozilla.org/intl/scriptableunicodeconverter"]
			.createInstance(Components.interfaces.nsIScriptableUnicodeConverter);
		conv.charset = "gb2312";
		return conv.ConvertFromUnicode(text);
	},
	u2gb2312: function(text) {
		return this.u2c(text, "GB2312");
	}
}

function sys_alert(v) {
	var prompts = Components.classes["@mozilla.org/embedcomp/prompt-service;1"]
                        .getService(Components.interfaces.nsIPromptService);
    prompts.alert(window,'警告',v);
}

function AlertOnLoad() {
	try {
		var parms = window.location.search.substring(1);
		var p = parms.split('&');
		var pairKey = {};
		for (var i = 0; i < p.length; i++) {
			var pp = p[i].split('=');
			pairKey[pp[0]] = unescape(pp[1]);
		}
	} catch (e) {
		_alert('无法读取界面参数！');
		dump(e);
		return;
	}
	_G('alert-content-label').value = pairKey['content'];
}

function _alert(v){
	try{
		var windowWatcher = Components.classes["@mozilla.org/embedcomp/window-watcher;1"].createInstance(Components.interfaces.nsIWindowWatcher);
		var _waitingWindow = windowWatcher.getWindowByName('waiting',window);
		if(_waitingWindow){
			_waitingWindow.close();
		}
	}catch(e){
		sys_alert(e);
	}
	var win = window.openDialog("alert.xul?content="+escape(v),"","modal,chrome,centerscreen=yes");
	return;
}

function _confirm(v){
	var prompts = Components.classes["@mozilla.org/embedcomp/prompt-service;1"]
                        .getService(Components.interfaces.nsIPromptService);
   return prompts.confirm(window,'提示',v);
}


function onWindowReloadKeyDown(e) {
	if (e.keyCode == 116)
		window.location.reload()
}

function playSound(filePath) {
	var _sound = Components.classes["@mozilla.org/sound;1"]
			.createInstance(Components.interfaces.nsISound);
	var ioService = Components.classes["@mozilla.org/network/io-service;1"]
			.getService(Components.interfaces.nsIIOService);
	var file = null;
	try {
		file = Components.classes["@mozilla.org/file/directory_service;1"]
				.getService(Components.interfaces.nsIProperties).get(
						"CurProcD", Components.interfaces.nsIFile);
		file.append(filePath);

		var url = ioService.newURI('file:///' + file.path, null, null);
		_sound.init();
		_sound.play(url);
	} catch (e) {
		dump('无法播放声音文件！' + e.toString());
		_sound.init();
		_sound.beep();
	} finally {
		_sound = null;
		ioService = null;
		file = null;
	}
}



function enableDump(bool) {
	const PREFS_CID = "@mozilla.org/preferences;1";
	const PREFS_I_PREF = "nsIPref";
	const PREF_STRING = "browser.dom.window.dump.enabled";
	try {
		var Pref = new Components.Constructor(PREFS_CID, PREFS_I_PREF);
		var pref = new Pref();
		pref.SetBoolPref(PREF_STRING, bool);
	} catch (e) {
		dump(e);
	}
}

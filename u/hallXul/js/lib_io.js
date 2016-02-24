/**
 * Var name rules:
 *	a_XXX		argument
 *	file		nsILocalFile
 *	__XXX		private method / attribute
 * _c_XXX		callback function
 */

var _OBF = {
	/* @mozilla.org/file/local;1 */
	localFile: function(/* String | nsILocalFile */ a_file) {
		var file =  Components.classes["@mozilla.org/file/local;1"]
				.createInstance(Components.interfaces.nsILocalFile);		
		if (a_file instanceof Components.interfaces.nsILocalFile)
			file.initWithFile(a_file);
		else {
			file.initWithPath(a_file);
		}
		//////////////alert(a_file.path?a_file.path:a_file);
		return file;
	},
	
	/* file-output-stream */
	fos: function(/* String | nsILocalFile */ a_file) {
		var file = _OBF.localFile(a_file);

		var fos = Components.classes["@mozilla.org/network/file-output-stream;1"]
				.createInstance(Components.interfaces.nsIFileOutputStream);
		fos.init(file, -1, -1, 0);
		return fos;
	},
	
	/* mozilla.org/network/file-input-stream;1 */
	fis: function (/* String | nsILocalFile */ a_file) {
		var file = _OBF.localFile(a_file);

		var fis = Components.classes["@mozilla.org/network/file-input-stream;1"]
				.createInstance();
		fis.QueryInterface(Components.interfaces.nsIFileInputStream);
		fis.init(file, 0x01, 0444, 0);
		fis.QueryInterface(Components.interfaces.nsILineInputStream);
		return fis;
	},

	/*  */	
	iniParser: function(/* String | nsILocalFile */ a_file) {
		var file = _OBF.localFile(a_file);
		  return Components.manager.getClassObjectByContractID(
		  		"@mozilla.org/xpcom/ini-parser-factory;1",
		  		Components.interfaces.nsIINIParserFactory
		  ).createINIParser(file);
		
	}
}

/**************************************************
 * FileIO
 **************************************************/
var FileIO = {
	appRootPath: "",
	
	init: function() {
		this.appRootPath = this.newAppRootFile().path;
	},
	
	createFile: function(/* nsILocalFile */ a_file, isDir) {
		if (a_file.exists())
			return;
		if (isDir)
			a_file.create(Components.interfaces.nsIFile.DIRECTORY_TYPE, -1);
		else
			a_file.create(Components.interfaces.nsIFile.NORMAL_FILE_TYPE, -1);
	},
	
	newAppRootFile: function(){
		//TODO: bug, fixme!!!!
		return this.__getFileByKey("CurProcD");
	},

	newUserTmpFile: function() {
		return this.__getFileByKey('TmpD');
	},
	
	saveText: function (/* String | nsILocalFile */ a_file, text) {
		var fos = _OBF.fos(a_file);
		fos.write(text,text.length);
		fos.close();
	},

	loadText: function(/* String | nsILocalFile */ a_file) {
		try {
			var fis = _OBF.fis(a_file);
			var text = "";
			var line = [];
			while(true) {
				var hasMore = fis.readLine(line);
				text += line.value;
				if (!hasMore)
					break;
			}
			fis.close();
			return text;
		}catch(e) {
			jsdump(e);
			return null;
		}
	},
	
	existsFile: function(filePath /* string */) {
		DBG("existsFile", filePath);
		var _file = Components.classes["@mozilla.org/file/local;1"].createInstance(Components.interfaces.nsILocalFile);
		_file.initWithPath(filePath);
		return _file.exists();
	},

	pickFile: function() {
		var nsIFilePicker = Components.interfaces.nsIFilePicker;
		var fp = Components.classes["@mozilla.org/filepicker;1"].createInstance(nsIFilePicker);
		fp.init(window, "选择文件", nsIFilePicker.modeOpen);
		var res = fp.show();
		if (res == nsIFilePicker.returnOK)
			return  fp.file;
		return null;
	},
	
	saveFile: function(/* string */ a_default_fname, /* nsILocalFile */ dir) {
		var nsIFilePicker = Components.interfaces.nsIFilePicker;
		var fp = Components.classes["@mozilla.org/filepicker;1"].createInstance(nsIFilePicker);
		fp.defaultString = a_default_fname;
		fp.init(window, "保存文件", nsIFilePicker.modeSave);
		
		if(dir != null)
			fp.displayDirectory = dir;
		
		var res = fp.show();
		if (res == nsIFilePicker.returnOK || res == nsIFilePicker.returnReplace)
			return fp.file;
		return null;
	},
	
	//////////////////// Private ////////////////////
	__getFileByKey: function(/* String */ _key){
		try{
			var file = Components.classes["@mozilla.org/file/directory_service;1"]
					.getService(Components.interfaces.nsIProperties)
					.get(_key, Components.interfaces.nsIFile);
			return file;
		}catch(e){
			//alert(e);
			return null;
		}
	}
}
FileIO.init();


/**************************************************
 * HttpIO
 **************************************************/
var HttpIO = {
	init: function() {
	},
	
	syncGet: function(/* String */ url, /* String | Object */params) {
		var req = null;
		try{
			req = new XMLHttpRequest();
			url = url.trim();
			if (url.indexOf('?') < 0)
				url += '?';

			var paramStr = "";
			if (typeof params != 'string')
				for (var k in params)
					paramStr += '&' + k + '=' + params[k];
			else
				paramStr = params;
			
			url += paramStr;			
			req.open("GET", url , false);
			req.send(null);
		}catch(e){
			//alert(e.toString());
			jsdump(e.toString());
			return null;
		}
		if(!req || !req.responseText){
			 return null;
		}else{
			//jsdump(req.responseText);
			return req.responseText;
		}
	},
	
	syncGetJSON: function(/* String */ url, /* String | Object */params) {
		var resp = this.syncGet(url, params);
		return richEval(resp);
	}
}
HttpIO.init();


/**************************************************
 * NetIO
 **************************************************/
var NetIO = {
	/*
	 *		onRequest	- The callback function when request begin, format:
	 *				function onRequest(contentLength) {};
	 *		onProgress	- The callback function when downloading data, format:
	 *				function onProgress(sizeOfDownloaded) {};
	 *		onOver		- The callback function when download is over, format:
	 *				function onOver() {};
 	 */
	IDownListener: {
		onRequest: function(/* int */ contentLength) {
		},
		onProgress: function(/* int */ sizeDownloaded, /*int*/ tmSpent, /*int*/tmLess, /*int*/tmAll, /*int*/speed) {
		},
		onOver: function(/* void */ ) {
		}
	},
	
	/**
	 * Download a file from uri.
	 * 
	 * params:
	 *		uri			- The URL to download from.
	 *		a_file 		- The local file you want to save.
	 *
	 * @author zhourui@skymriacle.com
 */
	download: function(uri, a_file, /* IDownListener */ downListener) {
		if (!downListener)
			downListener = this.IDownListener;
		
		if (uri.indexOf('?') < 0) 
			uri += '?' + Math.random();
		else
			uri += '&' + Math.random();
	
		var ios = Components.classes["@mozilla.org/network/io-service;1"]
			.getService(Components.interfaces.nsIIOService);
		var channel = ios.newChannel(uri, "UTF-8", null);
		var streamLoader = Components.classes["@mozilla.org/network/stream-loader;1"]
				.createInstance(Components.interfaces.nsIStreamLoader);
		var downedSize = 0;
		var allSize = 0;
		var tmBgn = (new Date()).getTime();
		var tmNow = (new Date()).getTime();
		var tmLes = -1;
		var tmAll = -1;
		var tmSpd = tmNow - tmBgn;
		var speed = 0;
		var observer = {
			onStartRequest : function(request,context) {
				tmBgn = (new Date()).getTime();
				allSize = channel.contentLength;
				downListener.onRequest(allSize);
				streamLoader.onStartRequest(request, context);
			},
			onStopRequest : function(request,context,statusCode) {
				streamLoader.onStopRequest(request, context, statusCode);
			},
			onStreamComplete : function(loader, context, status, length, result) {
				var fos = _OBF.fos(a_file);
				var bstream = Components.classes["@mozilla.org/binaryoutputstream;1"]
	                      .createInstance(Components.interfaces.nsIBinaryOutputStream);
				bstream.setOutputStream(fos);
				bstream.writeByteArray(result, length);
		
				if (fos instanceof Components.interfaces.nsISafeOutputStream) {
					fos.finish();
				} else {
					fos.close();
				}
				downListener.onOver();
			},
			onDataAvailable: function(request, context,inputStream,offset,count) {
				downedSize += count;
				tmNow = (new Date()).getTime();
				tmSpd = tmNow - tmBgn;
				spdMs = downedSize / tmSpd;
				tmAll = allSize / spdMs;
				spdS  = spdMs * 1000;
				tmLes = tmAll - tmSpd;
				downListener.onProgress(downedSize, tmSpd, tmLes, tmAll, spdS);
				streamLoader.onDataAvailable(request, context, inputStream, offset, count);
			}
		};
		streamLoader.init(observer);
		
		channel.asyncOpen(observer, channel); 
	}
}



var Req = {
	sessionKey : null,
	preParams: {},
	loadingCount : 0,
	showLoading: function(text) {
		Req.loadingCount ++;
		var G_loading = _G('loading-label');
		if(!G_loading){
			return;
		}
		if (text)
			G_loading.setAttribute('value',text);
		G_loading.style.visibility = 'visible';
	},
	
	hideLoading: function() {
		if (Req.loadingCount > 1) {
			Req.loadingCount --;
			return;
		}
		Req.loadingCount --;		
		var G_loading = _G('loading-label');
		if(!G_loading){
			return;
		}
		G_loading.style.visibility = 'hidden';
	},
	
	HIDE_LOADING: 'HIDE_LOADING',
	reqJson: function(url, params, onSucc, loadingText) {
		Req.reqJsonX(url, params, 
			onSucc, 
			function(data) {
				_alert(data);
			}, 
			loadingText, 
			function(XMLHttpRequest, textStatus, errorThrown)  {
				DEBUG(errorThrown);
				_alert("网络通讯异常，您的网络线路与服务器之间的连接出现故障。");
		});
	},
	
	openBrowser: function(url) {
		var url = url + "?sessionKey=" + Req.sessionKey;
			for(var k in Req.preParams)
				url += "&" + k + "=" + Req.preParams[k];
		
		var ioservice = Components.classes["@mozilla.org/network/io-service;1"]
		                          .getService(Components.interfaces.nsIIOService);
		var uriToOpen = ioservice.newURI(url, null, null);
		var extps = Components.classes["@mozilla.org/uriloader/external-protocol-service;1"]
		                      .getService(Components.interfaces.nsIExternalProtocolService);
		extps.loadURI(uriToOpen, null);
	},
	
	blockReqJson: function(url, params, loadingText) {
		if (loadingText != Req.HIDE_LOADING)
			Req.showLoading(loadingText);
		try {
			params = params ? params : {};
			params.sessionKey = Req.sessionKey;
			for(var k in Req.preParams)
				params[k] = Req.preParams[k];
			DBG("Req.blockReqJson ->", url);
			var res = null;
			var options = {
				url: url,
				async: false,
				data: params,
				success: function(r) {
					DBG("Req.blockReqJson <-", r);
					if ('yes' != r.res && '1' != r.res) {
						_alert(r.data);
						return;
					};
					res = r.data;
				},
				dataType: 'json',
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					DBG("blockReqJson", errorThrown);
				}
			}
			jQuery.ajax(options);
		}catch(e) {
			
		}
		Req.hideLoading();
		return res;
	},
	
	reqJsonX: function(url, params, onSucc, onFailed, loadingText, onError) {
		params = params ? params : {};
		params.sessionKey = Req.sessionKey;
		for(var k in Req.preParams)
			params[k] = Req.preParams[k];
		DBG("Req.reqJsonX ->", url);
		if (loadingText != Req.HIDE_LOADING)
			Req.showLoading(loadingText);
		var options = {
			url: url,
			data: params,
			type: 'POST',
			success: function(r) {
				DBG("Req.reqJsonX <-", r);
				Req.hideLoading();
				if ('yes' != r.res && '1' != r.res) {
					if (onFailed)
						onFailed(r.data);
					return;
				};
				if (onSucc)
					onSucc(r.data);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown)  {
				Req.hideLoading();
				if (onError)
					onError(XMLHttpRequest, textStatus, errorThrown) ;
			},
			dataType: 'json'
		}
		jQuery.ajax(options);
	}

}
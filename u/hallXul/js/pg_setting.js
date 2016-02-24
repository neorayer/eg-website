
var Setting = {
	
	savedData: {},
	gamesMap: {},
	props: {},
	gamesExeFiles: {
		'cs15':['mame32kpp.exe','cstrike.exe','hl.exe','hlds.exe','hltv.exe'],
		'cs16':['cstrike.exe','hl.exe','hlds.exe','hltv.exe'],
		'war3':['War3.exe','war3.exe','Warcraft III.exe'],
		'war3rpg':['War3.exe','war3.exe','Warcraft III.exe'],
		'starcraft':['starcraft.exe']
	},
	curGameId: null,
	curGame: null,
	
	onLoad: function() {
		var Main = window.opener.Main;
		Setting.gamesMap = Main.gamesMap;
		
		//添加List Item
		var gLst = _G('setting-games-list');
		for (var k in Setting.gamesMap) {
			var game = Setting.gamesMap[k];
			var item = gLst.appendItem(game.name, game.gameid);
			item.setAttribute('id', 'GAME_' + game.gameid);
		}
		
		
		//载入从前保存的设置数据
		Setting.loadSavedData();
		DBG("savedData", Setting.savedData);

		//缺省选择一项
		if (Main.curRoomId) {
			//如果在某房间里，则缺省选择该房间对应的游戏
			
			//取得该房间对应的gameid
			var gameId = Main.getCurRoom().gameid;
			gLst.selectedItem = _G('GAME_' + gameId);
			
		}else if (Main.curGameId) {
			//如果game Panels选择了一个游戏，则缺省选择该游戏item
			gLst.selectedItem = _G('GAME_' + Main.curGameId);
		}else {
			//如果没有进入房间，则不选择
			gLst.selectedIndex = -1;
		}
		gLst.focus();
		
		//载入Login.props
		Setting.loadProps();
		
		return;
	},
	
	loadProps: function() {
		var s = FileIO.loadText(FILE_loginProps);
		DBG("loadProps", s);
		if (!s)
			return;
		Setting.props = eval('(' + s+ ')');
		
		_G("autologin-checkbox").checked = Setting.props.isAutoLogin;
		_G('rempassword-checkbox').checked = Setting.props.isRemPassword;
	},
	
	saveProps: function() {
		Setting.props.isAutoLogin = _G("autologin-checkbox").checked ;
		Setting.props.isRemPassword = _G('rempassword-checkbox').checked;
		FileIO.saveText(FILE_loginProps, JSON.toString(Setting.props));
	},
	
	onWindowKeyDown: function(e) {
		if (e.keyCode == 116)
			window.location.reload()
	},
	
	loadSavedData: function() {
		if (!FILE_gameSetting.exists())
			FileIO.createFile(FILE_gameSetting, false);
		var text = FileIO.loadText(FILE_gameSetting);
		if (!text)
			text = "{}";
		Setting.savedData = eval('(' + unescape(text)+ ')');
	},
	
	onSelectGame: function() {
		if (_G('setting-games-list').selectedIndex < 0)
			return;
			
		var li = _G('setting-games-list').selectedItem;
		Setting.curGameId = li.value;
		Setting.curGame = Setting.gamesMap[Setting.curGameId];
		Setting.switchGame(Setting.curGameId);
		return;
	},
	
	switchGame: function(gameId) {
		var data = Setting.savedData[gameId];
		
		//如果以前没有保存过，则给个空数据
		data = data || {};
		
		//填充表单数据
		_G('game-exefile-textbox').value = data.exefile || ""; 
		_G('game-exefileparms-textbox').value = data.exefileparms || ""; 
		_G('game-startmodal-radiogroup').selectedIndex = data.modal ? data.modal : 0;
	},

	browserFile: function() {
		//打开文件选择框，选择文件
		var file = FileIO.pickFile();
		if (!file)
			return;
			
		_G("game-exefile-textbox").value = file.path;
		
		//检查文件名是否符合要求
		var s = Setting.checkFileValid(file.path);
		if (s) {
			_alert(s);
		}
	},

	apply: function() {
		//如果没选择任何选项，则返回成功
		var selectId = _G('setting-games-list').selectedIndex;
		if (selectId < 0) 
			return true;
		
		var gameId = _G('setting-games-list').selectedItem.value;
		//从表单中提取出设置的数据
		var data = {
			modal: _G('game-startmodal-radiogroup').selectedIndex,
			exefile: _G('game-exefile-textbox').value,
	   		exefileparms: _G('game-exefileparms-textbox').value
	   	}
	   	
	   	var s = Setting.checkFileValid(data.exefile);
	   	if (s) {
	   		_alert(s);
	   		return false;
	   	}
	   	
	   	//存入savedData
	   	Setting.savedData[gameId] = data;
	   	
	   	//saveProps
	   	Setting.saveProps();
	   	
	   	return true;
	},

	//TODO: 这里没做完
	doSubmit: function() {
		//应用当前设置,如果没成功，则直接返回
		if (!Setting.apply())
			return;
		
		//保存数据
		var s = escape(JSON.toString(Setting.savedData));
		FileIO.saveText(FILE_gameSetting, s);
		return true;
	},

	checkFileValid: function(filePath){
	   	//如果什么都没填写，则返回成功
	   	if (filePath == '')
	   		return null;
	   		
		//检查文件是否存在
		if (!FileIO.existsFile(filePath))
			return '文件"' + filePath + '"不存在!';
			
		//检查文件名是否符合规则
		var validFiles = Setting.gamesExeFiles[Setting.curGameId];
		var isValid = false;
		for(var i=0; i<validFiles.length; i++) {
			var src = filePath.toLowerCase();
			var keyWord = validFiles[i].toLowerCase();
			if (src.indexOf(keyWord) > 0) {
				isValid = true;
				break;
			}
		}
		if (!isValid) {
			var s = "不是有效的文件名，必须选择：";
			for(var i=0; i<validFiles.length; i++) {
				s += validFiles[i] + " ";
			}
			return s;
		}
		return null;

	},
	cGameFileInvalid: function(v){
			if(v.trim()=='') return null;
			var cName = $('setting-games-list').selectedItem.getAttribute('label');
			var maybeFileNames =  gameExeFiles[$('setting-games-list').selectedItem.getAttribute('value')];
			if(maybeFileNames){
				for(var i = 0 ; i<maybeFileNames.length; i++)	if(maybeFileNames[i]==v)	break;
				if(i==maybeFileNames.length){
					$("game-exefile-textbox").value = '';
					return ("你当前选择是："+ v +"\n--------\n“"+cName + "”对应可执行文件名：" + maybeFileNames.join('、'));
				}
				return null;
			}else{
				return ("“"+cName + "”对应文件名无法指定！");
			}
	},
	cGameFilesInvalid:function(){
		return this._cGameFileInvalid($("game-exefile-textbox").value);
	},
	searchFile: function(){
		window.openDialog('search-exe.xul','search-exe-dialog','modal,chrome,centerscreen=yes');
	},
	
	setGameExePath: function(path) {
		_G('game-exefile-textbox').value = path;
	}
	
	
}



window._PREWIDTH = 1000;
window._PREHEIGHT = 700;
var Main = {
	env: {},
	gamesMap: {},
	roomsMap: {},
	mapsMap: {},
	curGameId: null,
	curZoneId: null,
	curRoomId: null,
	isInGame: false,
	
	onLoad: function() {
		
		//清除roomPropsFile临时文件
		Main.delRoomPropsFile();
		
		//载入环境数据，此数据在Login时设置入文件
		Main.env = eval('(' + unescape(FileIO.loadText(FILE_initGameHall))+ ')');
		DBG("main: onLoad()", Main.env);

		//将sessionKey设置入Req.sessionKey
		Req.sessionKey = Main.env.gameuser.sessionkey;
		//将username设置入Req.preParams
		Req.preParams.username = Main.env.gameuser.username;

		//修改窗口标题
		window.document.title = Main.env.hall.name;

		//房间iframe初始化
		Main.initRoomChat();
		
		//启动托盘
		Main.startTray();

		//显示版本信息
		Main.showVer();
		
		//显示用户信息
		Main.showUserInfo();
	
		
		//启动与服务器连接的消息线程
		Main.startMsgListener();
		
		//载入Games列表
		Main.loadGames();
		
		//载入好友列表
		UserFriend.loadFriends();
		
		//载入我的战队
		Team.loadMyTeam();
		
		//载入本地录像
		Replay.loadLocalReplays();
		
	},
	
	startTray: function() {
		var params = [_UC.u2gb2312(Main.env.hall.name)];
		Shell.execFile(File_hallTray.path, params, false);
	},
	
	hideWin: function() {
		var params = ['-h', _UC.u2gb2312(Main.env.hall.name)];
		Shell.execFile(File_hallTray.path, params, false);
	},
	

	onUnload: function() {
		//清除roomPropsFile临时文件
		Main.delRoomPropsFile();
		
		//关闭所有附加窗口
		for(var key in Main.openedWins) {
			try {
				Main.openedWins[key].close();
			}catch(e)
			{
			}
		}

		//远程请求，退出平台
		//特别注意：调试的使用用F5刷页面，会在服务器上logout，很烦很烦
		//TODO:这里在调试时，先屏蔽
//return;

		Req.reqJsonX(Main.env.url_logout, {}, 
			function(data) {
			},
			function(data) {
			},
			"",
			function(data) {
			}
		);	
		return true;
	},
	
	
	onWindowKeyDown: function(e) {
		if (e.keyCode == 116)
			window.location.reload()
	},
	
	openBrowser: function(url) {
		var url = url + "&username=" + Main.env.gameuser.username + "&loca=fromkoplatform" + "&sessionKey=" + Main.env.gameuser.sessionkey;
		BrowserTool.openWithSys(url);
	},
	
	showVer: function() {
		var s = '登陆时间：' + (new Date()).toLocaleString() + ', 版本：' + VerMgr.getVer();
		_G('logininfo-label').setAttribute('value', s);
	},
	
	showUserInfo: function() {
		var gameuser = Main.env.gameuser;
		_G('person-username-desc').setAttribute('value',gameuser.username);
		_G('person-level-desc').setAttribute('value',  gameuser.level);
		_G('person-nickname-desc').setAttribute('value', gameuser.nickname);
		_G('person-point-desc').setAttribute('value', gameuser.point);
		_G('person-usertype-desc').setAttribute('value',gameuser.usertype);
		_G('person-realip-desc').setAttribute('value', gameuser.realip);
		_G('person-iplocation-desc').setAttribute('value', gameuser.iplocation);
		_G('person-iplocation-desc').setAttribute('value', gameuser.iplocation);
		_G('person-iplocation-desc').setAttribute('tooltiptext', gameuser.iplocation);
		
		_G('aide-avator-image').src = Main.env.url_user_avator;
	},
	
	startMsgListener: function() {
		Main.waitingMsg(Main.env.url_msg);
	},
	
	//接受消息：如果收到消息后，立刻再次发起请求。如果服务器实效，则停留5秒后发起请求。
	waitingMsg: function(url) {
		Req.reqJsonX(url, {}, 
			//onSucc 回调
			function(data) {
				try { //这里用try括起来，以达到面对异常的鲁棒性
					var msg = data;
					//处理消息
					switch (msg.cmd) {
						case 'roomInOut':
							Main.showInOutRoom(msg);
							break;
						case 'chat':
							if (msg.dest == '__ROOM__') {
								Main.showRoomChat(msg);
							} else {
								Main.showPsnChat(msg);
							}
							break;
						case 'gameInOut':
							Main.showGameInOut(msg);
							break;
						case 'ReqAddFriend':
						case 'AddFriendRes':
							Main.addMsgToQueue(msg);
							break;
						case 'fightRes':
							Main.showFightResMsg(msg);
							break;
						default:
							break;
					}
				}catch(e) {
					jsdump(e);
					DBG("waitingMsg", e);
				}
				Main.waitingMsg(url);
			},  
			//onFailed回调
			function(data) {
				setTimeout(function() {Main.waitingMsg(url)}, 5000);
			},
			 Req.HIDE_LOADING,
			//onError回调
			function(data) {
				setTimeout(function() {Main.waitingMsg(url)}, 5000);
			}
		);
	},
	
	msgQueue: [],
	addMsgToQueue: function(msg) {
		//放入消息Queue
		Main.msgQueue.push(msg);
		//消息提示
		Main.showMsgTip();
	},
	
	showMsgTip: function() {
		var lbl = _G("msg-label");
		if (Main.msgQueue.length > 0) {
			lbl.setAttribute("hidden", false);
			lbl.setAttribute("value", "您有" + Main.msgQueue.length + "条新消息");
		}else {
			lbl.setAttribute("hidden", true);
		}
	},
	
	viewLastMsg: function() {
		if (Main.msgQueue.length <= 0)
			return;
		//取出消息
		var msg = Main.msgQueue.pop();

		//根据消息类型分别处理
		switch (msg.cmd) {
			case 'ReqAddFriend':
				//请求加为好友消息
				Main.showReqAddFriendMsg(msg);
			break;
			case 'AddFriendRes':
				//加为好友被接受 消息
				var user = msg.sender;
				_alert('“' + user.nickname + '”接受了您的请求，成为您的好友！');
				UserFriend.addItem(user);
			break;
			case 'ChatTip':
				//聊天消息
				Main.createChatWin(msg.winName);
			break;
			default:
				_alert("ERROR:未知的消息类型" + msg.cmd);
			break;
		}
		
		Main.showMsgTip();
	},
	
	getChatWin: function(msg) {
		//如果发送者就是自己，说明是自己跟别人聊天的返回数据，则按目标者寻找创窗口
		var othername = msg.sender.username == Main.env.gameuser.username ? msg.dest : msg.sender.username;
		return WinTool.findByName("chat-" + othername);
	},
	
	openChatWin: function(msg) {
		return Main.getChatWin(msg) || Main.createChatWin(Main.getChatWinName((msg)));
	},
	
	createChatWin: function(winName) {
		var othername = winName.substring(5);
		var win = Main.openedWins[winName] = window.openDialog("chat.xul?o_username=" + othername, winName, "chrome,centerscreen=yes");
		win.focus();
		return win;
	},
	
	createChatWinToUser: function(username) {
		Main.createChatWin('chat-' + username);
	},
	
	showReqAddFriendMsg: function(msg) {
		var s = msg.sendernick + "(" + msg.sendername + ")请求与你成为好友,是否同意？";
		if (_confirm(s)) {
			UserFriend.addFriend(msg.sendername);
		}
	},
	
	showInOutRoom: function(msg) {
		//如果该信息是自己的进出信息，则不显示
		//if (msg.who.username == Main.env.gameuser.username)
		//	return;
			
		//TODO:这里要多做些功能，比如点用户查看信息，有操作菜单，如加入黑名单，加为好友等
		//显示用户进入消息
		var s = "";
		if (msg.isin == 'true') {
			var speed = msg.speed < 0 ? " >1000" : msg.speed;
			s += ' 进入房间, 速度' + speed + 'ms, 地点:' + msg.who.iplocation;
		}else
			s += ' 离开房间';

		var chatDoc = _G('room-chat-box').contentDocument;
		var el = chatDoc.createElement('span');
		el.innerHTML = s;
		
		Main.appendMsg(msg.msgtime, msg.who, el);
		
		//在roomUsersList中增删用户列表项
		if (msg.isin == 'true') {
			RoomUser.addItem(msg.who);
		}else {
			RoomUser.delItem(msg.who);
		}
	},
	
	showGameInOut: function(msg) {
		var s = "";
		if (msg.isin == 'true') {
			//进入游戏的消息
			s += ' 进入游戏';
			//更改roomUsers List的相关item 标题
			_G("RU_TITLE_" + msg.who.username).value = msg.who.nickname + "(游戏中...)";
		}else {
			//退出游戏的消息
			s += ' 退出游戏';
			//更改roomUsers List的相关item 标题
			_G("RU_TITLE_" + msg.who.username).value = msg.who.nickname ;
		}

		var chatDoc = _G('room-chat-box').contentDocument;
		var el = chatDoc.createElement('span');
		el.innerHTML = s;
		
		//显示消息
		Main.appendMsg(msg.msgtime, msg.who, el);
	},

	getChatWinName: function(msg) {
		var othername = msg.sender.username == Main.env.gameuser.username ? msg.dest : msg.sender.username;
		return "chat-" + othername;
	},
	
	isChatTipMsgExists: function(msg) {
		for(var i=0; i<Main.msgQueue.length; i++) {
			var theMsg = Main.msgQueue[i];
			if ('ChatTip' == theMsg.cmd && Main.getChatWinName(msg) == theMsg.winName)
				return true;
		}
		return false;
	},
	
	psnChatQueues: {},
	showPsnChat: function(msg) {
		var chatWin = Main.getChatWin(msg);
		if (chatWin) {
			//如果与对方的聊天窗口已经打开，则直接显示消息
			chatWin.Chat.showPsnChat(msg);
		}else {
			//如果与对方的聊天窗口未打开，则产生一个提示消息加入队列
			var winName = Main.getChatWinName(msg);

			//判断是否已经有相同的提示消息,没有发提示
			if (!Main.isChatTipMsgExists(msg)) {
				var chatTipMsg = {
					cmd: 'ChatTip',
					winName: winName
				}
				Main.addMsgToQueue(chatTipMsg);
			}
			
			//把消息加入与这家伙的聊天队列
			var q = Main.psnChatQueues[winName];
			if (!q)
				q = Main.psnChatQueues[winName] = [];
			q.push(msg);
		}
	},
	
	getLastChatMsg: function(winName) {
		var q = Main.psnChatQueues[winName];
		if (!q)
			return null;
		return q.pop();
	},
	
	showRoomChat: function(msg) {
		var chatDoc = _G('room-chat-box').contentDocument;
		var c = msg.content;
		
		//解析内容中的表情图标
		var regex =/\[\[\[([^\]]+)\]\]\]/;
		var r = regex.exec(c);
		var el = null;
		if (r && r.length > 0) {
			//是表情图标			
			el = chatDoc.createElement('img');
			el.src = r.pop();
			el.width = 50;
			el.height = 50;
		}else {
			//是普通文字
			el = chatDoc.createElement('span');
			el.innerHTML =  ' 说:' + c;
		}
		Main.appendMsg(msg.msgtime, msg.sender, el);
	},
	
	showFightResMsg: function(msg) {
		var chatDoc = _G('room-chat-box').contentDocument;

		var el = chatDoc.createElement('span');
		el.setAttribute('class', "fightres-chat-label");
		el.innerHTML =  "最新战报：" + msg.content;
		el.addEventListener("click", function() {
			var url = Main.env.url_fightres
					+ '&username='+ Main.env.gameuser.username
					+ '&sessionKey=' + Main.env.gameuser.sessionkey
					+ '&id=' + msg.fightlog.id;
			window.openDialog(url,"fightres-dialog","modal,chrome,centerscreen=yes");
		}, false);

		
		Main.appendMsg(msg.msgtime, null, el);
	},
	
	//向房间聊天窗口加入信息。dom是生成好的Element对象
	// sender是消息的发送者的GameUser对象
	// doms 可以是一个dom,或者一组dom
	appendMsg: function(time, sender, doms) {
		var chatDoc = _G('room-chat-box').contentDocument;
		var body = chatDoc.body;
		
		//容器：信息行
		var box = chatDoc.createElement("div");
		box.setAttribute('class', 'roomchat-line-box');
		body.appendChild(box);

		//时间
		if (time) {
			var el = chatDoc.createElement("span");
			box.appendChild(el);
			el.innerHTML = time + " ";
			el.setAttribute("class","chatline-time");
		}
		
		//发送者
		//此处的menupopup与onChatUserMenuPopupShow关联着看
		if (sender) {
			var el = chatDoc.createElement("span");
			box.appendChild(el);
			el.innerHTML = sender.nickname;
			el.setAttribute("class","chatline-sender");
		}

		//内容 信息
		var el =  chatDoc.createElement("span");
		box.appendChild(el);
		var doms = doms instanceof Array ? doms : [doms];
		for (var i=0; i<doms.length; i++) {
			el.appendChild(doms[i]);
		}

//		box.appendChild(el);
//		el.setAttribute("class", "chatline-content");
		
		body.scrollTop = body.scrollHeight + 100000;
	},

	initRoomChat: function() {
		var chatDoc = _G('room-chat-box').contentDocument;
		chatDoc.write('<html><head>');
		chatDoc.write('<link rel="stylesheet" href="http://hall.ko10000.com/GameHall2/u/hallXul/chatCss.jsp.vi" type="text/css" ></link>');
		chatDoc.write('</head><body></body></html>');
		chatDoc.close();
		
	},
	
	curMenuPopChatUsername: null,
	onChatUserMenuPopupShow: function(el) {
		Main.curMenuPopChatUsername = el.getAttribute("chatUsername");
		el.removeAttribute("chatUsername");
	},
	onChatUserMenuPopupHide: function(el) {
		
	},
	
	
	//将games数组转为gamesMap影射表
	gamesToMap : function(games) {
		var map = {};
		for(var i=0; i < games.length; i++) {
			var game = games[i];
			map[game.gameid] = game;
		}
		Main.gamesMap = map;
	},
	
	//将zones数组存入gamesMap的相应game.zonesMap中
	zonesToMap: function(gameId, zones) {
		var map = {};
		for(var i=0; i < zones.length; i++) {
			var zone = zones[i];
			map[zone.zoneid] = zone;
		}
		Main.gamesMap[gameId].zonesMap = map;
	},
	
	//将rooms数组存入gamesMap的game.zonesMap的zone的roomsMap中
	roomsToMap: function(gameId, zoneId, rooms) {
		var map = {};
		for(var i=0; i< rooms.length; i++) {
			var room = rooms[i];
			map[room.roomid] = room;
		}
		Main.gamesMap[gameId].zonesMap[zoneId].roomsMap = map;
	},
	
	//将maps数组存入gamesMap的game.zonesMap的zone的mapsMap中
	mapsToMap: function(gameId, zoneId, maps) {
		var map = {};
		for(var i=0; i< maps.length; i++) {
			var zonemap = maps[i];
			map[Main.getMapKey(zonemap)] = zonemap;
		}
		Main.gamesMap[gameId].zonesMap[zoneId].mapsMap = map;
	},
	
	loadGames: function() {
		//请求远程数据
		Req.reqJson(Main.env.url_get_games, {}, function(data) {
			//存入gamesMap
			Main.gamesToMap(data);
			
			//缺省选择第一个game
			_G("gametype-list").selectedIndex = 1;
		});
	},
	
	on_select_gametype_list: function(el) {
		var list = el;
		//如果选中了第一个item(其实它是隐藏的），则直接返回
		//通常这发生在window第一次load的自动选择
		if (0 == list.currentIndex)
			return;
		
		Main.switchGame(list.value);
	},
	
	switchGame: function(gameId) {
		//保存当前gameId
		Main.curGameId = gameId;
		
		//载入game的所有zones
		Main.loadZones(gameId);
	},
	
	loadZones: function(gameId) {
		//请求远程数据
		
		var _whenGotZones = function(zones) {
			//调用drawZones绘制
			Main.drawZones(zones);
			
			//缺省选择第一个zone
			if (_G('zone-list').selectedIndex == -1)
				_G('zone-list').selectedIndex = 1;
		}
		
		//查找cache中是否存在该game的zones信息
		if (Main.getCurGame().zonesMap) {
			//如果存在，则直接处理
			_whenGotZones(Main.getCurGame().zonesMap);
		}else {
			//如果不存在则请求远程数据
			Req.reqJson(Main.env.url_get_game_zones, {gameid: gameId}, function(data) {				
				//将zones存入gamesMap相应item中
				Main.zonesToMap(gameId, data);
				
				_whenGotZones(Main.getCurGame().zonesMap);
			});
		}
	},
	
	drawZones: function(zonesMap) {
		var gZoneLst = _G('zone-list');
		while(gZoneLst.hasChildNodes()) {
			gZoneLst.removeChild(gZoneLst.firstChild);
		}
		
		var zoneItem = _C(gZoneLst, 'richlistitem');
		zoneItem.setAttribute("hidden", true);
		
		for (var p in zonesMap) {
			var zone = zonesMap[p];
			var zoneItem = _C(gZoneLst, 'richlistitem', {
				value: zone.zoneid,
				align: "center"
			});
			
			var btn = _C(zoneItem, "button" );
			btn.setAttribute('label',zone.name);

		}
	},

	on_select_zone_list: function(list) {
		if (0 == list.currentIndex)
			return;
		Main.switchZone(list.value);
	},
	
	switchZone: function(zoneId) {
		DBG('switchZone', zoneId);
		//设置当前curZoneId
		Main.curZoneId = zoneId;
		
		// hallTabs切换(公共房间｜私人房间｜地图下载) 
		Main.hallTabsChanged();
	},
	
	refreshRooms: function() {
		var params = {
			gameid: Main.curGameId,
			zoneid: Main.curZoneId
		}
		Req.reqJson(Main.env.url_get_game_rooms, params, function(data) {
			//将rooms存入gamesMap的相应位置
			Main.roomsToMap(Main.curGameId, Main.curZoneId, data);
			
			//存入roomsMap
			var rooms = data;
			for (var i=0; i<rooms.length; i++) {
				var room = rooms[i];
				Main.roomsMap[room.roomid] = room;
			}
			
			//调用drawRooms绘制
			Main.drawRooms(Main.getCurZone().roomsMap);
		});
	},
	
	hallTabsChanged: function() {
		if(Main.curGameId == null)
			return;
		
		// 如果"公共房间"tab被选中
		if(_G('pubRoomTab').selected) {
			//查找gamesMap中是否有这个zone的roomsMap
			if (Main.getCurZone().roomsMap) {
				//如果已经有了，直接处理
				Main.drawRooms(Main.getCurZone().roomsMap);
			}else {
				//如果没有，则刷新
				Main.refreshRooms();
			}
			_G('roomsButtons').setAttribute('hidden', false);
		}
		
		// 如果"私人房间"tab被选中
		else if(_G('priRoomTab').selected) {
			_G('roomsButtons').setAttribute('hidden', false);
		}
		
		// 如果"下载地图"tab被选中
		else if (_G('mapDownTab').selected) {
			//查找mapsMap中是否有这个zone的mapsMap
			if (Main.getCurZone().mapsMap) {
				//如果已经有了，直接处理
				Main.drawMaps(Main.getCurZone().mapsMap);
			}else {
				//如果没有，则刷新
				Main.refreshMaps();
			}
			_G('roomsButtons').setAttribute('hidden', true);		
		}
			
	},
	
	refreshMaps: function() {
		var params = {
			gameid: Main.curGameId,
			zoneid: Main.curZoneId
		}
		Req.reqJson(Main.env.url_game_maps, params, function(data) {
			
			//将maps存入mapsMap的相应位置
			Main.mapsToMap(Main.curGameId, Main.curZoneId, data);
			
			//调用drawMaps绘制
			Main.drawMaps(Main.getCurZone().mapsMap);
		});
	},

	//测试房间速度，注意此房间必须在当前zone里
	//TODO: 此处需要考虑
	getRoomSpeed: function(roomId) {
		var room = Main.roomsMap[roomId];
		var roomsMap = {};
		roomsMap[roomId] = room;
		
		Main.fillRoomsSpeed(roomsMap);
		return room.speed;
	},
	
	fillRoomsSpeed: function(roomsMap) {
		try{
			FileIO.createFile(FILE_gameRoomSpeed);
			var speedIniStr = ""; 
			for(var p in roomsMap ){
				speedIniStr += p.toString() + '=' + roomsMap[p].pipehost.toString()  + '|' +   roomsMap[p].pipeudpport.toString() + '|' + roomsMap[p].roomaddr.toString() + '\r\n'; 			
			}
			FileIO.saveText(FILE_gameRoomSpeed, speedIniStr);
		
			var aa = [];
			var j = 0;
		
			//调用shell实现ping,
			var params = ['speed', _UC.u2gb2312(FILE_gameRoomSpeed.path)];
			Shell.execFile(FILE_startGame.path, params, true);

			//解析shell ping的结果	
			var iniParser = _OBF.iniParser(FILE_gameRoomSpeed.clone());
			for(var p in roomsMap ){
				roomsMap[p].speed = iniParser.getString('RoomSpeed',p.toString());
			}
		}catch(e){
			DBG("fillRoomsSpeed", "",e);
			_alert("测速程序运行异常");
		}
	},
	
	refreshRoomsSpeed: function() {
		var roomsMap = Main.getCurZone().roomsMap;

		//测速，填充数据
		Main.fillRoomsSpeed(roomsMap);
		
		//写入新房间速度数据
		for(var p in roomsMap ){
			var id = "speed_" + escape(p);
			//TODO: 这里缺少一个友好函数
			//var speed = getWelSpeed(roomsMap[p].speed);
			var speed = roomsMap[p].speed;
			speed = speed < 0 ? "No Ping " : speed + "ms";
			_G(id).setAttribute('label', speed);
		}
		
	},
	
	drawRooms: function(roomsMap) {
		var roomsTreechildren = _G('hall-pubrooms-treechildren');
		while(roomsTreechildren.hasChildNodes()) {
			roomsTreechildren.removeChild(roomsTreechildren.firstChild);
		}

		for(var p in roomsMap){
			var room = roomsMap[p];
			var ltem = _C(roomsTreechildren, 'treeitem', {});
			var lrow = _C(ltem, 'treerow', {});
	
			_C(lrow, 'treecell', {label : room.roomid});
			_C(lrow, 'treecell', {label : room.name, properties: 'first'});
			_C(lrow, 'treecell', {label : room.maxgamercount});
			_C(lrow, 'treecell', {label : room.curusercount});
			_C(lrow, 'treecell', {
				id: 'speed_' + escape(room.roomid),
				label : '-'
				});
		}

		//刷新房间速度
		Main.refreshRoomsSpeed();
		
	},
	
	drawMaps: function(mapsMap) {
		var mapsTreechildren = _G('hall-maps-treechildren');
		while(mapsTreechildren.hasChildNodes()) {
			mapsTreechildren.removeChild(mapsTreechildren.firstChild);
		}
		for(var p in mapsMap){
			var map = mapsMap[p];
			var ltem = _C(mapsTreechildren, 'treeitem', {});
			var lrow = _C(ltem, 'treerow', {});
			
			_C(lrow, 'treecell', {label : Main.getMapKey(map)});
			_C(lrow, 'treecell', {label : map.name});
			_C(lrow, 'treecell', {label : map.ispointable == "true"? "是": "否"});
			_C(lrow, 'treecell', {label : map.size/1000});
			_C(lrow, 'treecell', {label : ""});
		}
	},

	clickRooms: function( e) {
		var tree = _G("hall-pubrooms-tree");
		if (tree.currentIndex < 0)
			return;
		
   		var roomId = tree.view.getCellText(tree.currentIndex, tree.columns.getColumnAt(0));
  		Main.enterRoom(roomId);
		// window.openDialog("waiting.xul","waiting","chrome,dialog,modal,centerscreen");
	},
	
	getWC3MapsDir: function() {
		//如要没有当前游戏的设置, 读取游戏设置参数
		if(!Setting.savedData[Main.curGameId])
			Setting.loadSavedData();
		var gameSetting = Setting.savedData[Main.curGameId];
		
		//当该游戏的设置为空的时候，返回空
		if ((!gameSetting) || (!gameSetting.exefile)) 
			return null;
		
		// 游戏执行文件路径	
		var exefile = gameSetting.exefile;
		
		// 游戏地图文件路径
		var dirpath = exefile.substring(0, exefile.lastIndexOf('\\')) + "\\Maps";
		if(dirpath == null)
			return null;
		
		// 获地图文件夹	
		var file =  Components.classes["@mozilla.org/file/local;1"]
				.createInstance(Components.interfaces.nsILocalFile);
		file.initWithPath(dirpath);	
		if(file == null || !file.isDirectory())
			return null;
		
		return file;
	},
	
	downZoneMap: function( e) {
		var tree = _G("hall-maps-tree");
		if (tree.currentIndex < 0)
			return;
			
		// 地图主键	
		var mapkey = tree.view.getCellText(tree.currentIndex, tree.columns.getColumnAt(0));
		
		// 根据主键， 找到地图
		var map = Main.getCurZone().mapsMap[mapkey];
		if(map == null)
			return;
		
		// 要下载的地图文件名
		var filename = Main.getMapFileName(map);
		
		// 魔兽争霸地图文件夹
		var wC3MapsDir = Main.getWC3MapsDir();
		// 即将保存的新文件
		var file = FileIO.saveFile(filename, wC3MapsDir);
		if(file == null)
			return;
			
		// 下载
		_G('update-box').setAttribute('hidden',false);
		var downloadListener = {
			onRequest : function(contentLength) {
				totalLen = contentLength;
			},
			onOver : function() {
				_G('update-box').setAttribute('hidden',true);
			},
			onProgress : function(downedSize, tmSpent,tmLess, tmAll, speed) {
				var rate = parseFloat((downedSize*100)/totalLen );
				var p = document.getElementById('downloadprocessbar');
				p.setAttribute('value', parseInt(rate.toString()  + '%'));
		
				var dsK = parseInt(downedSize / 1024);
				var tsK = parseInt(totalLen / 1024);
				var lessS = parseInt(tmLess / 1000);
				var speedK = parseInt(speed / 1024);
				_G('updating-label').value = dsK + '/' + tsK + ' KB (剩余:' +lessS + '秒) 速度:' + speedK + 'KB/s';
			}
		}
		
		var url = Main.env.url_down_gamemap + "gameid=" + map.gameid + "&zoneid=" + map.zoneid + "&checksum=" + map.checksum;
		
		NetIO.download(url, file, downloadListener);
	},
	
	
	getMapFileName: function(map) {
		// 获取地图文件名
		return Main.getMapKey(map) + '.' + map.format;
	},
	
	getMapKey: function(map) {
		return map.gameid + '-' + map.zoneid + '-' + map.checksum;
	},

	getCurGame: function() {
		return Main.gamesMap[Main.curGameId];
	},
	
	getCurZone: function() {
		return  Main.getCurGame().zonesMap[Main.curZoneId];
	},
	
	getCurRoom: function() {
		return Main.getCurZone().roomsMap[Main.curRoomId];;
	},
	
	getCurRoomPath: function() {
		var curGame = Main.getCurGame();
		var curZone = Main.getCurZone();
		var curRoom = Main.getCurRoom();
		var s	= curGame.name
				+ " > " + curZone.name
				+ " > " + curRoom.name;
		return s;
	},
	
	returnToHall: function() {
		//在游戏中则直接返回，不让退出房间，警告一下
		if (Main.isInGame) {
			_alert("您已经进入了游戏。请先关闭游戏后，再离开房间！");
			return false;
		}
		
		//清除原chatWin的信息
		RoomChat.emptyChatHistory();
		
		//删除roomPropsFile临时文件
		Main.delRoomPropsFile();
		
		//远程退出房间请求的回调函数
		var _returnToHall = function() {
			//去除当前房间变量
			Main.curRoomId = null;
					
			//清除room-users-list
			var el = _G("room-users-list");
			while(el.hasChildNodes()) {
				el.removeChild(el.firstChild);
			}

			
			//disableroomUsersTab
			_G("aide-tabbox").selectedIndex = 0;
			
			
			//切换到“大厅”的tab上
			_G('body-tabbox').selectedIndex =	0;
			_G('body-room-tab').setAttribute('disabled','true');
			
			
		};
		//远程请求
		Req.reqJson(Main.env.url_u_leave_room, {roomid: Main.curRoomId}, function(data) {
			_returnToHall();
		});
		
		//"返回大厅"成功
		return true;
	},
	
	getRoomPropsFile: function() {
		var file = Components.classes["@mozilla.org/file/directory_service;1"]
				.getService(Components.interfaces.nsIProperties).get("TmpD",
						Components.interfaces.nsIFile);
		file.append(FN_gfProps);
		return file;		
	},
	
	delRoomPropsFile: function() {
		if (Main.getRoomPropsFile().exists())
			Main.getRoomPropsFile().remove(false);
	},
	
	saveRoomProps: function(props) {
		DBG('roomProps', props);
		try {
			var s = '[gf_props]' + '\r\n' + 'game_path=c:\\' + '\r\n'
					+ 'v_ipaddr=' + props.v_ipaddr.toString() + '\r\n'
					+ 'v_netmask_c=' + props.v_netmask_c.toString() + '\r\n'
					+ 'pipe_host=' + props.pipe_host.toString() + '\r\n'
					+ 'pipe_tcpport=' + props.pipe_tcpport.toString() + '\r\n'
					+ 'pipe_udpport=' + props.pipe_udpport.toString() + '\r\n'
					+ 'udpdig_port_bgn=' + props.udpdig_port_bgn.toString() + '\r\n'
					+ 'udpdig_port_end=' + props.udpdig_port_end.toString() + '\r\n'
					+ 'has_tcp=' + props.has_tcp.toString() + '\r\n'
					+ 'nickname=' + _UC.u2gb2312(Main.env.gameuser.nickname) + '\r\n' 
					+ 'username=' + Main.env.gameuser.username.toString() + '\r\n' 
					+ 'res_url=' + props.res_url.toString() + '\r\n'
					+ 'sessionkey=' + Main.env.gameuser.sessionkey.toString() + '\r\n' 
					+ 'myspeed=' + props.speed.toString() + '\r\n'
					+ 'roomid=' + props.roomid;
	
		
			FileIO.saveText(Main.getRoomPropsFile(), s);
		} catch (e) {
			DBG("saveRoomProps", e.toString());
		}
	},
	
	//TODO:注意那个getCurRoom()的BUG
	nowInRoom: function(roomid) {
		return Main.roomsMap[Main.curRoomId];
	},
	
	enterRoom: function(roomId){
		if (!roomId)
			return;
			
		//如果要进的房间与现在所处的房间相同，则直接显示
		if (Main.curRoomId == roomId) {
			//显示房间的tab
			_G('body-tabbox').selectedTab	=	_G('body-room-tab');
			_G('body-room-tab').setAttribute('disabled','false');
			
			//显示房间路径名
			var nowRoomPos = _G('room-path-label');
			nowRoomPos.setAttribute('value', Main.getCurRoomPath());
			
			return;
		}
		
		//如果用户已经在其他房间里了，则提出询问
		if (Main.curRoomId) {
			var inRoom = Main.nowInRoom();
			if (!_confirm("您已经在[" + inRoom.name + "]里了,是否离开原房间？"))
				return;
		
			//返回大厅,如果返回失败，则不继续进入房间
			if (!Main.returnToHall())
				return;
		}
		
		//测试房间速度
		var roomSpeed = Main.getRoomSpeed(roomId);
		
		//进入房间请求成功后的回调函数
		var _enterRoom = function(room, props) {
			//设置当前curRoomId
			Main.curRoomId = room.roomid;
			
			//将当前房间信息写入临时文件，供shell调用
			Main.saveRoomProps(props);
						
			//将focus给chat输入框
			//TODO: 没成功！:(
			_G("room-chat-textbox").focus();
			
			//显示房间的tab
			_G('body-tabbox').selectedTab	=	_G('body-room-tab');
			_G('body-room-tab').setAttribute('disabled','false');
			
			//显示房间路径名
			var nowRoomPos = _G('room-path-label');
			nowRoomPos.setAttribute('value', Main.getCurRoomPath());

			//显示房间用户列表Tab
			var aideTabRoom = _G("aide-room-tab");
			_G("aide-tabbox").selectedTab = aideTabRoom;
			
			//全程请求房间用户数据
			var params = {
				roomid: Main.curRoomId
			};
			Req.reqJson(Main.env.url_game_room_users, params, function(data) {
				RoomUser.drawUsers(data);
			});
			
			
		};
		
		//远程请求进入房间
		var params = {
			roomid: roomId,
			speed: roomSpeed
		};
		Req.reqJson(Main.env.url_u_enter_room, params,function(data) {
			 _enterRoom(data.room, data.props);
		 });
	
	},
	
	
	openedWins: {},
	
	onTeamMemberChatSelected: function() {
		var tree = _G("myteam-members-tree");
		var friendName = tree.selectedItem;
		alert(friendName)
	},

	openSetting: function() {
		window.openDialog("setting.xul", "skyGameAppSet","modal,chrome,centerscreen=yes");
	},
	
	startTcpServer: function() {
		var server = Components.classes["@mozilla.org/network/server-socket;1"]
				.createInstance(Components.interfaces.nsIServerSocket);
		server.init(9089, true, -1);
		server.asyncListen({
			onSocketAccepted : function(serv, transport) {
				window.restore();

				window.Main.isInGame = false;
				
				//远程请求，用户离开游戏
				Req.reqJson(Main.env.url_u_leave_game,  {});
				
				serv.close();
			},
			onStopListening : function(serv, status) {
			}
		});
	},
	
	startGame: function () {
		if (Main.isInGame) {
			_alert("您已经在游戏中了，请在windows任务栏中选择你的游戏窗口。");
			return;
		}
		
		//读取游戏设置参数
		Setting.loadSavedData();
		var settingData = Setting.savedData;
		var room = Main.getCurRoom();
		var gameSetting = settingData[room.gameid];
		
		//当该游戏的设置为空的时候，弹出设置窗口
		if ((!gameSetting) || (!gameSetting.exefile)) {
			Main.openSetting();
			return;
		}
		
		var exeFileName = gameSetting.exefile;
		var exeFileParms = gameSetting.exefileparms;
		
		// 开启一个TCP Server,用于c程序与xulrunner通讯，汇报游戏结束状态
		Main.startTcpServer();

		
		//发送 进入游戏 的消息到远程服务器
		//var sendInOutGameUrl = deUrl(window.g.getInOutGameURL());
		//sendInOutGameUrl = sendInOutGameUrl.replace('{INGAME}', 'true');
		/*doSkyAjax(sendInOutGameUrl, '', function(data) {
					dump('\n start game!!!!!!!.\n');
				}, 'get', false);
		*/
		
		//写入配置
		var s = "[GameHelperSetting]\r\n";
		//显血
		s += "xue=" + _G('room-xue-checkbox').checked + "\r\n";
		//智能全屏
		s += "sfwin=" + _G('room-sfwin-checkbox').checked + "\r\n";
		FileIO.saveText(FILE_ghSetting, s);
		
	
		
		var params = {
			//TODO: 目前没起作用
			speed: 0
		}
		
		//远程请求，用户进入游戏
		Req.reqJson(Main.env.url_u_enter_game,  params, function() {
			//最小化大厅客户端窗口
			window.minimize();

			//调用Shell启动游戏
			try {
				var argv = [];
				argv[0] = _UC.u2gb2312('/d:' + FILE_gameFilter.path);
				argv[1] = _UC.u2gb2312(exeFileName);
				argv[2] = _UC.u2gb2312(exeFileParms);
		
				var process = Components.classes["@mozilla.org/process/util;1"]
						.createInstance(Components.interfaces.nsIProcess);
				process.init(FILE_startGame);
				process.run(false, argv, argv.length);
				Main.isInGame = true;
			} catch (e) {
				_alert('启动游戏失败！请检查游戏路径！');
				dump(e);
			}
		});
	},
	
	gotoUserInfo: function() {
		var url = Main.env.url_userinfo + "?&username=" + Main.env.gameuser.username + "&sessionKey=" + Main.env.gameuser.sessionkey;
		BrowserTool.openWithSys(url);
	},
	
	checkWarKey: function(checkbox) {
		Main.openWarKey(checkbox.checked);
	},
	
	openWarKey: function(isOpen) {
		var params = isOpen ? [] : ['-q'];
		Shell.execFile(File_warKey.path, params, false);
	},
	
	logout: function() {
		//在游戏中则直接返回，不让退出房间，警告一下
		if (Main.isInGame) {
			_alert("您已经进入了游戏。请先关闭游戏后，再退出平台！");
			return false;
		}else {
			if (!_confirm("您是否确定退出" + Main.env.hall.name + "？"))
				return;
		}
		
		//NOTE：此处参看 Main.onUnload()
		window.close();

	}
}

/************************************************/
/*				RoomUser						*/
/************************************************/
var RoomUser = {
	drawUsers: function(users) {
		for(var i=0; i < users.length; i++) {
			this.addItem(users[i]);
		}
	},
		
	onMenuShowing: function() {
		var lstBox = _G("room-users-list");
		var friendName = lstBox.selectedItem.value;
		if (!friendName)
			return;
		
		//是否已经是好友
		var isFriend = false;
		//用户列表项已存在，则已经是好友
		if (_G('UF_' + friendName))
			isFriend = true;

		//如果是自己，则不能添加好友
		_G('room-users-addfriend-menuitem').disabled =
				(friendName == Main.env.gameuser.username)
				|| isFriend
		;
				
		
	},
	
	onRoomUserAddToFriends: function() {
		var lstBox = _G("room-users-list");
		var friendName = lstBox.selectedItem.value;
		Main.reqAddFriend(friendName);
	},

	addItem: function(user) {
		//用户列表项已存在，则直接返回
		if (_G('RU_' + user.username))
			return;
		var lstBox = _G("room-users-list");
		
		//创建richListItem
		var li = _C(lstBox, 'richlistitem', {
			value: user.username,
			id: "RU_" + user.username,
			tooltiptext: user.iplocation,
			context: "room-users-menupopup"
		});

		var src = Main.env.portal_host + "/51bisaifiles/" + user.username + "/1/" + user.username
					+ "Small.jpg" + "?" + Math.random();
		var img = _C(li, 'image', {
			src: src,
			class: 'user-item-avator'
		});
		
		
		//一个布局容器，用来装一些信息
		var vbox = _C(li, 'vbox', {flex: 1});
		
		//创建Title
		var title = user.nickname;
		if (user.ingame == 'true')
			title += '(游戏中...)';
		var titleLbl = _C(vbox, 'label', {
			id : "RU_TITLE_" + user.username,
			value: title,
			class: 'room-user-status',
			flex: 1
		});
	
		//创建等级显示
		var levelLbl = _C(vbox, 'label', {
			class: 'room-user-level',
			value: user.level,
			flex: 1
		});
		return;
	},

	delItem: function(user) {
		_D('RU_' + user.username);
	},

	getSelectedUsername: function() {
		var lstBox = _G("room-users-list");
		var friendName = lstBox.selectedItem.value;
		
		return friendName;
	},

	onAddToFriends: function() {
   		var username = this.getSelectedUsername();
   		if (!username)
   			return;
   		UserFriend.reqAddFriend(username);
	},
	
	chatToSelected: function() {
   		var username = this.getSelectedUsername();
   		if (!username)
   			return;
		Main.createChatWinToUser(username);		
	}
	
}

/************************************************/
/*				UserFriend						*/
/************************************************/
var UserFriend = {
	loadFriends: function() {
		Req.reqJson(Main.env.url_list_friends, {}, function(data){
			var users = data;
			for(var i=0; i< users.length; i++) 
			UserFriend.addItem(users[i]);
		});
	},

	addItem: function(user) {
		//用户列表项已存在，则直接返回
		if (_G('UF_' + user.username))
			return;
		var lstBox = _G("user-friend-list");
		
		//创建richListItem
		var li = _C(lstBox, 'richlistitem', {
			value: user.username,
			id: "UF_" + user.username,
			tooltiptext: user.iplocation,
			context: "user-friend-menupopup"
		});
		
		var src = Main.env.portal_host + "/51bisaifiles/" + user.username + "/1/" + user.username
					+ "Small.jpg" + "?" + Math.random();
		var img = _C(li, 'image', {
			src: src,
			class: 'user-friend-avator'
		});

		
		//一个布局容器，用来装一些信息
		var vbox = _C(li, 'vbox', {flex: 1});
		
		//创建Title
		var title = user.nickname;
		var titleLbl = _C(vbox, 'label', {
			id : "UF_TITLE_" + user.username,
			value: title,
			class: 'user-friend-status',
			flex: 1
		});
	
		//创建等级显示
		var levelLbl = _C(vbox, 'label', {
			class: 'user-friend-level',
			value: user.level,
			flex: 1
		});
		
		return;
	},
	
	delSelected: function() {
		var lstBox = _G("user-friend-list");
		var friendName = lstBox.selectedItem.value;
		if (_confirm("您是否确认删除该好友？"))
			this.delFriend(friendName);
	},
	
	chatToSelected: function() {
		var lstBox = _G("user-friend-list");
		var friendName = lstBox.selectedItem.value;
		Main.createChatWin('chat-' + friendName);
	},


	reqAddFriend: function(friendName) {
		var params = {
			friendname: friendName
		}
		Req.reqJson(Main.env.url_req_add_friend, params, function(data)  {
			var user = data;
			_alert('您已向“' + user.nickname + '”发出发出添加好友的请求，等待对方确认。');
		});
	},
	
	addFriend: function(friendName) {
		var params = {
			friendname: friendName
		}
		Req.reqJson(Main.env.url_add_friend, params, function(data)  {
			var user = data;
			_alert('添加好友“' + user.nickname + '”成功！');
			Main.addUserFriendItem(user);
		});
	},
	
	delFriend: function(friendName) {
		var params = {
			friendname: friendName
		}
		Req.reqJson(Main.env.url_del_friend, params, function(data)  {
			var user = data;
			_G("user-friend-list").removeChild(_G("UF_" + user.username));
		});
	},
	
	
	_:null
}

/************************************************/
/*				TeamMember						*/
/************************************************/
var Team = {

	onMenuShowing: function() {
		var username = this.getSelectedUsername();
		if (!username) {
			_G('team-member-addfriend-menuitem').disabled = true;
   			return false;
		}

   		//是否已经是好友
		var isFriend = false;
		//用户列表项已存在，则已经是好友
		if (_G('UF_' + username))
			isFriend = true;

		//如果是自己，则不能添加好友
		_G('team-member-addfriend-menuitem').disabled =
				(username == Main.env.gameuser.username)
				|| isFriend
		;
	},
	
	getSelectedUsername: function() {
		var tree = _G("myteam-members-tree");
		if (tree.currentIndex < 0)
			return;
		
   		return tree.view.getCellText(tree.currentIndex, tree.columns.getColumnAt(0));
		
	},

	onAddToFriends: function() {
   		var username = this.getSelectedUsername();
   		if (!username)
   			return;
   		Main.reqAddFriend(username);
	},
	
	chatToSelected: function() {
   		var username = this.getSelectedUsername();
   		if (!username)
   			return;
		Main.createChatWinToUser(username);		
	},
	
	gotoMyTeam: function() {
		Req.openBrowser(Main.env.url_goto_myteam);	
	},
	
	refreshMyTeam: function() {
		this.loadMyTeam();
	},
	
	loadMyTeam: function() {
		Req.reqJson(Main.env.url_myteam, {}, function(data){
			//判断是否有战队
			if (!data.team)
				return;
				
			var team = data.team;
			var members = data.members;
			
			//填写战队信息
			//_G("myteam-id-label").value = team.id;
			_G("myteam-name-label").value = team.teamname;
			_G("myteam-point-label").value = team.point;
			_G("myteam-membercount-label").value = team.membercount;
			
			//生成战队成员列表

			//清空
			var teamTree = _G('myteam-members-treechildren');
			while(teamTree.hasChildNodes()) {
				teamTree.removeChild(teamTree.firstChild);
			}
			for(var i=0; i<members.length; i++) {
				var m = members[i];
				var item = _C(teamTree, "treeitem");
				var row = _C(item, "treerow");
				_C(row, "treecell", {label: m.username});
				_C(row, "treecell", {label: m.nickname});
				_C(row, "treecell", {label: m.level});
			}
		});
	}
}


/************************************************/
/*				RoomChat						*/
/************************************************/
var RoomChat = {
	lastChatTime: 0,	//最后一次发送Chat的时间
	flushChatTimes: 0,	//快速发送的次数
	
	emptyChatHistory: function() {
		var chatDoc = _G('room-chat-box').contentDocument;
		var body = chatDoc.body;
		body.innerHTML = "";
	},
	
	showEmotion: function(e){		
		var winName = 'face-window';

		//如果已经打开了，就关闭
		var win = WinTool.findByName(winName);
		if (win) {
			win.close();
			return;
		}

		var _left =  e.clientX - 230 + window.screenX;
		var _top =  e.clientY - 238 + window.screenY;
		window.openDialog('face.xul',winName,'chrome,dependent,left='+_left+',top='+ _top );
	},
		
	//检查是否是高频率发送，return true or false
	flushChatCheck: function() {
		var nowTime = (new Date()).getTime();
		var isHighSpeed = (nowTime - this.lastChatTime)/1000 < 2  ? true : false;
		this.lastChatTime = new Date().getTime();
		
		//若不是高频发送，则清零返回
		if (!isHighSpeed) {
			this.flushChatTimes = 0;
			return false;
		}
		
		//高频发送计数器累加
		this.flushChatTimes ++;
		
		//超过三次高频，则认为是刷屏行为，返回false
		if (this.flushChatTimes < 3)
			return false;

		//提示用户消息
		var chatDoc = _G('room-chat-box').contentDocument;
		var el = chatDoc.createElement('span');
		el.setAttribute('class', 'chat-sys');
		el.innerHTML = "系统公告：说话节奏太快!请稍后...";
		Main.appendMsg(null, null, el);
		
		return true;
	},
	
	sendChatMsg: function() {
		var txtBox = _G('room-chat-textbox');
		var txt = txtBox.value.trim();
		//内容为空时，不让发送
		if ('' == txt)
			return;
	
		//清除上次说话内容
		txtBox.value = '';
		
		//刷屏检查：如果是刷屏行为，则不发送
		if (this.flushChatCheck())
			return;

		this.doSendChatMsg(txt);
	},
	
	doSendChatMsg: function(txt) {
		//发送：远程请求
		var params = {
			content: txt,
			sender: Main.env.gameuser.username,
			dest: '__ROOM__'
		}
		Req.reqJson(Main.env.url_msg_chat, params, function(data)  {
		});
		
		return;
	},
	
	sendChatFace: function(c) {
		this.doSendChatMsg(c);
	}
}

/** ********************************************* */
/* Replay */
/** ********************************************* */

var Replay = {
	replaysMap : {},

	loadLocalReplays : function() {
		// 载入本地所有replay文件
		var files = Replay.getLocalReplayFiles();

		// 存入replaysMap
		Replay.replaysToMap(files);

		// 调用drawReplays绘制
		Replay.drawReplays(Replay.replaysMap);
	},

	showTeamReplays : function() {
		alert('team');
	},

	drawReplays : function(replaysMap) {
		var replaysTreechildren = _G('replay-local-treechildren');
		while (replaysTreechildren.hasChildNodes()) {
			replaysTreechildren.removeChild(replaysTreechildren.firstChild);
		}

		for (var p in replaysMap) {
			var replay = replaysMap[p];
			var ltem = _C(replaysTreechildren, 'treeitem', {});
			var lrow = _C(ltem, 'treerow', {});

			_C(lrow, 'treecell', {label : replay.name});
			_C(lrow, 'treecell', {label : replay.name});
			_C(lrow, 'treecell', {label : replay.path});
			_C(lrow, 'treecell', {label : replay.size});
			_C(lrow, 'treecell', {label : replay.mdate});
		}
	},

	getLocalReplayFiles : function() {
		return replayDir.directoryEntries;
	},

	// 将文件数组转为replaysMap映射表
	replaysToMap : function(files) {
		var map = {};

		while (files.hasMoreElements()) {
			var file = files.getNext()
					.QueryInterface(Components.interfaces.nsIFile);
			var replay = Replay.fileToReplay(file);
			map[replay.name] = replay;
		}
		Replay.replaysMap = map;
		DBG("Replay.replaysToMap()", Replay.replaysMap);
	},

	// 将文件转为replay对象
	fileToReplay : function(file) {
		return {
			name : file.leafName,
			path : file.path,
			size : file.fileSize / 1000,
			mdate : (new Date(file.lastModifiedTime)).toLocaleString()
		};
	},

	replayToFile : function(replay) {
		var file = _OBF.localFile(replay.path);
		return file;
	},

	getSelectedReplay : function() {
		var tree = _G("replay-local-tree");
		if (tree.currentIndex < 0)
			return {};

		// 找出选中的录像名
		var replayname = tree.view.getCellText(tree.currentIndex, tree.columns.getColumnAt(0));
		return Replay.replaysMap[replayname];
	},

	exportReplay : function() {
		// 选中一条录像记录
		var replay = Replay.getSelectedReplay();
		if (!replay.name) {
			_alert('请选中一条记录！');
			return;
		}

		// 转为录像文件
		var replay_file = Replay.replayToFile(replay);

		// 获取即将要保存的新文件
		var descfile = FileIO.saveFile(replay_file.leafName);
		
		// 把原文件拷贝到新文件
		replay_file.moveTo(descfile.parent, descfile.leafName);

		// 刷新列表
		Replay.loadLocalReplays();
	},

	importReplay : function() {
		// 导入文件
		var file = FileIO.pickFile();
		if (file == null)
			return;

		// 导入的文件格式有一定的规则
		if (!/(\.w3g)$/.test(file.path)) {
			_alert('请导入正确的录像文件!' + '\n' + ' 以.w3g为后缀名的文件 ');
			return;
		}

		// 复制文件到录像文件夹
		file.moveTo(replayDir, file.leafName);

		// 刷新列表
		Replay.loadLocalReplays();
	},

	playReplay : function() {
		// 选中一条录像记录
		var replay = Replay.getSelectedReplay();
		if (!replay.name) {
			_alert('请选中一条记录！');
			return;
		}

		// 转为录像文件
		var replay_file = Replay.replayToFile(replay);

		if (!replay_file.exists())
			return;

		// 读取游戏设置参数
		Setting.loadSavedData();
		var settingData = Setting.savedData;

		// 目前的录像只是针对于war3,所以
		var gameId = "war3rpg";
		var gameSetting = settingData[gameId];

		// 当该游戏的设置为空的时候，弹出设置窗口
		if ((!gameSetting) || (!gameSetting.exefile)) {
			Main.openSetting();
			return;
		}

		var exeFileName = gameSetting.exefile;
		// var exeFileParms = gameSetting.exefileparms;

		var params = [];
		params[0] = '-loadfile';
		params[1] = replay_file.path;

		// 调用Shell启动游戏
		try {
			Shell.execFile(exeFileName, params, false);
		} catch (e) {
			_alert('启动游戏失败！请检查游戏路径！');
			dump(e);
		}
	},

	uploadReplay : function() {
		// 选中一条录像记录
		var replay = Replay.getSelectedReplay();
		if (!replay.name) {
			_alert('请选中一条记录！');
			return;
		}

		// 转为录像文件
		var replay_file = Replay.replayToFile(replay);
	},
	
	clearReplays: function() {
		if(!_confirm('您确定要删除所有的本地录像吗?\n这个操作将不可恢复!'))
			return;
		
		var files = Replay.getLocalReplayFiles();
		while (files.hasMoreElements()) {
			var file = files.getNext().QueryInterface(Components.interfaces.nsIFile);
			file.remove(false);
		}
		
		// 刷新列表
		Replay.loadLocalReplays();
	}

}

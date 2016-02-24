
var Chat = {
	o_username: "",
	otherUser: null,
	env: null,
	chatDoc: null,
	editor: null,
	editorDoc: null,
	onLoad: function() {
		Chat.env = opener.Main.env;
		
		Chat.chatDoc = _G('chat-iframe').contentDocument;
		Chat.chatDoc.write('<html><head>');
		Chat.chatDoc.write('<link rel="stylesheet" href="http://hall.ko10000.com/GameHall2/u/hallXul/chatCss.jsp.vi" type="text/css" ></link>');
		Chat.chatDoc.write('</head><body></body></html>');
		Chat.chatDoc.close();
		
		//初始化编辑器
		Chat.editor = _G("chat-editor");
		Chat.editorDoc = Chat.editor.contentDocument;
		Chat.editorDoc.write('<html><head>');
		Chat.editorDoc.write('<link rel="stylesheet" href="http://hall.ko10000.com/GameHall2/u/hallXul/chatCss.jsp.vi" type="text/css" ></link>');
		Chat.editorDoc.write('</head><body></body></html>');
		Chat.editorDoc.close();
		Chat.editorDoc.designMode = 'on';

		//发送系统提示信息
		Chat.appendMsg(null, null, "交谈中请勿轻信汇款中奖信息，勿拨打陌生电话。");

		//从url参数中取得friendname
		var parms = window.location.search.substring(1);
		var p = parms.split('&');
		var pairKey = {};
		for (var i = 0; i < p.length; i++) {
			var pp = p[i].split('=');
			pairKey[pp[0]] = unescape(pp[1]);
		}
		Chat.o_username = pairKey['o_username'];

		_G('avator-image').src = "http://egplayer.cn//51bisaifiles/" + Chat.o_username + "/1/" + Chat.o_username + ".jpg";
		
		//读取队列中已存在的消息
		var winName = 'chat-' + Chat.o_username;
		while(true) {
			var msg = opener.Main.getLastChatMsg(winName);
			if (!msg)
				break;
			Chat.showPsnChat(msg);
		}
		
		
		//取得对方用户信息
		var params = {
			theusername: Chat.o_username
		};
		//注意哦，这里要用opener的Req,因为里面有sessionkey
		opener.Req.reqJson(Chat.env.url_gameuser, params, function(data)  {
			//存储otherUser
			Chat.otherUser = data.gameuser;
			
			//修改窗口标题
			window.document.title = "与 " + Chat.otherUser.nickname + " 聊天";
			_G("win-title-label").value = window.document.title;
			
			var u =  Chat.otherUser;
			_G('person-username-desc').setAttribute('value',u.username);
			_G('person-level-desc').setAttribute('value',  u.level);
			_G('person-nickname-desc').setAttribute('value', u.nickname);
			_G('person-point-desc').setAttribute('value', u.point);
			_G('person-usertype-desc').setAttribute('value',u.usertype);
			_G('person-realip-desc').setAttribute('value', u.realip);
			_G('person-iplocation-desc').setAttribute('value', u.iplocation);
			_G('person-iplocation-desc').setAttribute('value', u.iplocation);
			_G('person-iplocation-desc').setAttribute('tooltiptext', u.iplocation);

		});
		
	},
	
	lastChatTime: 0,	//最后一次发送Chat的时间
	flushChatTimes: 0,	//快速发送的次数
	
	//检查是否是高频率发送，return true or false
	flushChatCheck: function() {
		var nowTime = (new Date()).getTime();
		var isHighSpeed = (nowTime - Chat.lastChatTime)/1000 < 2  ? true : false;
		Chat.lastChatTime = new Date().getTime();
		
		//若不是高频发送，则清零返回
		if (!isHighSpeed) {
			Chat.flushChatTimes = 0;
			return false;
		}
		
		//高频发送计数器累加
		Chat.flushChatTimes ++;
		
		//超过三次高频，则认为是刷屏行为，返回false
		if (Chat.flushChatTimes < 3)
			return false;

		//提示用户消息
		Chat.appendMsg(null, null, "系统公告：说话节奏太快!请稍后...");
		
		
		return true;
	},
	
	isSendKeyDown: function(event) {

		//Key is Alt-s
		if(83 == event.keyCode && event.altKey)
			return true;
		
		//Key is Enter
		if (13 == event.keyCode) {
			var isSendModeEnter = _G("send-enter-checkbox").checked;
			if (isSendModeEnter) //Key is Enter
				return true;
			else if (event.ctrlKey) //Ctrl-Enter
				return true;
		}
		return false;
	},

	onEditorKeydown: function(event) {
		
		//发送命令键
		if(Chat.isSendKeyDown(event)) {
			Chat.sendChatMsg();
			return false;
		}
		
		//
		
		return true;
	},
	
	sendChatMsg: function() {
		var txt = Chat.editorDoc.body.innerHTML;

		if (txt.length > 3000) {
			_alert("您要发送的内容太多，请分几次发送。");
			return;
		}
		
		//内容为空时，不让发送
		if ('' == txt || '<br>' == txt)
			return;

	
		//清除上次说话内容
		Chat.editorDoc.body.innerHTML = '';
		
		
		//刷屏检查：如果是刷屏行为，则不发送
		if (Chat.flushChatCheck())
			return;

		//发送内容
		Chat.doSendChatMsg(txt);
	},
	
	doSendChatMsg: function(txt) {
 			
		//发送：远程请求
		var params = {
			content: txt,
			sender: Chat.env.gameuser.username,
			dest: Chat.otherUser.username
		}
		opener.Req.reqJson(Chat.env.url_msg_chat, params, function(data)  {
		});
		
		return;
	},
	
	showFace: function(e) {
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
	
	sendChatFace: function(src) {
		var img = Chat.editorDoc.createElement("img");
		img.src = src;
		Chat.editorDoc.body.appendChild(img);
		Chat.editor.focus();
	},
	
	showPsnChat: function(msg) {
		Chat.appendMsg(msg.msgtime, msg.sender, msg.content);
	},
	
	appendMsg: function(time, sender, content) {
		var chatDoc = _G('chat-iframe').contentDocument;
		var body = chatDoc.body;
		
		//容器：信息行
		var box = chatDoc.createElement("div");
		body.appendChild(box);
		
		//时间
		if (time) {
			var el = chatDoc.createElement("span");
			box.appendChild(el);
			el.innerHTML = time + " ";
			el.setAttribute("class","chatline-time");
		}
		
		//发送者
		if (sender) {
			var el = chatDoc.createElement("span");
			box.appendChild(el);
			el.innerHTML = sender.nickname;
			el.setAttribute("class","chatline-sender");
		}
		
		//内容
		var el = Chat.createContent(content);
		box.appendChild(el);
		el.setAttribute("class", "chatline-content");
		
		body.scrollTop = body.scrollHeight + 100000;
	},
	
	createContent: function(content) {
		var chatDoc = _G('chat-iframe').contentDocument;
		var c = content;
		
		//解析内容中的表情图标
		var regex =/\[\[\[([^\]]+)\]\]\]/;
		var r = regex.exec(c);
		var el = null;
		if (r && r.length > 0) {
			//是表情图标
			el = chatDoc.createElement("img");
			el.src = r.pop();
		}else {
			//是普通文字			
			el = chatDoc.createElement("span");
			el.innerHTML = content;
		}

		var div = chatDoc.createElement("div");
		div.appendChild(el);
		return div;
	},
	
	clearChat: function() {
		var chatDoc = _G('chat-iframe').contentDocument;
		chatDoc.body.innerHTML = "";
	}

}



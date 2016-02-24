var Face = {
	sendChatFace: function(el) {
		var _src = el.getAttribute('src');
		if(_src == '') return;
		if (window.opener.RoomChat) {
			//主窗口的
			var _c = '[[[' + _src + ']]]';
			window.opener.RoomChat.sendChatFace(_c);
		}else {
			//聊天窗口的
			window.opener.Chat.sendChatFace(_src);
		}
		window.close();
	}
}

var UserMsg = {
	del : function(messageId, callBack) {
		if (!confirm("您确定要删除这条信息吗?"))
			return false;

		var params = {
			id : messageId
		}
		var url = '../userMsg/message.del.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}
				if (typeof callBack != 'undefined')
					callBack(data);
			}
		});

		return false;
	},
	delall : function(messageId, callBack) {
		if (!confirm("您确定要删除这条信息吗?"))
			return false;

		var params = {
			id : messageId
		}
		var url = '../userMsg/message.delall.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}
				if (typeof callBack != 'undefined')
					callBack(data);
			}
		});

		return false;
	},
	readall : function(messageId, callBack) {
			 
			var params = {
				id : messageId
			}
			var url = '../userMsg/message.readall.json.do';
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : url,
				data : params,
				success : function(data) {
					if (data.res == 'no') {
						alert(data.data);
						return;
					}
					if (typeof callBack != 'undefined')
						callBack(data);
				}
			});
	
			return false;
	},

	do_invalid : function(messageId) {
		var url = '../userMsg/message.invalid.json.do?id=' + messageId;
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}
			}
		});
	},
	
	vi_user : function(username) {
		//var $page = $('<div class="message-win"/>')
		//		.load('../team/userInfo.jsp.vi?username=' + username);
		//PopupWinX.create($page).show();
		$("#page-body-box").load("../user/index.jsp.vi?username="+username);
	},

	vi_msg : function(msgId, msgType, callBack) {
		var $page = $('<div  class="message-win"/>').load('../userMsg/'
				+ msgType + '.jsp.vi?id=' + msgId);
		PopupWinX.create($page, {
			closeEvent : function() {
				callBack();
			}
		}).show();
	}
}



var TeamMsg = {
	del : function(messageId, callBack) {
		if (!confirm("您确定要删除这条信息吗?"))
			return false;

		var params = {
			id : messageId
		}
		var url = '../teamMsg/message.del.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}
				if (typeof callBack != 'undefined')
					callBack(data);
			}
		});

		return false;
	},

	do_invalid : function(messageId) {
		var url = '../teamMsg/message.invalid.json.do?id=' + messageId;
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}
			}
		});
	},
	
	vi_team : function(teamId) {
		var $page = $('<div class="message-win"/>')
				.load('../team/team.jsp.vi?teamid=' + teamId);
		PopupWinX.create($page).show();
	},

	vi_user : function(username) {
		var $page = $('<div class="message-win"/>')
				.load('../team/userInfo.jsp.vi?username=' + username);
		PopupWinX.create($page).show();
	},

	vi_msg : function(msgId, msgType, callBack) {
		var $page = $('<div  class="message-win"/>').load('../teamMsg/'
				+ msgType + '.jsp.vi?id=' + msgId);
		PopupWinX.create($page, {
			closeEvent : function() {
				callBack();
			}
		}).show();
	}
}

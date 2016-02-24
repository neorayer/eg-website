 
var FreshMsg = {
 	del: function(messageId) {
		if (!confirm("您确定要删除这条信息吗?"))
			return false;

		var params = {
			id : messageId
		}
		var url = '../freshMsg/message.del.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					window.location.reload();
				}
			}
		});

		return false;
	},
	 
	delallmsg : function(username) {
		if (!confirm("您确定要全部删除?"))
			return false;

		var params = {
			username : username
		}
		var url = '../freshMsg/message.delall.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					window.location.reload();
				}
			}
		});
	},
	vi_user : function(username) {
		window.location = '../player/index.jsplayout.vi?username='+username;
	},
	vi_album : function(username,id) {
		window.location = '../player/photos.jsplayout.vi?username='+username+'&id='+id;
	},
	vi_article: function(username,id) {
		window.location='../player/article.jsplayout.vi?username='+username+'&id='+id;
	}
}



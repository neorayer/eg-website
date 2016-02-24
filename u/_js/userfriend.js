var UserFriend = {
	add_userfriend : function(username) {
		var $page = $('<div class="message-win"/>')
				.load('../userFriend/add.jsp.vi?friendname='+username);
		PopupWinX.create($page).show();
	},
	vi_user : function(username) {
		var $page = $('<div class="message-win"/>')
				.load('../user/userInfo.jsp.vi?username=' + username);
		PopupWinX.create($page).show();
	},
	vi_user_page : function(username) {
		window.location = '../player/index.jsplayout.vi?username='+username;
	},
	
	vi_msg : function(msgId, msgType, callBack) {
		var $page = $('<div  class="message-win"/>').load('../message/'
				+ msgType + '.jsp.vi?id=' + msgId);
		PopupWinX.create($page, {
			closeEvent : function() {
				callBack();
			}
		}).show();
	},
	reload : function() {
		location.href = '../userFriend/friends.jsplayout.vi';
	},
	pass:function(username,friendname){
		var params = {
			friendname : friendname
		}
		var url = '../UserFriend/userfriend.pass.json.do';
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
					UserFriend.reload(username);
				}
				 
			}
		});
	},
	ignore:function(username,friendname){
		var params = {
			friendname : friendname
		}
		var url = '../UserFriend/userfriend.ignore.json.do';
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
					UserFriend.reload(username);
				}
				 
			}
		});
	},
	del:function(username,friendname){
		if (!confirm("您确定要删除此好友?"))
			return false;
			
		var params = {
			friendname : friendname
		}
		var url = '../UserFriend/userfriend.del.json.do';
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
					UserFriend.reload(username);
				}
				 
			}
		});
	},
	url: '../userFriend/find.jsp.vi',
	search: function() {
		var query = $('#page-userfriend-search-form').formSerialize();
		location.href = '../userFriend/find.jsplayout.vi?'+ query;
	},
	modprivacy:function(username){
		$('#page-modprivacy-form').ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					UserFriend.reload(username);
				}
			}
		});
	},
	addbysomeone:function(username){
		$('form#page-addfriends-someone-form').validate({
		errorClass: "errorClass",
		errorElement: "label",
  		rules: {
			subject:{
				required: true,
				maxlength: 45 
			}
		},
		messages: {
			subject:{
				required: "请输入附加信息",
				maxlength:"字数限制：45字内！" 
			}
		},
  		submitHandler: function(form) {
  			var text=$("#page-addfriendmessage-text");
  			if(text[0].value==text[0].title){
  				alert("请输入附加信息");
  				return;
  			}
			var action = $(form).attr('action');
			action = action.replace(/jsp(layout)?/i,'json');
			$(form).attr('action',action);
	   		$(form).ajaxSubmit({
	   			success: function(data) {
					var responseText = data.replace(/<\/pre>/i, '').replace(/<pre>/i, '');
					var data = eval('(' +responseText + ')');
	   				if(data.res == 'no') {
	   					alert(data.data);
	   					return;
   					}else{
   						PopupWinX.close($(form.submits));
						UserFriend.reload(username);
   					}
	   			}
	   		});
	   	}
 	});
	},
	addbyanyone:function(username,form){
		$(form).ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					PopupWinX.close($(form.submita));
					UserFriend.reload(username);
				}
			}
		});
	},
	setting: function() {
		location.href = '../userFriend/control.jsplayout.vi';
	}
	
}
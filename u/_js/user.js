var User = {
	do_login : function(form) {
		$(form).ajaxSubmit({
			dataType : 'json',
			beforeSubmit : function(formData, $form) {
				var username = $('input#username', $form);
				if ($.trim(username.val()) == '') {
					alert('用户名不能为空11');
					return false;
				}
				var pwd = $('input#password', $form);
				if ($.trim(pwd.val()) == '') {
					alert('密码不能为空');
					return false;
				}
			},
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}
				//alert('登录成功!');
				//window.location.reload();
				window.location = '../user/index.jsplayout.vi';
			}
		});
	},

	vi_login : function() {
		$('#page-index-login-out-box').load('../portal/login.jsp.vi');
	},

	vi_info : function() {
		$('#page-index-login-out-box').load('../portal/userInfo.jsp.vi');
	},

	do_logout : function() {
		$.post('../portal/logout.jsplayout.do', function(data) {
			deleteCookie("saveCookie","/");
			window.location = '../user/index.jsplayout.vi';
		});
	}
}
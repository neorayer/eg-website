var Login = {
	accounts: {},
	env: {},
	version: 0,
	props: {},
	isLoginable: false,
	
	loadAccounts: function(){
		var s = "";
		if (FILE_lastLoginUser.exists())
			s = FileIO.loadText(FILE_lastLoginUser);
		DBG("loadAccounts", s);
		if (!s)
			return;
		Login.accounts =  eval('(' + s + ')');
	},
	
	loadProps: function() {
		var s = "";
		if (FILE_loginProps.exists())
			s = FileIO.loadText(FILE_loginProps);
		DBG("loadProps", s);
		if (!s)
			return;
		Login.props = eval('(' + s+ ')');
		
		_G("autologin-checkbox").checked = Login.props.isAutoLogin;
		_G('rempassword-checkbox').checked = Login.props.isRemPassword;
	},
	
	saveProps: function() {
		FileIO.saveText(FILE_loginProps, JSON.toString(Login.props));
	},
	
	buildAccountsList: function() {
		var G_popmenu = _G('username-menupopup');
		for(var p in Login.accounts){
			var account = Login.accounts[p];
			if(!account && typeof account != 'object' )
				continue;
			var menuitem = document.createElement('menuitem');
			menuitem.setAttribute('label',p);
			G_popmenu.appendChild(menuitem);
		}
	},
	
	getLastAccount: function() {
		var account;
		for(var p in Login.accounts){
			account = Login.accounts[p];
		}
		return account;
	},
	
	onWindowKeyDown: function(e) {
		if (e.keyCode == 116)
			window.location.reload()
	},
	
	onLoad: function(){
		//窗口居中 
		var w = _G('login-window');
		window.screenX = Math.floor((screen.availWidth - w.width)/2);	
		window.screenY = Math.floor((screen.availHeight - w.height)/2);

		//历史保存的用户帐号
		Login.loadAccounts();
		
		//构建历史保存帐号下拉列表
		Login.buildAccountsList();
		
		//用历史帐户中最近一个帐户填充表单
		Login.fillAccount(Login.getLastAccount());

		//给Username框焦点
		_G('username-menulist').focus();
		
		//载入版本号
		Login.version = VerMgr.getVer();

		//显示版本号
		Login.showVer();
		
		//载入loginEnv信息
		Login.loadLoginEnv();

		//检查版本
		Login.checkVersion();

		//载入login属性设置
		Login.loadProps();
		
		//自动登录
		Login.autoLogin();
	},
	
	autoLogin: function() {
		if (!Login.props.isAutoLogin)
			return;
		Login.doLogin();
	},

	loadLoginEnv: function() {
		var data = Req.blockReqJson(URL_getLoginEnv, {version: Login.version}, '正在获取服务器信息...');
		Login.env = data;
		
		//修改窗口标题
		window.document.title = Login.env.hall.name;
	},
	
	checkVersion: function() {
		Login.isLoginable = false;
		var data = Req.blockReqJson(Login.env.url_check_version, {version: Login.version, hallid: Login.env.hall.id}, '正在检查版本信息...');
		switch (data.response) {
			case 'update_client' :
				Login.doUpdate(data);
				break;
			default:
				Login.isLoginable = true;
		}
	},
	
	onSelectAccount:function(){
		var username = $('#username').attr('label');
		var account = Login.accounts[username];
		Login.fillAccount(account);
	},
	
	fillAccount: function(account){
		if (!account)
			return;
		DBG("", account);
		_G('username-menulist').label = account.username;
		_G('password-textbox').value = account.password;
	},
	
	doLogin:function(){
		if (!Login.isLoginable)
			return;

		//检查用户名
		var G_username = _G('username-menulist');
		var G_password = _G('password-textbox');
		if(G_username.label.trim()==''){
			_alert('请输入用户名！');
			G_username.focus();
			G_username.select();
			return;
		}

		//记住帐号，询问
		Login.askSaveAccount();

		//构造登录请求参数
		var params = {
			username: G_username.label,
			password: G_password.value,
			upkey: "wjrzm",
			version: VerMgr.getVer()
		};
		Req.reqJson(Login.env.url_login, params, function(data) {
			//保存历史帐号
			Login.saveAccount();
			
			//保存环境变量
			data.env.gameuser = data.gameuser; //注意：这里将gameuser放入了env变量一起保存
			data.env.hall = data.hall; //注意：这里将hall放入了env变量一起保存
			FileIO.saveText(FILE_initGameHall, escape(JSON.toString(data.env)));	

			//自动升级处理
			Login.checkUpdate(data.env);
			
			//保存自动登录属性
			Login.props.isAutoLogin =  _G('autologin-checkbox').checked;
			Login.props.isRemPassword = _G('rempassword-checkbox').checked;
			Login.saveProps();

			//打开主窗体
			Login.close();
			window.open("main.xul" ,"skymiraclegamemainapp","chrome,modal=no,titlebar=no,centerscreen=yes,resizable=no,height=700,width=1000");
		});
	},
	
	doUserReg: function(){
		BrowserTool.openWithSys(Login.env.url_ureg);
		return;
	},
	
	doUserForgotPass: function() {
		BrowserTool.openWithSys(Login.env.url_forget_pass);
		return;
	},
	
	checkUpdate: function(data) {
		if("update_client" == data.response){
			Login.doUpdate(data);
			return;
		}
	},
	
	_getURLs:function(datas){
		var lurlFile = FileIO.newAppRootFile();
		lurlFile.append(DN_conf);
		lurlFile.append(FN_localURL);
		var urlJosn = {};
		var text = '';
		if(datas){
				Login.userRegUrl = datas.url_ureg;
				Login.userForgotPass = datas.url_passback;
				//Login.coverUrl = datas.url_cover;				
				//Login.coverUrl = 'chrome://SkyGameClient/content/images/cover.jpg';
				//$('#loginURLBrowser').loadURI(Login.coverUrl);
				$('#userRegBtn').attr('disabled', '');
				if (!lurlFile.exists()) FileIO.createFile(lurlFile, false);
				FileIO.saveText(lurlFile,escape(JSON.toString(datas)));
		}else{
				DBG("Login._getUrls", "请检查你的网络");
				if (!lurlFile.exists()){
					_alert('无法读取本地url列表！');
				}else{
					text = FileIO.loadText(lurlFile);
					if(!text)
						text = '{}';
				    urlJosn = eval('(' + unescape(text)+ ')');		

				    Login.userRegUrl = urlJosn.url_ureg;
					//Login.coverUrl = urlJosn.url_cover;
					//Login.coverUrl = 'chrome://SkyGameClient/content/images/cover.jpg';
					//$('#loginURLBrowser').loadURI(Login.coverUrl);
					$('#userRegBtn').attr('disabled', '');		
					
				}
				
				////////////FileIO.createFile(FN_localURL, false);				
		}	
	},
	
	showVer: function() {
		var ver = VerMgr.getVer();
		_G('version-label').value = "版本 " + ver;
	},
	
	askSaveAccount: function() {
		var G_username = _G('username-menulist');
		var G_psswrdChk = _G('rempassword-checkbox');

		//无须记住密码，直接返回
		if(!G_psswrdChk.checked && !_G('autologin-checkbox').checked)
			return;

		//如果本来就是原来存的帐号，则跳过
		if (Login.accounts[G_username.label])
			return;
			
		//询问是否需要记住密码
		if(!_confirm('是否记住密码？\n' + G_psswrdChk.getAttribute('tooltiptext'))){
			G_psswrdChk.checked = false;
			return;
		}
	},
	
	saveAccount: function() {
		var G_username = _G('username-menulist');
		var G_password = _G('password-textbox');
		var G_psswrdChk = _G('rempassword-checkbox');

		//无须记住密码，直接返回
		if(!G_psswrdChk.checked  && !_G('autologin-checkbox').checked)
			return;

		//取出当前的用户账号，并重新排列顺序，将最近的帐号放到Login.accounts的最后
		var username = G_username.label;
		var password = G_password.value;
		var accounts = {};
		for(var p in Login.accounts) {
			if (p == username) 
				continue;
			accounts[p] = Login.accounts[p];
		}
		accounts[username] = {
			username: username,
			password: password
		};
		Login.accounts = accounts;
		
		//将历史帐号列表存入文件
		FileIO.saveText(FILE_lastLoginUser, JSON.toString(Login.accounts));
	},

	removeHistory: function(){
		if(!_confirm('清空所有历史记录？'))
			return;
		_G('username-menulist').removeAllItems();
		_G('username-menulist').label = '';
		_G('password-textbox').value = '';
		_G('rempassword-checkbox').checked = false;
		FileIO.saveText(FILE_lastLoginUser, '{}');	
		Login.props.isAutoLogin = false;
		Login.props.isRemPassword = false;
		Login.saveProps();
	},
	

	updateProcessbar: function (v){
		var p = document.getElementById('downloadprocessbar');
		p.setAttribute('value', parseInt(v));
	},
	
	doUpdate: function(data) {
		_G('update-box').setAttribute('hidden',false);
		_G('update-box').selectedPanel = _G('processbarinfo');
		
		var downloadListener = {
			onRequest : function(contentLength) {
				totalLen = contentLength;
			},
			onOver : function() {
				var unpackListener = {
					onStart: function() {
						_G('updating-label').value = "开始解压缩安装包";
					},
					onError: function(e) {
						_alert("游戏大厅升级失败，请重新启动游戏大厅软件，或下载新的版本。");
						window.close();
					},
					onEnd: function() {
						_G('updating-label').value = "解压完毕，升级成功";
						VerMgr.setVer(data.ver_version);
						window.location.reload();
					}
				}
				VerMgr.unPack(unpackListener);
				
			},
			onProgress : function(downedSize, tmSpent,tmLess, tmAll, speed) {
				var rate = parseFloat((downedSize*100)/totalLen );
				Login.updateProcessbar(rate.toString()  + '%');
				var dsK = parseInt(downedSize / 1024);
				var tsK = parseInt(totalLen / 1024);
				var lessS = parseInt(tmLess / 1000);
				var speedK = parseInt(speed / 1024);
				_G('updating-label').value = dsK + '/' + tsK + ' KB (剩余:' +lessS + '秒) 速度:' + speedK + 'KB/s';
			}
		}
		VerMgr.download(data.ver_url_download, downloadListener);
	},
	
	reopen: function() {
		window.open("login.xul", "login", "chrome");
	},
	
	close: function() {
		window.close();
	}

}
var SearchExe = {
	curGame : null,
	targetFiles : [],
	onLoad : function() {
		SearchExe.curGame = opener.Setting.curGame;
		var gameExeFiles = opener.Setting.gamesExeFiles;
		SearchExe.targetFiles = gameExeFiles[SearchExe.curGame.gameid];
		_G('game-filenames-label').value = SearchExe.targetFiles.join(';');
		_G('game-name-label').value = SearchExe.curGame.name;

	},

	allDisks : ['C:\\', 'D:\\', 'E:\\', 'F:\\', 'G:\\', 'H:\\'],

	start : function() {
		var disks = [];
		if (_G('disk-list').value == '')
			for (var i = 0; i < SearchExe.allDisks.length; i++)
				disks.push(SearchExe.allDisks[i]);
		else
			disks.push(_G('disk-list').value);

		SearchExe.doSearch(disks);
	},

	searchTimer : null,

	skipNames : [
		"window",
		"winnt"
	],
	
	doSearch : function(disks) {
		var stack = [];

		for (var i = 0; i < disks.length; i++) {
			var path = disks[i];
			var dir = _OBF.localFile(path);
			if (!dir.exists())
				continue;
			stack.push(dir);
		}
		

		var curFile = null;
		var isSearching = false;
		var isFound = false;
		var searchOne = function() {
			if (isFound)
				return;
			if (isSearching)
				return;

			isSearching = true;
			try {
				// 从stack中取出一个
				var file = stack.shift();
				if (!file)
					return;

				var leafName = file.leafName.toLowerCase();
				
				if (leafName == 'windows' || leafName == 'winnt') {
					//跳过window和winnt目录
				}else if (file.isDirectory()) {
					// show file
					_G('cur-path-label').setAttribute('value', file.path);
					
					// 判断文件是否存在
					for (var i = 0; i < SearchExe.targetFiles.length; i++) {
						var f = file.path + "\\" + SearchExe.targetFiles[i];
						var f = _OBF.localFile(f);
						if (f.exists()) {
							isFound = true;
							SearchExe.onFound(f);
							break;
						}
					}

					// 把子目录全部压入stack
					var files = file.directoryEntries;
					while (files.hasMoreElements()) {
						var next = files.getNext()
								.QueryInterface(Components.interfaces.nsIFile);
						if (next.isDirectory())
							stack.push(next);
					}
				}

			} catch (e) {
				jsdump(e);
				DBG("doSearch", e);
			}

			isSearching = false;
		}

		SearchExe.searchTimer = setInterval(searchOne, 0);
	},

	stop : function() {
		clearTimeout(SearchExe.searchTimer);

	},

	exit : function() {
		SearchExe.stop();
		window.close();
	},

	onFound : function(file) {
		SearchExe.stop();
		opener.Setting.setGameExePath(file.path);
		window.close();

	}

}
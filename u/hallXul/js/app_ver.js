
var VerMgr = {
	getVerFile: function() {
		var file = FileIO.newAppRootFile();
		file.append(FN_ver);
		return file;
	},
	
	getUpdateDownFile: function() {
		var file = FileIO.newAppRootFile();
		file.append(DN_updateDown);
		file.append(FN_updateDown);
		return file;
	},
	
	setVer: function(ver) {
		FileIO.saveText(this.getVerFile(), ver);
	},
	
	getVer: function() {
		var file = this.getVerFile();
		if (!file.exists())
			this.setVer("0");
		return FileIO.loadText(this.getVerFile());
	},
	
	download: function(url, downloadListener) {
		var file = this.getUpdateDownFile();

		NetIO.download(url, file, downloadListener);
	},

	unPack: function(unpackListener) {
		unpackListener.onStart();
		try {
			var zipReader = Components.classes["@mozilla.org/libjar/zip-reader;1"]
							 .getService(Components.interfaces.nsIZipReader);
			
			var zipFile = this.getUpdateDownFile();
			zipReader.open(zipFile);
	
			var entries = zipReader.findEntries("*");
			while (entries.hasMore()) {
				var entryName = entries.getNext();
				zipReader.test(entryName);
				var zipEntry = zipReader.getEntry(entryName);
				var path = FileIO.appRootPath + '/' + entryName;
				path = path.replace(/\//g, '\\');
				var extFile = _OBF.localFile(path);
				FileIO.createFile(extFile, zipEntry.isDirectory);
					if (!zipEntry.isDirectory)
						zipReader.extract(entryName ,extFile);
		     }
		     
			zipReader.close();
			unpackListener.onEnd();
		}catch(e) {
			jsdump( e);
			unpackListener.onError(e);
		}
	    return;
	}
}

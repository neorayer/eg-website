	function sReplayTab(_tab){
		if(!window.g) window.g ={};
		window.g.ReplayData  = null;
		try{
			var _root = FileIO.newAppRootFile();
			_root.append('w3gReplays');

			if(!_root.exists()){
				_root.create(Components.interfaces.nsIFile.DIRECTORY_TYPE, -1);
			}			
			var r = _getFilesBySuffix(_root,'w3g');
			var rr = null;
			if(r){
				window.g.ReplayData  =   groupBySameDate(sortByDesc(r));
			}
			setTimeout("__initReplayTree(window.g.ReplayData)",20);

			
			$('ReplayCDeck').selectedPanel = $('defaultForReplay');
			if(FILE_replay.exists()){
				var _iniParser = _OBF.iniParser(FILE_replay);
				if(parseInt(_iniParser.getString('wc3','playmode'))=='0')	$('isReplayWinMode').checked = true;
				else 	$('isReplayWinMode').checked = false;
			}
			
		}catch(e){
			_alert(e);
		}
	
	}
	function makeReplayPRowId(_str){
		return escape('replayPRow_' + _str);
	}
	function makeReplayRowId(_str){
		return escape('replayRow_' + _str);
	}
	function __initReplayTree(_data){
		var _body = $('myReplayBody');
		removeAllChild('myReplayBody');
		for(var p in  _data){
			var _titem = document.createElement('treeitem');
			_titem.setAttribute('id',makeReplayPRowId(p));			
			_body.appendChild(_titem);
			
			var _tcell = document.createElement('treecell');
			var _trow = document.createElement('treerow');
			_trow.appendChild(_tcell);
			_titem.appendChild(_trow);
			_tcell.setAttribute('label',p);

			_tcell = document.createElement('treecell');
			_tcell.setAttribute('label',p);
			_tcell.setAttribute('hidden','true');
			_trow.appendChild(_tcell);

			_tcell = document.createElement('treecell');
			_tcell.setAttribute('label','-1');
			_tcell.setAttribute('hidden','true');
			_trow.appendChild(_tcell);
			if(_data[p].length>0){
				_titem.setAttribute('container','true');
				var tchild = _titem.appendChild(document.createElement('treechildren'));				
				for(var i =0 ; i<_data[p].length; i++ ){
					var _tCrow = document.createElement('treerow');

					var _tCcell = document.createElement('treecell');
					var _time = _data[p][i].name;
					_time = _time.substring((p+'_').length).replace(/-/g,':');
					_time = _time.substring(0,_time.lastIndexOf('.'));					
					_tCcell.setAttribute('label',_time);					
					_tCrow.appendChild(_tCcell);
					
					_tCcell = document.createElement('treecell');
					_tCcell.setAttribute('label',p.toString());
					_tCcell.setAttribute('hidden','true');
					_tCrow.appendChild(_tCcell);
					
					_tCcell = document.createElement('treecell');
					_tCcell.setAttribute('label',i.toString());
					_tCcell.setAttribute('hidden','true');
					var _tCitem = document.createElement('treeitem');
					_tCitem.setAttribute('id',makeReplayRowId(_data[p][i].name));
					_tCrow.appendChild(_tCcell);
					_tCitem.appendChild(_tCrow);					
					tchild.appendChild(_tCitem);
				}
			}
			
		}
		if(_body.firstChild)_body.firstChild.setAttribute('open','true');
	/*
		try{
			_body.parentNode.treeBoxObject.getRowCount();
		}catch(e){
			alert(e);
		}
		*/
	}
	function groupBySameDate(fileLst){
		var _r = {};
		var _key = '';
		for(var i = 0 ;  i < fileLst.length; i++){
			fname = fileLst[i].name;
			_key = 	fname.substring(0,fname.indexOf('_'));
			if(!_r[_key])
				_r[_key] = new Array();
			_r[_key].push(fileLst[i]);
		}
		return _r;
	}
	function _getFilesBySuffix(_root,_suffix){
		try{
			var _files = _root.directoryEntries;
			var _result = new Array();
			while(_files.hasMoreElements()){
				var file = _files.getNext().QueryInterface(Components.interfaces.nsIFile);
				var fileName= file.leafName ;
				if(fileName.substring(fileName.lastIndexOf(_suffix))==_suffix){
					_result.push({name:fileName,path:file.path,size:file.fileSize,mdate:file.lastModifiedTime});				
				}
			}
			return _result;
		}catch(e){
			_alert(e);
			return null;
		}
	}
	function sortByDesc(r){
			var	compare =  function (a, b){
					    if (a.name > b.name)
					      return -1;
					    else if (a.name == b.name)
					      return 0;
					    return 1;
				  };
			r.sort(compare);
			return r;	
	}
	function __getReplayItem(aTree,aEvent) {
		try{
		    var row = {};
		    var col = {};
		    var obj = {};
		    var tree = aTree;
		    var treeBox = tree.treeBoxObject;
		    treeBox.getCellAt(aEvent.clientX, aEvent.clientY, row, col, obj);
		    if(row.value == -1) return ;
		    if(!col.value) return;
	 
	 	 	//var _v = tree.view.getCellText(tree.currentIndex,tree.columns.getColumnAt(0));
		   var _citem_type =  tree.view.getCellText(tree.currentIndex,treeBox.columns.getNamedColumn('myreplayType')); 
		   var _citem_no = tree.view.getCellText(tree.currentIndex,treeBox.columns.getNamedColumn('myreplayNo')); 
		   if(parseInt(_citem_no) >=0){
		   		$('ReplayCDeck').selectedPanel = $('p1ReplayInfo');
		   		var _v = window.g.ReplayData[_citem_type][_citem_no];
		   		$('_ReplayFType').setAttribute('value',_citem_type);
		   		$('_ReplayFIndex').setAttribute('value',_citem_no);
		   		$('_ReplayFPath').setAttribute('value',_v.path);
		   		$('_ReplayFName').setAttribute('value',_v.name);
				$('_ReplayFSize').setAttribute('value',_v.size);
				$('_ReplayFDate').setAttribute('value',(new Date(_v.mdate)).toLocaleString());
		   }else{
		   		$('ReplayCDeck').selectedPanel = $('lstReplaysInfo');
		   		var total = 0;
		   		for(var i = 0 ; i < window.g.ReplayData[_citem_type].length ; i++)
		   			if(window.g.ReplayData[_citem_type][i])   			total++;
		   		$('totalReplayNo').setAttribute('value',total);   
		   }
	   }catch(e){
			_alert(e);
			return;
	   }
	}
	function playReplay(){
		var _war3exe = 	'';
		if(FILE_replay.exists()){
			var _iniParser = _OBF.iniParser(FILE_replay);
			_war3exe = _iniParser.getString('wc3','file');
			var params = [];
			params[0] = '-loadfile';
			params[1] =  _UC.u2gb2312($('_ReplayFPath').getAttribute('value'));
			if(($('isReplayWinMode').checked))		params[2] =  '-window'; 
			//execFile(_UC.u2gb2312(_war3exe), params, false);
			execFile(_war3exe, params, false);
		}else{
			setPlayReplay();
		}
	}
	function setPlayReplay(){	
		window.openDialog("replaySet.xul","skyGameAppReplaySet","modal,chrome,centerscreen=yes");	
	}

	function deleteReplay(){
	    var __prompts = Components.classes["@mozilla.org/embedcomp/prompt-service;1"]
	                        .getService(Components.interfaces.nsIPromptService);		
		if(!__prompts.confirm ( window , '提示' , '删除录象？'))		return;
		try{
		   		var file =  Components.classes["@mozilla.org/file/local;1"]
						.createInstance(Components.interfaces.nsILocalFile);
				file.initWithPath($('_ReplayFPath').getAttribute('value'));
				if(file.exists()&&file.isFile())	file.remove(true);

				var _row = 	$(makeReplayRowId($('_ReplayFName').getAttribute('value')));
				_row.parentNode.removeChild(_row);
				var _citem_type = $('_ReplayFType').getAttribute('value');
		   		var _citem_no = $('_ReplayFIndex').getAttribute('value');
		   		
		   		var total = 0;
		   		for(var i = 0 ; i < window.g.ReplayData[_citem_type].length ; i++)
		   			if(window.g.ReplayData[_citem_type][i])   			total++;
		   		
		   		if(total <=1){
					var _prow = 	$(makeReplayPRowId(_citem_type));		   		
		   			_prow.parentNode.removeChild(_prow);
		   		}
				window.g.ReplayData[_citem_type][_citem_no] = null;
				$('ReplayCDeck').selectedPanel = $('defaultForReplay');
		}catch(e){
			_alert(e);
		}
	}
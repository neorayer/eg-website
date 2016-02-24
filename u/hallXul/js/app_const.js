/* JSP */
var URL_getLoginEnv				= 'http://hall.ko10000.com/GameHall2/u/hallXul/loginEnv.json.vi';

/* File Name */
var FN_startGame	= 'gf_startgame.exe';
var FN_gameFilter	= 'gf.dll';
var FN_loginProps 	= 'login.props';
var FN_gfProps		= 'gf_props.ini';
var FN_updateDown	= 'pack.zip';
var FN_gameSetting	= 'gameSetting.ini';
var FN_initGameHall = 'iniGameHall.ini';
var FN_lastLoginUser = 'lastLoginUsers.cc';
var FN_ver			= 'ver.bin';
var FN_roomspeed	= 'gf_roomspeed.ini';
var FN_localURL 	= 'localurl.ini';
var FN_replay 		= 'replaysetting.ini';
var FN_ghSetting 	= 'ghSetting.ini';
var FN_hallTray		= 'tray.exe';
var FN_warKey		= 'kokey.exe';

/* Directory Name */
var DN_updateDown	= 'updates';
var DN_xulrunner	= 'xulrunner';
var DN_conf = 'conf';
var DN_replay = 'w3gReplays';

var confDir = FileIO.newAppRootFile();
confDir.append(DN_conf);
var isDir = _OBF.localFile(confDir);
if(!isDir.exists()||!isDir.isDirectory()){
	isDir.create(Components.interfaces.nsIFile.DIRECTORY_TYPE, -1);	
}

var replayDir = FileIO.newAppRootFile();
replayDir.append(DN_replay);
var isDir = _OBF.localFile(replayDir);
if(!isDir.exists()||!isDir.isDirectory()){
	isDir.create(Components.interfaces.nsIFile.DIRECTORY_TYPE, -1);	
}

/* FileObject */
var FILE_startGame	= FileIO.newAppRootFile();
FILE_startGame.append(DN_xulrunner);
FILE_startGame.append(FN_startGame);

var File_hallTray 	= FileIO.newAppRootFile();
File_hallTray.append(DN_xulrunner);
File_hallTray.append(FN_hallTray);

var File_warKey 	= FileIO.newAppRootFile();
File_warKey.append(DN_xulrunner);
File_warKey.append(FN_warKey);

var FILE_gameFilter	= FileIO.newAppRootFile();
FILE_gameFilter.append(DN_xulrunner);
FILE_gameFilter.append(FN_gameFilter);

var FILE_gameSetting = FileIO.newAppRootFile();
FILE_gameSetting.append(DN_conf);
FILE_gameSetting.append(FN_gameSetting);

var FILE_loginProps = FileIO.newAppRootFile();
FILE_loginProps.append(DN_conf);
FILE_loginProps.append(FN_loginProps);

var FILE_initGameHall = FileIO.newAppRootFile();
FILE_initGameHall.append(DN_conf);
FILE_initGameHall.append(FN_initGameHall);

var	FILE_lastLoginUser = FileIO.newAppRootFile();
FILE_lastLoginUser.append(DN_conf);
FILE_lastLoginUser.append(FN_lastLoginUser);

var FILE_gameRoomSpeed	= FileIO.newUserTmpFile();
FILE_gameRoomSpeed.append(FN_roomspeed);

var FILE_mySpeed	=  FileIO.newAppRootFile();
FILE_mySpeed.append(DN_conf);
FILE_mySpeed.append('myspeed.ini');

var FILE_newRoomSpeed	=  FileIO.newAppRootFile();
FILE_newRoomSpeed.append(DN_conf);
FILE_newRoomSpeed.append('myspeed.ini');

var FILE_replay	=  FileIO.newAppRootFile();
FILE_replay.append(DN_conf);
FILE_replay.append(FN_replay);

var FILE_ghSetting = FileIO.newAppRootFile();
FILE_ghSetting.append(DN_conf);
FILE_ghSetting.append(FN_ghSetting);

var Chat_EMOTE ={
	fontSize:'12px',
	color:'#888',
	marginTop:'5px',
	marginBottom:'5px'	
}
var Chat_MYCHAT = {
	fontSize:'12px',
	color:'#005fff'
};

var Chat_WAIT = {
	fontSize:'12px',
	fontWeight:'bold',
	fontStyle:'italic',
	color:'#f62233'
};

var Chat_INROOM = {
	fontSize:'12px',
	color:'#ff2233'
};
var Chat_OUTROOM = {
	fontSize:'12px',
	color:'#666666'
};

var Chat_INGAME = {
	fontSize:'12px',
	color:'#ff2233'
};
var Chat_OUTGAME = {
	fontSize:'12px',
	color:'#666666'
};

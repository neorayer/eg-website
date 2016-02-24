function jsdump(str){
	var s = "" + str;
	Components.classes['@mozilla.org/consoleservice;1']
			.getService(Components.interfaces.nsIConsoleService)
			.logStringMessage(s);
}
var DEBUG = jsdump;

function DBG(funcName, data, e) {
	var s = funcName + ": ";
	if (data instanceof Object)
		s += JSON.toString(data);
	else 
		s += data;
	s += e ? e: "";
	s += "\n";
	dump(s);
}
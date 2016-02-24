<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />

<script type="text/javascript" src="../../_js/jquery/jquery-1.2.6.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.core.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.draggable.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.droppable.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.slider.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.tabs.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.sortable.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.resizable.js"></script>
<script type="text/javascript" src="../../_js/jquery/ui/ui.dialog.js"></script>

<script type="text/javascript" src="../../_js/jquery/plugins/jquery.form.js"></script>
<script type="text/javascript" src="../../_js/jquery/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="../../_js/jquery/plugins/jquery.metadata.js"></script>


<script language="javascript" type="text/javascript" src="../../_js/DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="../_js/album.js"></script>
<script type="text/javascript" src="../_js/js.js"></script>
<script type="text/javascript" src="../_js/user.js"></script>
<script type="text/javascript" src="../_js/team.js"></script>
<script type="text/javascript" src="../_js/message.js"></script>
<script type="text/javascript" src="../_js/game.js"></script>
<script type="text/javascript" src="../_js/cookie.js"></script>
<script type="text/javascript" src="../_js/userfriend.js"></script>
<script type="text/javascript" src="../_js/fresh.js"></script>
<script type="text/javascript" src="../_js/article.js"></script>



<link rel="stylesheet" href="../_css/common.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/portal.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/team.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/user.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/message.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/match.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/userspace.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/armory.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/teamspace.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/jquery/ui.jq.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/album.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/friend.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/install.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/article.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/userindex.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/teamindex.css" type="text/css"  ></link>
<link rel="stylesheet" href="../_css/player.css" type="text/css"  ></link>


<script type="text/javascript">
$.ajaxSetup ({
    cache: false //关闭AJAX相应的缓存
});

$().ajaxComplete(function(event, response, ajaxOptions){
	/**
	if('json' == ajaxOptions.dataType) {
		var responseText = response.responseText.replace(/<\/pre>/, '').replace(/<pre>/, '');
		var data = eval('(' +responseText + ')');
	   	if(data.res == 'no') {
			alert(data.data);
			return;
		}
	}
	*/
 });
 
var Portal = {
	checkSession: function() {
		<c:if test='${empty ACTOR}'>
			return false;
		</c:if>
		<c:if test='${not empty ACTOR}'>
			return true;
		</c:if>
	}
} 

if (typeof Util == 'undefined') {
	Util = {};
}

Util.formSubmit = function(form, callBack) {
	var $form = $(form);
	$form.attr("action", $form.attr("action").replace("jsplayout", "json"));
	$form.ajaxSubmit({
		type: 'post',
		dataType: 'json',
		success: function(data) {
			if (data.res != 'yes') {
				alert(data.data);
				Util.refreshAuthImage();
				return;
			}
			
			if($.isFunction(callBack))
				callBack(data);
			else if(typeof callBack == 'string')
				alert(callBack);
			
			var redirectTo = $form.get(0).redirectTo;
			if(Util.exists(redirectTo))
				window.location = redirectTo.value;
			else
				window.location.reload();
		}
	});
	return false;
};

Util.refreshAuthImage = function(authImageId) {
	if(!Util.exists(authImageId))
		authImageId = "AuthImg";
		
	var $authImage = $("#" + authImageId);
	if($authImage.length > 0) {
		$authImage.get(0).src= $authImage.attr('src') + '?' +Math.random();
	}
};

Util.exists = function(obj) {
	return typeof obj != 'undefined';
};

</script>




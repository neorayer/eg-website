<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">


#page-player-egreplay-head-box{
	height:25px;
}
#page-player-egreplay-head-box li{
	background:transparent url(../_css/images/portal/feedtabbg.gif) no-repeat scroll left -119px;
	float:left;
	margin-right:3px;
	padding:0 0 0 10px;
	margin-top:1px;
}

#page-player-egreplay-head-box a{
	background:transparent url(../_css/images/portal/feedtabbg.gif) no-repeat scroll right -80px;
	display:block;
	float:left;
	font-weight:bold;
	padding:7px 16px 4px 5px !important;
	text-decoration:none;
}

#page-player-egreplay-head-box .egreplay-selected{
	background:#FFFFFF url(../_css/images/portal/feedtabbg.gif) repeat scroll left -39px;
	line-height:16px;
}

#page-player-egreplay-head-box .egreplay-selected a{
	background:transparent url(../_css/images/portal/feedtabbg.gif) no-repeat scroll right -5px;
	color:#333333;
	font-size:13px;
	
	display:block;
	float:left;
	font-weight:bold;
	padding:7px 16px 4px 5px !important;
	text-decoration:none;
}

#page-player-egreplay-body-box {
	margin-top:3px;
}
</style>

<div id="page-player-egreplay-box">	
	<ul id="page-player-egreplay-head-box">
		<li id='egreplay-user-lab-box'>
			<a href="javascript:" onclick="EgReplay.userLoad();">平台记录</a>
		</li>
		
		<li id='egreplay-bisai-lab-box'>
			<a href="javascript:" onclick="EgReplay.bisaiLoad();">比赛记录</a>
		</li>
	</ul>
	
	<div id="page-player-egreplay-body-box"></div>
</div>


<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>




<div style="clear: both;"></div>

<script type="text/javascript">
var EgReplay ={
	load: function(url){
		$('#page-player-egreplay-body-box').load(url);
	},
	userLoad: function() {
		$('#page-player-egreplay-head-box > *').removeClass("egreplay-selected");
		$('#egreplay-user-lab-box').addClass("egreplay-selected");
		
		var url ='../player/userRecord.jsp.vi?username=<c:out value='${player.username}'/>';
		EgReplay.load(url);
	},
	bisaiLoad: function(){
		$('#page-player-egreplay-head-box > *').removeClass("egreplay-selected");
		$('#egreplay-bisai-lab-box').addClass("egreplay-selected");
		var url ='../player/bisaiRecord.jsp.vi?username=<c:out value='${player.username}'/>';
		EgReplay.load(url);
	}
}

;$(document).ready(function() {
	
	EgReplay.userLoad();
});  
</script>

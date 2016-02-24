<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">



#page-player-eglevel-head-box{
	height:26px;	
}
#page-player-eglevel-head-box li{
	background:transparent url(../_css/images/portal/feedtabbg.gif) no-repeat scroll left -119px;
	float:left;
	margin-right:3px;
	padding:0 0 0 10px;
	margin-top:1px;
}

#page-player-eglevel-head-box a{
	background:transparent url(../_css/images/portal/feedtabbg.gif) no-repeat scroll right -80px;
	display:block;
	float:left;
	font-weight:bold;
	padding:7px 16px 4px 5px !important;
	text-decoration:none;
}

#page-player-eglevel-head-box .eglevel-selected{
	background:#FFFFFF url(../_css/images/portal/feedtabbg.gif) repeat scroll left -39px;
	line-height:16px;
}

#page-player-eglevel-head-box .eglevel-selected a{
	background:transparent url(../_css/images/portal/feedtabbg.gif) no-repeat scroll right -5px;
	color:#333333;
	font-size:13px;
	
	display:block;
	float:left;
	font-weight:bold;
	padding:7px 16px 4px 5px !important;
	text-decoration:none;
}

</style>

<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>
<div id="page-player-eglevel-box">	
	<ul id="page-player-eglevel-head-box">
		<li id='eglevel-user-lab-box'>
			<a href="javascript:" onclick="EgLevel.bisaiLoad();">平台水平</a>
		</li>
	</ul>
	<div id="page-player-eglevel-body-box"></div>
</div>

<div style="clear: both;"></div>

<script type="text/javascript">
var EgLevel ={
	load: function(url){
		$('#page-player-eglevel-body-box').load(url);
	},
	bisaiLoad: function() {
		$('#page-player-eglevel-head-box > *').removeClass("eglevel-selected");
		$('#eglevel-user-lab-box').addClass("eglevel-selected");
		
		var url ='../team/teamPoint.jsp.vi?teamid=<c:out value='${teamid}'/>';
		EgLevel.load(url);
	}
}

;$(document).ready(function() {
	
	EgLevel.bisaiLoad();
});  
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style type="text/css">

#PL_team .hd-box {
	height: 0px;
}

#team-portlet-top-box {
	height: 40px;
	border:1px solid gray;
	margin-bottom: 20px;
}

#team-portlet-middle-left-box {
	float: left;
	width: 550px;
}

#team-portlet-middle-right-box {
	float: right;
	width: 230px;
}


</style>

<div class="tp-box"><div></div></div>
<div class="hd-box"></div>
<div class="bd-box">
	<div id="team-portlet-top-box">
		<c:import url="../team/todayMsg.jsp.vi"></c:import>
	</div>
	<div id="team-portlet-middle-box">
		<div id="team-portlet-middle-left-box">
			<c:import url="../team/coll.jsp.vi"></c:import>
		</div>
		<div id="team-portlet-middle-right-box">
			<div id="team-porlet-firstTeams-box">
				一线队员
			</div>
			<div id="team-porlet-secondTeams-box">
				二线队员
			</div>
			<div id="team-portlet-news-box">
				最新消息
			</div>			
		</div>
		
		<div style="clear:both;"></dvi>
	</div>
</div>
<div class="bt-box" ><div></div></div>

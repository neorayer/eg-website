<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
	#selection-bar-box {
		height: 26px;
	}
	
	#games-box {
		float: left;
	}
	
	#zones-box {
		float: left;
	}
	
	#rooms-box {
		float: left;
	}
	
	#users-box {
		clear: both;
		background-color: white;
		margin: 10px 0 0 0 ;
	}
	
	
</style>
<div class="standard-body-box">
	
	<div id="selection-bar-box" >
		<div id="games-box">
			游戏:
			<select id="games-select" onchange="javascript: RoomX.loadZones($('#games-select').val())" >
				<option value="">选择游戏</option>
				<c:forEach var="game" items="${games}">
				<option value="<c:out value="${game.gameId}" />">
					<c:out value="${game.name}" />
				</option>
				</c:forEach>
			</select>
		</div>
			
		<div id="zones-box">
		</div>
		
		<div id="rooms-box">
		</div>
	</div>
	
	<div id="users-box">
	</div>

	<div style="clear:both" ></div>
</div>

<script type="text/javascript">
var RoomX = {
	loadZones: function(gameId) {
		$("#zones-box").load("../room/zones.jsp.vi?gameid=" + gameId);
		$("#rooms-box").text("");
		$("#users-box").text("");
	},
	
	loadRooms: function(gameId, zoneId) {
		$("#rooms-box").load("../room/rooms.jsp.vi?gameid=" + gameId + "&zoneid=" + zoneId);
		$("#users-box").text("");
		
	},
	
	loadUsers: function(roomId) {
		$("#users-box").load("../room/users.jsp.vi?roomid=" + roomId);
	},
	
	timeoutCheck: function(roomId) {
		jQuery.post("../room/timeoutCheck.json.do?roomid=" + roomId, {}, function(res) {
			if ('yes' == res.res) {
				RoomX.loadUsers(roomId);
				return;
			}
			
			alert(res.data);
		}, 'json');
	}
}
</script>
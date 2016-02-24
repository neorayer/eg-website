<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<div id="user-space-box">
	<div class="user-space-left-box" >
		<c:import url="../user/coll.jsp.vi"></c:import>
	</div>
	<div class="user-space-right-box">
		<ul class="user-visitor-box">
			<li class="user-vistor-li">
				<div class="user-visitor-title">最近来访:</div>
				<div class="user-visitors">
					<c:import url="../user/user_caller.jsp.vi?username=${username}"></c:import>
				</div>
			</li>
			
			<li class="user-vistor-li">
				<div class="user-visitor-title">
					<span class="user-battle-title">战友:</span>
					<span class="user-battle-more"><a href="javascript:BattleFriend.loadMyFriend('<c:out value='${username}'/>')">更多..</a></span>
				</div>
				<ul class="user-battlefriends">
					<c:forEach var='friend' items='${battlefriends}'>
					<li>
						<div class="user-battlefriends-pic-box" title="<c:out value="${friend.friend.dispName}"/>,成为战友时间<c:out value='${friend.createDate}'/>">
							<a href="index.jsplayout.vi?username=<c:out value="${friend.descname}"/>">
						 		<img width="46px" height="45px" src="<c:out value='${friend.friend.avatorSmallUrl}'/>"/>
						  	</a>
						</div>
						<div class="user-battlefriends-name-box" title="<c:out value="${friend.friend.dispName}"/>,成为战友时间<c:out value='${friend.createDate}'/>">
							<a href="index.jsplayout.vi?username=<c:out value="${friend.descname}"/>">
								<c:out value="${friend.friend.dispName}"/>
							</a>
						</div>
					</li>
					</c:forEach>
					<div style="clear: both;"></div>
				</ul>
				
			</li>
				<!--
			<li>
				<div class="user-visitor-title">对手:</div>
				<div class="user-visitors"></div>
			</li>
			<li>
				<div class="user-visitor-title">可能认识的人:</div>
				<div class="user-visitors"></div>
			</li>-->
		</ul>
	</div>
	<div style="clear:both;"></div>
</div>
<script type="text/javascript">
	var BattleFriend={
		loadMyFriend:function(username){
			$(".user-space-left-box").load("../user/user_battlefriend.jsp.vi?username="+username);
		}
	}

	
</script>
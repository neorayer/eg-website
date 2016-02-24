<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style>

</style>
<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>游戏友谊</strong>
		</div>
		<div class="floatRight">
			<form action="../userFriend/find.jsplayout.vi" method="post" id="page-userfriend-search-form">
				<input type="text" name="searchuser" id="searchuser" value=""/>
				<input type="submit" value="搜索"  onclick="UserFriend.search();return false;" title="搜索" class="button"/>
				<input type="button" value="好友权限" onclick="UserFriend.setting();"   title="权限设置" class="button"/>
			</form>
			
		</div>
	</div>
	<div style="clear:both"></div>

	
	<div id="allvisitor-page-body">	
		<!-- 
		<div class="friends-top-box">	
			<form action="../userFriend/find.jsplayout.vi" method="post" id="page-userfriend-search-form">
				<input type="text" name="searchuser" id="searchuser" value=""/>
				<input type="submit" value="搜索"  onclick="UserFriend.search();return false;" title="搜索" class="button"/>
			</form>
			<div class="page-userfriend-toolbut-box">
				<input type="button" value="权限设置" onclick="UserFriend.setting();"   title="权限设置" class="button"/>
			</div>
			<div style="clear: both;"></div>	
		</div>
		 -->
		 <div id="friendrelation-label-box">
		 	<div class="friendrelation-label-hover"><a href="#">好友</a></div>
		 	<div class="friendrelation-label"><a href="../user/allbattlefriends.jsplayout.vi">战友</a></div>
		 	<div class="friendrelation-label" style="border-right:1px solid #959595"><a href="../user/allvisitors.jsplayout.vi">访客</a></div>
		 </div>
		 <div style="clear:both"></div>
		 
		 
		<c:if test='${not empty friends}'> 
			<div id="friends-middle-box">
				<div class="player-page-portlet-title">
					<div class="player-page-portlet-titlefont floatLeft">好友信息</div>
				</div>
				<div id="page-friend-nogo-list">
					<c:forEach items="${friends}" var="friend">
						<div class="friend-addfriend-msg-box">
							<div class="allvisitor-page-album floatLeft">
								<a href="../player/index.jsplayout.vi?username=<c:out value="${friend.friendname}" />">
									<img src="<c:out value="${friend.avatorSmallUrl}" />"/>
								</a>
							</div>
							<div class="friend-msg-box floatLeft">
								<p><a href="../player/index.jsplayout.vi?username=<c:out value="${friend.friendname}" />"><c:out value="${friend.dispName}" /></a>&#32;邀请你加为好友。</p>
								<c:if test='${not empty friend.subject}'>
								留言:<c:out value="${friend.subject}" />
								</c:if>
							</div>
							
							<div class="friend-op-box floatRight">
								<a onclick="UserFriend.pass('<c:out value="${ACTOR.username}"/>','<c:out value="${friend.friendname}"/>');" href="javascript:" >加为好友</a>
								<a onclick="UserFriend.ignore('<c:out value="${ACTOR.username}"/>','<c:out value="${friend.friendname}"/>');" href="javascript:" >拒绝</a>
							</div>
							<div style="clear: both;"></div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<div id="friends-foot-box">
			<div class="player-page-portlet-title">
				<div class="player-page-portlet-titlefont floatLeft">我的好友&#40;<c:out value="${totalcount}"/>&#41;</div>
			</div>
			<!-- 
			<ul id="page-friend-pass-list">
				<c:forEach items="${myfriends}" var="friend">
				<li>
					<img class="logo-small-image" src="<c:out value="${friend.avatorSmallUrl}" />"/>
					<a title="<c:out value="${friend.dispName}" />" href="index.jsplayout.vi?username=<c:out value="${friend.friendname}"/>">
						<c:out value="${friend.dispName}" />
					</a>
					<br/>
					<a onclick="UserFriend.del('<c:out value="${ACTOR.username}"/>','<c:out value="${friend.friendname}"/>');" href="javascript:" >&#91;删除&#93;</a>
				</li>
				</c:forEach>
			</ul>
			<div style="clear: both;"></div>
			<div id="page-userfriend-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
			
			 -->
			<div id="allvisitor-page-body">
				<ul>
					<c:forEach var='friend' items='${myfriends}'>
					<li>
						<div class="allvisitor-page-album floatLeft">
							<a href="../player/index.jsplayout.vi?username=<c:out value='${friend.friendname}'/>">
								<img src="<c:out value="${friend.avatorSmallUrl}" />">
							</a>
						</div>
						<div class="allvisitor-page-albumright floatRight">
							<div class="allvisitor-page-name"><a href="../player/index.jsplayout.vi?username=<c:out value='${friend.friendname}'/>"><c:out value='${friend.dispName}'/></a></div>
							<div class="allvisitor-page-time"><a onclick="UserFriend.del('<c:out value="${ACTOR.username}"/>','<c:out value="${friend.friendname}"/>');" href="javascript:" >删除</a></div>
							
						</div>
					</li>
					</c:forEach>
				</ul>
				<div style="clear:both"></div>
				<c:if test='${totalcount>12}'>
					<div id="allvisitors-pagebar">
						<c:out value='${pageBar}' escapeXml="false"/>		
					</div>
				</c:if>
			</div>
			
		</div>
	</div>
</div>
 
<script type="text/javascript">

$(document).ready(function() {
	 
});
 
</script>

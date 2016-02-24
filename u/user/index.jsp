<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>


.photos-img{
	width: 80px;
	height: 80px;
	border: 1px solid #d8dfea;
	padding: 2px;
}
</style>



<div id="index-page-user-body">
	<ul id="index-page-user-center-body">
		<li id="index-page-user-info-box">
			<div class="index-page-user-album-box floatLeft">
				<div class="index-page-user-album">
					<img src="<c:out value='${ACTOR.avatorUrl}'/>" width="121px" height="170px"/>
				</div>
				<div class="index-page-user-modbar">
					<a href="../user/user_avator.jsplayout.vi">修改头像</a>&nbsp;<a href="../user/userinfo.mod.jsplayout.vi">个人资料</a>
				</div>
			</div>
			<div class="index-page-user-info-box floatRight">
				<div class="index-page-username">
					<STRONG><c:out value='${ACTOR.dispName}'/></STRONG>
				</div>
				<table width="80%" id="index-page-myinfo-table">
					<tr>
						<td width="30%" align="right">职业:</td>
						<td class="index-page-info-value"><c:out value='${ACTOR.strProfession}'/></td>
					</tr>
					<tr>
						<td align="right">所属战区:</td>
						<td class="index-page-info-value"><c:out value='${ACTOR.warZone.name}'/></td>
					</tr>
					<tr>
						<td align="right">电竞等级:</td>
						<td class="index-page-info-value">未开放</td>
					</tr>
					<tr>
						<td align="right">个人信誉:</td>
						<td>
							<c:forEach begin="1" end="${ACTOR.kulou}">
								<img src="../_css/images/portal/kulou.jpg" title="<c:out value='${ACTOR.reputation}'/>分">
							</c:forEach>
							<c:forEach begin="1" end="${ACTOR.signHeart}">
								<img src="../_css/images/portal/heart.jpg" title="<c:out value='${ACTOR.reputation}'/>分">
							</c:forEach>
							<c:forEach begin="1" end="${ACTOR.doubleHeart}">
								<img src="../_css/images/portal/doubleheart.jpg" title="<c:out value='${ACTOR.reputation}'/>分">
							</c:forEach>
							<c:forEach begin="1" end="${ACTOR.diamond}">
								<img src="../_css/images/portal/diamond.jpg" title="<c:out value='${ACTOR.reputation}'/>分">
							</c:forEach>
							<c:forEach begin="1" end="${ACTOR.crown}">
								<img src="../_css/images/portal/crown.jpg" title="<c:out value='${ACTOR.reputation}'/>分">
							</c:forEach>
							<c:forEach begin="1" end="${ACTOR.sword}">
								<img src="../_css/images/portal/sword.jpg" title="<c:out value='${ACTOR.reputation}'/>分">
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td align="right">出身日期:</td>
						<td class="index-page-info-value"><c:out value='${ACTOR.birthday}'/></td>
					</tr>
				</table>
			</div>
			
		</li>
		
		
		<!-- 消息 -->
		<li id="index-page-user-msg-box">
			<div class="index-page-user-relation-title">
				<div class="index-page-user-titlefont floatLeft">
					<b>消息中心</b>
				</div>
			</div>
			<div id="user-index-newmsg-box">
				<div class="floatLeft"  style="margin-right: 40px;">
					<a href="../userMsg/msgs.jsplayout.vi">我的消息</a> : <a href="../userMsg/msgs.jsplayout.vi" <c:if test="${msgcount ne '0'}">style="color:red;"</c:if>><c:out value="${msgcount}"/>条新</a>
				</div>
				<!-- 
				<div class="floatLeft" style="margin-right: 40px;">
					<a href="#">战队动态</a> : <a href="#" style="color:red;">1条新</a>
				</div>
				 -->
				<div class="floatLeft"   style="margin-right: 40px;">
					<a href="#">好友信息</a> : 
					<a href="../userFriend/friends.jsplayout.vi" <c:if test="${friendscount ne '0'}">style="color:red;"</c:if> ><c:out value="${friendscount}"/>条新</a>
				</div>
				
				<div class="floatLeft" >
					<a href="#">留言板</a> : 
					<a href="../player/myMessageBoard.jsplayout.vi" <c:if test="${MessageBoardCount ne '0'}">style="color:red;"</c:if> ><c:out value="${MessageBoardCount}"/>条新</a>
				</div>
			</div>
		</li>
		
	
		<!-- 电竞水平 -->
		
		<li>	
			<div id="index-page-player-life">
				<div class="index-page-user-relation-title">
					<div class="index-page-user-titlefont floatLeft">
						<b>电竞水平</b>
					</div>
					<div class="floatRight"><a href="../player/ladderRule.jsplayout.vi">积分规则</a></div>
				</div>
				
				<div id="user-page-cglevel-body">
					<c:import url="../player/eglevel.jsp.vi?username=${player.username}" />
				</div>
			</div>
		</li>
		
		
		<!-- 新鲜事 -->
		<li id="index-page-user-fesh-box">
			<div class="index-page-user-relation-title">
				<div class="index-page-user-titlefont floatLeft">
					<b>新鲜事</b>
				</div>
			</div>
			<div class="index-page-user-fesh-body-box">
				<c:if test="${empty freshmsgs}">
					<div align="center" class="empty-box">
						当你的朋友发表日志、照片,这里会有提示。
					</div>
				</c:if>
				<c:if test="${not empty freshmsgs}">
					 <table id="freshmsgs-top-box" cellspacing="0" cellpadding="3" bordercolor="#ffffff" border="0" width="100%">
					 	<c:forEach var="message" items="${freshmsgs}">
							<tr>
								<td width="5%" valign="top">
									<img src="../_css/images/fleshmsg/<c:out value='${message.msgType}'/>-feed.gif" width="16" height="16"/>
								</td>
								<td>
									<div class="index-page-user-freshtitle"><c:out value='${message.subject}' escapeXml="false" /></div>
									
										<c:if test="${message.msgType eq 'AVATOR'}">
											<div class="index-page-user-freshbody">
											<a href="<c:out value='../player/index.jsplayout.vi?username=${message.sender}'/>">	
												<img src="<c:out value='${message.receiverAvator}'/>" class="logo-small-image">
											</a>
											</div>
										</c:if>
										<c:if test="${message.msgType eq 'ALBUM'}">
											<div class="index-page-user-freshbody">
											<c:forEach var="photo" items="${message.photo}">
												<a href="<c:out value="../player/photo.jsplayout.vi?username=${message.sender}&id=${photo.id}"/>">
													<img class="photos-img" src="<c:out value="${photo.scalePhotoimg}" />" />
												</a>							
											</c:forEach>
											</div>						
										</c:if>
										<c:if test="${message.msgType eq 'ARTICLE'}">
											<div class="index-page-user-freshbody">
										 	&#34;<c:out value="${message.article}" escapeXml="false"/>&#34;
											</div>
										</c:if>
									
								</td>
								<td width="5%" valign="top"><a title="删除" class="dellink" href="javascript:" onclick="FreshMsg.del('<c:out value='${message.id}'/>');" >删</a></td>
							</tr>
						</c:forEach>
					</table>
					<br>
					<div class="floatRight" >
						<a href="javascript:" onclick="FreshMsg.delallmsg('<c:out value="${ACTOR.username}" />');">全部删除</a>
						<a href="../freshMsg/msgs.jsplayout.vi">所有新鲜事</a>
					</div>	
				</c:if>
			</div>
			
			
		</li>
	</ul>
	<ul id="index-page-relation-body">
		
		<!-- 最近来访-->
		<li id="index-page-user-battlefriend-box">
			<div class="index-page-user-relation-title">
				<div class="index-page-user-titlefont floatLeft">
					<b>最近来访</b>
				</div>
			</div>
			<ul class="index-page-user-relation-body">
				<c:forEach var='visitor' items='${visitors}'>
					<li>
						<div class="user-battlefriends-pic-box" title="<c:out value="${visitor.caller.dispName}"/>,访问时间<c:out value='${visitor.time}'/>">
							<a href="../player/index.jsplayout.vi?username=<c:out value="${visitor.callername}"/>">
						 		<img width="50px" height="50px" src="<c:out value='${visitor.avatorSmallUrl}'/>"/>
						  	</a>
						</div>
						<div class="user-battlefriends-name-box" title="<c:out value="${visitor.caller.dispName}"/>,访问时间<c:out value='${visitor.time}'/>">
							<a href="../player/index.jsplayout.vi?username=<c:out value="${visitor.callername}"/>">
								<c:out value="${visitor.caller.dispName}"/>
							</a>
						</div>
					</li>
				</c:forEach>
				<div style="clear:both"></div>
				<c:if test='${visitorcount>6}'>
				<div class="floatRight">
					<a href="../user/allvisitors.jsplayout.vi">所有访客>></a>
				</div>
				</c:if>
			</ul>
		</li>
		
		
		
		<!-- 我的战友 -->
		<li id="index-page-user-visitor-box">
			<div class="index-page-user-relation-title">
				<div class="index-page-user-titlefont floatLeft">
					<b>我的战友</b>
				</div>
			</div>
			<ul class="index-page-user-relation-body">
				<c:forEach var='friend' items='${battlefriends}'>
					<li>
						<div class="user-battlefriends-pic-box" title="<c:out value="${friend.friend.dispName}"/>,成为战友时间<c:out value='${friend.createDate}'/>">
							<a href="../player/index.jsplayout.vi?username=<c:out value="${friend.descname}"/>">
						 		<img width="50px" height="50px" src="<c:out value='${friend.friend.avatorSmallUrl}'/>"/>
						  	</a>
						</div>
						<div class="user-battlefriends-name-box" title="<c:out value="${friend.friend.dispName}"/>,成为战友时间<c:out value='${friend.createDate}'/>">
							<a href="../player/index.jsplayout.vi?username=<c:out value="${friend.descname}"/>">
								<c:out value="${friend.friend.dispName}"/>
							</a>
						</div>
					</li>
					</c:forEach>
				<div style="clear: both;"></div>
				<c:if test='${battlefriendcount>6}'>
				<div class="floatRight">
					<a href="../user/allbattlefriends.jsplayout.vi">所有战友>></a>
				</div>
				</c:if>
			</ul>
		
		</li>
		<!-- 可能结识的人 -->
		<li id="index-page-user-maybeknow-box">
			<div class="index-page-user-relation-title">
				<div class="index-page-user-titlefont floatLeft">
					<b>可能结识的人</b>
				</div>
			</div>
			<ul class="index-page-user-relation-body">
				<c:forEach var='knowuser' items='${knowuser}'>
					<li>
						<div class="user-battlefriends-pic-box">
							<a href="../player/index.jsplayout.vi?username=<c:out value="${knowuser.username}"/>">
						 		<img width="50px" height="50px" src="<c:out value='${knowuser.avatorSmallUrl}'/>"/>
						  	</a>
						</div>
						<div class="user-battlefriends-name-box" >
							<a href="../player/index.jsplayout.vi?username=<c:out value="${knowuser.username}"/>">
								<c:out value="${knowuser.dispName}"/>
							</a>
						</div>
					</li>
				</c:forEach>
				<div style="clear:both"></div>
				<div class="floatRight">
					<a href="../userFriend/find.jsplayout.vi">更多>></a>
				</div>
			</ul>
		</li>
	</ul>
	<div style="clear:both"></div>
</div>

<script type="text/javascript">

$(document).ready(function() {
	
});
</script>
			
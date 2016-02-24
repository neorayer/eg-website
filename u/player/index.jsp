<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>

</style>

<c:if test='${not empty player}'>
	
<div id="page-index-box">
	<ul id="index-page-player-center-body">
		<li id="index-page-player-head">
			<div id="index-page-player-username-box">
				<STRONG><c:out value='${player.dispName}'/></STRONG>
			</div>
			<c:if test='${not empty player.signature}'>
				<div id="index-page-signature"><c:out value='${player.signature}'/></div>
			</c:if>
			<div class="index-page-player-topline">&nbsp;</div>
			<ul id="index-page-head-label-box">
				<li class="playertitle-selected">
					<a href="">个人主页</a>
				</li>
				<!-- 
				<li>
					<a href="">参赛历史</a>
				</li>
				<li>
					<a href="">挑战记录</a>
				</li>
				 -->
				<li>
					<a href="../player/articles.jsplayout.vi?username=<c:out value='${player.username}'/>">战斗日记</a>
				</li>
				
				<li>
					<a href="../player/albums.jsplayout.vi?username=<c:out value='${player.username}'/>">个人相册</a>
				</li>
				
			</ul>
			<div style="clear:both"></div>
		</li>
		<!-- 主页可变身体部分 -->
		<li id="index-page-player-body">
			<!-- 职业生涯描述 -->
			<div id="index-page-player-life">
				<div class="player-page-portlet-title">
					<div class="player-page-portlet-titlefont floatLeft">职业生涯描述:</div>
				</div>
				<div id="player-page-life-body">
					<c:if test='${not empty userhis}'>
						<table width="100%" cellspacing="0" cellpadding="3" class="player-life-table">
							<c:forEach var='history' items='${userhis}'>
							<tr>
								<td width="30%" align="center"><c:out value='${history.createDate}'/></td>
								<td><c:out value='${history.event}'/></td>
							</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
				<c:if test='${userhistorycount>2}'>
					<div id="player-page-bottom">
						<div class="player-page-allhis floatRight"><a href="../player/allplayerhistory.jsplayout.vi?username=<c:out value='${player.username}'/>">所有历史>></a></div>
					</div>
				</c:if>
				
				<div id="player-page-bottom-line"></div>
			</div>
			 
			<!-- 电竞水平 -->
			
			<div id="index-page-player-life">
				<div class="player-page-portlet-title">
					<div class="player-page-portlet-titlefont floatLeft">电竞水平:</div>
					<div class="floatRight"><a href="../player/ladderRule.jsplayout.vi">积分规则</a></div>
				</div>
				<div id="player-page-cglevel-body">
					<c:import url="../player/eglevel.jsp.vi?username=${player.username}" />
				</div>
			</div>
			
			<!-- 电竞记录 -->
			<div id="index-page-player-life">
				<div class="player-page-portlet-title">
					<div class="player-page-portlet-titlefont floatLeft">电竞记录:</div>
				</div>
				<div id="player-page-cgreplay-body">
					<c:import url="../player/egreplay.jsp.vi?username=${player.username }" />
				</div>
			</div>
			
			<!-- 挑战-->
			<!-- 
 			<c:if test='${not empty self}'>
			 
				<div id="index-page-player-life">
				 	<form id="page-player-duel-form" action="../player/duel.add.json.do" method="post">
						<input name="useremail" type="hidden" value="<c:out value="${useremail}" />" />
						<div class="player-page-portlet-title">
							<div class="player-page-portlet-titlefont floatLeft">向你挑战</div>
						</div>
						<div id="player-page-dueltoyou-body">
							<div >
								 <div class="player-page-duel-left floatLeft">
									 <table cellspacing="0" cellpadding="0" id="duelnote-info-table">
										<tr>
											<td>挑战游戏:</td>
											<td><select name="gametype">
													<option selected>真三国无双</option>
												</select>
											</td>
											<td>预订时间:</td>
											<td><input type="text" name="createdatetime" id="createdatetime" class="Wdate" onfocus="new WdatePicker(this,'%Y-%M-%D %h:%m',true)" size="19"></td>
										</tr>
										<tr>
											<td>使用平台:</td>
											<td>
												<select name="platform" >
													<option selected>KO平台</option>
													<option>百友平台</option>
													<option>浩方平台</option>
													<option>vs平台</option>
													
												</select>
											</td>
											<td>平台大厅:</td>
											<td><input type="text" name="hall" id="hall" size="19"></td>
										</tr>
										<tr>
											<td>联系方式:</td>
											<td colspan="3">
												<input type="text" name="contact" id="contact" size="20">
											</td>
										</tr>
									</table>
								</div>
								<div class="player-page-duel-right floatRight">
									<div>挑战宣言:</div>
									<textarea name="content" id="content" tabindex="2" cols="20" rows="3" name="body" style="overflow: hidden; height: 30px;min-height:30px;height:auto!important"/></textarea>
								</div>
							</div>
							
						</div>
						<div id="player-page-duel-bottom" style="clear:both">
								<input type="submit" value="发表">
						</div>
					</form>	
				</div>
			</c:if>	
			  -->
			<!-- 留言板 -->
			<c:import url="../player/messageBoard.jsp.vi?username=${player.username}"></c:import>
		
			
		</li>
	</ul>
	<ul id="index-page-relation-body">
		
		<!-- 最近来访-->
		<li id="index-page-user-battlefriend-box">
			<div class="player-page-portlet-title">
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
					<a href="../player/allvisitors.jsplayout.vi?username=<c:out value='${username}'/>">所有访客>></a>
				</div>
				</c:if>
			</ul>
		</li>
		
		
		
		<!-- 我的战友 -->
		<li id="index-page-user-visitor-box">
			<div class="player-page-portlet-title">
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
					<a href="../player/allbattlefriends.jsplayout.vi?username=<c:out value='${username}'/>">所有战友>></a>
				</div>
				</c:if>
			</ul>
		
		</li>
		<li id="index-page-user-maybeknow-box"></li>
	</ul>
	<div style="clear:both"></div>
</div>

</c:if>

<script type="text/javascript">

$('form#page-player-duel-form').validate({
	errorClass: "errorClass",
	errorElement: "div",
 	rules: {
		content: {
			required: true,
			maxlength: 10000,
			minlength: 2
		}
	},
	messages: {
		content: {
			required: "挑战宣言不可以为空！",
			maxlength: "超出字符长度",
			minlength: "字符长度不足"
		} 
	},
 	submitHandler: function(form) {
 		var action = $(form).attr('action');
		action = action.replace(/jsp(layout)?/i,'json');
   		$(form).ajaxSubmit({
   			type: 'post',
			dataType: 'json',
   			success: function(data) {
   				console.debug(data.data.login);
   				if(data.data.login == 'yes') {
   					alert("请先登录后，再进行挑战！");
 					window.location = '../portal/index.jsplayout.vi';
					return;
   				}
   				if(data.res == 'yes') {
   					alert("发表成功！");
	  				$("#content").val('');
	  				$("#contact").val('');
	  				$("#hall").val('');
	  				$("#createdatetime").val('');
  				}
				//Player_MessageBoard_Page.load();
 			
   			}
   		});
   	}
});

$(document).ready(function() {
		<c:if test='${empty player}'>
			window.location = '../portal/index.jsplayout.vi';
		</c:if>	
});

</script>
			
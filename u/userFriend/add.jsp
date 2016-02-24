<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style>

</style>
 
<div id="page-addfriends-box">
	
	<c:if test="${userfriend.privacy eq 'ANYONE'}">
		<div id="page-addfriends-anyone-box">
			<div id="page-addfriends-anyone-body-box">
				<img src="<c:out value="${userfriend.avatorUrl}" />" class="logo-middle-image"/>
				<c:if test="${userfriend.usingtip eq true}">
					<h3><c:out value='${userfriend.dispName}'/>&#58;&#32;<c:out value='${userfriend.friendreqtip}'/></h3>
				</c:if>
				<c:if test="${userfriend.usingtip eq false}">
					<h3>是否确定要将对方加为你的好友?</h3>
				</c:if>
				
			</div>
			<div id="page-addfriends-anyone-tail-box" align="right">
				<form onsubmit="UserFriend.addbyanyone('<c:out value='${ACTOR.username}'/>',this);return false;" action="../UserFriend/userfriend.addbyanyone.json.do" id="page-addfriends-anyone-form" method="post" enctype="multipart/form-data">
					<input type="hidden" name="friendname" value="<c:out value='${userfriend.username}'/>" />
					<input type="submit" value="加为好友" name="submita" class="button"/>
			  	</form>
			</div>
		</div>	
	</c:if>
	<c:if test="${userfriend.privacy eq 'SOMEONE'}">
		<form action="../UserFriend/userfriend.addbysomeone.json.do" id="page-addfriends-someone-form" method="post" enctype="multipart/form-data">
			<div id="page-addfriends-someone-header-box">
				<h3>对方已设置好友权限，需要验证才能添加为好友</h3>
			</div>
			<div id="page-addfriends-someone-body-box">
				<input type="hidden" name="friendname" value="<c:out value='${userfriend.username}'/>" />
				<img src="<c:out value="${userfriend.avatorUrl}" />" class="logo-middle-image" />
				<div id="page-addfriendmessage-box">
					<c:if test="${userfriend.usingtip eq true}">
						<h3><c:out value='${userfriend.dispName}'/>&#58;&#32;<c:out value='${userfriend.friendreqtip}'/></h3>
					</c:if>
					<textarea id="page-addfriendmessage-text" name="subject"
					title="附加信息(选填，45字内)">附加信息(选填，45字内)</textarea>
				</div>
			</div>
			<div id="page-addfriends-someone-tail-box" align="right">
				<input type="submit" value="确定" name="submits" class="button" onclick="UserFriend.addbysomeone('<c:out value='${ACTOR.username}'/>');"/>
			</div>
		</form>
	</c:if>
	<c:if test="${userfriend.privacy eq 'NOBODY'}">
		<div id="page-addfriends-nobody-box">
			<h3>对方已设置好友权限，你暂时不能添加此人为好友。</h3>
			<br/>
		</div>
	</c:if>
	<div id="SOR_EXCEPTION" style="color: red"><c:out value="${REASON}" /></div>
</div>



<script type="text/javascript">
 	 
$(document).ready(function(){
 	$("#page-addfriendmessage-text")
	  .blur(function(){
	  	if($(this)[0].value=='')$(this)[0].value=$(this)[0].title;
  	})
	  .focus(function(){
	  	if($(this)[0].value==$(this)[0].title)$(this)[0].value='';
  	});
	
});
</script>

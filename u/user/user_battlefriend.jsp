<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<style type="text/css">
</style>

<div id="user-battlefriend-all-box">
	<div id="user-battlefriend-all-top-box">
		<h3>
			所有战友
		</h3>	
		<a id="user-battlefriend-top-back-link" href="javascript:BattleAll.back()">返回</a>
	</div>
	
	<ul id="user-battle-middle-box">
		<c:forEach var="friend" items="${battlefriends}">
			<li>
				<div class="user-battle-pic-box">
					<a href="../user/index.jsplayout.vi?username=<c:out value="${friend.descname}"/>">
					<img width="118" height="158" src="<c:out value='${friend.friend.avatorUrl }'/>" />
					</a>
				</div>
				<div class="user-battle-all-name-box">
					<a href="../user/index.jsplayout.vi?username=<c:out value="${friend.descname}"/>">
						<c:out value='${friend.friend.dispName}'/>
					</a>
				</div>
			</li>
		</c:forEach>
	</ul>
	<div id="battlefriend-all-pagebar" style="clear:both">
		<c:out value='${pageBar}' escapeXml="false"/>
	</div>
</div>

<script type="text/javascript">
	var BattleAll={
		back:function(){
			$(".user-space-left-box").load("../user/coll.jsp.vi?username=<c:out value='${username}'/>");
		}
	};
	$(document).ready(function(){
    	$("#battlefriend-all-pagebar a").click(function(){
			var url=$(this).attr("href");
			$(".user-space-left-box").load("../user/user_battlefriend.jsp.vi"+url);
		    return false;
		});
	});


</script>
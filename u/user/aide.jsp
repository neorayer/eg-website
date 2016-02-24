<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
#aide-menus-box li{
 	height:20px;
 	font-size:14px;
 	font-weight:bold;
 	text-align:center;
 	line-height:20px;
 	padding:10px 0px 10px 20px;
}
#aide-menus-box a{
	
	color:#97CE0F;
	display:block;
}

#aide-menus-box a.iselected{
	display:block;
	background-color:#F7F7F7;
	border-top:1px solid gray;
	border-left:1px solid gray;
	border-bottom:1px solid gray;
	padding-top:2px;
	
	
}

</style>

<div style="width: 140px;">
	<ul id="aide-menus-box">
		<!-- 
		<li>
			<a id="aide-user-index-link" href="<c:out value='../user/index.jsplayout.vi?username=${player.username }'/>">主页</a>
		</li>
		 -->
		 <!-- 
		<li>
			<a id="aide-user-certificate-link" href="../user/certificate.jsplayout.vi">电竞生涯</a>
		</li>
		 -->
		<li>
			<a id="aide-user-userRecords-link" href="../user/userRecords.jsplayout.vi">电竞记录</a>
		</li>
		<li>
			<a id="aide-user-myteam-link" href="../user/myteam.jsplayout.vi">我的战队</a>
		</li>
		<li>
			<a id="aide-userFriend-friends-link" href="../userFriend/friends.jsplayout.vi">朋友知己</a>
		</li>
		<li>
			<a id="aide-article-articles-link" href="../article/articles.jsplayout.vi">战斗日记</a>
		</li>
		<li>
			<a id="aide-album-albums-link" href="../album/albums.jsplayout.vi">个人相册</a>
		</li>
		
	</ul>
</div>

<script type="text/javascript">
$(document).ready(function() {
	/**
	*	页面selected的条件
	*	aide-${module}-${resouce}-link
	*/
	var url = window.location.href;
	var match = url.match(/\/(\w+)\/(\w+)\.jsplayout\.vi/i);
	if(match!=null)
	$('#aide-'+ match[1] + '-' + match[2] +'-link').addClass('iselected');
});

</script>
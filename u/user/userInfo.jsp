<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
/* userInfo page */
#user-image-box {
	float: left;	
	margin: 10px;
	border: 1px solid gray;
	padding: 2px;*padding-bottom: 0px;
	background-color: #eeeeee;
}

#user-intro-box {
	float: left;
	width: 55%;
}

#user-intro-box li {
	border-bottom: 1px solid #818181;
	vertical-align: top;
	padding-left: 10px;
	line-height:162%;
	margin: 3px;
}

#user-signature-box {
	clear: both;
	margin: 10px;
}
</style>

<div id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}"/></div>

<div id="user-image-box">
	<img id="user-photo-image" width="125" height="170" src="<c:out value='${user.avatorUrl}'/>" align="bottom" />
</div>

<ul id='user-intro-box'>
	<li>
		<span class="label">用户名：</span><c:out value='${user.username}'/>
	</li>
	<li>
		<span class="label">游戏名：</span><c:out value='${user.nickname}'/>
	</li>
	<li>
		<span class="label">真实姓名：</span><c:out value='${user.realname}'/>
	</li>
	<li>
		<span class="label">性别：</span><c:out value='${user.sex}'/>
	</li>
	<li>
		<span class="label">Email：</span><c:out value='${user.email}'/>
	</li>
	<li>
		<span class="label">电话：</span><c:out value='${user.telphone}'/>
	</li>				
	<li>
		<span class="label">手机号：</span><c:out value='${user.phone}'/>
	</li>			
	<li>
		<span class="label">来自：</span><c:out value='${user.province}'/>
	</li>
</ul>

<div id="user-signature-box">
	<span class="label">个人简介：</span>
	<c:out value='${user.signature}'/>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style type="text/css">
.user-certificate-items-box .selected {
	background-color: #ffffcc;
}

#page-user-certificate-title {
	float: left;
}




</style>


<DIV id="SOR_EXCEPTION" style="color:red" align="center"><c:out value="${REASON}" /></DIV>

<div id="user-install-box">
	<div class="user-install-title">
		<span id="page-user-install-title">
			<c:out value='${ACTOR.dispName}'/>的个人设置
		</span>
		<span id="page-user-install-personallsetting-box">
			<a href="../user/index.jsplayout.vi">返回</a>
		</span>
	</div>
	<div class="user-install-sign" style="clear: both;">
		<!-- 
		<span class="label">用户签名:</span>	
		<c:if test="${not empty ACTOR.signature}">
			<c:out value="${ACTOR.signature}" />
		</c:if>
		<c:if test="${empty ACTOR.signature}">
			小伙证书等级很高，历史很辉煌!
		</c:if>
		 -->
	</div> 
	<ul class="user-install-items-box">
		<li>
			<a href="javascript:Option.avator();"  >头像设置</a>
		</li>	
		<li>
			<a href="javascript:Option.userinfo();" >修改信息</a>
		</li>
		<li>
			<a href="javascript:Option.userpassword();" >修改密码</a>
		</li>
		<li>
			<a href="javascript:Option.userquestion();" >密码保护</a>
		</li>
	</ul>
	<div style="clear: both;"></div>
	<div id="user-install-main-box">
		<c:import url="../album/user_avator.jsp.vi"></c:import>
	</div>
</div>

<script type="text/javascript">
var  Option = {
	userinfo: function() {
		$("#user-install-main-box").load("userinfo.mod.jsp.vi");
	},
	userpassword:function(){
		$("#user-install-main-box").load("userpassword.mod.jsp.vi");
	},
	userquestion:function(){
		$("#user-install-main-box").load("userquestion.mod.jsp.vi");
	},
	cancel:function(){
		$("#user-install-main-box").load("index.jsplayout.vi");
	},
	avator:function(){
		$("#user-install-main-box").load("../album/user_avator.jsp.vi");
	} 
}
  
</script>
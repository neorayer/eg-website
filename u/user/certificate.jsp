<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>



<DIV id="SOR_EXCEPTION" style="color:red" align="center"><c:out value="${REASON}" /></DIV>

<div id="user-certificate-box">
	<div class="user-certi-title">
		<div class="user-certificate-title">
			<span id="page-user-certificate-title">
				<c:out value='${user.dispName}'/>的电子竞技生涯
			</span>
			<span id="page-user-personallsetting-box">
			
			</span>
		</div>
		
		<div class="user-certificate-sign" style="clear: both;">
			<!-- <span class="label">用户签名:</span> -->	
			<c:if test="${not empty player.signature}">
				<c:out value="${player.signature}" />
			</c:if>
			
			<c:if test="${empty player.signature}">
				小伙牛叉啊！
			</c:if>
		</div>
	</div>
	<div id="user-space-body">
		
		<ul class="user-certificate-items-box">
			<c:forEach var="type" items="${matchtypes}">
				<li id="type_<c:out value='${type.id}' />" >
					<a href="javascript:" onclick="<c:out value='UserMatchType.open("${type.id }")'/>">
						<c:out value="${type.name}" />
					</a>
				</li>
			</c:forEach>
		</ul>
		<div id="user-certificate-main-box">
		</div>
	</div>
</div>

<script type="text/javascript">
UserMatchType = {
	username: '<c:out value='${player.username }' />',
	open: function(matchtypeId) {
		$('#type_' + matchtypeId)
			.parent().children().removeClass('usercert-selected').end().end()
			.addClass('usercert-selected');
		$('#user-certificate-main-box').load('../user/user_project_certificate.jsp.vi?matchtypeid=' + matchtypeId + "&username=" +UserMatchType.username);
	},
	
	selectFirst: function() {
		var matchtypeId = $('.user-certificate-items-box :first-child').attr('id').replace('type_', '');
		UserMatchType.open(matchtypeId);
	}
}

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

$(document).ready(function() {
	UserMatchType.selectFirst();
});
</script>
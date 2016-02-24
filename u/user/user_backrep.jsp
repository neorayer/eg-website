<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


 
    <ul id="reputation-body-box">
		<li id="reputation-right-body-box">
			<form action="../user/dobackrep.json.do" method="post" id="repback-userform">
			
			<input type="hidden" name="referee" value="<c:out value='${referee.username}'/>"/>
				<input type="hidden" name="other" value="<c:out value='${other.id}'/>"/>
				<input type="hidden" name="id" value="<c:out value='${id}'/>"/>
			<input type="hidden" name="matchid" value="<c:out value='${match.id}'/>"/>
			<div class="reputation-right-body-title">信誉回评：请评价该场比赛其它选手:</div>
			<c:if test='${not empty referee}'>
			<ul class="reputatition-right-body-team">
				<li>裁判:<c:out value='${referee.dispName}'/>:</li>
				<li><input type="radio" name="refrep" value="1">好评&nbsp;<input type="radio" name="refrep" value="0">中评&nbsp;<input type="radio" name="refrep" value="-1">差评</li>
				<li class="reputation-comment-title">附加评语:</li>
				<li class="reputation-comment-textarea"><textarea name="refrepcomm" rows="2" cols="40"></textarea></li>
		    </ul>
			</c:if>
			<c:if test='${not empty other}'>
			<ul class="reputatition-right-body-team">
				<li>对手:<c:out value='${other.DispName}'/>:</li>
				<li><input type="radio" name="otherrep" value="1">好评&nbsp;<input type="radio" name="otherrep" value="0">中评&nbsp;<input type="radio" name="otherrep" value="-1">差评</li>
				<li class="reputation-comment-title">附加评语:</li>
				<li class="reputation-comment-textarea"><textarea name="otherrepcomm" rows="2" cols="40"></textarea></li>
				</ul>
			</c:if>
			<div><input type="button" class="button2" value="提交" id="repback-usersub-button"></div>
			
			</form>
		</li>
	</ul>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#repback-usersub-button").click(function(){
			$("#repback-userform").ajaxSubmit(function(){
				alert("感谢你的回评");
				$('.certificate-album').load('../user/user_reputation.jsp.vi?username=<c:out value="${reputation.username}"/>');
			});
		});
	});
</script>
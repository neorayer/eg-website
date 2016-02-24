<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div class="rep-body-box">
	<ul class="user-reputation-page-rtitle">
		<li class="user-reputation-page-left-title"><c:out value='${user.dispName}'/>的信誉:
			<c:forEach begin="1" end="${user.kulou}">
				<img src="../_css/images/portal/kulou.jpg" title="<c:out value='${user.reputation}'/>分">
			</c:forEach>
			<c:forEach begin="1" end="${user.signHeart}">
				<img src="../_css/images/portal/heart.jpg" title="<c:out value='${user.reputation}'/>分">
			</c:forEach>
			<c:forEach begin="1" end="${user.doubleHeart}">
				<img src="../_css/images/portal/doubleheart.jpg" title="<c:out value='${user.reputation}'/>分">
			</c:forEach>
			<c:forEach begin="1" end="${user.diamond}">
				<img src="../_css/images/portal/diamond.jpg" title="<c:out value='${user.reputation}'/>分">
			</c:forEach>
			<c:forEach begin="1" end="${user.crown}">
				<img src="../_css/images/portal/crown.jpg" title="<c:out value='${user.reputation}'/>分">
			</c:forEach>
			<c:forEach begin="1" end="${user.sword}">
				<img src="../_css/images/portal/sword.jpg" title="<c:out value='${user.reputation}'/>分">
			</c:forEach>
		</li>
		<li class="user-reputation-page-right-title">
			<img src="../_css/images/portal/good.jpg">好评&nbsp;<c:out value='${good}'/>&nbsp;
			<img src="../_css/images/portal/medium.jpg">中评&nbsp;<c:out value='${medium}'/>&nbsp;
			<img src="../_css/images/portal/bad.jpg">差评&nbsp;<c:out value='${bad}'/>&nbsp;
		</li>
	</ul>
	
	
	
    <div class="all-rep-box">
    	信誉评价:
    </div>
    
	<ul class="reputation-rep-all-box">
		<c:forEach var='reputation' items='${reputations}'>
		<li class="reputation-rep-box">
			<div>
				<span class="reputation-type">
					<c:choose>
						<c:when test="${reputation.type eq 'good'}"><img src="../_css/images/portal/good.jpg" title="好评"></c:when>
						<c:when test="${reputation.type eq 'medium'}"><img src="../_css/images/portal/medium.jpg" title="中评"></c:when>
						<c:when test="${reputation.type eq 'bad'}"><img src="../_css/images/portal/bad.jpg" title="差评"></c:when>
					</c:choose>
				
				</span>
				<span class="reputation-list-match"><c:out value='${reputation.bisai.title}'/></span>  
				
				 <c:if test='${!reputation.recalled&&reputation.needRecall&&reputation.username eq ACTOR.username}'>
				 	<span class="reputation-back">
				 		<a href="javascript:Reputation.backRep('<c:out value='${reputation.id}'/>')">回评</a>
				 	</span>
				 </c:if>
				 <span class="reputation-list-fromuser">来自
				 	<a href="../user/index.jsplayout.vi?username=<c:out value='${reputation.fromu.username}'/>"><c:out value='${reputation.fromu.dispName}'/></a>(<c:out value='${reputation.createDate}'/>)</span> 
			</div>
				
			
			<div class="reputation-leavemessage-box">&nbsp;
			<c:if test='${not empty reputation.comment}'>
			<span>留言:</span><span><c:out value='${reputation.comment}'/></span>
			</c:if>
			</div>
			
		</li>
		</c:forEach>
		<li class="reputation-pagebar">
			<c:out value='${pageBar}' escapeXml="false"/>
		</li>
	</ul>
	
</div>

<script type="text/javascript">
    var Reputation={
    	backRep:function(id){
    		$(".certificate-album").load("../user/user_backrep.jsp.vi?id="+id);
    	},
    };
    $(document).ready(function(){
    	$(".reputation-pagebar a").click(function(){
    		  var url = $(this).attr('href');
			$('.certificate-album').load("../user/user_reputation.jsp.vi"+url);		
			return false;
    	});
    })

//-->
</script>

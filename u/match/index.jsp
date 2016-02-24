<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--
#index-matchs-box{
	width:100%;
	height: 100%;
}

#page-match-header-box {
	height:30px;
	line-height:30px;
	background-color: #DAB606
}
#match-rank-box{
	font-size:14px;
	font-weight:bold;
	padding-left:20px;
	float: left;
}
#match-manage-box {
	float: right;
	margin-right: 20px;
}

#page-match-body-box {
	clear:both;
}

#match-items-box{
	padding-top:10px;
	border-bottom:1px solid gray;
	height:25px;
}
#match-items-box li{
	float:left;
	width:80px;
	height:25px;
	line-height:25px;
	display:inline;
	border-left:1px solid gray;
	border-top:1px solid gray;
	border-right:1px solid gray;
	margin-left:10px;
	text-align:center;
	color:black;
}
.ui-tabs-selected {
	background-color: #ffffff;
}

#match-index-body{
	clear:both;
	border-left:1px solid gray;
	border-right:1px solid gray;
	border-bottom:1px solid gray;
	padding:10px;
	background-color: #ffffff;
	height: 100%;
}

#match-map-box{
	padding:10px;
	height:500px;
	background:url(../_css/images/portal/china.jpg) center center no-repeat;
}
#match-body-box {
	clear:both;
	padding:10px;
	height:350px;
	
}
-->
</style>

<div id="index-matchs-box">
	<div id="page-match-header-box">
		<div id="match-rank-box">
			赛事分级:
			<a href="#" title="宗师级赛事">SSS</a> , 
			<a href="#" title="大师级赛事">SS</a> , 
			<a href="#" title="专家级赛事">S</a> , 
			<a href="#" title="高级赛事">A</a> , 
			<a href="#" title="中级赛事">B</a> , 
			<a href="#" title="初师级赛事">C</a> , 
			<a href="#" title="菜鸟级赛事">D</a>
		</div>
		<div id="match-manage-box">
			<c:if test="${ACTOR.username == 'eg_admin'}">
				<a href="../match/bisai_manage.jsplayout.vi" class="button">
					管理比赛
				</a>
			</c:if>
			&nbsp;
			<c:if test="${'eg_admin' eq ACTOR.username || (not empty ACTOR && ACTOR.profession eq 'Organizer')}">
				<a href="../match/bisai_create.jsplayout.vi" class="button">
					创建比赛
				</a>
			</c:if>
		</div>
	</div>
	
	<div id="page-match-body-box">
		<ul class="match-items-box" id="match-items-box">
			<c:forEach var="type" items="${matchtypes}">
			<li>
				<a class="tab" title="match-body-box" href="<c:out value='../match/bisaisByType.jsp.vi?matchtypeid=${type.id }' />">
					<c:out value="${type.name}" />
				</a>
			</li>
			</c:forEach>
		</ul>
		
		<div id="match-index-body">
			<div id="match-map-box"></div>
			<div id="match-body-box"></div>
		</div>
	</div>
	
</div>

<script type="text/javascript">

$(document).ready(function() {
	var $match_tabs = $("#match-items-box").tabs({
		show: function(event, ui) {
			$(ui.tab).blur();
		}
	});
	
});
</script>
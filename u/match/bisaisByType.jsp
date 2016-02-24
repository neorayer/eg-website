<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--

#page-matchs-box{
	padding-top:10px;
	border-bottom:1px solid gray;
	height:25px;
}
#page-matchs-box li{
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

#tabs-match-box {
	clear:both;
	border-left:1px solid gray;
	border-right:1px solid gray;
	border-bottom:1px solid gray;
	padding:10px;
	background-color: #ffffff;
	margin-bottom: 20px;
}
-->
</style>

<ul class="page-matchs-box" id="page-matchs-box">
<c:forEach var="warzone" items="${warzones}">
	<li>
		<a class="tab" title="tabs-match-box" href="<c:out value='../match/bisaisByTypeandZone.jsp.vi?matchtypeid=${matchtypeid }&warzoneid=${warzone.id }' />">
			<c:out value="${warzone.name}" />
		</a>
	</li>
</c:forEach>
</ul>

<div id="tabs-match-box">
</div>

<script type="text/javascript">
var Bisai_bisaisByType_Page = {
	matchtypeid: '<c:out value="${matchtypeid}" />'
};

$(document).ready(function() {
	var $bisai_tabs =$("#page-matchs-box").tabs({
		show: function(event, ui) {
			$(ui.tab).blur();
		}
	});
});

 
</script>

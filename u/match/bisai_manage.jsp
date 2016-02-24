<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<style>
<!--
#page-manage-box {

}
#manage-search-box {
	padding: 10px;
}
#manage-bisai-list {
	height: 400px;
}

#manage-bisai-list-table-box{
	width:100%;
	text-align: center;
}
 
#manage-bisai-list-table-box thead th {
	border:1px solid gray;
	background-color: #E6E6E6;
	height: 30px;
}

#manage-bisai-list-table-box tbody td {
	border:1px solid gray;
	height: 30px;
}
-->
</style>


<div id="page-manage-box">

	 <div id="page-manage-middle-box" class="box">
		<div class="tp-box"><div></div></div>
	 	<div class="hd-box">
	 		<h3 class="yellow">
	 			管理比赛
	 			
	 			<div class="hd-button-box">
		 			<a href="index.jsplayout.vi" class="button" style="line-height: 30px;margin-right: 30px;">【返回】</a>
	 			</div>
	 		</h3>
	 	</div>
	 	
	 	<div class="bd-box">
	 		<div id="manage-search-box">
				<form id="manage-search-form" action="../match/bisais.jsp.vi" method="post">
					<span class="label">类型：</span>
					<select name="matchtypeid" onchange="Bisai_manage_Page.searchBisais();">
						<option value="" selected="selected">所有类型</option>
						<c:forEach var="type" items="${matchtypes}">
							<option value="<c:out value='${type.id }'/>">
								<c:out value='${type.name }'/>
							</option>
						</c:forEach>
					</select>
					&nbsp;
					
					<span class="label">战区：</span>
					<select name="warzoneid" onchange="Bisai_manage_Page.searchBisais();">
						<option value="" selected="selected">所有战区</option>
						<c:forEach var="warzone" items="${warzones}">
							<option value="<c:out value='${warzone.id }'/>">
								<c:out value='${warzone.name }'/>
							</option>
						</c:forEach>
					</select>
					&nbsp;
					
					<span class="label">比赛名：</span>
					<input type="text" name="title" value="" />
					&nbsp;
					
					<input type="submit" class="button" value="查询" onclick="Bisai_manage_Page.searchBisais();return false;" />
				</form>
	 		</div>
		 	<div id="manage-bisai-list">
		 		<c:import url="../match/bisais.jsp.vi?matchtypeid=&warzoneid=&title=" />
			</div>
	 	</div>
	 	<div class="bt-box"><div></div></div>
	 </div>
</div>

<script type="text/javascript">
var Bisai_manage_Page = {
	del: function(id) {
		Bisai.del(id);
		var query = $('#manage-search-form').formSerialize();
		var url ='../match/bisais.jsp.vi?'+query;
		Bisai_manage_Page.load(url);
	},
	setFirst: function(id,viewed) {
		alert(id);
		Bisai.setFirst(id,viewed);
		var query = $('#manage-search-form').formSerialize();
		var url ='../match/bisais.jsp.vi?'+query;
		Bisai_manage_Page.load(url);
	},
	searchBisais: function() {
		var $form = $('#manage-search-form');
		var query = $form.formSerialize();
		var url ='../match/bisais.jsp.vi?'+query;

		Bisai_manage_Page.load(url);
	},
	load: function(url) {
		$("#manage-bisai-list").load(url);
	}
};


</script>
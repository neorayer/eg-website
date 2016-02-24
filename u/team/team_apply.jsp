<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
.box {
	width: 420px;
	float: left;
	margin-right: 10px;
}

.box .hd-box h3 {
	text-align: left;
}

#page-team-tail-box {
	clear: both;
	padding: 2px;
	width: 800px;
	text-align: right;
}

#team-image-box {
	float: left;	
	margin: 10px;
	border: 1px solid gray;
	padding: 2px;*padding-bottom: 0px;
	background-color: #eeeeee;
}

#page-team-intro-box {
	float: left;
}

#page-team-intro-box li {
	border-bottom: 1px solid #818181;
	vertical-align: top;
	padding-left: 10px;
	line-height:162%;
	margin: 3px;
	text-align: left;
}
#page-team-intro-box li.label {
	padding-left:10px;
	width: 100px;
}

#page-team-intro-memo-box {
	clear: both;
	background-color: #ccc;
	width: 96%;
}

#page-team-intro-memo-box td {
}



/*teamMembers*/
 #team-teammembers-list-box{
 	width: 100%;
 }
 
 #team-teammembers-list-box thead td {
 	color: #73A61D;
 	font-weight: bold;
 	border-bottom:1px solid #969696;
 	height: 30px;
 }
 
 #team-teammembers-list-box tbody .main{
 	height: 30px;
 	cursor: pointer;
 }
 
 #team-teammembers-list-box tbody .main-td {
	border-bottom:1px dotted #969696;
	border-top:1px dotted #969696;
 }
 
 /*detail*/
 #team-teammembers-list-box tbody .detail{
 	display: none;
 }
 
 #team-teammembers-list-detail-box {
 	border:1px solid gray;
 	margin: 2px;
	background: #eeeeee;
 }
 
  #team-teammembers-list-detail-box table {
	text-align: left;
	width: 90%;
	margin: 10px;
  }
  
  #team-teammembers-list-detail-box table td {
  	border-bottom: 1px dotted #ffffff;
  }
 
</style>


<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>

<div id="page-team-tail-box">
	<c:if test="${empty ACTOR }">
		<a href="../player/index.jsplayout.vi">【请您先登录】</a>
	</c:if>
	<c:if test="${not empty ACTOR }">
		<c:if test="${!ACTOR.hasTeam }">
			<a  href="#" onclick="Team_TeamApply_page.applyTo_team('<c:out value='${team.id}'/>');">
				【申请加入】
			</a>
		</c:if>
	</c:if>
	
	<a href="../team/index.jsplayout.vi">【返回】</a>
</div>



<div id="page-team-team-box" class="box">
	<div class="tp-box"><div></div></div>
	<div class="hd-box">
		<h3 class="blue">
			<c:out value='${team.teamName}'/>战队
		</h3>
	</div>
	<div class="bd-box" style="text-align: left;">
		<div id="team-image-box">
			<a href="../team/index.jsplayout.vi?teamid=<c:out value='${team.id}'/>">
				<img id="team-logo-image" class="team-logo-small-image" src="<c:out value='${team.logoPath}'/>">
			</a>
		</div>
		<ul id="page-team-intro-box">
			<li>
				<span class="label">战队名称：</span>
				<span>
					<c:out value='${team.teamName}'/>
				</span>
			</li>
			<li>
				<span class="label">队长：</span>
				<span>
					<c:out value='${team.creator }'/>
				</span>
			</li>
			<li>
				<span class="label">队员数：</span>
				<span>
					<c:out value='${team.memberCount }'/>
				</span>
			</li>
			<li>
				<span class="label">QQ群号：</span>
				<span>
					<c:out value='${team.qq}'/>
				</span>
			</li>
		</ul>
		<table id="page-team-intro-memo-box">
			<tr>
				<td class="label" align="center">战队简介：</td>
				<td class="content">
					<c:out value='${team.memo}' escapeXml="false"/>
				</td>
			</tr>
		</table>
	</div>
	<div class="bt-box" ><div></div></div>
</div>

<div id="page-team-members-box" class="box">
	<div class="tp-box"><div></div></div>
	<div class="hd-box">
		<h3 class="blue">
			队员列表
		</h3>
	</div>	
	<div class="bd-box">
		<table id="team-teammembers-list-box" width="96%">
			<thead>
				<tr>
					<td>用户名</td>
					<td>游戏名</td>
					<td>职位</td>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="member" items="${teammembers}">
				<c:set var="user" value="${member.user}" />
				<tr class="main">
					<td class="main-td">
						<span title="用户名"><c:out value='${member.username}' /></span>
					</td>
					<td class="main-td">
						<c:out value='${user.nickname}'/>
					</td>
					<td class="main-td">
						<c:out value='${member.role.name }'/>						
					</td>
				</tr>
				<tr class="detail">
					<td colspan="3">
						<div id="team-teammembers-list-detail-box">
							<table>
								<tr>
									<td>用户名:</td>
									<td><c:out value='${user.username}'/></td>
									<td>游戏名:</td>
									<td><c:out value='${user.nickname}'/></td>						
								</tr>
								<tr>
									<td>等级:</td>
									<td><c:out value='${user.level}'/></td>
									<td>积分:</td>
									<td><c:out value='${user.point}'/></td>			
								</tr>
								<tr>
									<td>注册时间:</td>
									<td><c:out value='${user.regDateTime}'/></td>
									<td>电话:</td>
									<td><c:out value='${user.phone}'/></td>				
								</tr>
								<tr>
									<td>Email:</td>
									<td><c:out value='${user.email}'/></td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				</c:forEach>
			</tbody>
			
		</table>
		<div id="page-team-pagebar-box"><c:out value="${pageBar}" escapeXml="false" /></div>
	</div>
	<div class="bt-box" ><div></div></div>
</div>



<script type="text/javascript">
<!--
Team_TeamApply_page = {
	applyTo_team: function(teamId) {
		TeamMember.apply(teamId, function() {
			window.location = "../player/index.jsplayout.vi";
		});
	}
};

(function($){	
	// 分页
	$('#page-team-pagebar-box a')
	.click(function() {
		var url = $(this).attr('href');
		url += "&id=" + <c:out value='${team.id}'/>;
		window.location=url;
		return false;
	});
	
})(jQuery);

var memberRows = $("#team-teammembers-list-box").find(".main");
//hover 效果
memberRows.mouseover(
	function() {
		$(this).addClass("hover");
	}
);

memberRows.mouseout(
	function() {
		$(this).removeClass("hover");
	}
);	
//显示成员详细信息
memberRows.click(
	function() {
		var tr = $(this).next();
		if (tr.css("display") == "none") {
			tr.show();
			tr.click(function() {
				tr.hide();
			});
		}
		else
			tr.hide();
	}
);

//-->
</script>	

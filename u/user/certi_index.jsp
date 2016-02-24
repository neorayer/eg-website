<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!-- 证件首页 -->

<div id="certi-index-top-box">
	<div id="certi-index-left-box">
		<div class="certi-index-left-album"><img src="<c:out value='${user.avatorUrl}'/>"/></div>
		<c:if test="${user.username eq ACTOR.username}">
		<div>&nbsp;<a href="../user/user_avator.jsplayout.vi" class="mod-user-link">修改头像</a>&nbsp;<a href="../user/userinfo.mod.jsplayout.vi" class="mod-user-link">修改信息</a></div>
		</c:if>
	</div>
	
	<div id="certi-index-right-box">
		<table width="100%" class="certi-index-right-table" align="center">
			<tr >
				<td width="28%" class="certi-index-right-table-title">姓名：</td>
				<td width="30%" class="certi-index-right-table-info"><c:out value='${user.dispName}'/></td>
				<td width="25%" class="certi-index-right-table-title">职业：</td>
				<td class="certi-index-right-table-info"><c:out value='${user.strProfession}'/></td>
			</tr>
			<tr>
				<td class="certi-index-right-table-title">出生年月：</td>
				<td class="certi-index-right-table-info1" colspan="3"><c:out value='${user.birthday}'/></td>
			</tr>
			<tr>
				<td class="certi-index-right-table-title">参赛项目：</td>
				<td class="certi-index-right-table-info1" colspan="3"><c:out value='${userProInfo.project.name}'/></td>
			</tr>
			
			<tr>
				<td class="certi-index-right-table-title">电竞等级：</td>
				<td class="certi-index-right-table-info1" colspan="3">未开放</td>
			</tr>
		</table>
	</div>
</div>
<div id="certi-index-bottom-box">
	<table width="100%" style="margin-top: 10px;">
		<tr>
			<td class="certi-index-bottom-table-title" width="20%">个人信誉：</td>
			<td class="certi-index-bottom-table-info" width="30%">
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
			</td>
			<td class="certi-index-bottom-table-title" width="20%">所属战区：</td>
			<td class="certi-index-bottom-table-info" width="30%"><c:out value='${user.warZone.name}'/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		
	</table>
	<div class="certi-index-number-box">个人参赛证编号：<c:out value='${userProInfo.certiId}' default="无"/></div>
</div>


<script type="text/javascript">

</script>
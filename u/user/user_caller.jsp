<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<style type="text/css">


</style>
<ul id="user-caller-list">
	<c:forEach var="caller" items="${callers}">
		<li>
			<div class="user-caller-pic-box" title="<c:out value="${caller.caller.dispName}"/>,到访时间<c:out value='${caller.time}'/>"><a href="index.jsplayout.vi?username=<c:out value="${caller.callername}"/>">
			 		<img width="46px" height="45px" src="<c:out value='${caller.avatorSmallUrl}'/>"/>
				 </a>
			</div>
			<div class="user-caller-name-box" title="<c:out value="${caller.caller.dispName}"/>,到访时间<c:out value='${caller.time}'/>"><a href="index.jsplayout.vi?username=<c:out value="${caller.callername}"/>">
					<c:out value="${caller.caller.dispName}"/>
				</a>
			</div>
		</li>
	</c:forEach>
	<div style="clear: both;"></div>
</ul>

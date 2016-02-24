<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<img id="newsImg" src="../team/bbs_attach.img.vi?id=<c:out value="${attach.id }" />" />

<script language="javascript">
window.parent.insImg(document.getElementById("newsImg").src);
</script>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	session.invalidate();
	out
			.println("<script language=JavaScript>window.location.target='_top';window.location.href=\"u/user/index.jsplayout.vi\"</script>");
%>

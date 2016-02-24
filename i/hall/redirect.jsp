<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="java.util.*" import="org.json.*"
	import="com.skymiracle.json.JSONTools"
%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	//String TMP_HOST = "http://60.191.220.102/";
	String HALL_HOST = "http://hall.ko10000.com";
	String PORTAL_HOST = "http://www.ko10000.com";
	String contextPath = "/GamePortal2";
	
	String version = request.getParameter("version");
	
	// Output paraments
	JSONObject jo = new JSONObject();
	jo.put("url_login", HALL_HOST + "/GameHall2/u/hallXul/login.json.do?");
	jo.put("url_ureg", PORTAL_HOST + contextPath + "/pg/vi/user/reg.jsp");
	jo.put("url_passback", PORTAL_HOST + contextPath +  "/pg/vi/user/finduser.jsp?");
	jo.put("url_forget_password", PORTAL_HOST + contextPath +  "/pg/vi/user/finduser.jsp?");
	jo.put("url_check_version", HALL_HOST + "/GameHall2/u/hallXul/checkVersion.json.do");
	
	out.println(JSONTools.getResJSONString("1", jo));
%>

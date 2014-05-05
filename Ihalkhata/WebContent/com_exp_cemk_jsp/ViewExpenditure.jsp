<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Monthly Expenditure</title>

<%@ page import="com.exp.cemk.util.SessionUtil"%>
<%@ page import="domainmodel.Person"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>
</head>
<body>

	<%
Person up=(Person)session.getAttribute(SessionUtil.LOGGED_IN_USER);
if(up==null){
	String loginRedirect=request.getContextPath()+ "/com_exp_cemk_jsp/login.jsp";
	response.sendRedirect(loginRedirect);
}
else{
%>

	<!-- Include Ext and app-specific scripts: -->
	<jsp:include page="IncludeFile.jsp" />
	<jsp:include page="Menu.jsp" />
	<script type="text/javascript"	src="../com_exp_cemk_extjs_js/Ext.ux.ProgressBarPager.js"></script>
	
	<!-- Include Graph here: -->
	
	<jsp:include page="IncludeGraph.jsp" />
	<jsp:include page="IncludeLovCombo.jsp" />
	<script type="text/javascript" src="../com_exp_cemk_js/utility.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/ajaxFunction.js"></script>
	<script type="text/javascript"	src="../com_exp_cemk_js/viewExpenditureDetails.js"></script>

	<div id="viewExpenditurecontent" style="margin-left: 5%;margin-right: 5%;"></div>
	<%}%>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense Graph</title>


<%@ page import="com.exp.cemk.util.SessionUtil"%>
<%@ page import="domainmodel.Person"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>


<%
Person up=(Person)session.getAttribute(SessionUtil.LOGGED_IN_USER);
if(up==null){
	String loginRedirect=request.getContextPath()+ "/com_exp_cemk_jsp/login.jsp";
	response.sendRedirect(loginRedirect);
}else{
%>
<script type="text/javascript">

var data="userId="+"<%=request.getParameter("userId")%>"+
	"&startMonth="+"<%=request.getParameter("startMonth")%>"+
	"&endMonth="+"<%=request.getParameter("endMonth")%>"+
	"&finalYear="+"<%=request.getParameter("finalYear")%>"+
	"&endYear="+"<%=request.getParameter("endYear")%>";
var user="<%=request.getParameter("userName")%>";
</script>
<!-- Include Ext and app-specific scripts: -->
<link rel="shortcut icon" href="../com_exp_cemk_css/images/favicon.ico">
<jsp:include page="IncludeFile.jsp" />
<script type="text/javascript"	src="../com_exp_cemk_extjs_js/graph/jquery-1.6.1.js"></script>
<jsp:include page="IncludeGraph.jsp" />
<script type="text/javascript" src="../com_exp_cemk_extjs_js/graph/gray.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/ajaxFunction.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/Chart.js"></script>

<%}%>
</head>
<body bgcolor="#D7E3FC">
	<div id="container" style="width: 90%; height: 600px; margin: 0 auto"></div>
</body>
</html>
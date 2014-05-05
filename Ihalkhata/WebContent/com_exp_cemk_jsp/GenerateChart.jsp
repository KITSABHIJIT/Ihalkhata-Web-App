<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Expense</title>

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
	<jsp:include page="IncludeFile.jsp" />
	<jsp:include page="Menu.jsp" />
	<!-- Include Ext and app-specific scripts: -->
	
	<script type="text/javascript" src="../com_exp_cemk_extjs_js/Ext.ux.Image.js"></script>
	
	<jsp:include page="IncludeLovCombo.jsp" />
	
	<!-- Include page specific scripts: -->
	<script type="text/javascript" src="../com_exp_cemk_js/utility.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/ajaxFunction.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/generateChart.js"></script>

	
	<div id="pdfGenerateDatacontent" style="margin-left: 5%;margin-right: 5%;"></div>
	


	<input type="hidden" id="userId" name="userId" />
	<input type="hidden" id="start_Month" name="start_Month" />
	<input type="hidden" id="end_Month" name="end_Month" />
	<input type="hidden" id="Final_Year" name="Final_Year" />
	<input type="hidden" id="END_Year" name="END_Year" />
<%}%>

</body>
</html>

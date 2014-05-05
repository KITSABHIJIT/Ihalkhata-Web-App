<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<%@ page import="com.exp.cemk.util.CommonUtil"%>
<%
String uriPrefix=CommonUtil.getURIPrefix(request);
%>
<script type="text/javascript">var uriPrefix='<%=uriPrefix.trim()%>'</script>

</head>
<body>
<%@ page import="com.exp.cemk.util.SessionUtil"%>
<%@ page import="domainmodel.Person"%>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>

<%
Person up=(Person)session.getAttribute(SessionUtil.LOGGED_IN_USER);
	String logontime="";
	String userName="";
	String loginId="";
	String company="";
	String email="";
	String phone="";
	int grpId=0;
	if(up==null){
			//getServletContext().getRequestDispatcher("/com_exp_cemk_jsp/login.jsp").forward(request, response);
		String loginRedirect=request.getContextPath()+ "/com_exp_cemk_jsp/login.jsp";
		response.sendRedirect(loginRedirect);
	}
	else{

		
		userName=up.getUserName();
		loginId=up.getUserName();
		logontime=up.getLastLoginTime();
		company=up.getCompany();
		email=up.getEmail();
		phone=up.getPhone();
		grpId=up.getGroupId();
	

%>
	<jsp:include page="IncludeFile.jsp" />
	<jsp:include page="Menu.jsp" />
	<!-- Include Ext and app-specific scripts: -->
	
	<link rel="shortcut icon" href="../com_exp_cemk_css/images/favicon.ico"	type="image/x-icon" />
	<script type="text/javascript"	src="../com_exp_cemk_extjs_js/Ext.ux.Image.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/ExtVTypes.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_extjs_js/Ext.ux.ProgressBarPager.js"></script>
	
	
	<!-- Include Ext stylesheets here: -->
	
	<link rel="stylesheet" type="text/css"	href="../com_exp_cemk_css/fileuploadfield.css" />
	<script type="text/javascript"	src="../com_exp_cemk_extjs_js/FileUploadField.js"></script>
	
	<jsp:include page="IncludeGraph.jsp" />
	<script type="text/javascript" src="../com_exp_cemk_extjs_js/graph/gray.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/ajaxFunction.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/registration.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/forgetPassword.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/firstLogin.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/editProfile.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/home.js"></script>
	
	<div id="viewportContent" style="margin-left: 5%;margin-right: 5%;"></div>
	<!-- use class="x-hide-display" to prevent a brief flicker of the content -->
	
	<div id="details" class="x-hide-display">
		<table align="center">
			<tr>
				<td align="center"><img
					src="<%=uriPrefix%>/ImageServlet?loginId=<%=loginId%>"
					alt="Welcome <%=userName%>" height="150" width="150">
				</td>
			</tr>
			<tr>
				<td><b><%=userName%></b>
				</td>
			</tr>
			<%if(company!=null  && !company.trim().equals("")  && !company.equals("null")){%>
			<tr>
				<td><b><%=company%></b>
				</td>
			</tr>
			<%} %>
			<tr>
				<td><b>Email: <%=email%></b>
				</td>
			</tr>
			<%if(phone!=null && !phone.trim().equals("") && !phone.equals("null")){%>
			<tr>
				<td><b>Phone No: <%=phone%></b>
				</td>
			</tr>
			<%} %>
		</table>
	</div>
<%}%>
</body>
</html>
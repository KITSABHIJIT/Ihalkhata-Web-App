<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ihalkhata</title>
<LINK HREF="../com_exp_cemk_css/loginStyle.css" rel="stylesheet" TYPE="text/css">
<!-- Include Ext and app-specific scripts: -->


<link rel="shortcut icon" href="../com_exp_cemk_css/images/favicon.ico" type="image/x-icon" />
<jsp:include page="../com_exp_cemk_jsp/IncludeFile.jsp" />
<script type="text/javascript" src="../com_exp_cemk_extjs_js/Ext.ux.Image.js"></script>

 <%@ page import="com.exp.cemk.util.CommonUtil" %>
 <%
 String uriPrefix=CommonUtil.getURIPrefix(request);
%>
<script type="text/javascript">
var uriPrefix="<%=uriPrefix%>";
</script>
<script type="text/javascript" src="../com_exp_cemk_js/ExtVTypes.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/ajaxFunction.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/registration.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/login.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/forgetPassword.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/firstLogin.js"></script>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>
</head>
<body leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" bgcolor="#000000" background="../com_exp_cemk_css/images/back_all.jpg">
<center><table cellpadding="0" cellspacing="0" height="100%" border="0">
	
	<tr>
		<td height="50%" style="vertical-align: bottom;"><p style="font-size:200px;">ihalkhata</p></td>
	</tr>
	<tr>
		<td height="10%" style="vertical-align: top; text-align: center;">
		<a href="javascript:loginPopup()" title="Start ihalkhata"><img alt="Start ihalkhata" src="../com_exp_cemk_css/images/start1.png"></a>
		</td>
	</tr>
	<tr>
		<td height="40%" style="vertical-align: bottom;">
			<div align="center"><p>Copyright &copy; 2013. Design by <a href="" title="ihalkhata Web Developers">ihalkhata Web Developers</a></p></div>
		</td>
	</tr>
</table></center>
</body>
</html>
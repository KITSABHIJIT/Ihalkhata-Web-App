<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entry Page</title>

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
	
	<script type="text/javascript"	src="../com_exp_cemk_extjs_js/Ext.ux.Image.js"></script>
	
	<!-- Include Ext stylesheets here: -->

	<jsp:include page="IncludeLovCombo.jsp" />
	<!-- Include page specific scripts: -->
	<script type="text/javascript" src="../com_exp_cemk_js/utility.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/ExtVTypes.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/calculator.js"></script>
	<link rel="stylesheet" type="text/css"	href="../com_exp_cemk_css/calculator.css" />
	<script type="text/javascript" src="../com_exp_cemk_js/ajaxFunction.js"></script>
	<script type="text/javascript"	src="../com_exp_cemk_js/brokerAutoFill.js"></script>
	<script type="text/javascript" src="../com_exp_cemk_js/entry.js"></script>






	<!--  <script type="text/javascript">
    var files = [];
    
    files.push('../com_exp_cemk_js/entry.js');
    // Appends unique time to each filename and writes to browser
    var refs = [];
    for (var i = 0; i < files.length; i++) {
       // files[i] += "?" + new Date().getTime();
        refs[i] = document.createElement('script');
        refs[i].type = 'text/javascript';
        refs[i].src = files[i];
        document.getElementsByTagName('head')[0].appendChild(refs[i]);          
    }
</script>-->

	<div id="viewportContent" style="margin-left: 5%;margin-right: 5%;">
	<div style="float: left; width: 36%" align="right">
		<form onsubmit="return false;">
			<div id="calinfoout">
				<div class="calinfoinner">
					<input type="text" name="input" size="16" id="calInfoOutPut"
						onclick="this.focus()" maxlength="16" value="0" readonly>
					<hr>
					<input type="button" value="sin" onclick="r('sin')"
						class="calinfofunc"><input type="button" value="cos"
						onclick="r('cos')" class="calinfofunc"><input
						type="button" value="tan" onclick="r('tan')" class="calinfofunc"><input
						type="button" value="ln" onclick="r('ln')" class="calinfofunc"><input
						type="button" value="log" onclick="r('log')" class="calinfofunc"><input
						type="button" value="asin" onclick="r('asin')" class="calinfofunc"><input
						type="button" value="acos" onclick="r('acos')" class="calinfofunc"><input
						type="button" value="atan" onclick="r('atan')" class="calinfofunc"><input
						type="button" value="e^x" onclick="r('ex')" class="calinfofunc"><input
						type="button" value="10^x" onclick="r('10x')" class="calinfofunc"><input
						type="button" value="x^y" onclick="r('pow')" class="calinfofunc"><input
						type="button" value="x^3" onclick="r('x3')" class="calinfofunc"><input
						type="button" value="x^2" onclick="r('x2')" class="calinfofunc"><input
						type="button" value="e" onclick="r('e')" class="calinfofunc"><input
						type="button" value="&#960;" onclick="r('pi')" class="calinfofunc"><input
						type="button" value="y&#8730;x" onclick="r('apow')"
						class="calinfofunc"><input type="button" value="3&#8730;x"
						onclick="r('3x')" class="calinfofunc"><input type="button"
						value="&#8730;x" onclick="r('sqrt')" class="calinfofunc"><input
						type="button" value="x&#8596;y" onclick="r('swap')"
						class="calinfofunc"><input type="button" value="1/x"
						onclick="r('1/x')" class="calinfofunc"><input
						type="button" value="(" onclick="r('(')" class="calinfofunc"><input
						type="button" value=")" onclick="r(')')" class="calinfofunc"><input
						type="button" value="n!" onclick="r('n!')" class="calinfofunc"><input
						type="button" value="&#8240;" onclick="r('qc')"
						class="calinfofunc"><input type="button" value="%"
						onclick="r('pc')" class="calinfofunc">
					<hr>
					<input type="button" value="1" onclick="r(1)" class="calinfonm"><input
						type="button" value="2" onclick="r(2)" class="calinfonm"><input
						type="button" value="3" onclick="r(3)" class="calinfonm"><input
						type="button" value="+" onclick="r('+')" class="calinfoop"><input
						type="button" value="MS" onclick="r('MS')" class="calinfoop"><br />
					<input type="button" value="4" onclick="r(4)" class="calinfonm"><input
						type="button" value="5" onclick="r(5)" class="calinfonm"><input
						type="button" value="6" onclick="r(6)" class="calinfonm"><input
						type="button" value="-" onclick="r('-')" class="calinfoop"><input
						type="button" value="M+" onclick="r('M+')" class="calinfoop"><input
						type="button" value="7" onclick="r(7)" class="calinfonm"><input
						type="button" value="8" onclick="r(8)" class="calinfonm"><input
						type="button" value="9" onclick="r(9)" class="calinfonm"><input
						type="button" value="*" onclick="r('*')" class="calinfoop"><input
						type="button" value="M-" onclick="r('M-')" class="calinfoop"><input
						type="button" value="0" onclick="r(0)" class="calinfonm"><input
						type="button" value="." onclick="r('.')" class="calinfonm"><input
						type="button" value="EXP" onclick="r('EXP')" class="calinfoop"><input
						type="button" value="/" onclick="r('/')" class="calinfoop"><input
						type="button" value="MR" onclick="r('MR')" class="calinfoop"><br />
					<input type="button" value="+/-" onclick="r('+/-')"
						class="calinfoop"><input type="button" value="RND"
						onclick="r('RND')" class="calinfoop"><input type="button"
						value="C" onclick="r('C')" class="calinfoeq"><input
						type="button" value="=" onclick="r('=')" class="calinfoeq"><input
						type="button" value="MC" onclick="r('MC')" class="calinfoop"><br />
				</div>
			</div>
		</form>
	</div>
	<div style="float: left; width: 64%" id="entryDatacontent" align="left">
	</div>
	</div>
	<div id="info" class="balloonstyle"style="background-color: lightyellow">Click to Add items to Personal List
	</div>

	<input type="hidden" id="dates" name="dates" />
	<input type="hidden" id="itemsList" name="itemsList" />
	<input type="hidden" id="totalExpense" name="totalExpense" />
	<input type="hidden" id="shareholderCount" name="shareholderCount" />
	<input type="hidden" id="paidBy" name="paidBy" />
	<input type="hidden" id="shareholders" name="shareholders" />
	<input type="hidden" id="perHead" name="perHead" />
	<input type="hidden" id="description" name="description" />
<%}%>

</body>
</html>

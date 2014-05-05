<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- ------------------------------------
	Internet versions: 
	-------------------------------------
<script type="text/javascript"	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
-->
<!-- ------------------------------------
	Local versions: 
	--------------------------------------->
<script type="text/javascript"
	src="../com_exp_cemk_extjs_js/graph/jquery-1.6.1.js"></script>

<!-- Include Feedback Form specific  scripts @ css: -->
<link rel="stylesheet" href="../com_exp_cemk_extjs_js/feedback/core.css">
<script src="../com_exp_cemk_extjs_js/feedback/core.js"></script>
<!-- Include menu specific  scripts @ css: -->

<script type="text/javascript"
	src="../com_exp_cemk_doclet/js/fisheye-iutil.js"></script>
<script type="text/javascript"
	src="../com_exp_cemk_doclet/js/dock-example1.js"></script>
<link href="../com_exp_cemk_doclet/style.css" rel="stylesheet"
	type="text/css" />
<link rel="shortcut icon" href="../com_exp_cemk_css/images/favicon.ico">
<!-- For file upload -->
<script type="text/javascript"
	src="../com_exp_cemk_extjs_js/FileUploadField.js"></script>
<link href="../com_exp_cemk_css/fileuploadfield.css" rel="stylesheet"
	type="text/css" />

<%@ page import="com.exp.cemk.util.CommonUtil"%>
<%
	String uriPrefix = CommonUtil.getURIPrefix(request);
%>
<script type="text/javascript">var uriPrefix='<%=uriPrefix%>'
</script>
<script type="text/javascript" src="../com_exp_cemk_js/ExtVTypes.js"></script>
<link href="../com_exp_cemk_css/style.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../com_exp_cemk_css/loading.css" />


<script type="text/javascript" src="../com_exp_cemk_js/ajaxFunction.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/registration.js"></script>

<script type="text/javascript" src="../com_exp_cemk_js/editProfile.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/selectGroup.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/utility.js"></script>
<script type="text/javascript"
	src="../com_exp_cemk_js/forgetPassword.js"></script>
<script type="text/javascript"
	src="../com_exp_cemk_js/changePassword.js"></script>
<script type="text/javascript" src="../com_exp_cemk_js/firstLogin.js"></script>


<%@ page import="com.exp.cemk.util.SessionUtil"%>
<%@ page import="domainmodel.Person"%>


<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevent caching at the proxy server
%>

<%
	Person up = (Person) session
			.getAttribute(SessionUtil.LOGGED_IN_USER);
	String logontime = "";
	String userName = "";
	String loginId = "";
	String company = "";
	String email = "";
	String phone = "";
	int grpId = 0;
	boolean isCompanyPresent = true;
	boolean isPhonePresent = true;
	if (up == null) {
		String loginRedirect = request.getContextPath()
				+ "/com_exp_cemk_jsp/login.jsp";
		response.sendRedirect(loginRedirect);
	} else {
		userName = up.getUserName();
		loginId = up.getUserId();
		logontime = up.getLastLoginTime();
		company = up.getCompany();
		email = up.getEmail();
		phone = up.getPhone();
		grpId = up.getGroupId();
		isCompanyPresent = !CommonUtil.isNullorEmpty(company);
		isPhonePresent = !CommonUtil.isNullorEmpty(phone);
	}
%>
<!--	BALLOON POP UP -->
<link rel="stylesheet" type="text/css"
	href="../com_exp_cemk_balloon_popup/balloontip.css" />
<script type="text/javascript"
	src="../com_exp_cemk_balloon_popup/balloontip.js"></script>
<!--  BALLOON POP UP END-->
</head>
<body>
	<!-- Pretty loading mask -->
	<div id="loading-mask"></div>
	<div id="loading">
		<div class="loading-indicator">
			<br />Ihalkhata Loading...
		</div>
	</div>
	<div class="dock" id="dock">
		<div class="dock-container">
			<a class="dock-item" href="Home.jsp" rel="balloon"><img
				src="<%=uriPrefix%>/ImageServlet?loginId=<%=loginId%>" /><span><%=userName%></span>
			</a> <a class="dock-item" href="EntryPage.jsp"><img
				src="../com_exp_cemk_doclet/images/enterData.png" alt="Enter Data" /><span>Enter
					Data</span> </a> <a class="dock-item" href="ViewPage.jsp"><img
				src="../com_exp_cemk_doclet/images/expenseRecord.png"
				alt="Expense Records" /><span>Expense Records</span> </a> <a
				class="dock-item" href="ViewPayments.jsp"><img
				src="../com_exp_cemk_doclet/images/payments.png"
				alt="Payment Details" /><span>Payment Details</span> </a> <a
				class="dock-item" href="ViewExpenditure.jsp"><img
				src="../com_exp_cemk_doclet/images/Colourful_Chart.png"
				alt="Expense Details" /><span>Expense Details</span> </a> <a
				class="dock-item" href="ViewIndividuals.jsp"><img
				src="../com_exp_cemk_doclet/images/personalExpense.png"
				alt="Individual Expense" /><span>Individual Expense</span> </a> <a
				class="dock-item" href="GeneratePdf.jsp"><img
				src="../com_exp_cemk_doclet/images/pdf.png" alt="Expense Details" /><span>Generate
					Pdf</span> </a> <a class="dock-item" href="GenerateChart.jsp"><img
				src="../com_exp_cemk_doclet/images/lineChart.png"
				alt="Expense Details" /><span>Track Expense</span> </a> <a
				class="dock-item" href="SendMail.jsp"><img
				src="../com_exp_cemk_doclet/images/email_icon.png" alt="Send Mail" /><span>Send
					Mail</span> </a> <a class="dock-item"
				href="javascript:registerPopup('<%=grpId%>')"><img
				src="../com_exp_cemk_doclet/images/addUser.png" alt="Add User" /><span>Add
					User</span> </a> <a class="dock-item"
				href="javascript:editProfilePopup('<%=loginId%>','<%=userName%>','<%=company%>','<%=email%>','<%=phone%>')"><img
				src="../com_exp_cemk_doclet/images/editProfile.png"
				alt="Edit Profile" /><span>Edit Profile</span> </a> <a
				class="dock-item" href="javascript:changePasswordPopup()"><img
				src="../com_exp_cemk_doclet/images/changePwd.png"
				alt="Change Password" /><span>Change Password</span> </a> <a
				class="dock-item" href="javascript:selectGroupPopup()"><img
				src="../com_exp_cemk_doclet/images/group.png" alt="Change Group" /><span>Change
					Group</span> </a><a class="dock-item" href="<%=uriPrefix%>/LogoffServlet"><img
				src="../com_exp_cemk_doclet/images/logout.png" alt="Logout" /><span>Logout</span>
			</a>

		</div>
	</div>

	<table border="0" id="balloon" class="balloonstyle"
		style="background-color: lightyellow; -moz-border-radius: 15px; -webkit-border-radius: 15px; -khtml-border-radius: 15px; border-radius: 15px">
		<tr>
			<td><img src="<%=uriPrefix%>/ImageServlet?loginId=<%=loginId%>"
				alt="Welcome <%=userName%>" height="60" width="60" /></td>
			<td>
				<table>
					<tr>
						<td><b>&nbsp;<%=userName%></b></td>
					</tr>
					<%
						if (isCompanyPresent) {
					%>
					<tr>
						<td><b>&nbsp;Company:- <%=company%></b></td>
					</tr>
					<%
						}
					%>
					<tr>
						<td><b>&nbsp;<%=email%></b></td>
					</tr>
					<%
						if (isPhonePresent) {
					%>
					<tr>
						<td><b>&nbsp;Phone:- <%=phone%></b></td>
					</tr>
					<%
						}
					%>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><b>Last Login Time:-<br /><%=logontime%></b></td>
		</tr>
	</table>
	<!-- Feedback form -->
	<div class="feedback">
		<a id="feedback_button">Feedback</a>

		<div class="form">
			<h2 id='status'>Please Send Us Your Feedback</h2>
			<div id='loadingmessage'
				style="text-align: center; position: absolute; top: 0; left: 0; width: 100%; color: blue; display: none">
				<img src='../com_exp_cemk_extjs_js/feedback/loading_image.gif'
					width="150px" height="150px" />
			</div>
			<textarea id="feedback_text"
				onkeyup="limiter(200,'feedback_text','limit','html')"></textarea>
			<script type="text/javascript">
				document
						.write("<div id='limit' style='width:300px;text-align:right;'><font color='blue'><i>200 Characters left</i></font></div>");
			</script>
			<input type="button" class="button" value=" Send " id="submit_form" />
		</div>
	</div>

	<!-- Flying twiter -->

	<!-- twitter follow badge -->
	<script src=' ../com_exp_cemk_feeds/tripleflap.js'
		;' type='text/javascript'></script>
	<script type='text/javascript' charset='utf-8'>
	<!--
		var twitterAccount = 'Ihalkhata';
		var showTweet = false;
		var birdSprite = ' ../com_exp_cemk_feeds/birdsprite.png';
		var twitterfeedreader = ' ';
		tripleflapInit();
		-->
	</script>
	<!-- end of twitter follow badge -->

	<script type="text/javascript" charset="utf-8">
		Ext.onReady(function() {
			/**
			 * This is largely the same as the basic grid example at http://www.extjs.com/deploy/dev/examples/grid/array-grid.html
			 * Set up a simple grid with some fake data and a print button
			 */

			//finally, remove the loading mask
			(function() {
				Ext.get('loading').remove();
				Ext.get('loading-mask').fadeOut({
					remove : true
				});
			}).defer(250);
		});
	</script>
</body>
</html>
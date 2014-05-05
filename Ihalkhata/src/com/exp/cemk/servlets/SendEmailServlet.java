package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.SendEmailProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class SendEmailServlet
 */
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(SendEmailServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendEmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		logger.info("Servlet-->GridPaymentServlet-->Controller-->SendEmailProcessor##sendEmail");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		int groupId = userSession.getGroupId();

		String user = request.getParameter("user");
		String[] userArray = user.split(",");
		boolean result = SendEmailProcessor.getInstance().sendEmail(userArray,
				groupId);

		PrintWriter out = response.getWriter();
		if (result) {
			outMethod("Email successfully send to " + user, out, "success");
		} else {
			outMethod("Couldn't send Email", out, "failure");
		}
	}

	private void outMethod(String url, PrintWriter out, String action) {

		JSONObject returnJson = new JSONObject();

		returnJson.put(action, "true");
		returnJson.put("res", url);

		out.print(returnJson.toString());
	}
}

package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.SendEmailProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class SendFeedbackServlet
 */
public class SendFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(SendFeedbackServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendFeedbackServlet() {
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
		logger.info("Servlet-->SendFeedbackServlet-->Controller-->SendEmailProcessor##sendFeedBack");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		String userName = userSession.getUserName();
		boolean result = false;
		String user = request.getParameter("user");
		String message = request.getParameter("feedback");
		if (!StringUtils.isEmpty(message))
			message = "<font color='blue'>" + message
					+ "</font><br/><br/><font color='red'>Regards,<br>"
					+ userName + "</font>";
		String[] userArray = user.split(",");
		if (!StringUtils.isEmpty(message) && !StringUtils.isEmpty(user))
			result = SendEmailProcessor.getInstance().sendFeedback(userArray,
					message);

		PrintWriter out = response.getWriter();
		if (result) {
			out.print("Message Sent");
		} else {
			out.print("Message not Sent");

		}
	}

}

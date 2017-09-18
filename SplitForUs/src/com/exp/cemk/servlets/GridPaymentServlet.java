package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.GridPaymentDataProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GridPaymentServlet
 */
public class GridPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GridPaymentServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GridPaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		String groupId = userSession.getGroupId();

		int start = 0;
		int limit = 0;
		try {
			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));
		} catch (NumberFormatException nfException) {
			start = 0;
			limit = 0;
		}
		logger.info("Servlet-->GridPaymentServlet-->Controller-->GridPaymentDataProcessor##getGridPaymentData");
		JSONObject gridPaymentData = GridPaymentDataProcessor.getInstance()
				.getGridPaymentData(start, limit, groupId);

		if (!gridPaymentData.isEmpty()) {
			PrintWriter out = response.getWriter();
			out.write(gridPaymentData.toString());
		}
	}

}

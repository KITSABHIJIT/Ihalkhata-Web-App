package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.GridExpenseDataProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GridExpenseServlet
 */
public class GridExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(GridExpenseServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GridExpenseServlet() {
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
		logger.info("Servlet-->GridExpenseServlet-->Controller-->GridExpenseDataProcessor##getGridExpenseData");
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
		JSONObject gridExpenseData = GridExpenseDataProcessor.getInstance()
				.getGridExpenseData(start, limit, groupId);

		if (!gridExpenseData.isEmpty()) {
			// log.debug("jsonArray " + gain.toString());
			PrintWriter out = response.getWriter();
			out.write(gridExpenseData.toString());
		}
	}

}

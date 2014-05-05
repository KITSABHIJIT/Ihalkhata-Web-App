package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.GridExpenditureProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GridExpenditureServlet
 */
public class GridExpenditureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GridExpenditureServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GridExpenditureServlet() {
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
		logger.info("Servlet-->GridExpenditureServlet-->Controller-->GridExpenditureProcessor##getGridExpenditureData");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		String month = request.getParameter("month");
		String endmonth = request.getParameter("endmonth");
		String year = request.getParameter("year");
		String endYear = request.getParameter("endYear");
		String type = request.getParameter("type");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		int groupId = userSession.getGroupId();

		int start = 0;
		int limit = 0;
		try {
			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));
		} catch (NumberFormatException nfException) {
			start = 0;
			limit = 0;
		}
		JSONObject gridExpenditureData = GridExpenditureProcessor.getInstance()
				.getGridExpenditureData(start, limit, month, year, endmonth,
						endYear, type, groupId);

		if (!gridExpenditureData.isEmpty()) {
			PrintWriter out = response.getWriter();
			out.write(gridExpenditureData.toString());
		}
	}

}

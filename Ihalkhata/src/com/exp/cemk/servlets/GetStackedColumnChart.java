package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.exp.cemk.controller.getStackedColumnChartProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GetStackedColumnChart
 */
public class GetStackedColumnChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(GetStackedColumnChart.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStackedColumnChart() {
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
		logger.info("Servlet-->GetStackedColumnChart-->Controller-->getStackedColumnChartProcessor##getStackedColumnChartData");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		int groupId = userSession.getGroupId();

		String year = request.getParameter("Year");
		JSONArray stackedColumnChartData = getStackedColumnChartProcessor
				.getInstance().getStackedColumnChartData(groupId, year);

		if (!stackedColumnChartData.isEmpty()) {
			PrintWriter out = response.getWriter();
			out.write(stackedColumnChartData.toString());
		}
	}

}

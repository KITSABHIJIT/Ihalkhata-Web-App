package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.exp.cemk.controller.HighChartProcessor;

/**
 * Servlet implementation class GenerateChartServlet
 */
public class GenerateChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(GenerateChartServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateChartServlet() {
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
		logger.info("Servlet-->GenerateChartServlet-->Controller-->HighChartProcessor##getHighChartData");
		String user = request.getParameter("userId");
		String month = request.getParameter("startMonth");
		String endmonth = request.getParameter("endMonth");
		String year = request.getParameter("finalYear");
		String endYear = request.getParameter("endYear");
		JSONArray jsonArray = HighChartProcessor.getInstance()
				.getHighChartData(user, month, year, endmonth, endYear);
		String data = "";
		for (int i = 0; i < jsonArray.size(); i++) {
			if (data.equals(""))
				data = jsonArray.getString(i);
			else
				data = data + "|" + jsonArray.getString(i);

		}

		if (data != null) {
			PrintWriter out = response.getWriter();
			out.write(data);

		}
	}

}

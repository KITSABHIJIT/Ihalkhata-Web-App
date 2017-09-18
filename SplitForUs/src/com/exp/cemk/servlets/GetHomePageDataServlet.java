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

import com.exp.cemk.controller.GridPaymentDataProcessor;
import com.exp.cemk.controller.GridPaymentDetailsProcessor;
import com.exp.cemk.controller.IndividualChartProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GetHomePageDataServlet
 */
public class GetHomePageDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GetHomePageDataServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetHomePageDataServlet() {
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
		
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		String groupId = userSession.getGroupId();
		String userId = userSession.getUserId();
		String userName = userSession.getUserName();
		String type = request.getParameter("type");
		int start = 0;
		int limit = 0;
		try {
			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));
		} catch (NumberFormatException nfException) {
			start = 0;
			limit = 0;
		}
		if ("1".equals(type) || "2".equals(type)) {
			logger.info("Servlet-->GetHomePageDataServlet-->Controller-->GridPaymentDetailsProcessor##getDebtCreditNotifications");
			JSONObject gridPaymentDetailsData = new JSONObject();
			gridPaymentDetailsData = GridPaymentDetailsProcessor.getInstance()
					.getDebtCreditNotifications(start, limit, groupId, userId,
							type);

			if (!gridPaymentDetailsData.isEmpty()) {
				// log.debug("jsonArray " + gain.toString());
				PrintWriter out = response.getWriter();
				out.write(gridPaymentDetailsData.toString());
			}
		} else if ("3".equals(type)) {
			logger.info("Servlet-->GetHomePageDataServlet-->Controller-->IndividualChartProcessor##getHomeLineData");
			JSONArray jsonArray = IndividualChartProcessor.getInstance()
					.getHomeLineData(userId);
			if (jsonArray != null) {
				PrintWriter out = response.getWriter();
				out.write(jsonArray.toString());
			}
		} else if ("4".equals(type)) {
			logger.info("Servlet-->GetHomePageDataServlet-->Controller-->GridPaymentDataProcessor##getHomePaymentDetails");
			JSONObject jsonobj = GridPaymentDataProcessor.getInstance()
					.getHomePaymentDetails(groupId, userName);
			if (jsonobj != null) {
				PrintWriter out = response.getWriter();
				out.write(jsonobj.toString());
			}
		}
	}

}

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

import com.exp.cemk.controller.GridPaymentDetailsProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GridPaymentDetailsServlet
 */
public class GridPaymentDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GridPaymentDetailsServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GridPaymentDetailsServlet() {
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
		logger.info("Servlet-->GridPaymentDetailsServlet-->Controller-->GridPaymentDetailsProcessor##getGridPaymentDetails");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		int groupId = userSession.getGroupId();
		String type = request.getParameter("type");
		JSONObject gridPaymentDetailsData=new JSONObject();
		int start = 0;
		int limit = 0;
		try {
			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));
		} catch (NumberFormatException nfException) {
			start = 0;
			limit = 0;
		}
		if(StringUtils.isEmpty(type))
			gridPaymentDetailsData = GridPaymentDetailsProcessor
				.getInstance().getGridPaymentDetails(start, limit,groupId);
		else
			gridPaymentDetailsData = GridPaymentDetailsProcessor
			.getInstance().getGridPaidPaymentDetails(start, limit,groupId);

		if (!gridPaymentDetailsData.isEmpty()) {
			// log.debug("jsonArray " + gain.toString());
			PrintWriter out = response.getWriter();
			out.write(gridPaymentDetailsData.toString());
		}
	}

}

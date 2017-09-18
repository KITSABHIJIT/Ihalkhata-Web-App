package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GetUserServlet
 */
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GetUserServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserServlet() {
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
		logger.info("Servlet-->GetUserServlet-->Controller-->UserDataProcessor##getUserData");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		String groupId = userSession.getGroupId();
		String loginUser = request.getParameter("user");

		JSONArray userData = UserDataProcessor.getInstance().getUserData(
				groupId);

		PrintWriter out = response.getWriter();
		if (StringUtils.isBlank(loginUser)) {
			if (!userData.isEmpty()) {
				out.write(userData.toString());
			}
		} else {
			JSONArray loginUserData = new JSONArray();
			JSONObject dataObject = new JSONObject();
			dataObject.put("userName", userSession.getUserName());
			dataObject.put("userId", userSession.getUserId());
			loginUserData.add(dataObject);
			out.write(loginUserData.toString());
		}
	}

}

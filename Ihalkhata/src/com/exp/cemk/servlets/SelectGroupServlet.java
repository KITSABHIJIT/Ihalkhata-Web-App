package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class SelectGroupServlet
 */
public class SelectGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(SelectGroupServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectGroupServlet() {
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
		Person up = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		logger.info("Servlet-->SelectGroupServlet-->Controller-->UserDataProcessor##getSelectedUserGroup");
		String userId = up.getUserId();
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		if ("1".equals(type)) {
			JSONArray jsonArray=UserDataProcessor.getInstance().getSelectedUserGroup(userId);
			if (jsonArray != null) {
				out.write(jsonArray.toString());
			}
		} else {
			String groupName = request.getParameter("groupName");
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			Person user = (Person) request.getSession().getAttribute(
					SessionUtil.LOGGED_IN_USER);
			logger.info(user.getUserName() + " Previous Group :-"
					+ user.getGroupName());
			user.setGroupId(groupId);
			user.setGroupName(groupName);
			request.getSession().setAttribute(SessionUtil.LOGGED_IN_USER, user);
			logger.info(user.getUserName() + " Group changed to:- "
					+ user.getGroupName());
			JSONObject returnJson = new JSONObject();
			returnJson.put("success","true");
			returnJson.put("res","Group Succesfully Changed to <b>"+groupName+"</b>");
			out.print(returnJson.toString());
		}
	}
}

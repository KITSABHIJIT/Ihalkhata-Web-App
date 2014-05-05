package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.exp.cemk.controller.GetItemsProcessor;
import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class AddItemsServlet
 */
public class AddItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddItemsServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddItemsServlet() {
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
		logger.info("Servlet-->AddItemsServlet-->Controller-->GetItemsProcessor##addItems");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		String loginUser = userSession.getUserId();
		String item = request.getParameter("item");

		boolean result = GetItemsProcessor.getInstance().addItems(item.toUpperCase().trim(),
				loginUser);

		PrintWriter out = response.getWriter();
		JSONObject dataObject = new JSONObject();
		if (result) {
			dataObject.put("success", "true");
			dataObject.put("res", "Item successfully added");
		} else {
			dataObject.put("failure", "true");
			dataObject.put("res", "Item is already added");
		}

		out.write(dataObject.toString());
	}

}

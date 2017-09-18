package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.exp.cemk.controller.GetItemsProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class AutoFillServlet
 */
public class AutoFillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AutoFillServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutoFillServlet() {
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
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		logger.info("Servlet-->AutoFillServlet-->Controller-->GetItemsProcessor##getItems");
		Person userSession = (Person)request.getSession().getAttribute(SessionUtil.LOGGED_IN_USER);
		String query = request.getParameter("query");
		logger.info("Servlet-->AutoFillServlet-->query " + query);
		query=query+"%";
		JSONArray jsonArray = GetItemsProcessor.getInstance().getItems(
				query,userSession.getUserId().trim());
		if (jsonArray != null) {
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

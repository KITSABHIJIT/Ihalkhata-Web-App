package com.exp.cemk.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class CheckUserNameServlet
 */
public class CheckUserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CheckUserNameServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckUserNameServlet() {
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
		String userName = request.getParameter("userName");
		logger.info("Servlet-->CheckUserNameServlet-->Controller-->UserDataProcessor##isUserPresent");
		PrintWriter pw = response.getWriter();
		String flag = "false";
		boolean tempFlag = false;
		if (up != null) {
			if (up.getUserId().equals(userName))
				tempFlag = true;
			else
				tempFlag = false;
		} else {
			tempFlag = true;
		}
		if (tempFlag) {
			List<Person> data = new ArrayList<Person>();
			try {
				data = UserDataProcessor.getInstance().isUserPresent(userName);
				if (data.size() > 1 || !data.isEmpty())
					flag = "true";

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pw.write(flag);
	}

}

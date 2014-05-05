package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.exp.cemk.controller.GivePaymentProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GivePaymentServlet
 */
public class GivePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GivePaymentServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GivePaymentServlet() {
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
		logger.info("Servlet-->GivePaymentServlet-->Controller-->GivePaymentProcessor##givePayment");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		String payee = request.getParameter("payee");
		String creditor = request.getParameter("creditor");
		String date = request.getParameter("date");
		String amount = request.getParameter("amount");
		int groupId = userSession.getGroupId();
		boolean result = GivePaymentProcessor.getInstance().givePayment(payee,
				creditor,groupId, date, amount);

		PrintWriter out = response.getWriter();
		if (result) {
			out.write("Success:Payment done successfully ");
		} else {
			out.write("Failure:Couldn't make the payment");
		}
	}

}

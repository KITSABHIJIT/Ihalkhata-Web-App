package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cemk.exp.sendMail.SendMail;
import com.cemk.exp.sendMail.SendMailConstants;
import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.CryptoUtil;
import com.exp.cemk.util.PasswordGenerator;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GetPasswordServlet
 */
public class GetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(GetPasswordServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetPasswordServlet() {
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
		logger.info("Servlet-->GetPasswordServlet-->Controller-->UserDataProcessor##isUserPresent");
		String userName = request.getParameter("userName");
		String secAns = CryptoUtil.getInstance().encrypt(
				request.getParameter("secAns"));
		PrintWriter pw = response.getWriter();
		
			String password = "";
			List<Person> data = new ArrayList<Person>();
			data = UserDataProcessor.getInstance().isUserPresent(userName);
			if (!StringUtils.isEmpty(secAns) && data.get(0).getSecAns().equals(secAns)) {
			String userArray[] = new String[] { data.get(0).getEmail() };
			try {
				logger.info("Servlet-->GetPasswordServlet-->Util-->PasswordGenerator##getPassword");
				password = PasswordGenerator.getInstance().getPassword();
				logger.info("Servlet-->GetPasswordServlet-->Util-->CryptoUtil##encrypt");
				String encryptedPassword = CryptoUtil.getInstance().encrypt(
						password);
				logger.info("Servlet-->GetPasswordServlet-->SendMail Service-->SendMail##sendEmail");
				if (SendMail.sendEmail(userArray,
						SendMailConstants.changePassword + password,
						"changePassword")) {
					logger.info("Servlet-->GetPasswordServlet-->Controller Service-->UserDataProcessor##setdefaultPassword");
					int rowEffected = UserDataProcessor.getInstance()
							.setdefaultPassword(userName, secAns,
									encryptedPassword, "Y");
					if (rowEffected > 0) {
						password = "<b>"
								+ data.get(0).getUserName()
								+ "</b> your password has been changed successfully<br/>"
								+ "Please check your mail for new password";
					} else
						password = "Sorry. Try to give a proper answer.";
				} else
					password = "Sorry. We are unable to send your password through mail";

				pw.write(password);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			pw.write("Please enter correct Security Answer.");

	}

}

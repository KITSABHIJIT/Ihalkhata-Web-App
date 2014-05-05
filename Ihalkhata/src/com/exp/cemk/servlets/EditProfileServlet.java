package com.exp.cemk.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.FileUpload;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EditProfileServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
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
		String name = request.getParameter("userName");
		String filePath = request.getParameter("filePath");
		String company = request.getParameter("company");
		String email = request.getParameter("email");
		String phone = request.getParameter("mobile");
		Person up = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		logger.info("Servlet-->EditProfileServlet-->Controller-->UserDataProcessor##updateUser");
		String userId = up.getUserId();
		String action = null;
		JSONObject returnJson = new JSONObject();
		File userImage = null;

		try {
			Person user = new Person();
			user = up;
			if (!StringUtils.isEmpty(filePath) && filePath != null) {
				userImage = FileUpload.getUserImage(request);
				user.setImage(userImage);
				action = "Profile & Picture";
			} else {
				action = "Profile";
				user.setImage(up.getImage());
			}
			user.setUserId(userId);
			user.setUserName(name);
			user.setCompany(company);
			user.setEmail(email);
			user.setPhone(phone);
			int result = UserDataProcessor.getInstance().updateUser(user);
			if (result > 0) {
				returnJson.put("success", "true");
				returnJson.put("msg", up.getUserName() + "'s " + action
						+ " has been updated successfully");
				request.getSession()
						.removeAttribute(SessionUtil.LOGGED_IN_USER);
				request.getSession().setAttribute(SessionUtil.LOGGED_IN_USER,
						user);
			} else {
				returnJson.put("failure", "true");
				returnJson.put("msg", action + " editing failed");
			}

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.write(returnJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

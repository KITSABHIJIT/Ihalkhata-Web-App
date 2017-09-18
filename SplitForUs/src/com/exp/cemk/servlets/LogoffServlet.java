package com.exp.cemk.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;



/**
 * Servlet implementation class LogoffServlet
 */
public class LogoffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LogoffServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Servlet-->LogoffServlet-->LogOff proceess started");
		  Person up = (Person)request.getSession().getAttribute(SessionUtil.LOGGED_IN_USER);
	         if(up != null)
	         {
	        	 logger.info("Servlet-->LogoffServlet-->Controller-->UserDataProcessor##updateSessionId");
	        	int rowsAffectedSession=UserDataProcessor.getInstance().updateSessionId(null, up.getUserId());
				request.getSession().removeAttribute(SessionUtil.LOGGED_IN_USER);
				request.getSession().invalidate();
			}
	        logger.info("Servlet-->LogoffServlet-->successfully logged out");
	        String loginRedirect=request.getContextPath()+ "/index.html";
			logger.info("Servlet-->LogoffServlet-->Redirecting to "+loginRedirect);
			response.sendRedirect(loginRedirect);

		}

}

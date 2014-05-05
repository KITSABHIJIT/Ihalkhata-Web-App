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

import domainmodel.Person;



/**
 * Servlet implementation class GetSecurityQuestionServlet
 */
public class GetSecurityQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GetSecurityQuestionServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSecurityQuestionServlet() {
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
		logger.info("Servlet-->GetSecurityQuestionServlet-->Controller-->UserDataProcessor##isUserPresent");
		String userName=request.getParameter("userName");
		PrintWriter pw=response.getWriter();
		String secAns=""; 
		List<Person> data=new ArrayList<Person>();
		try {  
			data= UserDataProcessor.getInstance().isUserPresent(userName);
			if(data.size()>1 || !data.isEmpty()){
				for(Person user:data){
				if (userName.equals(user.getUserId())){
					secAns=user.getSecQues();
					break;}
				}
				}
			pw.write(secAns);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

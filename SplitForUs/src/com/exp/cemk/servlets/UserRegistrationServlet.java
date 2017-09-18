package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.exp.cemk.constants.LoginConstants;
import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.CryptoUtil;
import com.exp.cemk.util.FileUpload;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

import net.sf.json.JSONObject;



/**
 * Servlet implementation class UserRegistrationServlet
 */
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserRegistrationServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
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
		logger.info("Servlet-->UserRegistrationServlet-->Controller-->UserDataProcessor##insertUser");
		Person userSession = (Person)request.getSession().getAttribute(SessionUtil.LOGGED_IN_USER);
		 String groupId=null; 
		if(userSession != null)
	         groupId=userSession.getGroupId();
		 else
			  groupId=request.getParameter("grpId");
		
		
		String name=request.getParameter("first")+" "+request.getParameter("last");
		String company=request.getParameter("company");
		String email=request.getParameter("email");
		String phone=request.getParameter("mobile");
		String userName=request.getParameter("userName");
		String secQues=request.getParameter("secQues");
		String secAns=CryptoUtil.getInstance().encrypt(request.getParameter("answer"));
		String password=CryptoUtil.getInstance().encrypt(request.getParameter("password"));
		JSONObject returnJson = new JSONObject();
		Person user=new Person();
		user.setUserName(name);
		user.setEmail(email);
		user.setGroupId(groupId);
		user.setCompany(company);
		user.setUserId(userName);
		user.setPassword(password);
		user.setPhone(phone);
		user.setSecQues(secQues);
		user.setSecAns(secAns);
		user.setFirstLoginFlag("N");
		user.setActiveFlag("Y");
		user.setImage(FileUpload.getDefaultUserImage(userName));
		int flag=UserDataProcessor.getInstance().insertUser(user);
		if(flag>0){
			returnJson.put("success", "true");
			returnJson.put("error", "<b>"+name+"</b> Successfully registered");
			
		}
		else{
			returnJson.put("failure", "true");
			returnJson.put("error", "User Registration Failed");
		}
		
		PrintWriter pw=response.getWriter();
		pw.write(returnJson.toString());
	
	}

}

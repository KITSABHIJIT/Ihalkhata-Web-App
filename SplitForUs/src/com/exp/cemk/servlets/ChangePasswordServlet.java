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

import net.sf.json.JSONObject;

import com.cemk.exp.sendMail.SendMail;
import com.cemk.exp.sendMail.SendMailConstants;
import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.CryptoUtil;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ChangePasswordServlet.class); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		Person up=(Person)request.getSession().getAttribute(SessionUtil.LOGGED_IN_USER);
		String userName=up.getUserId();
		String pass=up.getPassword();
		String seqAnswer=up.getSecAns();
		String oldPassword=CryptoUtil.getInstance().encrypt(request.getParameter("oldPassword"));
		String newPassword=request.getParameter("newPassword");
		JSONObject returnJson = new JSONObject();
		PrintWriter pw=response.getWriter();
		
		
		if(pass.equals(oldPassword)){
			try {  
				newPassword=CryptoUtil.getInstance().encrypt(newPassword);
				logger.info("Servlet-->ChangePasswordServlet-->Controller-->UserDataProcessor##setdefaultPassword");
				int rowEffected=UserDataProcessor.getInstance().setdefaultPassword(userName, seqAnswer, newPassword,"N");
				
				if(rowEffected>0){
					
					returnJson.put("success", "true");
					returnJson.put("msg", "<b>"+up.getUserName()+"</b> your password has been changed successfully");
				}
			else{
				returnJson.put("failure", "true");
				returnJson.put("msg", "Please enter proper credentials");
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}else{
			returnJson.put("failure", "true");
			returnJson.put("msg", userName+" please enter proper credentials");
		}
		
		pw.write(returnJson.toString());
	}

}

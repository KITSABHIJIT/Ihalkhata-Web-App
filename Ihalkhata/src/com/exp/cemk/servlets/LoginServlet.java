package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.UserDataProcessor;
import com.exp.cemk.util.CommonUtil;
import com.exp.cemk.util.CryptoUtil;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String userName=request.getParameter("userName");
		String password=CryptoUtil.getInstance().encrypt(request.getParameter("password"));
		List<Person> data=new ArrayList<Person>();
		JSONObject returnJson = new JSONObject();
		PrintWriter pw=response.getWriter();
		try {  
			
			data= UserDataProcessor.getInstance().getLoginUser(userName, password);
			if(data.size()>0 && !data.isEmpty()){
				for(Person user:data){
				if (userName.equals(user.getUserId()) && password.equals(user.getPassword())){
					if(user.getLastLoginTime()==null)
						user.setLastLoginTime(new SimpleDateFormat("EE, dd MMM yyyy hh:mm aa").format(new Date()));
						request.getSession().setAttribute(SessionUtil.LOGGED_IN_USER, user);
						
						if(user.getFirstLoginFlag().equals("Y")){
							returnJson.put("success", "true");
							returnJson.put("msg", user.getUserName()+"|"+user.getSecQues());
							
						}
						else{
							String loginTime=new SimpleDateFormat("EE, dd MMM yyyy hh:mm aa").format(new Date());
							int rowsAffected=UserDataProcessor.getInstance().updateLoginTime(loginTime, userName);
							int rowsAffectedSession=UserDataProcessor.getInstance().updateSessionId(request.getSession().getId(), userName);
							if(rowsAffected>0 && rowsAffectedSession>0)
								logger.info("## "+userName+": Login time updated to "+loginTime);
							else
								logger.info(userName+": Login time updaion failed");
							returnJson.put("success", "true");
							returnJson.put("msg", "Welcome <b>"+user.getUserName()+"</b>");
							returnJson.put("url", CommonUtil.getURIPrefix(request)+"/com_exp_cemk_jsp/Home.jsp");
							
							}
						break;}
					else{
						returnJson.put("failure", "true");
						returnJson.put("msg", "Invalid User Name or Password");
						}
					}
				}else{
					returnJson.put("failure", "true");
					returnJson.put("msg", "Invalid User Name or Password");
				}
			
			pw.write(returnJson.toString());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

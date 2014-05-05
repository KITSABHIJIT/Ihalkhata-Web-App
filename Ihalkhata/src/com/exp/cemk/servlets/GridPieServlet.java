package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.exp.cemk.controller.GridExpenditureProcessor;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;

/**
 * Servlet implementation class GridPieServlet
 */
public class GridPieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GridPieServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GridPieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		String month=request.getParameter("month");
		String endmonth=request.getParameter("endmonth");
		String year=request.getParameter("year");
		String endYear=request.getParameter("endYear");
		String type=request.getParameter("type");
		String data=request.getParameter("data");
		Person userSession = (Person)request.getSession().getAttribute(SessionUtil.LOGGED_IN_USER);
		int groupId=userSession.getGroupId();
		 String userName=userSession.getUserName();
		 JSONArray jsonArray=null;
		 
		 if(!"1".equals(data)){
			 logger.info("Servlet-->GridPieServlet-->Controller-->GridExpenditureProcessor.getGridPieExpenditureData");
			 jsonArray = GridExpenditureProcessor.getInstance().getGridPieExpenditureData(month,year,endmonth,endYear,type,groupId);
		 } else{
			 logger.info("Servlet-->GridPieServlet-->Controller-->GridExpenditureProcessor.getExpenditureData");
			 jsonArray = GridExpenditureProcessor.getInstance().getExpenditureData(month,year,endmonth,endYear,type,groupId,userName);
		 }
		 if(jsonArray!= null)
		{
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		}
	}
	

}


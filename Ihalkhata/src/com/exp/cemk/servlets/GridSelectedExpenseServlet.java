package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.GridIndividualExpenseProcessor;

/**
 * Servlet implementation class GridSelectedExpenseServlet
 */
public class GridSelectedExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GridSelectedExpenseServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GridSelectedExpenseServlet() {
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
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		String user=request.getParameter("userId");
		String month=request.getParameter("month");
		String endmonth=request.getParameter("endmonth");
		String year=request.getParameter("year");
		String endYear=request.getParameter("endYear");
		String itemType=request.getParameter("itemType");
		int start=0,limit=0;
		try{
		start = Integer.parseInt(request.getParameter("start"));
		limit = Integer.parseInt(request.getParameter("limit"));
		}
		catch(NumberFormatException nfExp){
			start=0;
			limit=0;
		}
		logger.info("GridSelectedExpenseServlet--->GridIndividualExpenseProcessor");
		JSONObject gridSelectedExpenseData=GridIndividualExpenseProcessor.getInstance().getGridIndividualExpenseData(start,limit,user,month,year,endmonth,endYear,itemType,"selected");

	
	
	if(!gridSelectedExpenseData.isEmpty())
	{
		//log.debug("jsonArray " + gain.toString());
		PrintWriter out = response.getWriter();
		out.write(gridSelectedExpenseData.toString());
	}
}

}

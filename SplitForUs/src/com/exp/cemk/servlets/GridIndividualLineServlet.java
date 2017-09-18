package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.exp.cemk.controller.IndividualChartProcessor;

/**
 * Servlet implementation class GridIndividualLineServlet
 */
public class GridIndividualLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GridIndividualLineServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GridIndividualLineServlet() {
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
		logger.info("Servlet-->GridIndividualLineServlet-->Controller-->IndividualChartProcessor##getIndividualLineData");
		String user=request.getParameter("user");
		String month=request.getParameter("month");
		String endmonth=request.getParameter("endmonth");
		String year=request.getParameter("year");
		String endYear=request.getParameter("endYear");
		JSONArray jsonArray = IndividualChartProcessor.getInstance().getIndividualLineData(user,month,year,endmonth,endYear);
		if(jsonArray!= null)
		{
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
		}
	}

}

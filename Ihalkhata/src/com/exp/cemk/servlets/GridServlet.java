package com.exp.cemk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.pojo.GridAutoFillDemo;


/**
 * Servlet implementation class GridServlet
 */
public class GridServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GridServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GridServlet() {
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
		logger.info("Servlet-->GridServlet-->Pojo-->GridAutoFillDemo##getGridData");
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		JSONObject gridData=GridAutoFillDemo.getInstance().getGridData(start, limit);
	
		
		
		if(!gridData.isEmpty())
		{
			//log.debug("jsonArray " + gain.toString());
			PrintWriter out = response.getWriter();
			out.write(gridData.toString());
		}
		
		
	}

}

package com.exp.cemk.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.exp.cemk.util.CommonUtil;
import com.exp.cemk.util.SessionUtil;

import domainmodel.Person;



/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ImageServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
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
		
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
		//String userName=request.getParameter("loginId");
		Person userSession = (Person)request.getSession().getAttribute(SessionUtil.LOGGED_IN_USER);
		OutputStream os = response.getOutputStream();
		try {  
			
			// open image  
			File imgPath = userSession.getImage();
			BufferedImage originalImage = ImageIO.read(imgPath); 
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			BufferedImage resizeImage = CommonUtil.resizeImage(originalImage, type);
			// sending image to client    
			response.setContentType("image/png");
			ImageIO.write(resizeImage, "png", os);
			logger.info("Servlet-->ImageServlet-->Fetching "+userSession.getUserName()+" Image from User Session");
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally{
			os.close();
		}
	}
	
	   

}

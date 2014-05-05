package com.exp.cemk.servlets;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CaptchaServlet
 */
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CaptchaServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final char data[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'0','1','2','3','4','5','6','7','8','9'};
	
	public CaptchaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void getCaptchaCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			String flag=request.getParameter("flag");
			logger.info("Servlet-->CaptchaServlet-->Creating captcha Code");
			if(flag!=null && flag.equals("1")){
				String captcha = (String) request.getSession().getAttribute("captcha");
				PrintWriter writer=response.getWriter();
				writer.write(captcha.toString());
			}
			else
				processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		int width = 150;
		int height = 50;

		Random r = new Random();
		int index0 = Math.abs(r.nextInt()) % 62;
		int index1 = Math.abs(r.nextInt()) % 62;
		int index2 = Math.abs(r.nextInt()) % 62;
		int index3 = Math.abs(r.nextInt()) % 62;
		int index4 = Math.abs(r.nextInt()) % 62;
		
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();

		Font font = new Font("Georgia", Font.BOLD, 18);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		GradientPaint gp = new GradientPaint(0, 0, Color.LIGHT_GRAY, 0, height / 2,
				Color.black, true);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);

		g2d.setColor(new Color(255, 153, 0));

		

		String captcha = String.valueOf(data[index0])+String.valueOf(data[index1])+String.valueOf(data[index2])+String.valueOf(data[index3])+String.valueOf(data[index4]);
		request.getSession().setAttribute("captcha", captcha);

		int x = 0;
		int y = 0;

		for (int i = 0; i < captcha.length(); i++) {
			x += 10 + (Math.abs(r.nextInt()) % 15);
			y = 20 + Math.abs(r.nextInt()) % 20;
			g2d.drawChars(captcha.toCharArray(), i, 1, x, y);
		}

		g2d.dispose();

		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		os.close();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getCaptchaCode(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getCaptchaCode(request, response);
	}

	

}

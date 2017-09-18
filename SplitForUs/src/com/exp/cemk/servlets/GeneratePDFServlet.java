package com.exp.cemk.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.exp.cemk.controller.IndividualPDFGeneraterProcessor;
import com.exp.cemk.util.CommonUtil;
import com.exp.cemk.util.DateUtil;
import com.exp.cemk.util.RequestUtil;
import com.exp.cemk.util.SessionUtil;
import com.itext.Objects.MonthExpense;
import com.itext.Objects.User;
import com.itext.controller.PDFGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import domainmodel.Person;

/**
 * Servlet implementation class GeneratePDFServlet
 */
public class GeneratePDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GeneratePDFServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeneratePDFServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Servlet-->GeneratePDFServlet-->Controller-->IndividualPDFGeneraterProcessor##getIndividualPDFGenerater");
		Person userSession = (Person) request.getSession().getAttribute(
				SessionUtil.LOGGED_IN_USER);
		response.setContentType("text/html");
		String user = request.getParameter(RequestUtil.USER);
		String userName = request.getParameter(RequestUtil.USER_NAME);
		String month = request.getParameter(RequestUtil.STARTMONTH);
		String endMonth = request.getParameter(RequestUtil.ENDMONTH);
		String year = request.getParameter(RequestUtil.YEAR);
		String endYear = request.getParameter(RequestUtil.END_YEAR);
		// String charts=request.getParameter(RequestUtil.CHARTSFLAG);
		// String limit=request.getParameter(RequestUtil.LIMIT);
		// String title=request.getParameter(RequestUtil.TITLEFLAG);
		List<MonthExpense> listMain = IndividualPDFGeneraterProcessor
				.getInstance().getIndividualPDFGenerater(
						userSession.getUserId(), month, year, endMonth,
						endYear, "", "individual");
		if (listMain.isEmpty()) {
			PrintWriter out = response.getWriter();
			JSONObject returnJson = new JSONObject();
			returnJson.put("success", "true");
			returnJson.put("res", "Data is not available for current scenario");
			out.print(returnJson.toString());
		} else {
			//response.reset();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition",
					"inline; filename=" + userSession.getUserName()
							+ " expenses_" + DateUtil.getFormatedCurrentDate("dd-MM-yyyy-HH-mm-ss")
							+ ".pdf");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "No-cache");
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);

			try {
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, buffer);
				document.open();
				File imgPath = userSession.getImage();
				BufferedImage originalImage = ImageIO.read(imgPath);
				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
						: originalImage.getType();
				BufferedImage resizeImage = CommonUtil.resizeImage(
						originalImage, type);
				User c = new User(userSession.getUserName(),
						userSession.getCompany(), userSession.getPhone(),
						resizeImage, CommonUtil.getURIPrefix(request)
								+ "/com_exp_cemk_css/images/logo.jpg",
						CommonUtil.getURIPrefix(request)
								+ "/com_exp_cemk_css/images/stamp1.jpeg");
				PDFGenerator pdfGenerator = new PDFGenerator(c, listMain);
				pdfGenerator.generatorPDF(document, writer);
					document.close();

				DataOutput dataOutput = new DataOutputStream(
						response.getOutputStream());
				byte[] bytes = buffer.toByteArray();
				response.setContentLength(bytes.length);
				for (int i = 0; i < bytes.length; i++) {
					dataOutput.writeByte(bytes[i]);
				}

			} catch (DocumentException e) {
				e.printStackTrace();
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				JSONObject returnJson = new JSONObject();
				returnJson.put("failure", "true");
				returnJson.put("error",
						"Some Problem Occured. Please try again.");
				out.print(returnJson.toString());
			}

		}
	}
}

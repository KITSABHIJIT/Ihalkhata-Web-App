package com.itext.controller;


import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itext.Objects.ExpenseItem;
import com.itext.Objects.MonthExpense;
import com.itext.Objects.User;
import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFGenerator{

	/**
	 * @param args
	 */
	private User _user;
	private List<MonthExpense> _monthExpenseList = new ArrayList<MonthExpense>();
	//private static String USER_PASS = "Hello123";
	//private static String OWNER_PASS = "Owner123";
	protected PdfPTable header;
	protected PdfPTable footer;

	public PDFGenerator(User user, List<MonthExpense> monthExpenseList) {
		_user = user;
		_monthExpenseList = monthExpenseList;
	}

	public void generatorPDF(Document d,PdfWriter writer) throws DocumentException, MalformedURLException, IOException {
		 //Rectangle pageSize = new Rectangle(650, 950);
		//pageSize.setBackgroundColor(new java.awt.Color(0xDF, 0xFF, 0xDE));
		//Document d = new Document(pageSize);
		//Document d = new Document();
		writer.setPageEvent(new HeaderAndFooter(writer,_user.getLogoUrl()));
		try {
			//d.open();
			//addMetaData(d);
			//writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),PdfWriter.AllowPrinting, PdfWriter.STRENGTH128BITS);
			if (!_monthExpenseList.isEmpty()) {
				PdfPTable headerTable = new PdfPTable(3);
				generateHeaderTable(_user,headerTable);
				d.add(headerTable);
				int flag=0;
				for (MonthExpense me : _monthExpenseList) {
					String MonthName = null;
					if (!me.getExpenseItem().isEmpty()) {
						DateFormat outputFormatter = new SimpleDateFormat(
								"EEE, dd MMM yyyy");
						Date date = (Date) outputFormatter.parse(me
								.getExpenseItem().get(0).getDate());
						MonthName = new SimpleDateFormat("MMMM-yyyy").format(date);
						
						Paragraph preface = new Paragraph();
						preface.add(new Paragraph("Expense Breakup(" + MonthName
								+ "):-", PDFCellStyles.smallBold));
						addEmptyLine(preface, 1);
						d.add(preface);
						PdfPTable table = generateLineItemTable(me.getExpenseItem(),MonthName);
						d.add(table);
						flag++;
						if (flag<_monthExpenseList.size()){
							d.newPage();// Start a new page
						}
					}
				}
				Paragraph note = new Paragraph(
						"\n\nNote:- \n\tExpense between Rs.100 and Rs.500 are marked by blue.\n\tExpense greater than Rs.500 are marked by Red.",PDFCellStyles.smallItalic);
				Paragraph p = new Paragraph("\nFor more, please visit ");
				Anchor anchor = new Anchor("www.Ihalkhata.com",
						FontFactory.getFont(FontFactory.COURIER, 12,
								Font.UNDERLINE, new Color(0, 0, 255)));

				p.add(anchor);
				d.add(note);
				d.add(p);
			}
			//d.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void addMetaData(Document document) {
		document.addTitle("Expense Report");
		document.addSubject("Expense Report");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("www.Ihalkhata.com");
		document.addCreator("www.Ihalkhata.com");
	}



	private PdfPTable generateLineItemTable(List<ExpenseItem> listItems,String monthName) {
		PdfPTable table = new PdfPTable(4);
		DecimalFormat df = new DecimalFormat("0.00");
		double total = 0;
		for (ExpenseItem lineItem : listItems) {
			if (lineItem.getPrice() >= 100 && lineItem.getPrice() < 500) {
				table.addCell(PDFCellStyles.listBlueCell(lineItem.getDate()));
				table.addCell(PDFCellStyles.listBlueCell(lineItem.getItem()));
				table.addCell(PDFCellStyles.listBlueCell(lineItem
						.getDescription()));
				table.addCell(PDFCellStyles.listBlueCell("Rs."
						+ df.format(lineItem.getPrice())));
			} else if (lineItem.getPrice() > 500) {
				table.addCell(PDFCellStyles.listRedCell(lineItem.getDate()));
				table.addCell(PDFCellStyles.listRedCell(lineItem.getItem()));
				table.addCell(PDFCellStyles.listRedCell(lineItem
						.getDescription()));
				table.addCell(PDFCellStyles.listRedCell("Rs."
						+ df.format(lineItem.getPrice())));
			} else {
				table.addCell(PDFCellStyles.listCell(lineItem.getDate()));
				table.addCell(PDFCellStyles.listCell(lineItem.getItem()));
				table.addCell(PDFCellStyles.listCell(lineItem
						.getDescription()));
				table.addCell(PDFCellStyles.listCell("Rs."
						+ df.format(lineItem.getPrice())));
			}
			total += lineItem.getPrice();
		}
		table.addCell(PDFCellStyles.totalStyle(""));
		table.addCell(PDFCellStyles.totalStyle("Total Expense"));
		table.addCell(PDFCellStyles.totalStyle(monthName));
		table.addCell(PDFCellStyles.totalStyle("Rs." + df.format(total)));
		return table;
	}

	private void generateHeaderTable(User c,PdfPTable headerTable) throws Exception {
		// Generic User Image
		//PdfPTable headerTable = new PdfPTable(3);
		Image userImage = Image.getInstance(c.getImageUrl(), null);
		userImage.setAlignment(Element.ALIGN_RIGHT);
		userImage.scalePercent(40);

		PdfPCell userImageCell = new PdfPCell(userImage, false);
		userImageCell.setBorder(0);
		headerTable.addCell(userImageCell);
		
		// Generic logo
		Image logoImage = Image.getInstance(c.getLogoUrl());
		logoImage.setAlignment(Element.ALIGN_RIGHT);
		logoImage.scalePercent(30);

		PdfPCell logoImageCell = new PdfPCell(logoImage, false);
		logoImageCell.setBorder(0);
		headerTable.addCell(logoImageCell);
		
		// Generic Stamp Image
		Image stampImage = Image.getInstance(c.getStampUrl());
		stampImage.setAlignment(Element.ALIGN_RIGHT);
		stampImage.scalePercent(30);
		PdfPCell stampImageCell = new PdfPCell(stampImage, false);
		stampImageCell.setBorder(0);
		headerTable.addCell(stampImageCell);
		// cell = PDFCellStyles.borderlessCell("Expense Form");
		// headerTable.addCell(cell);
		if (c.getName() != null && c.getName() != "") {
			headerTable.addCell(PDFCellStyles.borderlessCell(""));
			headerTable.addCell(PDFCellStyles.borderlessCell("User Name"));
			headerTable.addCell(PDFCellStyles.borderlessCell(c.getName()));
		}
		if (c.getAddress() != null && c.getAddress() != "") {
			headerTable.addCell(PDFCellStyles.borderlessCell(""));
			headerTable.addCell(PDFCellStyles.borderlessCell("Company"));
			headerTable.addCell(PDFCellStyles.borderlessCell(c.getAddress()));
		}
		if (c.getPhone() != null && c.getPhone() != "") {
			headerTable.addCell(PDFCellStyles.borderlessCell(""));
			headerTable.addCell(PDFCellStyles.borderlessCell("Phone Number"));
			headerTable.addCell(PDFCellStyles.borderlessCell(c.getPhone()));
		}
		//return headerTable;
	}

	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}

package com.itext.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class HeaderAndFooter extends PdfPageEventHelper {

	protected PdfPTable header;
	protected PdfPTable footer;
	protected PdfWriter writer;
	protected String footerImage;
	public HeaderAndFooter(PdfWriter _writer,String _footerImage) throws BadElementException, MalformedURLException, IOException {
		writer=_writer;
		footerImage=_footerImage;
		header = addBarcodeHeader(Long.toString(generateRandom(13)));
		footer = new PdfPTable(3);
		Image logoImage = Image.getInstance(footerImage);
		logoImage.setAlignment(Element.ALIGN_RIGHT);
		logoImage.scalePercent(30);
		PdfPCell logoImageCell = new PdfPCell(logoImage, false);
		logoImageCell.setColspan(3);
		logoImageCell.setBorder(0);
		footer.setTotalWidth(250);
		footer.addCell(logoImageCell);
		}
	private PdfPTable addBarcodeHeader(String code) {
		PdfPTable headerTable = new PdfPTable(3);
		PdfContentByte cb = writer.getDirectContent();
		BarcodeEAN codeEAN = new BarcodeEAN();
		codeEAN.setCodeType(Barcode.EAN13);
		// codeEAN.setCode("9780201615883");
		//codeEAN.setCode(Long.toString(generateRandom(13)));
		codeEAN.setCode(code);
		Image imageEAN = codeEAN.createImageWithBarcode(cb, null, null);
		PdfPCell cell = new PdfPCell(imageEAN, false);
		//cell.setPaddingTop(10);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan(3);
		cell.setBorder(0);
		headerTable.setTotalWidth(250);
		headerTable.addCell(cell);

		return headerTable;
	}
	
	public static long generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}
	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		/*ColumnText.showTextAligned(
				cb,
				Element.ALIGN_CENTER,
				footer,(document.right() - document.left() - 300) / 2+ document.leftMargin(), document.bottom() - 10, 0);
*/
		header.writeSelectedRows(0,-1,(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, cb);

		footer.writeSelectedRows(0,-1,(document.right() - document.left()-100) / 2+ document.leftMargin(), document.bottom(), cb);
	}
}
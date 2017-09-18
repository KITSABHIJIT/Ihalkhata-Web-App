package com.itext.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class PDFBackground {

	public static void setBackground(String inputFileName,String outputFileName,String background) throws FileNotFoundException, DocumentException, IOException {
		PdfReader reader = new PdfReader(inputFileName);
		int n = reader.getNumberOfPages();

		// Create a stamper that will copy the document to a new file
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
				outputFileName));
		int i = 1;
		PdfContentByte under;
		PdfContentByte over;

		Image img = Image.getInstance(background);
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,
				BaseFont.EMBEDDED);

		img.setAbsolutePosition(200, 400);

		while (i < n) {
			// Watermark under the existing page
			under = stamp.getUnderContent(i);
			under.addImage(img);

			// Text over the existing page
			over = stamp.getOverContent(i);
			over.beginText();
			over.setFontAndSize(bf, 18);
			over.showText("page " + i);
			over.endText();

			i++;
		}

		stamp.close();
	}
}

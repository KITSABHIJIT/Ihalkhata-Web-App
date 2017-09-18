package com.itext.controller;

import java.awt.Color;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;

public class PDFCellStyles {

	public static Font smallBold = new Font(Font.COURIER, 12, Font.BOLD,
			Color.DARK_GRAY);
	
	public static Font smallItalic = new Font(Font.COURIER, 9, Font.ITALIC,
			Color.BLACK);
	
	static PdfPCell borderlessCell(String s) {
		PdfPCell cell = new PdfPCell();
		Font f = new Font(Font.COURIER);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.addElement(new Paragraph(s, f));
		return cell;
	}

	static PdfPCell listCell(String s) {
		PdfPCell cell = new PdfPCell();
		Font f = new Font(Font.COURIER);
		f.setSize(8);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.addElement(new Paragraph(s, f));
		return cell;
	}
	static PdfPCell listBlueCell(String s) {
		PdfPCell blueCell = new PdfPCell();
		Font f = new Font(Font.COURIER);
		f.setSize(8);
		f.setColor(Color.BLUE);
		blueCell.setBorder(0);
		blueCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		blueCell.addElement(new Paragraph(s, f));
		return blueCell;
	}
	static PdfPCell listRedCell(String s) {
		PdfPCell redCell = new PdfPCell();
		Font f = new Font(Font.COURIER);
		f.setSize(8);
		f.setColor(Color.RED);
		redCell.setBorder(0);
		redCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		redCell.addElement(new Paragraph(s, f));
		return redCell;
	}
	static PdfPCell totalStyle(String s) {
		PdfPCell cell = new PdfPCell();
		Font f = new Font(Font.COURIER);
		f.setSize(11);
		f.setStyle(Font.BOLD);
		f.setColor(Color.RED);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.addElement(new Paragraph(s, f));
		return cell;
	}
}

package com.itext.test;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.JFreeChart;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;

public class WriteChartToPDF {
	
	 public static void writeChartToPDF(Document document,PdfContentByte contentByte,JFreeChart chart, int width, int height) {
	  	try {
	  		//document.newPage(); 
	        PdfTemplate template = contentByte.createTemplate(width, height);
	        Graphics2D graphics2d = template.createGraphics(width, height,new DefaultFontMapper());
	        Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,height);
	        chart.draw(graphics2d, rectangle2d);
	        graphics2d.dispose();
	        contentByte.addTemplate(template, 0, 0);
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	}
}


package com.itext.test;

import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class ITextTest {
	public static void main(String[] args) {
		
		
		/*******************************************************************		
		 *              Java code to read a File				           * 	  
		 *******************************************************************/	  	  
	
		String fileNameTxt="C:/Users/Abhijit/Desktop/test.txt";
		ArrayList<String>  textArray=new ArrayList<String>();
		int count=0;
		
		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(fileNameTxt);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
				  textArray.add(strLine);
				  //System.out.println (strLine);
				  count++;
			}
			  System.out.println (textArray.toString());
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		
		
		
		
		/*******************************************************************		
		 *              Java code to create PDF File				       * 	  
		 *******************************************************************/	  	  
	
	String fileName="C:/Users/Abhijit/Desktop/ITextTest.pdf";
		
		
		try {
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(fileName));
			document.open();
			document.add(new Paragraph("First page of the document."));
			/*document.add(new Paragraph(
							"Some more text on the first page with different color and font type.",
							FontFactory.getFont(FontFactory.COURIER, 14,
									Font.BOLD, new Color(255, 150, 200))));*/
				//for(int i=textArray.size();i>=0;i--)
			for(int i=0;i<textArray.size();i++)
				{
					document.add(new Paragraph(textArray.get(i),
							FontFactory.getFont(FontFactory.COURIER, 14,
									Font.BOLD, new Color(255, 150, 200))));
				}
			Paragraph title1 = new Paragraph("Chapter 1", FontFactory.getFont(
					FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0,
							255)));
			Chapter chapter1 = new Chapter(title1, 1);
			chapter1.setNumberDepth(0);
			Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
					FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
							new Color(255, 0, 0)));
			Section section1 = chapter1.addSection(title11);
			Paragraph someSectionText = new Paragraph(
					"This text comes as part of section 1 of chapter 1.");
			section1.add(someSectionText);
			someSectionText = new Paragraph("Following is a 3 X 2 table.");
			section1.add(someSectionText);
			Table t = new Table(3, 2);
			t.setBorderColor(new Color(220, 255, 100));
			t.setPadding(5);
			t.setSpacing(5);
			t.setBorderWidth(1);
			Cell c1 = new Cell("header1");
			t.addCell(c1);
			c1 = new Cell("Header2");
			t.addCell(c1);
			c1 = new Cell("Header3");
			t.addCell(c1);
			t.addCell("1.1");
			t.addCell("1.2");
			t.addCell("1.3");
			section1.add(t);

			List l = new List(true, true, 10);
			l.add(new ListItem("First item of list"));
			l.add(new ListItem("Second item of list"));
			section1.add(l);

			document.add(chapter1);
			document.close();
			
			
			/*******************************************************************		
			 *              First Approach to open PDF File				       * 	  
			 *            Windows solution to view a PDF file		           *
			 *******************************************************************/	  	  
					
			

		/*	  try {
				  
					if ((new File(fileName)).exists()) {
			 
						Process p = Runtime
						   .getRuntime()
						   .exec("rundll32 url.dll,FileProtocolHandler "+fileName);
						p.waitFor();
			 
					} else {
			 
						System.out.println("File is not exists");
			 
					}
			 
					System.out.println("Done");
			 
			  	  } catch (Exception ex) {
					ex.printStackTrace();
				  }*/
	
		/*******************************************************************		
		 *              Second Approach to open PDF File				   * 	  
		 *            Cross platform solution to view a PDF file		   *
		 *******************************************************************/	  	  
			
			  	 try {
			  		 
			 		File pdfFile = new File(fileName);
			 		if (pdfFile.exists()) {
			  
			 			if (Desktop.isDesktopSupported()) {
			 				Desktop.getDesktop().open(pdfFile);
			 			} else {
			 				System.out.println("Awt Desktop is not supported!");
			 			}
			  
			 		} else {
			 			System.out.println("File is not exists!");
			 		}
			  
			 		System.out.println("Done");
			  
			 	  } catch (Exception ex) {
			 		ex.printStackTrace();
			 	  }


		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}
}

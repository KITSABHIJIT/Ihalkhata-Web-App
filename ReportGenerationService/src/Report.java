import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.io.*;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Report {



    public static void main(String arg[])throws Exception
     {


        try{
            File temp = File.createTempFile("tempfile", ".pdf");


                OutputStream file = new FileOutputStream(temp);
                Document document  = new Document();

                PdfWriter.getInstance(document, file);
                document.open();
                document.addHeader("header1", "this is my header file");
                document.setMargins(50, 50, 100, 100);
                document.add(new Paragraph("hello neck"));
                document.close();
                file.close();

                if (Desktop.isDesktopSupported()) {
                    Desktop dtop = Desktop.getDesktop();

                    if (dtop.isSupported(Desktop.Action.OPEN)) {
                        String temp2 = temp.getPath();      
                        dtop.open(new File(temp2));
                    }
                }


            } catch (Exception e) {

            e.printStackTrace();
        }
     }
}
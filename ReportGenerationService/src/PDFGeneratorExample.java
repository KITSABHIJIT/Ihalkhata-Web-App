import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import com.itext.Objects.MonthExpense;
import com.itext.Objects.User;
import com.itext.controller.PDFDataProvider;
import com.itext.controller.PDFGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;




public class PDFGeneratorExample {

	public static void main(String[] args) throws Exception {
		File imgPath = new File("resources//397912.jpg");
		BufferedImage originalImage = ImageIO.read(imgPath); 
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		User c = new User("Abhijit Roy","Mumbai","8879073503",originalImage,"resources//logo.jpg","resources//stamp1.jpeg");
		List <MonthExpense>listMain = PDFDataProvider.getData();
		Document d = new Document();
		String outputFileName="C:/Users/Abhijit/Desktop/ITextTest.pdf";
		PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(outputFileName));
		PDFGenerator pdfGenerator = new PDFGenerator(c,listMain);
		d.open();
		pdfGenerator.generatorPDF(d,writer);
		d.close();
	}

}

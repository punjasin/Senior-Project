package tabs.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import tabs.entity.BidData;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


@Component
public class PDFView extends AbstractITextPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		Collection<BidData> winList = (Collection<BidData>) model.get("winList");
		
		doc.add(new Paragraph("Recommended books for Spring framework"));
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setPadding(5);
         
        // write table header
        cell.setPhrase(new Phrase("No.", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Student ID", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Sign", font));
        table.addCell(cell);

         
        // write table row data
        for (BidData win : winList) {
        	int i = 1;
        	table.addCell(String.valueOf(i));
            table.addCell(String.valueOf(win.getStudent_id()));
            table.addCell(win.getFirstName()+" "+win.getLastName());
            table.addCell("                          ");
            i++;
        }         
        doc.add(table);		
	}



}

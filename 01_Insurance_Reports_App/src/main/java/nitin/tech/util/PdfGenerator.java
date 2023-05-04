package nitin.tech.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import nitin.tech.entity.CitizenPlan;
import nitin.tech.repo.CitizenplanRepository;

@Component
public class PdfGenerator {
	
	private CitizenplanRepository planRepo;
	
	public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception
	{
		Document document = new Document(PageSize.A4);
//		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		
		//creating font
		//Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(25);
		
		//creating paragraph
		Paragraph paragraph = new Paragraph("Citizen Plan", font);
		
		//Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
	    //Adding the created paragraph in document
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(7);
		table.setSpacingBefore(6);
		
		table.addCell("Id");
		table.addCell("Citizen Name");
		table.addCell("Gender");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Plan Start Date");
		table.addCell("Plan End Date");
	
		

		
		for(CitizenPlan plan:plans)
		{
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate()+"");
			table.addCell(plan.getPlanEndDate()+"");
			
		}
		document.add(table);
		document.close();
	
	}

}

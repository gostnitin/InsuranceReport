package nitin.tech.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nitin.tech.entity.CitizenPlan;
import nitin.tech.repo.CitizenplanRepository;

@Component
public class ExcelGenerator {
	
	@Autowired
	private CitizenplanRepository planRepo;
	
	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception
	{
//	Workbook workbook = new XSSFWorkbook()
	Workbook workbook = new HSSFWorkbook();
	Sheet sheet = workbook.createSheet("plans-data");
	Row headRow = sheet.createRow(0);
	
	headRow.createCell(0).setCellValue("Id");
	headRow.createCell(1).setCellValue("Citizen Name");
	headRow.createCell(2).setCellValue("Gender");
	headRow.createCell(3).setCellValue("Plan Name");
	headRow.createCell(4).setCellValue("Plan Status");
	headRow.createCell(5).setCellValue("Plan Start Date");
	headRow.createCell(6).setCellValue("Plan End Date");
	headRow.createCell(7).setCellValue("Benefit Amount");
	
	
	int dataRowIndex = 1;
	
	for(CitizenPlan plan : records)
	{
		Row dataRow = sheet.createRow(dataRowIndex);
		dataRow.createCell(0).setCellValue(plan.getCitizenId());
		dataRow.createCell(1).setCellValue(plan.getCitizenName());
		dataRow.createCell(2).setCellValue(plan.getGender());
		dataRow.createCell(3).setCellValue(plan.getPlanName());
		dataRow.createCell(4).setCellValue(plan.getPlanStatus());
		
		if(null !=plan.getPlanStartDate()) {
		dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");
		}else {
			dataRow.createCell(5).setCellValue("N/A");
		}
		
		if(null != plan.getPlanEndDate()) {
		dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+"");
		}else {
			dataRow.createCell(6).setCellValue("N/A");
		}
		
		if(null != plan.getBenefitAmount()) {
		dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
		}else {
			dataRow.createCell(7).setCellValue("N/A");
		}
	  
		dataRowIndex++;
	}

	FileOutputStream fos = new FileOutputStream(file);
	workbook.write(fos);
	
	ServletOutputStream outputStream = response.getOutputStream();
	workbook.write(outputStream);
	workbook.close();
	
}


}

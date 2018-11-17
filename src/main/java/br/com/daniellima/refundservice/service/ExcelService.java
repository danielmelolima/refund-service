package br.com.daniellima.refundservice.service;

import java.io.File;
import java.io.FileInputStream;
import java.time.format.DateTimeFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import br.com.daniellima.refundservice.exception.SpreadsheetException;
import br.com.daniellima.refundservice.model.Expense;
import br.com.daniellima.refundservice.model.Spreadsheet;

@Service
public class ExcelService {
	private static final Integer FIRST_LINE_EXPENSES = 19;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelService.class);

	public File generateFile(Spreadsheet spreadsheet) {
		LOGGER.info("Gerando excel");
		try{
			File tmp = File.createTempFile("relatorio-nacional-reembolso", ".xls");
			FileInputStream fsIP= new FileInputStream(new File("modelo-relatorio-nacional-reembolso.xls"));  
			HSSFWorkbook workbook = new HSSFWorkbook(fsIP);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			fillHeader(sheet, spreadsheet);
			
			Integer count = 0;
			for(Expense expense : spreadsheet.getExpenses()){
				insertRow(sheet, expense, count++);			
			}
			//deleteEmptyRows(sheet, count);
			workbook.write(tmp);
			workbook.close();
			return tmp;			
		} catch(Exception e){
			e.printStackTrace();
			throw new SpreadsheetException();
		}
	}

	private void insertRow(HSSFSheet sheet, Expense expense, Integer rowNumber) {
		HSSFRow row = sheet.getRow(FIRST_LINE_EXPENSES + rowNumber);
//		row.getCell(0).setCellValue(rowNumber + 1);
		row.getCell(1).setCellValue(expense.getDate().format(formatter));
		row.getCell(2).setCellValue(expense.getType());
		row.getCell(3).setCellValue(expense.getPrice().doubleValue());
		row.getCell(4).setCellValue(expense.getJustification());
	}
	
	private void fillHeader(HSSFSheet sheet, Spreadsheet spreadsheet){
		HSSFRow row = sheet.getRow(6);
		row.getCell(2).setCellValue(spreadsheet.getProject());
		row = sheet.getRow(7);
		row.getCell(2).setCellValue(spreadsheet.getClient());
		row = sheet.getRow(8);
		row.getCell(2).setCellValue(spreadsheet.getConsultant());
	}
}
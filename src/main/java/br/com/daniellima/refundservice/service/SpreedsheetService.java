package br.com.daniellima.refundservice.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniellima.refundservice.model.Spreadsheet;

@Service
public class SpreedsheetService {

	@Autowired
	private ExcelService excelService;
	@Autowired
	private MailService mailService;
	@Autowired
	private ZipService zipService;

	public void generateSpreeadsheetFile(Spreadsheet spreadsheet) {
		File spreadesheetExcelFile = excelService.generateFile(spreadsheet);
		File photosZipFile = zipService.createPhotosZip(spreadsheet);
		mailService.sendSpreadsheetEmail(spreadesheetExcelFile, photosZipFile, spreadsheet.getEmail());
	}
}
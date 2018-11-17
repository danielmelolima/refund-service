package br.com.daniellima.refundservice.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniellima.refundservice.exception.SpreadsheetException;
import br.com.daniellima.refundservice.model.Expense;
import br.com.daniellima.refundservice.model.Spreadsheet;

@Service
public class ZipService {

	@Autowired
	private PhotoService photoService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ZipService.class);
	
	private void writeToZipFile(File aFile, ZipOutputStream zipStream, String filename) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(aFile);
		ZipEntry zipEntry = new ZipEntry(filename);
		zipStream.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipStream.write(bytes, 0, length);
		}
		zipStream.closeEntry();
		fis.close();
	}

	public File createPhotosZip(Spreadsheet spreadsheet) {
		LOGGER.info("Gerando zip das fotos");
		try{
			File zip = File.createTempFile("fotos", ".zip");
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
			Integer count = 1;
			for (Expense expense : spreadsheet.getExpenses()) {
				if (expense.getCouponPhoto() != null) {
					File photo = photoService.convertToImage(expense.getCouponPhoto());
					writeToZipFile(photo,out, count++ + ".jpg");
				}
			}
			out.closeEntry();
			out.close();
			return zip;
		}catch(Exception e){
			e.printStackTrace();
			throw new SpreadsheetException();
		}
	}
}

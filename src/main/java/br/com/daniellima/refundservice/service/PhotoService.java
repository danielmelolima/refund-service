package br.com.daniellima.refundservice.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	public File convertToImage(String blob) throws IOException {
		File photo = File.createTempFile("photo_temp", ".jpg");
		FileOutputStream fos = new FileOutputStream(photo);
		fos.write(Base64.getDecoder().decode(blob));
		fos.close();
		return photo;
	}
}
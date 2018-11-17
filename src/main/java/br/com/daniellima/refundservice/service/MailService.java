package br.com.daniellima.refundservice.service;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	private final static String FROM = "reembolso@gmail.com";
	private final static String TITLE = "Relatório de reembolso";
	private final static String TEXT = "Segue em anexo o relatório de despesas";
	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

	private Boolean sendMail(String title, String to, String text, File spreadsheetFile, File photosFile) {
		LOGGER.info("Send email to " + to);
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			helper.setTo(to);
			helper.setFrom(FROM);
			helper.setSubject(title);
			addAttachment(spreadsheetFile, photosFile, mimeMessage);
			javaMailSender.send(mimeMessage);
			return true;
		} catch (MailException | MessagingException e) {
			e.printStackTrace();
			LOGGER.info("Error send email to " + to);
			return false;
		}
	}
	
	private void addAttachment(File spreadsheetFile, File photosFile, MimeMessage mimeMessage) throws MessagingException{
		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(spreadsheetFile);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName("relatório-de-despesas.xls");
		multipart.addBodyPart(messageBodyPart);
		
		messageBodyPart = new MimeBodyPart();
		source = new FileDataSource(photosFile);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName("fotos.zip");
		multipart.addBodyPart(messageBodyPart);
		mimeMessage.setContent(multipart);
	}

	public boolean sendSpreadsheetEmail(File spreadsheetFile, File photosFile, String email) {
		return sendMail(TITLE, email, TEXT, spreadsheetFile, photosFile);
	}
}

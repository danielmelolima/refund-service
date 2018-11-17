package br.com.daniellima.refundservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.daniellima.refundservice.exception.MailException;
import br.com.daniellima.refundservice.exception.SpreadsheetException;
import br.com.daniellima.refundservice.model.Response;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value= MailException.class)
	public ResponseEntity<?> mailException(){
		return new ResponseEntity<>(new Response("Erro no envio do email"), HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(value= SpreadsheetException.class)
	public ResponseEntity<?> spreadsheetFoundException(){
		return new ResponseEntity<>(new Response("Erro ao gerar a planilha"), HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}


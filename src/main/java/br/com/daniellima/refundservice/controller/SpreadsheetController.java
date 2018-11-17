package br.com.daniellima.refundservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.daniellima.refundservice.model.Response;
import br.com.daniellima.refundservice.model.Spreadsheet;
import br.com.daniellima.refundservice.service.SpreedsheetService;

@Controller
public class SpreadsheetController {
	
	@Autowired
	private SpreedsheetService spreadsheetService;
	
	@PostMapping("v1/spreadsheet/send")
	public ResponseEntity<?> teste(@RequestBody Spreadsheet spreadsheet){
		spreadsheetService.generateSpreeadsheetFile(spreadsheet);
		return new ResponseEntity<>(new Response("Sucesso ao enviar a planilha e as fotos"), HttpStatus.OK);
	}
}

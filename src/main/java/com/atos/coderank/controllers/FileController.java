package com.atos.coderank.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private/files")
public class FileController {

	@GetMapping("/generate")
	public ResponseEntity<String> generateURL(){
		//llega q?type=metrics&template=0&historic=0
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	public FileSystemResource downloadFile() {
		
		return null;
	}
	
	public void generateFile() {
		//TODO create a file to a given path
	}
}

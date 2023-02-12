package com.agrotis.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.project.dto.LaboratorioDTO;
import com.agrotis.project.dto.LaboratorioDTOPost;
import com.agrotis.project.service.LaboratorioService;

@RestController
@RequestMapping("/agrotis/laboratorio")
public class LaboratorioController {
	
	@Autowired
	LaboratorioService laboratorioService;
	
	@GetMapping("/all")
    public ResponseEntity<List<LaboratorioDTO>> getAll() {
		List<LaboratorioDTO> listDto = laboratorioService.findAll();
    	return new ResponseEntity<List<LaboratorioDTO>>(listDto, HttpStatus.OK);
    }
	
    @PostMapping("/save")
    public ResponseEntity<LaboratorioDTO> create(@RequestBody LaboratorioDTOPost form) {
    	LaboratorioDTO dto = laboratorioService.create(form);
    	return new ResponseEntity<LaboratorioDTO>(dto, HttpStatus.CREATED);
    }
}

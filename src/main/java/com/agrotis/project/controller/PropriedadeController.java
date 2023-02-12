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

import com.agrotis.project.dto.PropriedadeDTO;
import com.agrotis.project.dto.PropriedadeDTOPost;
import com.agrotis.project.service.PropriedadeService;

@RestController
@RequestMapping("/agrotis/propriedade")
public class PropriedadeController {
	
	@Autowired
	PropriedadeService propriedadeService;
	
	@GetMapping("/all")
    public ResponseEntity<List<PropriedadeDTO>> getAll() {
		List<PropriedadeDTO> listDto = propriedadeService.findAll();
    	return new ResponseEntity<List<PropriedadeDTO>>(listDto, HttpStatus.OK);
    }
	
    @PostMapping("/save")
    public ResponseEntity<PropriedadeDTO> create(@RequestBody PropriedadeDTOPost form) {
    	PropriedadeDTO dto = propriedadeService.create(form);
    	return new ResponseEntity<PropriedadeDTO>(dto, HttpStatus.CREATED);
    }
}

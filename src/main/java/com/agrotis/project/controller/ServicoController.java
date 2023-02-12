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

import com.agrotis.project.dto.ServicoDTO;
import com.agrotis.project.dto.ServicoDTOPost;
import com.agrotis.project.service.ServicoService;

@RestController
@RequestMapping("/agrotis/servico")
public class ServicoController {
	
	@Autowired
	ServicoService servicoService;
	
	@GetMapping("/all")
    public ResponseEntity<List<ServicoDTO>> getAll() {
		List<ServicoDTO> listDto = servicoService.findAll();
    	return new ResponseEntity<List<ServicoDTO>>(listDto, HttpStatus.OK);
    }
	
    @PostMapping("/save")
    public ResponseEntity<ServicoDTO> create(@RequestBody ServicoDTOPost form) {
    	ServicoDTO dto = servicoService.create(form);
    	return new ResponseEntity<ServicoDTO>(dto, HttpStatus.CREATED);
    }
}

package com.agrotis.project.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.agrotis.project.config.exception.AGROTISException;
import com.agrotis.project.dto.LaboratorioDTO;
import com.agrotis.project.dto.LaboratorioDTOPost;
import com.agrotis.project.service.LaboratorioService;

@RestController
@RequestMapping("/agrotis/laboratorio")
public class LaboratorioController {
	
	@Autowired
	LaboratorioService laboratorioService;
	
	@GetMapping("/{id}")
    public ResponseEntity<LaboratorioDTO> getBy(@PathVariable("id") BigInteger id) {
		LaboratorioDTO dto = laboratorioService.findByDto(id);
    	return new ResponseEntity<LaboratorioDTO>(dto, HttpStatus.OK);
    }
	
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
    
    @PutMapping("/up/{id}")
    public ResponseEntity<LaboratorioDTO> update(@PathVariable("id") BigInteger id, @RequestBody LaboratorioDTOPost form) {
    	LaboratorioDTO dto = laboratorioService.update(id, form);
    	return new ResponseEntity<LaboratorioDTO>(dto, HttpStatus.CREATED);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> delete(@PathVariable BigInteger id)  {
    	try {
    		String retorno = laboratorioService.delete(id);
    		if(!retorno.equals("SUCESSO")) throw new AGROTISException("Erro interno, contate o Administrador");
    		return ResponseEntity.noContent().build();
    	} catch (ResponseStatusException e) {
 		   throw new ResponseStatusException(e.getStatus(), e.getReason());
 	   }
    }
}

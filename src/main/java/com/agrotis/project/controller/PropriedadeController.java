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
import com.agrotis.project.dto.PropriedadeDTO;
import com.agrotis.project.dto.PropriedadeDTOPost;
import com.agrotis.project.service.PropriedadeService;

@RestController
@RequestMapping("/agrotis/propriedade")
public class PropriedadeController {
	
	@Autowired
	PropriedadeService propriedadeService;
	
	@GetMapping("/{id}")
    public ResponseEntity<PropriedadeDTO> getBy(@PathVariable("id") BigInteger id) {
		PropriedadeDTO dto = propriedadeService.findByDto(id);
    	return new ResponseEntity<PropriedadeDTO>(dto, HttpStatus.OK);
    }
	
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
    
    @PutMapping("/up/{id}")
    public ResponseEntity<PropriedadeDTO> update(@PathVariable("id") BigInteger id, @RequestBody PropriedadeDTOPost form) {
    	PropriedadeDTO dto = propriedadeService.update(id, form);
    	return new ResponseEntity<PropriedadeDTO>(dto, HttpStatus.CREATED);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> delete(@PathVariable BigInteger id)  {
    	try {
    		String retorno = propriedadeService.delete(id);
    		if(!retorno.equals("SUCESSO")) throw new AGROTISException("Erro interno, contate o Administrador");
    		return ResponseEntity.noContent().build();
    	} catch (ResponseStatusException e) {
 		   throw new ResponseStatusException(e.getStatus(), e.getReason());
 	   }	
    }
}

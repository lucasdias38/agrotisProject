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
import com.agrotis.project.dto.ServicoDTO;
import com.agrotis.project.dto.ServicoDTOPost;
import com.agrotis.project.service.ServicoService;

@RestController
@RequestMapping("/agrotis/servico")
public class ServicoController {
	
	@Autowired
	ServicoService servicoService;
	
	@GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> getBy(@PathVariable("id") BigInteger id) {
		ServicoDTO dto = servicoService.findByDto(id);
    	return new ResponseEntity<ServicoDTO>(dto, HttpStatus.OK);
    }
	
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
    
    @PutMapping("/up/{id}")
    public ResponseEntity<ServicoDTO> update(@PathVariable("id") BigInteger id, @RequestBody ServicoDTOPost form) {
    	ServicoDTO dto = servicoService.update(id, form);
    	return new ResponseEntity<ServicoDTO>(dto, HttpStatus.CREATED);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> delete(@PathVariable BigInteger id)  {
    	try {
    		String retorno = servicoService.delete(id);
    		if(!retorno.equals("SUCESSO")) throw new AGROTISException("Erro interno, contate o Administrador");
    		return ResponseEntity.noContent().build();
    	} catch (ResponseStatusException e) {
 		   throw new ResponseStatusException(e.getStatus(), e.getReason());
 	   }
    }
    
}

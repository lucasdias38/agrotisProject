package com.agrotis.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.project.config.exception.AGROTISException;
import com.agrotis.project.dto.LaboratorioDTO;
import com.agrotis.project.dto.LaboratorioDTOPost;
import com.agrotis.project.model.LaboratorioModel;
import com.agrotis.project.repository.LaboratorioRepository;

@Service
public class LaboratorioService {
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	public List<LaboratorioDTO> findAll(){
		return LaboratorioDTO.ofList(laboratorioRepository.findAll());
	}
	
	public LaboratorioDTO create(LaboratorioDTOPost dtoPost){
		if(laboratorioRepository.existsNome(dtoPost.getNome())) throw new AGROTISException("Nome do laboratório informado existe na base!");
		
		LaboratorioModel model = LaboratorioModel.ofPost(dtoPost);
		laboratorioRepository.save(model);
		return LaboratorioDTO.of(model);
	}

	
}

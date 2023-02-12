package com.agrotis.project.service;

import java.math.BigInteger;
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
	
	@Autowired
	private ServicoService servicoService;
	
	public LaboratorioDTO findByDto(BigInteger id){
		return LaboratorioDTO.of(findByModel(id));
	}
	
	private LaboratorioModel findByModel(BigInteger id){
		LaboratorioModel model = laboratorioRepository.findById(id).orElseThrow(() -> new AGROTISException("Laboratorio não foi encontrado!"));
		return model;
	}
	
	public List<LaboratorioDTO> findAll(){
		return LaboratorioDTO.ofList(laboratorioRepository.findAll());
	}
	
	public LaboratorioDTO create(LaboratorioDTOPost dtoPost){
		if(laboratorioRepository.existsNome(dtoPost.getNome(), null)) throw new AGROTISException("Nome do laboratório informado existe na base!");
		
		LaboratorioModel model = LaboratorioModel.ofPost(dtoPost);
		laboratorioRepository.save(model);
		return LaboratorioDTO.of(model);
	}
	
	public LaboratorioDTO update(BigInteger id,  LaboratorioDTOPost dtoPost){
		if(laboratorioRepository.existsNome(dtoPost.getNome(), id)) throw new AGROTISException("Nome do laboratório informado existe na base!");
		
		LaboratorioModel model = findByModel(id); 
		model.setNome(dtoPost.getNome());
		
		laboratorioRepository.save(model);
		return LaboratorioDTO.of(model);
	}
	
	public String delete(BigInteger id) {
		findByModel(id);
		if(servicoService.validVinculoLaboratorio(id)) throw new AGROTISException("Ação não realizada, pois existe vínculo com Serviço!");
		
		laboratorioRepository.deleteById(id);
		return "SUCESSO";
	}
	
}

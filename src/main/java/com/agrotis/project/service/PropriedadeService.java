package com.agrotis.project.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.project.config.exception.AGROTISException;
import com.agrotis.project.dto.PropriedadeDTO;
import com.agrotis.project.dto.PropriedadeDTOPost;
import com.agrotis.project.model.PropriedadeModel;
import com.agrotis.project.repository.PropriedadeRepository;

@Service
public class PropriedadeService {
	
	@Autowired
	private PropriedadeRepository propriedadeRepository;
	
	public PropriedadeModel findByModel(BigInteger id){
		PropriedadeModel model = propriedadeRepository.findById(id).orElseThrow(() -> new AGROTISException("Propriedade não foi encontrada!")) ;
		return model;
	}
	
	public List<PropriedadeDTO> findAll(){
		return PropriedadeDTO.ofList(propriedadeRepository.findAll());
	}
	
	public PropriedadeDTO create(PropriedadeDTOPost dtoPost){
		if(!validaCaractere(dtoPost.getCnpj())) throw new AGROTISException("CNPJ não está no formato correto! Segue modelo: 'XX.XXX.XXX/XXXX-XX'");
		if(propriedadeRepository.existsCnpj(dtoPost.getCnpj())) throw new AGROTISException("CNPJ informado existe na base!");
		
		PropriedadeModel model = PropriedadeModel.ofPost(dtoPost);
		propriedadeRepository.save(model);
		return PropriedadeDTO.of(model);
	}

	private Boolean validaCaractere (String cnpj) {
		if(			cnpj.length() 	!= 18
				||	cnpj.charAt(2) 	!= '.' 
				|| 	cnpj.charAt(6)  != '.'
				||	cnpj.charAt(10) != '/' 
				|| 	cnpj.charAt(15) != '-'		) {
			return false;
		}
		return true;
	}
	
}

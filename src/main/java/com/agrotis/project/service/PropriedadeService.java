package com.agrotis.project.service;

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
	
	public List<PropriedadeDTO> findAll(){
		return PropriedadeDTO.ofList(propriedadeRepository.findAll());
	}
	
	public PropriedadeDTO create(PropriedadeDTOPost dtoPost){
		if(!validaCaractere(dtoPost.getCnpj())) throw new AGROTISException("CNPJ não está no formato correto! Segue modelo: 'XX.XXX.XXX/XXXX-XX'");
		if(propriedadeRepository.existsCnpj(dtoPost.getNome())) throw new AGROTISException("CNPJ informado existe na base!");
		
		PropriedadeModel model = PropriedadeModel.ofPost(dtoPost);
		propriedadeRepository.save(model);
		return PropriedadeDTO.of(model);
	}



	private Boolean validaCaractere (String cnpj) {
		if(cnpj.length() != 18)
			return false;
		else if(!cnpj.substring(2).equals(".") || !cnpj.substring(6).equals("."))
			return false;
		else if(!cnpj.substring(10).equals("/") || !cnpj.substring(15).equals("-"))
			return false;
		return true;
	}
	
}

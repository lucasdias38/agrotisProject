package com.agrotis.project.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.springframework.util.ObjectUtils.isEmpty;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.project.config.exception.AGROTISException;
import com.agrotis.project.dto.ServicoDTO;
import com.agrotis.project.dto.ServicoDTOPost;
import com.agrotis.project.model.LaboratorioModel;
import com.agrotis.project.model.PropriedadeModel;
import com.agrotis.project.model.ServicoModel;
import com.agrotis.project.repository.LaboratorioRepository;
import com.agrotis.project.repository.PropriedadeRepository;
import com.agrotis.project.repository.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private PropriedadeRepository propriedadeRepository;  
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	public ServicoDTO findByDto(BigInteger id){
		return ServicoDTO.of(findByModel(id));
	}
	
	public ServicoModel findByModel(BigInteger id){
		ServicoModel model = servicoRepository.findById(id).orElseThrow(() -> new AGROTISException("Servico não foi encontrado!"));
		return model;
	}
	
	public List<ServicoDTO> findAll(){
		return ServicoDTO.ofList(servicoRepository.findAll());
	}
	
	public ServicoDTO create(ServicoDTOPost dtoPost){
		Date dataHoje = new Date();
		
		if(validaCampos(dtoPost)) throw new AGROTISException("Preencha os campos obrigatórios!");
		if(validaData(dtoPost.getDataInicial(), dataHoje)) throw new AGROTISException("Data Inicial tem que ser maior do que hoje!");
		if(validaData(dtoPost.getDataFinal(), dtoPost.getDataInicial())) throw new AGROTISException("Data Final tem que ser maior do que Data Inicial!");
		
		ServicoModel model = setaDados(dtoPost);
		model.setDtaCriacao(dataHoje);
		servicoRepository.save(model);
		return ServicoDTO.of(model);
	}
	
	public ServicoDTO update(BigInteger id, ServicoDTOPost dtoPost){
		Date dataHoje = new Date();
		findByModel(id);
		
		if(validaCampos(dtoPost)) throw new AGROTISException("Preencha os campos obrigatórios!");
		if(validaData(dtoPost.getDataInicial(), dataHoje)) throw new AGROTISException("Data Inicial tem que ser maior do que hoje!");
		if(validaData(dtoPost.getDataFinal(), dtoPost.getDataInicial())) throw new AGROTISException("Data Final tem que ser maior do que Data Inicial!");
		
		ServicoModel model = setaDados(dtoPost);
		model.setId(id);
		servicoRepository.save(model);
		return ServicoDTO.of(model);
	}
	
	public String delete(BigInteger id) {
		findByModel(id);
		servicoRepository.deleteById(id);
		return "SUCESSO";
	}
	
	

	private Boolean validaCampos (ServicoDTOPost dtoPost) {
		if(	   	   isEmpty(dtoPost.getNome())
				|| isEmpty(dtoPost.getDataInicial()) 
				|| isEmpty(dtoPost.getDataFinal())
				|| isEmpty(dtoPost.getInfosPropriedade())
				|| isEmpty(dtoPost.getLaboratorio())			) {
			return true;
		}
		return false;
	}
	
	private Boolean validaData (Date data1, Date data2) {
		SimpleDateFormat capturaData = new SimpleDateFormat("yyyyMMdd");

		Integer dt1 = Integer.parseInt(capturaData.format(data1));
		Integer dt2 = Integer.parseInt(capturaData.format(data2));
		
		if(dt1 < dt2)
			return true;
		
		return false;
	}
	
	private ServicoModel setaDados (ServicoDTOPost dtoPost) {
		ServicoModel model = new ServicoModel();
		PropriedadeModel propriedadeModel = propriedadeRepository.findById(dtoPost.getInfosPropriedade().getId())
				.orElseThrow(() -> new AGROTISException("Propriedade não foi encontrada!")) ;
		
		LaboratorioModel laboratorio = laboratorioRepository.findById(dtoPost.getLaboratorio().getId())
				.orElseThrow(() -> new AGROTISException("Laboratorio não foi encontrado!"));

		if(!dtoPost.getCnpj().equals(propriedadeModel.getCnpj())) throw new AGROTISException("Inconsistência ao validar o CNPJ na base!");
		BeanUtils.copyProperties(dtoPost, model);
		model.setPropriedade(propriedadeModel);
		model.setLaboratorio(laboratorio);
		return model;
	}
	
//	##################################################
//					Valida Vinculos	
//	##################################################
	
	public Boolean validVinculoLaboratorio (BigInteger idLab) {
		return servicoRepository.existsLaboratorio(idLab);
	}
	
	public Boolean validVinculoPropriedade (BigInteger idProp) {
		return servicoRepository.existsPropriedade(idProp);
	}
	
	
}

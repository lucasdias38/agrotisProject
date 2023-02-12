package com.agrotis.project.dto;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.agrotis.project.model.LaboratorioModel;
import com.agrotis.project.model.ServicoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {

	private BigInteger id;
	private String nome;
   	private Date dataInicial;
   	private Date dataFinal;
    private InfosPropriedadeDTO infosPropriedade;
	private String cnpj;
    private LaboratorioModel laboratorio;
	private String observacoes;
   	private Date dtaCriacao;
   	

	public static ServicoDTO of (ServicoModel entity) {
        var dto = new ServicoDTO();
        dto.setInfosPropriedade(new InfosPropriedadeDTO());
        BeanUtils.copyProperties(entity, dto);
        BeanUtils.copyProperties(entity.getPropriedade(), dto.getInfosPropriedade());
        dto.setCnpj(entity.getPropriedade().getCnpj());
        return dto;
    }
    
	public static List<ServicoDTO> ofList (List<ServicoModel> entityList) {
		List<ServicoDTO> dtoList = new ArrayList<>();
		var dto = new ServicoDTO();
		
		for (ServicoModel entity : entityList) {
	        dto.setInfosPropriedade(new InfosPropriedadeDTO());
   	   		BeanUtils.copyProperties(entity, dto);
   	        BeanUtils.copyProperties(entity.getPropriedade(), dto.getInfosPropriedade());
   	        dto.setCnpj(entity.getPropriedade().getCnpj());
   	        
   	   		dtoList.add(dto);
   	   		dto = new ServicoDTO();
		}
		return dtoList;
    }
	
}

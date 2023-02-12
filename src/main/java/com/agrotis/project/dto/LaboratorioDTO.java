package com.agrotis.project.dto;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.agrotis.project.model.LaboratorioModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioDTO {

	private BigInteger id;
	private String nome;

	public static LaboratorioDTO of (LaboratorioModel entity) {
        var dto = new LaboratorioDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    
	public static List<LaboratorioDTO> ofList (List<LaboratorioModel> entityList) {
		List<LaboratorioDTO> dtoList = new ArrayList<>();
		var dto = new LaboratorioDTO();
		
		for (LaboratorioModel entity : entityList) {
   	   		BeanUtils.copyProperties(entity, dto);
   	   		dtoList.add(dto);
   	   		dto = new LaboratorioDTO();
		}
		return dtoList;
    }
	
}

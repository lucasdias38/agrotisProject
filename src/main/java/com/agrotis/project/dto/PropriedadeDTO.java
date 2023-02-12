package com.agrotis.project.dto;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.agrotis.project.model.PropriedadeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropriedadeDTO {

	private BigInteger id;
	private String nome;
	private String email;

	public static PropriedadeDTO of (com.agrotis.project.model.PropriedadeModel entity) {
        var dto = new PropriedadeDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    
	public static List<PropriedadeDTO> ofList (List<PropriedadeModel> entityList) {
		List<PropriedadeDTO> dtoList = new ArrayList<>();
		var dto = new PropriedadeDTO();
		
		for (PropriedadeModel entity : entityList) {
   	   		BeanUtils.copyProperties(entity, dto);
   	   		dtoList.add(dto);
   	   		dto = new PropriedadeDTO();
		}
		return dtoList;
    }
	
}

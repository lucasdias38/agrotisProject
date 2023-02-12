package com.agrotis.project.model;


import java.math.BigInteger;

import org.springframework.beans.BeanUtils;

import com.agrotis.project.dto.PropriedadeDTOPost;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PROPRIEDADE")
public class PropriedadeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private BigInteger id;
	
	@Column(name = "NOME") 
	private String nome;

	@Column(name = "CNPJ") 
	private String cnpj;
	
	
	public static PropriedadeModel ofPost (PropriedadeDTOPost dto) {
        var model = new PropriedadeModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }
	
}

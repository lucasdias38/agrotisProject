package com.agrotis.project.model;


import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.agrotis.project.dto.LaboratorioDTOPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "LABORATORIO")
public class LaboratorioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private BigInteger id;
	
	@Column(name = "NOME") 
	private String nome;
	
	
	public static LaboratorioModel ofPost (LaboratorioDTOPost dto) {
        var model = new LaboratorioModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}

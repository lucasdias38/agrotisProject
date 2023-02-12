package com.agrotis.project.dto;

import lombok.Data;

@Data
public class PropriedadeDTOPost {

	private String nome;
	private String cnpj;

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj.trim();
	}
	
}

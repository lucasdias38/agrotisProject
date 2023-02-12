package com.agrotis.project.dto;

import java.util.Date;

import com.agrotis.project.model.LaboratorioModel;

import lombok.Data;

@Data
public class ServicoDTOPost {

	private String nome;
   	private Date dataInicial;
   	private Date dataFinal;
    private InfosPropriedadeDTO infosPropriedade;
	private String cnpj;
    private LaboratorioModel laboratorio;
	private String observacoes;
	
}

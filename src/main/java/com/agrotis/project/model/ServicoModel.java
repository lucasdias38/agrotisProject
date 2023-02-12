package com.agrotis.project.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SERVICO")
public class ServicoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private BigInteger id;
	
	@Column(name = "NOME") 
	private String nome;
	
    @Column(name = "DTA_INICIAL")
   	private Date dataInicial;

    @Column(name = "DTA_FINAL")
   	private Date dataFinal;
    
    @JoinColumn(name = "ID_PROPRIEDADE", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
    private PropriedadeModel propriedade;

	@Column(name = "CNPJ") 
	private String cnpj;
	
	@JoinColumn(name = "ID_LABORATORIO", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
    private LaboratorioModel laboratorio;
	
	@Column(name = "OBSERVACOES") 
	private String observacoes;
	
    @Column(name = "DTA_CRIACAO")
   	private Date dtaCriacao;
    
}

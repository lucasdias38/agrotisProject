package com.agrotis.project.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.project.model.ServicoModel;

public interface ServicoRepository extends JpaRepository<ServicoModel, BigInteger>{

}

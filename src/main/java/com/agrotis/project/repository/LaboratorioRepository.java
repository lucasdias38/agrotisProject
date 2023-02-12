package com.agrotis.project.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.project.model.LaboratorioModel;

public interface LaboratorioRepository extends JpaRepository<LaboratorioModel, BigInteger>{

}

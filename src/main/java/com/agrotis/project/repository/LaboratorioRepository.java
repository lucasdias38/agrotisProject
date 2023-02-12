package com.agrotis.project.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agrotis.project.model.LaboratorioModel;

public interface LaboratorioRepository extends JpaRepository<LaboratorioModel, BigInteger>{

	@Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END "
			+ "FROM LaboratorioModel l  WHERE 	l.nome = :nome ")
	boolean existsNome(@Param("nome") String nome);
	
}

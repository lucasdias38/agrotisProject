package com.agrotis.project.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agrotis.project.model.ServicoModel;

public interface ServicoRepository extends JpaRepository<ServicoModel, BigInteger>{
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM ServicoModel s "
			+ " WHERE s.laboratorio.id = :idLab")
	boolean existsLaboratorio(@Param("idLab") BigInteger idLab); 
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM ServicoModel s "
			+ " WHERE s.propriedade.id = :idProp")
	boolean existsPropriedade(@Param("idProp") BigInteger idProp); 
	
}

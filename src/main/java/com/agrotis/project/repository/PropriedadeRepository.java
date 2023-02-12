package com.agrotis.project.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agrotis.project.model.PropriedadeModel;

public interface PropriedadeRepository extends JpaRepository<PropriedadeModel, BigInteger>{

	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END "
			+ "FROM PropriedadeModel e  WHERE 	e.cnpj = :cnpj ")
	boolean existsCnpj(@Param("cnpj") String cnpj);
	
}

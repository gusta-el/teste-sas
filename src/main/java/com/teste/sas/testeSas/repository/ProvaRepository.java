
  package com.teste.sas.testeSas.repository;
  
  import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teste.sas.testeSas.entity.Prova;
import com.teste.sas.testeSas.entity.Simulado;
  
  public interface ProvaRepository extends CrudRepository<Prova, Long>{
  
	  List<Prova> findBySimulado(Simulado simulado);
	  
	  
  }
 
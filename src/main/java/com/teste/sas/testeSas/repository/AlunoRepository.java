package com.teste.sas.testeSas.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teste.sas.testeSas.entity.Aluno;
import com.teste.sas.testeSas.entity.Simulado;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
	
	List<Aluno> findBySimulado(Simulado simulado);

	Aluno findByIdAluno(Long idAluno);
	
}
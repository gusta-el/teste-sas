package com.teste.sas.testeSas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teste.sas.testeSas.entity.Aluno;
import com.teste.sas.testeSas.entity.ProvaAluno;

public interface ProvaAlunoRepository extends CrudRepository<ProvaAluno, Long> {

	List<ProvaAluno> findByAluno(Aluno aluno);
	
}

package com.teste.sas.testeSas.repository;
import org.springframework.data.repository.CrudRepository;

import com.teste.sas.testeSas.entity.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}
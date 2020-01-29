package com.teste.sas.testeSas.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idAluno;

    private String nomeAluno;
    private String sobrenomeAluno;
    private Double notaFinal;
      
    @OneToMany(mappedBy = "aluno")
    Set<ProvaAluno> provaAluno;
    
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getSobrenomeAluno() {
		return sobrenomeAluno;
	}
	public void setSobrenomeAluno(String sobrenomeAluno) {
		this.sobrenomeAluno = sobrenomeAluno;
	}
	public Double getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Double notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	public Set<ProvaAluno> getProvaAluno() {
		return provaAluno;
	}
	public void setProvaAluno(Set<ProvaAluno> provaAluno) {
		this.provaAluno = provaAluno;
	}

    
}
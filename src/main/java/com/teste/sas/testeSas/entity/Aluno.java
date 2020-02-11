package com.teste.sas.testeSas.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idAluno;

    private String nomeAluno;
    private String sobrenomeAluno;
    private Integer notaFinal;
      
    @OneToMany(mappedBy = "aluno")
    private Set<ProvaAluno> provaAluno;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_simulado")
    private Simulado simulado;
    
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
	public Set<ProvaAluno> getProvaAluno() {
		return provaAluno;
	}
	public void setProvaAluno(Set<ProvaAluno> provaAluno) {
		this.provaAluno = provaAluno;
	}
	public Simulado getSimulado() {
		return simulado;
	}
	public void setSimulado(Simulado simulado) {
		this.simulado = simulado;
	}
	public Integer getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Integer notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Aluno(String nomeAluno, Integer notaFinal) {
		super();
		this.nomeAluno = nomeAluno;
		this.notaFinal = notaFinal;
	}
	public Aluno(Long idAluno, String nomeAluno) {
		super();
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
	}

	
    
}
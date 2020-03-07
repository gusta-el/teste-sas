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

@Entity(name = "PROVA")
public class Prova {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProva;
	private String nomeProva;
	
    @OneToMany(mappedBy = "prova", fetch = FetchType.EAGER)
    private Set<Questao> questoes;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_simulado")
    private Simulado simulado;   
    @OneToMany(mappedBy = "prova")
    Set<ProvaAluno> provaAluno;

	public Long getIdProva() {
		return idProva;
	}

	public void setIdProva(Long idProva) {
		this.idProva = idProva;
	}

	public String getNomeProva() {
		return nomeProva;
	}

	public void setNomeProva(String nomeProva) {
		this.nomeProva = nomeProva;
	}

	public Set<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Set<Questao> questoes) {
		this.questoes = questoes;
	}

	public Simulado getSimulado() {
		return simulado;
	}

	public void setSimulado(Simulado simulado) {
		this.simulado = simulado;
	}

	public Set<ProvaAluno> getProvaAluno() {
		return provaAluno;
	}

	public void setProvaAluno(Set<ProvaAluno> provaAluno) {
		this.provaAluno = provaAluno;
	}

	public Prova(Long idProva, String nomeProva, Set<Questao> questoes) {
		super();
		this.idProva = idProva;
		this.nomeProva = nomeProva;
		this.questoes = questoes;
	}

	public Prova(Long idProva, String nomeProva) {
		super();
		this.idProva = idProva;
		this.nomeProva = nomeProva;
	}
	
	
    
}

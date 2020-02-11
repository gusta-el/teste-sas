package com.teste.sas.testeSas.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="SIMULADO")
public class Simulado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSimulado;
	private String nomeSimulado;
	private LocalDate dataSimulado;
	private Boolean simuladoEncerrado;
	    
    @OneToMany(mappedBy = "simulado", fetch = FetchType.EAGER)
    private Set<Prova> provas;

    public Long getIdSimulado() {
		return idSimulado;
	}

	public void setIdSimulado(Long idSimulado) {
		this.idSimulado = idSimulado;
	}

	public String getNomeSimulado() {
		return nomeSimulado;
	}

	public void setNomeSimulado(String nomeSimulado) {
		this.nomeSimulado = nomeSimulado;
	}

	public LocalDate getDataSimulado() {
		return dataSimulado;
	}

	public void setDataSimulado(LocalDate dataSimulado) {
		this.dataSimulado = dataSimulado;
	}

	public Set<Prova> getProvas() {
		return provas;
	}

	public void setProvas(Set<Prova> provas) {
		this.provas = provas;
	}

	public Boolean getSimuladoEncerrado() {
		return simuladoEncerrado;
	}

	public void setSimuladoEncerrado(Boolean simuladoEncerrado) {
		this.simuladoEncerrado = simuladoEncerrado;
	}

	public Simulado(String nomeSimulado, Set<Prova> provas) {
		super();
		this.nomeSimulado = nomeSimulado;
		this.provas = provas;
	}

	public Simulado(String nomeSimulado, Boolean simuladoEncerrado) {
		super();
		this.nomeSimulado = nomeSimulado;
		this.simuladoEncerrado = simuladoEncerrado;
	}
	
	
	

}

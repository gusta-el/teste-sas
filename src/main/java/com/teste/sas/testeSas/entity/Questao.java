package com.teste.sas.testeSas.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "QUESTAO")
public class Questao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idQuestao;
	private String enunciado;
	private String complexidade;
	private Character alternativaCorreta;
	private Integer numeroQuestao;
	   
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prova")
    private Prova prova;
		
	public Long getIdQuestao() {
		return idQuestao;
	}
	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public String getComplexidade() {
		return complexidade;
	}
	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}
	public Character getAlternativaCorreta() {
		return alternativaCorreta;
	}
	public void setAlternativaCorreta(Character alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}
	public Integer getNumeroQuestao() {
		return numeroQuestao;
	}
	public void setNumeroQuestao(Integer numeroQuestao) {
		this.numeroQuestao = numeroQuestao;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	
	public Questao(Long idQuestao, String enunciado, String complexidade, Character alternativaCorreta,
			Integer numeroQuestao) {
		super();
		this.idQuestao = idQuestao;
		this.enunciado = enunciado;
		this.complexidade = complexidade;
		this.alternativaCorreta = alternativaCorreta;
		this.numeroQuestao = numeroQuestao;
	}
}

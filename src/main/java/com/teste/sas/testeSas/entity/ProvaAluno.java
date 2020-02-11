package com.teste.sas.testeSas.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="PROVA_ALUNO")
public class ProvaAluno{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idAlunoProva;
		
    @ManyToOne
    @JoinColumn(name = "id_prova")
    Prova prova;
 
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    Aluno aluno;
    Integer nota;    
    String respostasAcertos;
    String gabaritoAluno;
    Boolean provaFinalizada;
    
	public Long getIdAlunoProva() {
		return idAlunoProva;
	}
	public void setIdAlunoProva(Long idAlunoProva) {
		this.idAlunoProva = idAlunoProva;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public String getRespostasAcertos() {
		return respostasAcertos;
	}
	public void setRespostasAcertos(String respostasAcertos) {
		this.respostasAcertos = respostasAcertos;
	}
	public String getGabaritoAluno() {
		return gabaritoAluno;
	}
	public void setGabaritoAluno(String gabaritoAluno) {
		this.gabaritoAluno = gabaritoAluno;
	}
	public Boolean getProvaFinalizada() {
		return provaFinalizada;
	}
	public void setProvaFinalizada(Boolean provaFinalizada) {
		this.provaFinalizada = provaFinalizada;
	}
	public ProvaAluno(Long idAlunoProva, Aluno aluno, Integer nota, Boolean provaFinalizada) {
		super();
		this.idAlunoProva = idAlunoProva;
		this.aluno = aluno;
		this.nota = nota;
		this.provaFinalizada = provaFinalizada;
	}    
	
	public ProvaAluno() {
		super();
	}    
	
}

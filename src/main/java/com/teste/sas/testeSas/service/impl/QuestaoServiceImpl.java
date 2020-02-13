package com.teste.sas.testeSas.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.sas.testeSas.dto.Complexidade;
import com.teste.sas.testeSas.dto.RespostaOutDTO;
import com.teste.sas.testeSas.dto.RespostaQuestao;
import com.teste.sas.testeSas.entity.ProvaAluno;
import com.teste.sas.testeSas.entity.Questao;
import com.teste.sas.testeSas.repository.ProvaAlunoRepository;
import com.teste.sas.testeSas.service.QuestaoService;
import com.teste.sas.testeSas.utils.UtilsTesteSas;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestaoServiceImpl implements QuestaoService {

	@Autowired
	ProvaAlunoRepository provaAlunoRepository;

	@Override
	public RespostaOutDTO execute(RespostaQuestao resposta) {

		Optional<ProvaAluno> provaAlunoOp = provaAlunoRepository.findById(resposta.getIdProva());

		UtilsTesteSas.assertBusinessException(provaAlunoOp.isPresent(), () -> UtilsTesteSas.PROVA_NAO_ENCONTRADA_MESSAGE);
		ProvaAluno provaAluno = provaAlunoOp.get();

		UtilsTesteSas.assertBusinessException(
				provaAluno.getGabaritoAluno()
						.charAt(resposta.getNumeroQuestao().intValue() - 1) == UtilsTesteSas.QUESTAO_NAO_RESPONDIDA,
				() -> "Questão de Nº " + resposta.getNumeroQuestao() + " já respondida!");

		UtilsTesteSas.assertBusinessException(!provaAluno.getProvaFinalizada(),
				() -> "Prova finalizada e calculada. Nota do aluno: " + provaAluno.getNota());

		Questao questao = this.responderQuestao(provaAluno, resposta);
		return this.transform(questao, resposta.getAlternativaSelecionada());

	}

	@Transactional
	private Questao responderQuestao(ProvaAluno prova, RespostaQuestao resposta) {

		Optional<Questao> questaoOp = prova.getProva().getQuestoes().stream()
				.filter(questao -> resposta.getNumeroQuestao().equals(questao.getNumeroQuestao())).findAny();

		UtilsTesteSas.assertBusinessException(questaoOp.isPresent(), () -> UtilsTesteSas.QUESTAO_NAO_ENCONTRADA_MESSAGE);
		Questao questao = questaoOp.get();

		Integer score = 0;
		if (prova.getGabaritoAluno().equals(UtilsTesteSas.QUESTOES_BLANK))
			score = 600;

		this.pontuarAcerto(prova, questao, score,
				questao.getAlternativaCorreta().equals(resposta.getAlternativaSelecionada()));
		provaAlunoRepository.save(this.prepararResposta(prova, resposta));

		if (prova.getGabaritoAluno().chars().mapToObj(alternativa -> (char) alternativa).allMatch(c -> c != ' '))
			prova.setProvaFinalizada(true);
		
		return questao;

	}

	private ProvaAluno prepararResposta(ProvaAluno prova, RespostaQuestao resposta) {

		StringBuilder gabaritoAlunoBuider = new StringBuilder(prova.getGabaritoAluno());
		gabaritoAlunoBuider.setCharAt(resposta.getNumeroQuestao().intValue() - 1, resposta.getAlternativaSelecionada());
		prova.setGabaritoAluno(gabaritoAlunoBuider.toString());

		return prova;
	}

	private void pontuarAcerto(ProvaAluno prova, Questao questao, Integer score, Boolean respostaCorreta) {

		if (respostaCorreta) {
			if (questao.getComplexidade().equals(Complexidade.FÁCIL.getNormalized()))
				score += Complexidade.FÁCIL.getValue();
			if (questao.getComplexidade().equals(Complexidade.MÉDIA.getNormalized()))
				score += Complexidade.MÉDIA.getValue();
			if (questao.getComplexidade().equals(Complexidade.DIFÍCIL.getNormalized()))
				score += Complexidade.DIFÍCIL.getValue();
		}

		prova.setNota(prova.getNota() + score);

	}

	private RespostaOutDTO transform(Questao questao, Character respostaSelecionada) {

		return RespostaOutDTO.builder().numeroQuestao(questao.getNumeroQuestao())
				.complexidade(Complexidade.valueOf(questao.getComplexidade()))
				.alternativaCorreta(questao.getAlternativaCorreta()).alternativaEscolhida(respostaSelecionada).build();

	}


}
